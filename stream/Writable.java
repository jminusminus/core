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

    //
    public boolean write(String str, String encoding) {
        try {
            this.write(str.getBytes(encoding));
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //
    public boolean end(String str, String encoding) {
        try {
            return this.end(str.getBytes(encoding));
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //
    public boolean end(byte[] b) {
        try {
            this.flush();
            this.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // Forces buffering of all writes.
    //
    // Buffered data will be flushed either at stream.uncork() or at stream.end() call.
    public void cork() {

    }

    // Flush all data, buffered since stream.cork() call.
    public void uncork() {
        
    }
}
