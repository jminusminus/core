//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.http;

import github.com.jminusminus.core.io.BufferedInputStreamReader;

public class MessageHandler implements Runnable {

    protected int id;

    // The handler function for incoming message.
    protected MessageHandlerFunc handler;

    // The raw socket for the incoming message.
    protected java.net.Socket socket;

    // The default keep-alive max value.
    protected int keepAliveMax = 500;

    // Create an instance of a MessageHandler.
    public MessageHandler(int id, MessageHandlerFunc handler, java.net.Socket socket) {
        this.id = id;
        this.handler = handler;
        this.socket = socket;
    }

    // Thread run method.
    public void run() {
        // Get the streams from the given socket.
        BufferedInputStreamReader reader;
        java.io.OutputStream writer;
        try {
            reader = new BufferedInputStreamReader(this.socket.getInputStream());
            writer = this.socket.getOutputStream();
        } catch (Exception e) {
            System.out.println("socket getInputStream or getOutputStream error");
            System.out.println(e);
            return;
        }
        // Keep processing the socket until the keep-alive-max is 0 or the connection is closed.
        while (--this.keepAliveMax >= 0 && this.socket.isClosed() == false && reader.hasMore()) {
            // System.out.println(Integer.toString(this.id) + ":" + Integer.toString(this.keepAliveMax));
            IncomingMessage req = new IncomingMessage(reader);
            // Check to see if this is a keep-alive request.
            if (req.isKeepAlive() == false) {
                this.keepAliveMax = 0;
            }
            OutgoingMessage res = new OutgoingMessage(writer, req, this.keepAliveMax);
            this.handler.call(req, res);
        }
        // Once all connections have been dealt with close the socket.
        try {
            // The socket should have been closed by the client already but close it to be safe.
            this.socket.close();
        } catch (Exception e) {
            System.out.println("Socket close error.");
            System.out.println(e);
        }
    }
}
