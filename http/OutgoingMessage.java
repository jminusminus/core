//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.http;

import github.com.jminusminus.core.util.Map;
import github.com.jminusminus.core.util.MapItem;
import github.com.jminusminus.core.util.LinkedListMap;
import github.com.jminusminus.core.util.ByteArray;

public class OutgoingMessage {

    // The HTTP version for this message.
    public final String httpVersion = "HTTP/1.1";

    // When using implicit headers (not calling response.writeHead() explicitly), this property controls 
    // the status code that will be sent to the client when the headers get flushed.
    //
    // Example:
    // ```
    // response.statusCode = 404;
    // ```
    // After response header was sent to the client, this property indicates the status code which was sent out.
    public int statusCode = 200;

    // When using implicit headers (not calling response.writeHead() explicitly), this property 
    // controls the status message that will be sent to the client when the headers get flushed. 
    // If this is left as undefined then the standard message for the status code will be used.
    //
    // Example:
    // ```
    // response.statusMessage = 'Not found';
    // ```
    // After response header was sent to the client, this property indicates the status message which was sent out.
    public String statusMessage = "OK";

    // The encoding to use for the message.
    public String encoding = "UTF8";

    // When true, the Date header will be automatically generated and sent in the response 
    // if it is not already present in the headers. Defaults to true.
    // This should only be disabled for testing; HTTP requires the Date header in responses.
    public boolean sendDate = true;

    // Boolean value that indicates whether the OutgoingMessage has completed. 
    // Starts as false. After OutgoingMessage.end() executes, the value will be true.
    public boolean finished = false;

    // Boolean (read-only). True if headers were sent, false otherwise.
    public boolean headersSent = false;

    // The IncomingMessage instance for this message.
    protected IncomingMessage request;

    // Headers.
    protected Map headers = new LinkedListMap();

    // Raw Headers.
    protected String rawHeaders = "";

    // The current keep-alive value for this message socket.
    protected int keepAliveMax;

    // The raw output stream for the incoming message.
    protected java.io.OutputStream writer;

    // The big buffer.
    protected byte[] writeBuffer = new byte[0];

    // Carriage Return and Line Feed.
    protected final String CRLF = "\r\n";

    // Create an instance of an OutgoingMessage.
    public OutgoingMessage(java.io.OutputStream writer, IncomingMessage request, int keepAliveMax) {
        this.writer = writer;
        this.request = request;
        this.keepAliveMax = keepAliveMax;
    }

    // Sets a single header value for implicit headers. If this header already exists in the to-be-sent headers, 
    // its value will be replaced.
    //
    // Example:
    // ```
    // outgoingMessage.setHeader('Content-Type', 'text/html');
    // ```
    // Attempting to set a header field name or value that contains invalid characters will result in a TypeError being thrown.
    public void setHeader(String name, String value) {
        this.headers.put(name, value);
    }

    // Reads out a header that's already been queued but not sent to the client. 
    // Note that the name is case insensitive. This can only be called before headers get implicitly flushed.
    //
    // Example:
    // ```
    // var contentType = outgoingMessage.getHeader('content-type');
    // ```
    public String getHeader(String name) {
        MapItem val = this.headers.get(name);
        if (val == null) {
            return "";
        }
        return val.asString();
    }

    // Removes a header that's queued for implicit sending.
    //
    // Example:
    // ```
    // outgoingMessage.removeHeader('Content-Encoding');
    // ```
    public void removeHeader(String name) {
        this.headers.delete(name);
    }

    public int write(String data) {
        return this.write(data, this.encoding);
    }

    public int write(String data, String encoding) {
        try {
            return this.write(data.getBytes(encoding));
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    // If this method is called and response.writeHead() has not been called, it will switch to implicit 
    // header mode and flush the implicit headers.
    //
    // This sends a chunk of the response body. This method may be called multiple times to provide 
    // successive parts of the body.
    //
    // chunk can be a string or a buffer. If chunk is a string, the second parameter specifies how to 
    // encode it into a byte stream. By default the encoding is 'utf8'. The last parameter callback 
    // will be called when this chunk of data is flushed.
    //
    // Note: This is the raw HTTP body and has nothing to do with higher-level multi-part body 
    // encodings that may be used.
    //
    // The first time response.write() is called, it will send the buffered header information and the 
    // first body to the client. The second time response.write() is called, Node.js assumes you're 
    // going to be streaming data, and sends that separately. That is, the response is buffered 
    // up to the first chunk of body.
    //
    // Returns true if the entire data was flushed successfully to the kernel buffer. Returns false if 
    // all or part of the data was queued in user memory. 'drain' will be emitted when the buffer is free again.
    public int write(byte[] data) {
        this.writeBuffer = ByteArray.append(this.writeBuffer, data);
        return data.length;
    }

    public int end() {
        return this.end("");
    }

    public int end(String data) {
        return this.end(data, this.encoding);
    }

    public int end(String data, String encoding) {
        try {
            return this.end(data.getBytes(encoding));
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    // This method signals to the server that all of the OutgoingMessage headers and body have been sent;
    // that server should consider this message complete. The method, OutgoingMessage.end(), MUST be called on each OutgoingMessage.
    // If data is specified, it is equivalent to calling OutgoingMessage.write(data, encoding) followed by OutgoingMessage.end().
    public int end(byte[] data) {
        // This is the end of the road so record the fact.
        this.finished = true;
        // Write any data we were given to the buffer.
        this.write(data);
        // Write the buffer.
        this.rawWrite(this.writeBuffer);
        return data.length;
    }

    // Send the content of the buffer to the client including headers.
    public int flush() {
        int count = this.rawWrite(this.writeBuffer);
        this.writeBuffer = new byte[0];
        return count;
    }

    // Write to the OutputStream and send the headers if they are not already sent.
    protected int rawWrite(byte[] data) {
        try {
            if (this.headersSent) {
                this.writer.write(data);
            } else {
                // Calling write() more than once appears to slow down the server by about 33%.
                this.writer.write(ByteArray.append(this.renderHeaders(), data));
                this.headersSent = true;
            }
            this.writer.flush();
            return data.length;
        } catch (Exception e) {
            System.out.println("socket outputStream write error");
            System.out.println(e);
        }
        return 0;
    }

    // Renders the HTTP headers to a byte array.
    protected byte[] renderHeaders() {
        this.rawHeaders += this.httpVersion + " " + Integer.toString(this.statusCode) + " " + this.statusMessage + this.CRLF;
        if (this.sendDate) {
            this.rawHeaders += "Date: " + java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME.format(java.time.ZonedDateTime.now(java.time.ZoneId.of("GMT"))) + this.CRLF;
        }
        if (this.finished) {
            this.rawHeaders += "Content-Length: " + Integer.toString(this.writeBuffer.length) + this.CRLF;
        } else {
            this.rawHeaders += "Transfer-Encoding: Chunked" + this.CRLF;
        }
        if (this.keepAliveMax > 0) {
            // this.rawHeaders += "Keep-Alive: timeout=15, max=" + Integer.toString(this.keepAliveMax) + this.CRLF;
            this.rawHeaders += "Connection: Keep-Alive" + this.CRLF;
        } else {
            this.rawHeaders += "Connection: Close" + this.CRLF;
        }
        this.reanderExtraHeaders();
        return (this.rawHeaders + this.CRLF).getBytes();
    }

    // Appends user set headers to this.rawHeaders string.
    protected void reanderExtraHeaders() {
        this.headers.forEach((header) -> {
            this.rawHeaders += (String)header.key() + ": " + (String)header.value() + this.CRLF;
        });
    }
}
