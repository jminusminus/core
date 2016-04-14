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
        this.write(ByteArray.slice(b, off, off + len - 1));
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

    // Local implementation of append so this class has no dependencies.
    protected static byte[] append(byte[] a, byte[] b) {
        int len = a.length + b.length;
        byte[] c = new byte[len];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }
}
