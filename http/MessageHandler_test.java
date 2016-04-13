//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.http;

import github.com.jminusminus.core.io.SocketMock;
import github.com.jminusminus.simplebdd.Test;

public class MessageHandler_test extends Test {
    public static void main(String[] args) {
        MessageHandler_test test = new MessageHandler_test();
        test.run();
    }

    public void test_new_MessageHandler() {
        this.should("return an instance of MessageHandler");
        MessageHandlerFunc fn = (req, res) -> {
            res.end();
        };
        SocketMock socket = new SocketMock("".getBytes());
        MessageHandler mh = new MessageHandler(0, fn, socket);
        this.assertEqual("github.com.jminusminus.core.http.MessageHandler", mh.getClass().getName());
    }

    public void test_run_empty_request() {
        this.should("return a empty response");
        MessageHandlerFunc fn = (req, res) -> {
            res.end();
        };
        SocketMock socket = new SocketMock("".getBytes());
        MessageHandler mh = new MessageHandler(0, fn, socket);
        mh.run();
        this.assertEqual("", this.getOutput(socket));
    }

    public void test_run_input_error() {
        this.should("return a empty response because of an InputStream error");
        MessageHandlerFunc fn = (req, res) -> {
            res.end();
        };
        SocketMock socket = new SocketMock(this.simpleRequest.getBytes());
        socket.throwInputException = true;
        MessageHandler mh = new MessageHandler(0, fn, socket);
        mh.run();
        socket.throwInputException = false;
        this.assertEqual("", this.getOutput(socket));
    }

    public void test_run_output_error() {
        this.should("return a empty response because of an OutputStream error");
        MessageHandlerFunc fn = (req, res) -> {
            res.end();
        };
        SocketMock socket = new SocketMock(this.simpleRequest.getBytes());
        socket.throwOutputException = true;
        MessageHandler mh = new MessageHandler(0, fn, socket);
        mh.run();
        socket.throwOutputException = false;
        this.assertEqual("", this.getOutput(socket));
    }

    public void test_run_simple_request() {
        this.should("return the basic response");
        MessageHandlerFunc fn = (req, res) -> {
            res.sendDate = false;
            res.end();
        };
        SocketMock socket = new SocketMock(this.simpleRequest.getBytes());
        MessageHandler mh = new MessageHandler(0, fn, socket);
        mh.run();
        this.assertEqual(this.simpleResponse, this.getOutput(socket));
    }

    public void test_run_keepalive_request() {
        this.should("return the keep alive response");
        MessageHandlerFunc fn = (req, res) -> {
            res.sendDate = false;
            res.end();
        };
        SocketMock socket = new SocketMock(this.keepAliveRequest.getBytes());
        MessageHandler mh = new MessageHandler(0, fn, socket);
        mh.run();
        this.assertEqual(this.keepAliveResponse, this.getOutput(socket));
    }

    public void test_run_close_error() {
        this.should("return a empty response from a close error");
        MessageHandlerFunc fn = (req, res) -> {
            res.end();
        };
        SocketMock socket = new SocketMock("".getBytes());
        MessageHandler mh = new MessageHandler(0, fn, socket);
        socket.throwCloseException = true;
        mh.run();
        this.assertEqual("", this.getOutput(socket));
    }

    protected String getOutput(SocketMock socket) {
        return new String(socket.getOutputStream().buffer);
    }

    protected String simpleRequest = ""
        + "GET / HTTP/1.1\r\n"
        + "Host: localhost:8080\r\n"
        + "\r\n"
        + "<html></html>";

    protected String simpleResponse = ""
        + "HTTP/1.1 200 OK\r\n"
        + "Content-Length: 0\r\n"
        + "Connection: Close\r\n"
        + "\r\n";

    protected String keepAliveRequest = ""
        + "GET / HTTP/1.1\r\n"
        + "Host: localhost:8080\r\n"
        + "Connection: Keep-Alive"
        + "\r\n"
        + "<html></html>";

    protected String keepAliveResponse = ""
        + "HTTP/1.1 200 OK\r\n"
        + "Content-Length: 0\r\n"
        + "Connection: Keep-Alive\r\n"
        + "\r\n";
}
