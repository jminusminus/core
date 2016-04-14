//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

public class Writable extends java.io.OutputStream {
    // Jmm Writable java.io.OutputStream.

    public void write(int b) {
        this.write(new byte[]{(byte)b});
    }
}
