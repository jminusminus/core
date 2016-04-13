//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.io;

import github.com.jminusminus.simplebdd.Test;

public class InputStreamMock_test extends Test {
    public static void main(String[] args) {
        InputStreamMock_test test = new InputStreamMock_test();
        test.run();
    }

    public void test_new_InputStreamMock() {
        InputStreamMock s = new InputStreamMock("test".getBytes());
        this.should("return an instance of InputStreamMock");
        this.assertEqual("github.com.jminusminus.core.io.InputStreamMock", s.getClass().getName());
    }

    public void test_read() {
        InputStreamMock s = new InputStreamMock("test".getBytes());
        int count = s.read();
        this.should("create an InputStreamMock and call read");
        this.assertEqual(0, count);
    }

    public void test_read_to_buffer() {
        InputStreamMock s = new InputStreamMock("test".getBytes());
        byte[] buf = new byte[4];
        int count = s.read(buf);
        this.should("create an InputStreamMock and read it's stream");
        this.assertEqual("test", new String(buf));
        this.should("return 4 as the number of bytes read");
        this.assertEqual(4, count);
    }

    public void test_read_whole_buffer_then_read_again() {
        InputStreamMock s = new InputStreamMock("test".getBytes());
        byte[] buf = new byte[4];
        s.read(buf);
        int count = s.read(buf);
        this.should("return 0 as the buffer is empty");
        this.assertEqual(0, count);
    }

    public void test_throw_error() {
        InputStreamMock s = new InputStreamMock("test".getBytes());
        s.throwException = true;
        byte[] buf = new byte[4];
        boolean error = false;
        try {
            s.read(buf);
        } catch (Exception e) {
            error = true;
        }
        this.should("throw an error");
        this.assertEqual(true, error);
    }
}
