//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.io;

import github.com.jminusminus.simplebdd.Test;

public class BufferedInputStreamReader_test extends Test {

    public static void main(String[] args) {
        BufferedInputStreamReader_test test = new BufferedInputStreamReader_test();
        test.run();
    }

    protected static final byte X = (byte)88;
    protected static final byte Y = (byte)89;
    protected static final byte LF = (byte)10;

    public void test_read() {
        InputStreamMock s = new InputStreamMock(this.simpleString.getBytes());
        BufferedInputStreamReader reader = new BufferedInputStreamReader(s);
        this.should("return 97 (ascii 'a')");
        this.assertEqual(97, reader.read());
    }

    public void test_read_to_X() {
        InputStreamMock s = new InputStreamMock(this.simpleString.getBytes());
        BufferedInputStreamReader reader = new BufferedInputStreamReader(s);
        this.should("return up to the first X");
        this.assertEqual("aaaaX", new String(reader.readTo(this.X)));
        this.should("return up to the second X");
        this.assertEqual("bbbX", new String(reader.readTo(this.X)));
        this.should("return up to the third X");
        this.assertEqual("ccX", new String(reader.readTo(this.X)));
        this.should("return up to the fourth X");
        this.assertEqual("dX", new String(reader.readTo(this.X)));
        this.should("return up to the last X");
        this.assertEqual("X", new String(reader.readTo(this.X)));
    }

    public void test_read_to_end() {
        InputStreamMock s = new InputStreamMock(this.simpleString.getBytes());
        BufferedInputStreamReader reader = new BufferedInputStreamReader(s);
        this.should("return the content of the whole buffer");
        this.assertEqual(this.simpleString, new String(reader.readTo(this.Y)));
    }

    public void test_read_to_no_more() {
        InputStreamMock s = new InputStreamMock(this.endString.getBytes());
        BufferedInputStreamReader reader = new BufferedInputStreamReader(s);
        reader.readTo(this.Y);
        this.should("return an empty buffer after one read");
        this.assertEqual(0, reader.readTo(this.X).length);
    }

    public void test_read_twice_to_no_more() {
        InputStreamMock s = new InputStreamMock(this.endString.getBytes());
        BufferedInputStreamReader reader = new BufferedInputStreamReader(s);
        reader.readTo(this.X);
        reader.readTo(this.Y);
        this.should("return an empty buffer after two reads");
        this.assertEqual(0, reader.readTo(this.X).length);
    }

    public void test_read_to_LF() {
        InputStreamMock s = new InputStreamMock(this.simpleRequest.getBytes());
        BufferedInputStreamReader reader = new BufferedInputStreamReader(s);
        this.should("return the request line");
        this.assertEqual("GET / HTTP/1.1\r\n", new String(reader.readTo(this.LF)));
        this.should("return the host header");
        this.assertEqual("Host: localhost:8080\r\n", new String(reader.readTo(this.LF)));
        this.should("return CRLF");
        this.assertEqual("\r\n", new String(reader.readTo(this.LF)));
        this.should("return the html tags");
        this.assertEqual("<html></html>", new String(reader.readTo(this.LF)));
    }

    public void test_hasMore_no() {
        InputStreamMock s = new InputStreamMock(this.simpleRequest.getBytes());
        BufferedInputStreamReader reader = new BufferedInputStreamReader(s, 0);
        this.should("return there is NO more to read");
        this.assertEqual(false, reader.hasMore());
    }

    public void test_hasMore_yes() {
        InputStreamMock s = new InputStreamMock(this.simpleRequest.getBytes());
        BufferedInputStreamReader reader = new BufferedInputStreamReader(s, 1);
        this.should("return there IS more to read");
        this.assertEqual(true, reader.hasMore());
    }

    public void test_throw() {
        InputStreamMock s = new InputStreamMock(this.simpleRequest.getBytes());
        s.throwException = true;
        BufferedInputStreamReader reader = new BufferedInputStreamReader(s, 1);
        this.should("return false as an error was thrown");
        this.assertEqual(false, reader.hasMore());
    }

    public void test_mark() {
        InputStreamMock s = new InputStreamMock(this.simpleString.getBytes());
        BufferedInputStreamReader reader = new BufferedInputStreamReader(s);
        this.should("re-return the line read from when mark was set");
        reader.mark(1);
        this.assertEqual("aaaaX", new String(reader.readTo(this.X)));
        reader.reset();
        this.assertEqual("aaaaX", new String(reader.readTo(this.X)));
    }

    protected String simpleString = "aaaaXbbbXccXdXX";

    protected String endString = "aaaaXbbbbY";

    protected String simpleRequest = ""
        + "GET / HTTP/1.1\r\n"
        + "Host: localhost:8080\r\n"
        + "\r\n"
        + "<html></html>";
}
