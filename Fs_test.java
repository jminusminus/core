//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core;

import github.com.jminusminus.simplebdd.Test;

public class Fs_test extends Test {
    public static void main(String[] args) {
        Fs_test test = new Fs_test();
        test.run();
    }

    protected String tmp = "./fixtures/filesystem/tmp.txt";

    public void test_new_FileSystem() {
        this.should("return an instance of Fs");
        Fs fs = new Fs();
        this.assertEqual("github.com.jminusminus.core.Fs", fs.getClass().getName());
    }

    public void test_readdir() {
        this.should("return an array of files");
        String[] files = Fs.readdir("./fixtures/filesystem");
        java.util.Arrays.sort(files);
        this.assertEqual("execute.txt", files[0]);
    }

    public void test_readdir_bad_path() {
        this.should("return null as the directory doesn't exist");
        String[] files = Fs.readdir("/foo/bar");
        this.assertEqual(true, files == null);
    }

    public void test_access_exists_true() {
        this.should("return true as the file exists");
        this.assertEqual(true, Fs.access("./fixtures/filesystem/exists.txt"));
    }

    public void test_access_exists_false() {
        this.should("return false as the file doesn't exists");
        this.assertEqual(false, Fs.access("./fixtures/filesystem/no.txt"));
    }

    public void test_access_execute_true() {
        this.should("return true as the file can execute");
        this.assertEqual(true, Fs.access("./fixtures/filesystem/execute.txt", 0111));
    }

    public void test_access_execute_false() {
        this.should("return false as the file cannot execute");
        this.assertEqual(false, Fs.access("./fixtures/filesystem/read.txt", 0111));
    }

    public void test_access_write_true() {
        this.should("return true as the file can be written");
        this.assertEqual(true, Fs.access("./fixtures/filesystem/write.txt", 0222));
    }

    public void test_access_write_false() {
        this.should("return false as the file cannot be written");
        this.assertEqual(false, Fs.access("./fixtures/filesystem/execute.txt", 0222));
    }

    public void test_access_write_execute_true() {
        this.should("return true as the file can be read and executed");
        this.assertEqual(true, Fs.access("./fixtures/filesystem/write_execute.txt", 0333));
    }

    public void test_access_write_execute_false() {
        this.should("return false as the file cannot be read and executed");
        this.assertEqual(false, Fs.access("./fixtures/filesystem/write.txt", 0333));
    }

    public void test_access_read_true() {
        this.should("return true as the file can be read");
        this.assertEqual(true, Fs.access("./fixtures/filesystem/read.txt", 0444));
    }

    public void test_access_read_false() {
        this.should("return false as the file cannot be read");
        this.assertEqual(false, Fs.access("./fixtures/filesystem/no.txt", 0444));
    }

    public void test_access_read_execute_true() {
        this.should("return true as the file can be read and executed");
        this.assertEqual(true, Fs.access("./fixtures/filesystem/read_execute.txt", 0555));
    }

    public void test_access_read_execute_false() {
        this.should("return false as the file cannot be read and executed");
        this.assertEqual(false, Fs.access("./fixtures/filesystem/read_write.txt", 0555));
    }

    public void test_access_read_write_true() {
        this.should("return true as the file can be read and written");
        this.assertEqual(true, Fs.access("./fixtures/filesystem/read_write.txt", 0666));
    }

    public void test_access_read_write_false() {
        this.should("return false as the file cannot be read and written");
        this.assertEqual(false, Fs.access("./fixtures/filesystem/read_execute.txt", 0666));
    }

    public void test_access_read_write_execute_true() {
        this.should("return true as the file can be read, written and executed");
        this.assertEqual(true, Fs.access("./fixtures/filesystem/read_write_execute.txt", 0777));
    }

    public void test_access_read_write_execute_false() {
        this.should("return false as the file cannot be read, written and executed");
        this.assertEqual(false, Fs.access("./fixtures/filesystem/read_write.txt", 0777));
    }

    public void test_appendFile_new() {
        this.should("create a new file and write the bytes to it");
        this.assertEqual(true, Fs.appendFile(this.tmp, "test".getBytes()));
        Fs.unlink(this.tmp);
    }

    public void test_appendFile_existing() {
        this.should("append to an existing file and write the bytes to it");
        Fs.appendFile(this.tmp, "test".getBytes());
        this.assertEqual(true, Fs.appendFile(this.tmp, "test".getBytes()));
        Fs.unlink(this.tmp);
    }

    public void test_unlink() {
        this.should("create a file and then unlink it");
        Fs.appendFile(this.tmp, "test".getBytes());
        this.assertEqual(true, Fs.unlink(this.tmp));
    }

    public void test_unlink_error() {
        this.should("create a file and then unlink it");
        this.assertEqual(false, Fs.unlink(this.tmp));
    }
}
