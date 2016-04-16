//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

// ## Stability: 0 - Unstable
package github.com.jminusminus.core;

public class Arrays {

    // Appends to the given array the given string.
    //
    // Exmaple:
    //
    // ```java
    // String[] a = new String[]{"a", "b"};
    // 
    // String[] a = Arrays.append(a, "c");
    // // returns a,b,c
    // ```
    public static String[] append(String[] a, String b) {
        int len = a.length;
        String[] c = new String[len + 1];
        System.arraycopy(a, 0, c, 0, a.length);
        c[len] = b;
        return c;
    }

    // Appends to the given array the given array.
    //
    // Exmaple:
    //
    // ```java
    // String[] a = new String[]{"a", "b"};
    // String[] a = new String[]{"c", "d"};
    // 
    // String[] a = Arrays.append(a, b);
    // // returns a,b,c,d
    // ```
    public static String[] append(String[] a, String[] b) {
        int len = a.length + b.length;
        String[] c = new String[len];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    // Slice the given array upto and including `end`.
    //
    // Exmaple:
    //
    // ```java
    // String[] a = new String[]{"a", "b", "c", "d"};
    // 
    // String[] s = Arrays.slice(a, 3);
    // // returns a,b,c
    //
    // String[] s = Arrays.slice(a, -2);
    // // returns a,b
    // ```
    public static String[] slice(String[] a, int end) {
        if (end < 0) {
            end = a.length + end;
        }
        return Arrays.slice(a, 0, end);
    }

    // Slice the given array after `start` upto and including `end`.
    //
    // Exmaple:
    //
    // ```java
    // String[] a = new String[]{"a", "b", "c", "d"};
    // 
    // String[] s = Arrays.slice(a, 0, 4);
    // // a,b,c,d
    //
    // String[] s = Arrays.slice(a, 1, 3);
    // // b,c
    //
    // String[] s = Arrays.slice(a, 1, -1);
    // // b,c
    // ```
    public static String[] slice(String[] a, int start, int end) {
        int len = a.length;
        if (start > len) {
            return new String[0];
        }
        if (start < 0) {
            start = 0;
        }
        if (end > len) {
            end = len - 1;
        }
        if (end < 0) {
            end = a.length + end;
        }
        String[] b = new String[end - start];
        System.arraycopy(a, start, b, 0, b.length);
        return b;
    }
}
