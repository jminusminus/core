//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

public class OutputStream extends java.io.OutputStream {
    // Jmm Writable extends https://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html

    // Helper for unit testing.
    // 
    // When OutputStream.throwException is set to true any OutputStream.write() call will throw a runtime exception.
    public boolean throwException = false;

    // Holds the bytes written to the OutputStream.
    public byte[] buffer = new byte[0];

    public void write(byte[] b, int off, int len) {
        this.write(this.slice(b, off, off + len - 1));
    }

    public void write(byte[] b) {
        if (this.throwException) {
            throw new RuntimeException("java.io.IOException");
        }
        for (int i = 0; i < b.length; i++) {
            this.buffer = this.append(this.buffer, b[i]);
        }
    }

    public void write(int b) {
        this.write(new byte[]{(byte)b});
    }

    // Local implementation of slice so this class has no dependencies.
    protected byte[] slice(byte[] a, int start, int end) {
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

    // Local implementation of append so this class has no dependencies.
    protected byte[] append(byte[] a, byte b) {
        int len = a.length;
        byte[] c = new byte[len + 1];
        System.arraycopy(a, 0, c, 0, a.length);
        c[len] = b;
        return c;
    }
}
