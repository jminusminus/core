//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.http;

import github.com.jminusminus.core.io.BufferedInputStreamReader;
import github.com.jminusminus.core.io.InputStreamMock;
import github.com.jminusminus.simplebdd.Test;

public class IncomingMessage_test extends Test {
    public static void main(String[] args) {
        IncomingMessage_test test = new IncomingMessage_test();
        test.run();
    }

    public void test_empty_request() {
        InputStreamMock s = new InputStreamMock("".getBytes());
        BufferedInputStreamReader b = new BufferedInputStreamReader(s);
        IncomingMessage message = new IncomingMessage(b);
        this.should("return empty as the method");
        this.assertEqual("", message.method);
    }

    public void test_bad_request() {
        InputStreamMock s = new InputStreamMock("GET /".getBytes());
        BufferedInputStreamReader b = new BufferedInputStreamReader(s);
        IncomingMessage message = new IncomingMessage(b);
        this.should("return empty as the method");
        this.assertEqual("", message.method);
        this.should("return empty as the url");
        this.assertEqual("", message.url);
    }

    public void test_parse_simple_request() {
        InputStreamMock s = new InputStreamMock(this.simpleRequest.getBytes());
        BufferedInputStreamReader b = new BufferedInputStreamReader(s);
        IncomingMessage message = new IncomingMessage(b);
        this.should("return GET as the method");
        this.assertEqual("GET", message.method);
        this.should("return / as the path");
        this.assertEqual("/", message.url);
        this.should("return HTTP/1.1 as the method");
        this.assertEqual("HTTP/1.1", message.httpVersion);
    }

    public void test_parse_keep_alive_request() {
        InputStreamMock s = new InputStreamMock(this.keepAliveRequest.getBytes());
        BufferedInputStreamReader b = new BufferedInputStreamReader(s);
        IncomingMessage message = new IncomingMessage(b);
        this.should("return ture from isKeepAlive");
        this.assertEqual(true, message.isKeepAlive());
    }

    public void test_parse_bad_keep_alive_request() {
        InputStreamMock s = new InputStreamMock(this.badKeepAliveRequest.getBytes());
        BufferedInputStreamReader b = new BufferedInputStreamReader(s);
        IncomingMessage message = new IncomingMessage(b);
        this.should("return ture from isKeepAlive");
        this.assertEqual(false, message.isKeepAlive());
    }

    protected String simpleRequest = ""
        + "GET / HTTP/1.1\r\n"
        + "Host: localhost:8080\r\n"
        + "\r\n"
        + "<html></html>";

    protected String keepAliveRequest = ""
        + "GET / HTTP/1.1\r\n"
        + "Host: localhost:8080\r\n"
        + "Connection: Keep-Alive"
        + "\r\n"
        + "<html></html>";

    protected String badKeepAliveRequest = ""
        + "GET / HTTP/1.1\r\n"
        + "Host: localhost:8080\r\n"
        + "Connection: NO"
        + "\r\n"
        + "<html></html>";
}
