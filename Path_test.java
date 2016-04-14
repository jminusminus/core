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
        this.should("returns the extname with a start ext");
        this.assertEqual("", Path.extname(".index"));
    }

    public void test_toString_full() {
        this.should("returns a string path from a full Path object");
        Path path = new Path();
        path.root = "/";
        path.dir = "/home/user/dir";
        path.base = "file.txt";
        path.ext = ".txt";
        path.name = "file";
        this.assertEqual("/home/user/dir/file.txt", path.toString());
    }

    public void test_toString_dir_base() {
        this.should("returns a string path from the dir and path");
        Path path = new Path();
        path.root = "";
        path.dir = "/home/user/dir";
        path.base = "file.txt";
        path.ext = "";
        path.name = "";
        this.assertEqual("/home/user/dir/file.txt", path.toString());
    }

    public void test_toString_root_base() {
        this.should("returns a string path from the root and base");
        Path path = new Path();
        path.root = "/";
        path.dir = "";
        path.base = "file.txt";
        path.ext = "";
        path.name = "";
        this.assertEqual("/file.txt", path.toString());
    }

    public void test_toString_base() {
        this.should("returns a string path from the base");
        Path path = new Path();
        path.root = "";
        path.dir = "";
        path.base = "file.txt";
        path.ext = "";
        path.name = "";
        this.assertEqual("file.txt", path.toString());
    }

    public void test_toString_name_ext() {
        this.should("returns a string path from the anme and ext");
        Path path = new Path();
        path.root = "";
        path.dir = "";
        path.base = "";
        path.ext = ".txt";
        path.name = "file";
        this.assertEqual("file.txt", path.toString());
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

    public void test_join_empty() {
        this.should("return the relative directory marker");
        String path = Path.join("", "", "");
        this.assertEqual(".", path);
    }

    public void test_join_relative() {
        this.should("return the relative directory path");
        String path = Path.join("./foo", "bar");
        this.assertEqual("./foo/bar", path);
    }

    public void test_join_relative_up_one() {
        this.should("return the relative directory path");
        String path = Path.join("../foo", "bar");
        this.assertEqual("./bar", path);
    }

    public void test_normalize_with_first() {
        this.should("return an absolute normalized path with first ..");
        String path = Path.normalize("../foo/bar//baz/asdf/quux");
        this.assertEqual("./bar/baz/asdf/quux", path);
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

    public void test_parse() {
        this.should("returns a Path object from the string");
        Path path = Path.parse("/home/user/dir/file.txt");
        this.assertEqual("/", path.root);
        this.assertEqual("/home/user/dir", path.dir);
        this.assertEqual("file.txt", path.base);
        this.assertEqual(".txt", path.ext);
        this.assertEqual("file", path.name);
    }

    public void test_relative() {
        this.should("return a relative path");
        String path = Path.relative("/data/orandea/test/aaa", "/data/orandea/impl/bbb");
        this.assertEqual("../../impl/bbb", path);
    }

    public void test_relative_abef() {
        this.should("return a relative path to abef");
        String path = Path.relative("/a/b/c/d", "/a/b/e/f");
        this.assertEqual("../../e/f", path);
    }

    public void test_relative_efg() {
        this.should("return a relative path to efg");
        String path = Path.relative("/a/b/c", "/e/f/g");
        this.assertEqual("../../../e/f/g", path);
    }

    public void test_relative_match() {
        this.should("return an empty string as the paths match");
        String path = Path.relative("/data/orandea", "/data/orandea");
        this.assertEqual("", path);
    }

    public void test_relative_empty() {
        this.should("return an empty string as the paths were empty");
        String path = Path.relative("", "");
        this.assertEqual("", path);
    }

    public void test_relative_cwd_dev() {
        this.should("return a path from cwd to dev null");
        String path = Path.relative("", "/dev/null");
        this.assertEqual(true, path.contains("../../dev/null"));
    }

    public void test_relative_dev_cwd() {
        this.should("return a path from dev null to cwd");
        String path = Path.relative("/dev/null", "");
        this.assertEqual("../.." + System.getProperty("user.dir"), path);
    }

    public void test_resolve() {
        this.should("returns a resolved relative path");
        String from = "/data/orandea/test/aaa";
        String to = "/data/orandea/impl/bbb";
        this.assertEqual(Path.resolve(from, Path.relative(from, to)), Path.resolve(to));
    }

    public void test_resolve_relative() {
        this.should("returns the relative path for the current directory");
        this.assertEqual("../../etc", Path.resolve("./foo/bar", "/etc"));
    }

    public void test_resolve_absolute_second() {
        this.should("returns a relative path gogin to root and down");
        this.assertEqual("../../tmp/file", Path.resolve("/foo/bar", "/tmp/file/"));
    }

    public void test_resolve_cwd() {
        this.should("returns a relative path from the cwd");
        this.assertEqual(System.getProperty("user.dir") + "/wwwroot/static_files/gif/image.gif", Path.resolve("wwwroot", "static_files/png/", "../gif/image.gif"));
    }

    public void test_resolve_cwd_default() {
        this.should("returns an absolute path from the cwd ./");
        this.assertEqual(System.getProperty("user.dir") + "/wwwroot/static_files/gif/image.gif", Path.resolve("./wwwroot", "static_files/png/", "../gif/image.gif"));
    }
}
