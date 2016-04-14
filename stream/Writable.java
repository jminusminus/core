//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

public class Writable {
    // Jmm Writable wraps https://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html

    protected java.io.OutputStream stream;

    Writable(java.io.OutputStream stream) {
        this.stream = stream;
    }

    // Forces buffering of all writes.
    //
    // Buffered data will be flushed either at stream.uncork() or at stream.end() call.
    public void cork() {

    }

    // Flush all data, buffered since stream.cork() call.
    public void uncork() {
        
    }

    // Flushes this output stream and forces any buffered output bytes to be written out.
    public boolean flush() {
        try {
            this.stream.flush();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // Writes the specified byte to this output stream.
    public boolean write(int b) {
        return this.write(new byte[]{(byte)b});
    }

    //
    public boolean write(String str) {
        return this.write(str, "utf8");
    }

    //
    public boolean write(String str, String encoding) {
        try {
            return this.write(str.getBytes(encoding));
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // Writes b.length bytes from the specified byte array to this output stream.
    public boolean write(byte[] b) {
        try {
            this.stream.write(b);
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
    public boolean end() {
        return this.end(new byte[0]);
    }

    //
    public boolean end(byte[] b) {
        try {
            this.write(b);
            this.stream.flush();
            this.stream.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
