//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core;

import github.com.jminusminus.simplebdd.Test;

public class Arrays_string_test extends Test {
    public static void main(String[] args) {
        Arrays_string_test test = new Arrays_string_test();
        test.run();
    }

    public void test_new_Arrays() {
        this.should("return an instance of Arrays");
        Arrays a = new Arrays();
        this.assertEqual("github.com.jminusminus.core.Arrays", a.getClass().getName());
    }

    public void test_append() {
        this.should("return an array with the new string");
        String[] a = new String[]{"a", "b"};
        String[] b = Arrays.append(a, "c");
        this.assertEqual("a", b[0]);
        this.assertEqual("b", b[1]);
        this.assertEqual("c", b[2]);
        this.assertEqual(3, b.length);
    }

    public void test_append_array() {
        this.should("return an array with the new string array");
        String[] a = new String[]{"a", "b"};
        String[] b = new String[]{"c", "d"};
        String[] c = Arrays.append(a, b);
        this.assertEqual("a", c[0]);
        this.assertEqual("b", c[1]);
        this.assertEqual("c", c[2]);
        this.assertEqual("d", c[3]);
        this.assertEqual(4, c.length);
    }

    public void test_slice() {
        this.should("return an array of the first three srings");
        String[] a = new String[]{"a", "b", "c", "d"};
        String[] b = Arrays.slice(a, 3);
        this.assertEqual("a", b[0]);
        this.assertEqual("b", b[1]);
        this.assertEqual("c", b[2]);
        this.assertEqual(3, b.length);
    }

    public void test_slice_negative() {
        this.should("return an array of strings up to the the last -1");
        String[] a = new String[]{"a", "b", "c", "d"};
        String[] b = Arrays.slice(a, -1);
        this.assertEqual("a", b[0]);
        this.assertEqual("b", b[1]);
        this.assertEqual("c", b[2]);
        this.assertEqual(3, b.length);
    }

    public void test_slice_with_start() {
        this.should("return an array of the middle two srings");
        String[] a = new String[]{"a", "b", "c", "d"};
        String[] b = Arrays.slice(a, 1, 3);
        this.assertEqual("b", b[0]);
        this.assertEqual("c", b[1]);
        this.assertEqual(2, b.length);
    }

    public void test_slice_with_start_negitve_end() {
        this.should("return an array of strings from the second item to the last -1");
        String[] a = new String[]{"a", "b", "c", "d"};
        String[] b = Arrays.slice(a, 1, -1);
        this.assertEqual("b", b[0]);
        this.assertEqual("c", b[1]);
        this.assertEqual(2, b.length);
    }
}
