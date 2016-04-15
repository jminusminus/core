//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

// ## Stability: 0 - Unstable
package github.com.jminusminus.core;

public class Arrays {

    protected static String[] append(String[] a, String b) {
        int len = a.length;
        String[] c = new String[len + 1];
        System.arraycopy(a, 0, c, 0, a.length);
        c[len] = b;
        return c;
    }

    protected static String[] append(String[] a, String[] b) {
        int len = a.length + b.length;
        String[] c = new String[len];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
}
