//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

public class Writable extends java.io.OutputStream {
    // Jmm Writable https://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html

    public void write(int b) {
        try {
            this.write(new byte[]{(byte)b});
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
