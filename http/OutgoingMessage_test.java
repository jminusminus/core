//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.http;

import github.com.jminusminus.core.io.BufferedInputStreamReader;
import github.com.jminusminus.core.io.InputStreamMock;
import github.com.jminusminus.core.io.OutputStreamMock;
import github.com.jminusminus.simplebdd.Test;

public class OutgoingMessage_test extends Test {
    public static void main(String[] args) {
        OutgoingMessage_test test = new OutgoingMessage_test();
        test.run();
    }

    protected OutputStreamMock writer;

    public void beforeEach() {
        this.writer = new OutputStreamMock();
    }

    public void test_new_OutgoingMessage() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return an instance of OutgoingMessage");
        this.assertEqual("github.com.jminusminus.core.http.OutgoingMessage", res.getClass().getName());
    }

    public void test_setHeader() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return output header that was set");
        res.setHeader("X-test", "foo");
        res.end();
        this.assertEqual(true, this.getOutput().contains("X-test: foo"));
    }

    public void test_getHeader() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return the header that was set");
        res.setHeader("X-test", "foo");
        this.assertEqual("foo", res.getHeader("X-test"));
    }

    public void test_removeHeader() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return an empty string");
        res.setHeader("X-test", "foo");
        res.removeHeader("X-test");
        this.assertEqual("", res.getHeader("X-test"));
    }

    public void test_end() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return the basic HTTP response");
        res.sendDate = false;
        res.end();
        this.assertEqual(this.simpleResponse, this.getOutput());
    }

    public void test_end_error() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return 0 from end() because there was an encoding error");
        int count = res.end("xxx", "xxx");
        this.assertEqual(0, count);
    }

    public void test_end_keep_alive() {
        OutgoingMessage res = this.createOutgoingMessage("", 1);
        this.should("return the basic HTTP response with Keep-Alive");
        res.sendDate = false;
        res.end();
        this.assertEqual(this.simpleResponseKeepAlive, this.getOutput());
    }

    public void test_write_empty() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return the basic HTTP response with empty body");
        res.sendDate = false;
        res.write("");
        res.end();
        this.assertEqual(this.simpleResponse, this.getOutput());
    }

    public void test_write_hello() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return the basic HTTP response with hello body");
        res.sendDate = false;
        res.write("hello");
        res.end();
        this.assertEqual(this.simpleResponseHello, this.getOutput());
    }

    public void test_write_error() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return 0 from write() because there was an encoding error");
        int count = res.write("xxx", "xxx");
        this.assertEqual(0, count);
    }

    public void test_send_date() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return a date in the headers");
        res.end();
        this.assertEqual(true, this.getOutput().contains("Date: "));
    }

    public void test_flush() {
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return Transfer-Encoding: Chunked");
        res.flush();
        res.end();
        this.assertEqual(true, this.getOutput().contains("Transfer-Encoding: Chunked"));
    }

    public void test_flush_write_error() {
        this.writer.throwException = true;
        OutgoingMessage res = this.createOutgoingMessage("", 0);
        this.should("return 0 from end() because there was an encoding error");
        res.end("xxx");
        int count = res.flush();
        this.assertEqual(0, count);
    }

    protected OutgoingMessage createOutgoingMessage(String msg, int keepAlive) {
        InputStreamMock input = new InputStreamMock(msg.getBytes());
        BufferedInputStreamReader reader = new BufferedInputStreamReader(input);
        IncomingMessage req = new IncomingMessage(reader);
        return new OutgoingMessage(this.writer, req, keepAlive);
    }

    protected String getOutput() {
        return new String(this.writer.buffer);
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

    protected String simpleResponseKeepAlive = ""
        + "HTTP/1.1 200 OK\r\n"
        + "Content-Length: 0\r\n"
        + "Connection: Keep-Alive\r\n"
        + "\r\n";

    protected String simpleResponseHello = ""
        + "HTTP/1.1 200 OK\r\n"
        + "Content-Length: 5\r\n"
        + "Connection: Close\r\n"
        + "\r\n"
        + "hello";
}
