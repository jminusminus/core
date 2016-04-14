//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

public class Writable extends java.io.OutputStream {
    // Jmm Writable extends https://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html

    // ### public void close()
    // Closes this output stream and releases any system resources associated with this stream.
    //
    // ### public void flush()
    // Flushes this output stream and forces any buffered output bytes to be written out.
    //
    // ### public void write(byte[] b)
    // Writes b.length bytes from the specified byte array to this output stream.
    // 
    // ### public void write(byte[] b, int off, int len)
    // Writes len bytes from the specified byte array starting at offset off to this output stream.
    //
    // Writes the specified byte to this output stream.
    public void write(int b) {
        try {
            this.write(new byte[]{(byte)b});
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
