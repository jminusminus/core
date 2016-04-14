//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

import github.com.jminusminus.core.util.ByteArray;

public class InputStream extends java.io.InputStream {

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
        int copied = ByteArray.copy(this.buffer, this.position, b);
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
}
