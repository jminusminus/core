//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.http;

public class Server {

    // The handler function used for all incoming messages.
    protected MessageHandlerFunc handler;

    // Accepts connections in a separate thread.
    protected ServerAccept serverAccept;

    // Create an instance of a Server.
    protected Server (MessageHandlerFunc handler) {
        this.handler = handler;
    }

    // Returns a new instance of Server using the given handler.
    public static Server createServer(MessageHandlerFunc handler) {
        Server s = new Server(handler);
        return s;
    }

    // Begin accepting connections on the specified port. 
    // The server will accept connections on any IPv6 address (::) when IPv6 
    // is available, or any IPv4 address (0.0.0.0) otherwise.
    // A port value of zero will assign a random port.
    public void listen(int port) {
        int cores = Runtime.getRuntime().availableProcessors();
        int threads = cores * 2;
        this.serverAccept = new ServerAccept(port, threads, this.handler);
        Thread serverThread = new Thread(this.serverAccept);
        serverThread.start();
        System.out.println("Server started on port " + port + ", using " + Integer.toString(cores) + " cores and " + Integer.toString(threads) + " threads...");
    }

    // Returns is the server is still listening.
    public boolean isClosed() {
        return this.serverAccept.isClosed();
    }

    // Stops the server from accepting new connections. See java.net.ServerSocket.close().
    public void close() {
        if (this.isClosed() == false) {
            this.serverAccept.close();
        }
    }
}
