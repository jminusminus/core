//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.io;

import github.com.jminusminus.simplebdd.Test;

public class OutputStreamMock_test extends Test {

    public static void main(String[] args) {
        OutputStreamMock_test test = new OutputStreamMock_test();
        test.run();
    }

    public void test_new_OutputStreamMock() {
        OutputStreamMock s = new OutputStreamMock();
        this.should("return an instance of OutputStreamMock");
        this.assertEqual("github.com.jminusminus.core.io.OutputStreamMock", s.getClass().getName());
    }

    public void test_write_byte_array_part() {
        OutputStreamMock s = new OutputStreamMock();
        s.write("abcdefg".getBytes(), 2, 2);
        this.should("return the two bytes writen to the stream");
        this.assertEqual("cd", new String(s.buffer));
    }

    public void test_new_byte_array() {
        OutputStreamMock s = new OutputStreamMock();
        s.write("foo".getBytes());
        this.should("return the bytes writen to the stream");
        this.assertEqual("foo", new String(s.buffer));
    }

    public void test_write_byte() {
        OutputStreamMock s = new OutputStreamMock();
        s.write((byte)101);
        this.should("return the single byte writen to the stream");
        this.assertEqual((byte)101, s.buffer[0]);
    }
}
