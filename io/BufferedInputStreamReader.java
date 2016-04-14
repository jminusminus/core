//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.io;

public class BufferedInputStreamReader extends java.io.InputStream {

    protected java.io.InputStream stream;
    protected byte[] buffer;
    protected int start;
    protected int cursor;
    protected int end;
    protected int marker = -1;
    protected int bufferSize = 10000;

    public BufferedInputStreamReader(String stream) {
        this.stream = new InputStreamMock(stream.getBytes());
    }

    public BufferedInputStreamReader(byte[] stream) {
        this.stream = new InputStreamMock(stream);
    }

    public BufferedInputStreamReader(java.io.InputStream stream) {
        this.stream = stream;
    }

    public BufferedInputStreamReader(java.io.InputStream stream, int bufferSize) {
        this.stream = stream;
        this.bufferSize = bufferSize;
    }

    public int read() {
        if (this.hasMore() == false) {
            return 0;
        }
        int b = this.buffer[this.cursor];
        this.cursor++;
        this.start = this.cursor;
        return b;
    }

    public boolean hasMore() {
        int left = this.end - this.cursor;
        if (left <= 0) {
            return this.fillBuffer() > 0;
        }
        return true;
    }

    public byte[] readTo(byte code) {
        if (this.hasMore() == false) {
            return new byte[0];
        }
        while (true) {
            if (this.buffer[this.cursor] == code || this.buffer[this.cursor] == 0) {
                int extraBytes = 1;
                if (this.buffer[this.cursor] == 0) {
                    extraBytes = 0;
                }
                int len = this.cursor - this.start + extraBytes;
                byte[] read = new byte[len];
                System.arraycopy(this.buffer, this.start, read, 0, len);
                this.cursor++;
                this.start = this.cursor;
                return read;
            }
            this.cursor++;
        }
    }

    public boolean markSupported() {
        return true;
    }

    public void mark(int readlimit) {
        // TODO: Implement read limit.
        this.marker = this.start;
    }

    public void reset() {
        if (this.marker > -1) {
            this.start = this.marker;
            this.cursor = this.marker;
            this.marker = -1;
        }
    }

    protected int fillBuffer() {
        this.buffer = new byte[this.bufferSize];
        this.start = 0;
        this.cursor = 0;
        this.end = 0;
        try {
            this.end = this.stream.read(this.buffer);
        } catch (Exception e) {
            System.out.println("socket inputStream read error");
            System.out.println(e);
        }
        return this.end;
    }
}
