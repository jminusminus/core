//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.util;

public class ByteArray {

    public static int indexOf(byte[] a, byte b) {
        int i = -1;
        while (++i < a.length) {
            if (a[i] == b) {
                return i;
            }
        }
        return -1;
    }

    public static byte[] append(byte[] a, byte b) {
        int len = a.length;
        byte[] c = new byte[len + 1];
        System.arraycopy(a, 0, c, 0, a.length);
        c[len] = b;
        return c;
    }

    public static byte[] append(byte[] a, byte[] b) {
        int len = a.length + b.length;
        byte[] c = new byte[len];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static byte[] slice(byte[] a, int end) {
        return ByteArray.slice(a, 0, end);
    }

    public static byte[] slice(byte[] a, int start, int end) {
        int len = a.length;
        if (start > len || end < 0 || start > end) {
            return new byte[0];
        }
        if (start < 0) {
            start = 0;
        }
        if (end > len) {
            end = len - 1;
        }
        byte[] b = new byte[end - start + 1];
        System.arraycopy(a, start, b, 0, b.length);
        return b;
    }

    public static int copy(byte[] a, int start, byte[] b) {
        int aLen = a.length;
        if (start < 0 || start > aLen) {
            return -1;
        }
        int bLen = b.length;
        if (bLen > aLen - start) {
            bLen = aLen - start;
        }
        System.arraycopy(a, start, b, 0, bLen);
        return bLen;
    }
}
