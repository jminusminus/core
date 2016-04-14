//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

import github.com.jminusminus.simplebdd.Test;

public class Readable_test extends Test {
    public static void main(String[] args) {
        Readable_test test = new Readable_test();
        test.run();
    }

    public void test_new_Readable_string() {
        this.should("return an instance of Readable from string");
        Readable r = new Readable("");
        this.assertEqual("github.com.jminusminus.core.stream.Readable", r.getClass().getName());
    }

    public void test_new_Readable_string_encoding() {
        this.should("return an instance of Readable from string with encoding");
        Readable r = new Readable("", "utf8");
        this.assertEqual("github.com.jminusminus.core.stream.Readable", r.getClass().getName());
    }

    public void test_new_Readable_byte_array() {
        this.should("return an instance of Readable from byte array");
        Readable r = new Readable(new byte[0]);
        this.assertEqual("github.com.jminusminus.core.stream.Readable", r.getClass().getName());
    }

    public void test_new_Readable_stream() {
        this.should("return an instance of Readable from input stream");
        Readable r;
        try {
            r = new Readable(new java.io.FileInputStream("./fixtures/stream/input.txt"));
            this.assertEqual("github.com.jminusminus.core.stream.Readable", r.getClass().getName());
        } catch (Exception e) {
            this.assertEqual("github.com.jminusminus.core.stream.Readable", e);
        }
    }
}
