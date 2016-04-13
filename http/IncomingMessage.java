//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.http;

import github.com.jminusminus.core.util.Map;
import github.com.jminusminus.core.util.LinkedListMap;
import github.com.jminusminus.core.io.BufferedInputStreamReader;

public class IncomingMessage {

    // The request method as a string. Read only. Example: "GET", "DELETE".
    public String method = "";

    // Request URL string. This contains only the URL that is present in the actual 
    // HTTP request. If the request is:
    // ```
    // GET /status?name=ric HTTP/1.1\r\n
    // Accept: text/plain\r\n
    // \r\n
    // ```
    // Then `url` will be:
    // ```
    // "/status?name=ric"
    // ```
    public String url = "";

    // In case of server request, the HTTP version sent by the client.
    // Probably either "1.1" or "1.0".
    public String httpVersion = "";

    // The request headers object.
    // Key-value pairs of header names and values. Header names are lower-cased.
    // Duplicates in raw headers are handled in the following ways, depending on the header name:
    // Duplicates of `age`, `authorization`, `content-length`, `content-type`, `etag`, `expires`, `from`, `host`, 
    // `if-modified-since`, `if-unmodified-since`, `last-modified`, `location`, `max-forwards`, `proxy-authorization`, 
    // `referer`, `retry-after`, or `user-agent` are discarded.
    // `set-cookie` is always an array. Duplicates are added to the array.
    // For all other headers, the values are joined together with ", ".
    public Map headers = new LinkedListMap();

    // The buffered input stream for the incoming message.
    protected BufferedInputStreamReader reader;

    // Keep-alive.
    protected boolean keepAlive = false;

    // Line Feed.
    protected static final byte LF = (byte)10;

    // Create an instance of an IncomingMessage.
    public IncomingMessage(BufferedInputStreamReader reader) {
        this.reader = reader;
        this.readRequestLine();
        this.readRawHeaders();
        // Check for "Connection: Keep-Alive" here as we need it.
        if (this.headers.get("connection") != null && "keep-alive".equals(this.headers.get("connection").asString().toLowerCase())) {
            this.keepAlive = true;
        }
    }

    // Returns if the current message is `keep-alive` or not.
    public boolean isKeepAlive() {
        return this.keepAlive;
    }

    protected void readRequestLine() {
        String requestLine = new String(this.reader.readTo(this.LF)).trim();
        if (requestLine.length() == 0) {
            return;
        }
        String[] parts = requestLine.split(" ");
        if (parts.length == 3) {
            this.method = parts[0].toUpperCase();
            this.url = parts[1];
            this.httpVersion = parts[2].toUpperCase();
        }
    }

    protected void readRawHeaders() {
        byte[] buf = this.reader.readTo(this.LF);
        String header;
        while (buf.length > 0) {
            header = new String(buf).trim();
            // If the header length is zero then all the headers have been read.
            if (header.length() == 0) {
                return;
            }
            String[] parts = header.split(":", 2);
            if (parts.length == 2) {
                // TODO: Check dupes here.
                this.headers.put(parts[0].toLowerCase(), parts[1].trim());
            }
            // Read the next header into the buffer.
            buf = this.reader.readTo(this.LF);
        }
    }
}
