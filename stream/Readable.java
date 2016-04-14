//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.stream;

public class Readable extends java.io.InputStream {
    // Jmm Readable extends https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html

    // ### public int available()
    // Returns an estimate of the number of bytes that can be read (or skipped over) from this 
    // input stream without blocking by the next invocation of a method for this input stream.
    // 
    // ### public void close()
    // Closes this input stream and releases any system resources associated with the stream.
    //
    // ### public void mark(int readlimit)
    // Marks the current position in this input stream.
    //
    // ### public int read(byte[] b)
    // Reads some number of bytes from the input stream and stores them into the byte array b.
    //
    // ### reset()
    // Repositions this stream to the position at the time the mark method was last called on this input stream.
    //
    // ### skip(long n)
    // Skips over and discards n bytes of data from this input stream.
    //
    // ### read(byte[] b, int off, int len)
    // Reads up to len bytes of data from the input stream into the byte array b.
    //
    // Reads the next byte of data from the input stream.
    public int read() {
        byte[] b = new byte[1];
        try {
            this.read(b);
        } catch (Exception e) {
            System.out.println(e);
        }
        return b[0];
    }

    // 
    public byte[] read(int size) {
        return new byte[size];
    }

    // 
    public void pipe(Writable destination) {

    }

    //
    public void setEncoding(String encoding) {

    }
}
