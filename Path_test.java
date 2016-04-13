//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core;

import github.com.jminusminus.simplebdd.Test;

public class Path_test extends Test {
    public static void main(String[] args) {
        Path_test test = new Path_test();
        test.run();
    }

    public void test_new_Path() {
        this.should("return an instance of Path");
        Path p = new Path();
        this.assertEqual("github.com.jminusminus.core.Path", p.getClass().getName());
    }

    public void test_normalize_with_first() {
    	this.should("return an absolute normalized path with first ..");
    	String path = Path.normalize("/foo/../bar//baz/asdf/quux");
    	this.assertEqual("/bar/baz/asdf/quux", path);
    }

    public void test_normalize_with_mid() {
    	this.should("return an absolute normalized path with mid ..");
    	String path = Path.normalize("/foo/bar//baz/../asdf/quux/..");
    	this.assertEqual("/foo/bar/asdf", path);
    }

    public void test_normalize_with_last() {
    	this.should("return an absolute normalized path with last ..");
    	String path = Path.normalize("/foo/bar//baz/asdf/quux/..");
    	this.assertEqual("/foo/bar/baz/asdf", path);
    }
}
