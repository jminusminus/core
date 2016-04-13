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

    public void test_basename_ext() {
        this.should("returns the basename with ext");
        this.assertEqual("quux.html", Path.basename("/foo/bar/baz/asdf/quux.html"));
    }

    public void test_basename_no_ext() {
        this.should("returns the basename with ext that is not found");
        this.assertEqual("quux", Path.basename("/foo/bar/baz/asdf/quux", ".html"));
    }

    public void test_basename_without_ext() {
        this.should("returns the basename without the ext");
        this.assertEqual("quux", Path.basename("/foo/bar/baz/asdf/quux.html", ".html"));
    }

    public void test_dirname_without_ext() {
        this.should("returns the dirname without an ext");
        this.assertEqual("/foo/bar/baz/asdf", Path.dirname("/foo/bar/baz/asdf/quux"));
    }

    public void test_dirname_with_ext() {
        this.should("returns the dirname with an ext");
        this.assertEqual("/foo/bar/baz/asdf", Path.dirname("/foo/bar/baz/asdf/quux.html"));
    }

    public void test_extname_with_ext() {
        this.should("returns the extname with an ext");
        this.assertEqual(".html", Path.extname("index.html"));
    }

    public void test_extname_with_empty_ext() {
        this.should("returns the extname with an empty ext");
        this.assertEqual(".", Path.extname("index."));
    }

    public void test_extname_with_no_ext() {
        this.should("returns the extname with no ext");
        this.assertEqual("", Path.extname("index"));
    }

    public void test_extname_with_start_ext() {
        this.should("returns the extname with no ext");
        this.assertEqual("", Path.extname(".index"));
    }

    public void test_isAbsolute_true() {
        this.should("returns true as the path is absolute");
        this.assertEqual(true, Path.isAbsolute("/foo"));
    }

    public void test_isAbsolute_false() {
        this.should("returns false as the path is NOT absolute");
        this.assertEqual(false, Path.isAbsolute("foo"));
    }

    public void test_isAbsolute_false_relative() {
        this.should("returns false as the path is relative");
        this.assertEqual(false, Path.isAbsolute("./foo"));
    }

    public void test_isAbsolute_false_home() {
        this.should("returns false as the path is home");
        this.assertEqual(false, Path.isAbsolute("~/foo"));
    }

    public void test_isAbsolute_false_empty() {
        this.should("returns false as the path is empty");
        this.assertEqual(false, Path.isAbsolute(""));
    }

    public void test_isAbsolute_false_null() {
        this.should("returns false as the path is null");
        this.assertEqual(false, Path.isAbsolute(null));
    }

    public void test_join() {
        this.should("return an absolute joined path");
        String path = Path.join("/foo", "bar", "baz/asdf", "/quux/", "..");
        this.assertEqual("/foo/bar/baz/asdf", path);
    }

    public void test_normalize_with_first() {
        this.should("return an absolute normalized path with first ..");
        String path = Path.normalize("../foo/bar//baz/asdf/quux");
        this.assertEqual("/foo/bar/baz/asdf/quux", path);
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
