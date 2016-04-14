//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

import github.com.jminusminus.simplebdd.Test;

public class InputStream_test extends Test {
    public static void main(String[] args) {
        InputStream_test test = new InputStream_test();
        test.run();
    }

    public void test_new_InputStream() {
        this.should("return an instance of InputStream");
        InputStream i = new InputStream(new byte[0]);
        this.assertEqual("github.com.jminusminus.core.stream.InputStream", i.getClass().getName());
    }
}
