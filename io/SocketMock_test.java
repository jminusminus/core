//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.io;

import github.com.jminusminus.simplebdd.Test;

public class SocketMock_test extends Test {
    public static void main(String[] args) {
        SocketMock_test test = new SocketMock_test();
        test.run();
    }

    public void test() {
        SocketMock s = new SocketMock("test".getBytes());
        byte[] buf = new byte[4];
        s.getInputStream().read(buf);
        this.should("return the buffered bytes");
        this.assertEqual("test", new String(buf));
    }

    public void test_getOutputStream() {
        SocketMock s = new SocketMock("test".getBytes());
        java.io.OutputStream o = s.getOutputStream();
        this.should("return a java.io.OutputStream");
        this.assertEqual("github.com.jminusminus.core.io.OutputStreamMock", o.getClass().getName());
    }

    public void test_set_throwInputException() {
        this.should("return a java.io.InputStream that will throw an exception");
        SocketMock s = new SocketMock("test".getBytes());
        s.throwInputException = true;
        try {
            java.io.InputStream o = s.getInputStream();
        } catch (Exception e) {
            this.assertEqual(true, true);
            return;
        }
        this.assertEqual(false, true);
    }

    public void test_set_throwOutputException() {
        this.should("return a java.io.OutputStream that will throw an exception");
        SocketMock s = new SocketMock("test".getBytes());
        s.throwOutputException = true;
        try {
            java.io.OutputStream o = s.getOutputStream();
        } catch (Exception e) {
            this.assertEqual(true, true);
            return;
        }
        this.assertEqual(false, true);
    }
}
