//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core;

import github.com.jminusminus.simplebdd.Test;

public class FileSystem_test extends Test {
    public static void main(String[] args) {
        FileSystem_test test = new FileSystem_test();
        test.run();
    }

    public void test_new_FileSystem() {
        this.should("return an instance of FileSystem");
        FileSystem fs = new FileSystem();
        this.assertEqual("github.com.jminusminus.core.FileSystem", fs.getClass().getName());
    }
}
