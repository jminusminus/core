//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core;

import github.com.jminusminus.simplebdd.Test;

public class Arrays_test extends Test {
    public static void main(String[] args) {
        Arrays_test test = new Arrays_test();
        test.run();
    }

    public void test_new_Arrays() {
        this.should("return an instance of Arrays");
        Arrays a = new Arrays();
        this.assertEqual("github.com.jminusminus.core.Arrays", a.getClass().getName());
    }
}
