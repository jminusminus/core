//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.path;

import github.com.jminusminus.simplebdd.Test;

public class Path_test extends Test {
    public static void main(String[] args) {
        Path_test test = new Path_test();
        test.run();
    }

    public void test_new_Path() {
        this.should("return an instance of Path");
        Path p = new Path();
        this.assertEqual("github.com.jminusminus.path.Path", p.getClass().getName());
    }
}
