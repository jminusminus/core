
# Server
#### Package: github.com.jminusminus.core.http
## Install
```
jmm get github.com/jminusminus/core/http
```
## Class: github.com.jminusminus.core.http.Server
```
import github.com.jminusminus.core.http.Server;
```
## Attributes
## Methods
## static Server createServer(MessageHandlerFunc handler)
Returns a new instance of Server using the given handler.

## void listen(int port)
Begin accepting connections on the specified port.
The server will accept connections on any IPv6 address (::) when IPv6
is available, or any IPv4 address (0.0.0.0) otherwise.
A port value of zero will assign a random port.

## boolean isClosed()
Returns is the server is still listening.

## void close()
Stops the server from accepting new connections. See java.net.ServerSocket.close().

