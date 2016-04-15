
# IncomingMessage
#### Package: github.com.jminusminus.core.http
## Install
```
jmm get github.com/jminusminus/core/http
```
## Class: github.com.jminusminus.core.http.IncomingMessage
```
import github.com.jminusminus.core.http.IncomingMessage;
```
## Attributes
## String method = ""
The request method as a string. Read only. Example: "GET", "DELETE".

## String url = ""
Request URL string. This contains only the URL that is present in the actual
HTTP request. If the request is:
```
GET /status?name=ric HTTP/1.1\r\n
Accept: text/plain\r\n
\r\n
```
Then `url` will be:
```
"/status?name=ric"
```

## String httpVersion = ""
In case of server request, the HTTP version sent by the client.
Probably either "1.1" or "1.0".

## Map headers = new LinkedListMap()
The request headers object.
Key-value pairs of header names and values. Header names are lower-cased.
Duplicates in raw headers are handled in the following ways, depending on the header name:
Duplicates of `age`, `authorization`, `content-length`, `content-type`, `etag`, `expires`, `from`, `host`,
`if-modified-since`, `if-unmodified-since`, `last-modified`, `location`, `max-forwards`, `proxy-authorization`,
`referer`, `retry-after`, or `user-agent` are discarded.
`set-cookie` is always an array. Duplicates are added to the array.
For all other headers, the values are joined together with ", ".

## Methods
## IncomingMessage(BufferedInputStreamReader reader)
Create an instance of an IncomingMessage.

## boolean isKeepAlive()
Returns if the current message is `keep-alive` or not.

