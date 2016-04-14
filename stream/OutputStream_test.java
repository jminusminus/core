//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

import github.com.jminusminus.simplebdd.Test;

public class OutputStream_test extends Test {
    public static void main(String[] args) {
        OutputStream_test test = new OutputStream_test();
        test.run();
    }

    public void test_new_OutputStream() {
        this.should("return an instance of OutputStream");
        OutputStream o = new OutputStream();
        this.assertEqual("github.com.jminusminus.core.stream.OutputStream", o.getClass().getName());
    }
}
