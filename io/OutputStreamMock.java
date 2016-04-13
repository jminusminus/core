//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.io;

import github.com.jminusminus.core.util.ByteArray;

public class OutputStreamMock extends java.io.OutputStream {

    public boolean throwException = false;
    public byte[] buffer = new byte[0];

    public void write(byte[] b, int off, int len) {
        this.write(ByteArray.slice(b, off, off + len - 1));
    }

    public void write(byte[] b) {
        if (this.throwException) {
            throw new RuntimeException("java.io.IOException");
        }
        for (int i = 0; i < b.length; i++) {
            this.buffer = ByteArray.append(this.buffer, b[i]);
        }
    }

    public void write(int b) {
        this.write(new byte[]{(byte)b});
    }
}
