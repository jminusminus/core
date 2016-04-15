
# OutgoingMessage
#### Package: github.com.jminusminus.core.http
## Install
```
jmm get github.com/jminusminus/core/http
```
## Class: github.com.jminusminus.core.http.OutgoingMessage
```
import github.com.jminusminus.core.http.OutgoingMessage;
```
## Attributes
## final String httpVersion = "HTTP/1.1"
The HTTP version for this message.

## int statusCode = 200
When using implicit headers (not calling response.writeHead() explicitly), this property controls
the status code that will be sent to the client when the headers get flushed.

Example:
```
response.statusCode = 404;
```
After response header was sent to the client, this property indicates the status code which was sent out.

## String statusMessage = "OK"
When using implicit headers (not calling response.writeHead() explicitly), this property
controls the status message that will be sent to the client when the headers get flushed.
If this is left as undefined then the standard message for the status code will be used.

Example:
```
response.statusMessage = 'Not found';
```
After response header was sent to the client, this property indicates the status message which was sent out.

## String encoding = "UTF8"
The encoding to use for the message.

## boolean sendDate = true
When true, the Date header will be automatically generated and sent in the response
if it is not already present in the headers. Defaults to true.
This should only be disabled for testing; HTTP requires the Date header in responses.

## boolean finished = false
Boolean value that indicates whether the OutgoingMessage has completed.
Starts as false. After OutgoingMessage.end() executes, the value will be true.

## boolean headersSent = false
Boolean (read-only). True if headers were sent, false otherwise.

## Methods
## OutgoingMessage(java.io.OutputStream writer, IncomingMessage request, int keepAliveMax)
Create an instance of an OutgoingMessage.

## void setHeader(String name, String value)
Sets a single header value for implicit headers. If this header already exists in the to-be-sent headers,
its value will be replaced.

Example:
```
outgoingMessage.setHeader('Content-Type', 'text/html');
```
Attempting to set a header field name or value that contains invalid characters will result in a TypeError being thrown.

## String getHeader(String name)
Reads out a header that's already been queued but not sent to the client.
Note that the name is case insensitive. This can only be called before headers get implicitly flushed.

Example:
```
var contentType = outgoingMessage.getHeader('content-type');
```

## void removeHeader(String name)
Removes a header that's queued for implicit sending.

Example:
```
outgoingMessage.removeHeader('Content-Encoding');
```

## int write(String data)

## int write(String data, String encoding)

## int write(byte[] data)
If this method is called and response.writeHead() has not been called, it will switch to implicit
header mode and flush the implicit headers.

This sends a chunk of the response body. This method may be called multiple times to provide
successive parts of the body.

chunk can be a string or a buffer. If chunk is a string, the second parameter specifies how to
encode it into a byte stream. By default the encoding is 'utf8'. The last parameter callback
will be called when this chunk of data is flushed.

Note: This is the raw HTTP body and has nothing to do with higher-level multi-part body
encodings that may be used.

The first time response.write() is called, it will send the buffered header information and the
first body to the client. The second time response.write() is called, Node.js assumes you're
going to be streaming data, and sends that separately. That is, the response is buffered
up to the first chunk of body.

Returns true if the entire data was flushed successfully to the kernel buffer. Returns false if
all or part of the data was queued in user memory. 'drain' will be emitted when the buffer is free again.

## int end()

## int end(String data)

## int end(String data, String encoding)

## int end(byte[] data)
This method signals to the server that all of the OutgoingMessage headers and body have been sent;
that server should consider this message complete. The method, OutgoingMessage.end(), MUST be called on each OutgoingMessage.
If data is specified, it is equivalent to calling OutgoingMessage.write(data, encoding) followed by OutgoingMessage.end().

## int flush()
Send the content of the buffer to the client including headers.

