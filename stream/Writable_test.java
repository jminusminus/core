//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

import github.com.jminusminus.simplebdd.Test;

public class Writable_test extends Test {
    public static void main(String[] args) {
        Writable_test test = new Writable_test();
        test.run();
    }

    public void test_new_Readable() {
        this.should("return an instance of Writable");
        Writable w = new Writable();
        this.assertEqual("github.com.jminusminus.core.stream.Writable", w.getClass().getName());
    }
}
