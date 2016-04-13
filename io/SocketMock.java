//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.io;

public class SocketMock extends java.net.Socket {

    public boolean throwInputException = false;
    public boolean throwOutputException = false;
    public boolean throwCloseException = false;
    protected InputStreamMock i;
    protected OutputStreamMock o;

    public SocketMock(byte[] b) {
        this.i = new InputStreamMock(b);
        this.o = new OutputStreamMock();
    }

    public InputStreamMock getInputStream() {
        if (this.throwInputException) {
            throw new RuntimeException("java.io.IOException");
        }
        return this.i;
    }

    public OutputStreamMock getOutputStream() {
        if (this.throwOutputException) {
            throw new RuntimeException("java.io.IOException");
        }
        return this.o;
    }

    public void close() {
        if (this.throwCloseException) {
            throw new RuntimeException("java.io.IOException");
        }
    }
}
