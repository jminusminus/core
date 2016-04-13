//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.http;

class ServerAccept implements Runnable {

    // The number of threads to use for accepting connections.
    protected int threads;

    // The handler function for incoming message.
    protected MessageHandlerFunc handler;

    // The listening socket.
    protected java.net.ServerSocket listener;

    ServerAccept(int port, int threads, MessageHandlerFunc handler) {
        try {
            this.listener = new java.net.ServerSocket(port);
        } catch (java.io.IOException e) {
            System.out.println("ServerSocket creation error");
            System.out.println(e);
            this.close();
        }
        this.threads = threads;
        this.handler = handler;
    }

    // Begin accepting connections on the specified port.
    public void run() {
        java.util.concurrent.ExecutorService executor = java.util.concurrent.Executors.newFixedThreadPool(this.threads);
        try {
            int id = 0;
            while (this.isClosed() == false) {
                executor.execute(new MessageHandler(++id, this.handler, this.listener.accept()));
            }
        } catch (java.io.IOException e) {
            System.out.println("ServerSocket accept error");
            System.out.println(e);
            this.close();
        }
    }

    // Returns the closed state of the ServerSocket.
    public boolean isClosed() {
        return this.listener.isClosed();
    }

    // Stops the server from accepting new connections. See java.net.ServerSocket.close().
    public void close() {
        try {
            this.listener.close();
        } catch (java.io.IOException e) {
            System.out.println("ServerSocket close error");
            System.out.println(e);
        }
    }
}
