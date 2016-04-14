//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

public class Readable {
    // Jmm Readable wraps https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html

    protected java.io.InputStream stream;

    Readable(String str) {
        try {
            this.stream = new InputStream(str.getBytes("utf8"));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    Readable(String str, String encoding) {
        try {
            this.stream = new InputStream(str.getBytes(encoding));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    Readable(byte[] b) {
        this.stream = new InputStream(b);
    }

    Readable(java.io.InputStream stream) {
        this.stream = stream;
    }

    // Returns an estimate of the number of bytes that can be read (or skipped over) from this 
    // input stream without blocking by the next invocation of a method for this input stream.
    public int available() {
        try {
            return this.stream.available();
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    // Closes this input stream and releases any system resources associated with the stream.
    public boolean close() {
        try {
            this.stream.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // Marks the current position in this input stream.
    public void mark(int readlimit){
        this.stream.mark(readlimit);
    }

    // Repositions this stream to the position at the time the mark method was last called on this input stream.
    public boolean reset() {
        try {
            this.stream.reset();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // 
    public String read(int size, String encoding) {
        return "";
    }

    // 
    public byte[] read(int size) {
        return new byte[size];
    }

    //
    public String readTo(String str, String encoding) {
        return "";
    }

    //
    public byte[] readTo(int code) {
        return new byte[0];
    }

    //
    public byte[] readTo(byte code) {
        return new byte[0];
    }

    // 
    public void pipe(Writable destination) {

    }
}
