//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

public class InputStream extends java.io.InputStream {
    // Jmm Readable extends https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html

    // Helper for unit testing.
    // 
    // When InputStream.throwException is set to true any InputStream.read() call will throw a runtime exception.
    public boolean throwException = false;

    protected byte[] buffer;
    protected int position = 0;

    public InputStream(byte[] b) {
        this.buffer = b;
    }

    public int read(byte[] b) {
        if (this.throwException) {
            throw new RuntimeException("java.io.IOException");
        }
        int copied = this.copy(this.buffer, this.position, b);
        this.position = this.position + copied;
        return copied;
    }

    public int read() {
        byte[] b = new byte[1];
        try {
            this.read(b);
        } catch (Exception e) {
            System.out.println(e);
        }
        return b[0];
    }

    // Local implementation of copy so this class has no dependencies.
    protected static int copy(byte[] a, int start, byte[] b) {
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
