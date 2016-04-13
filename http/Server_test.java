//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.http;

import github.com.jminusminus.simplebdd.Test;

public class Server_test extends Test {
    public static void main(String[] args) {
        Server_test test = new Server_test();
        test.run();
    }

    public void test_new_Server() {
        this.should("return an instance of Server");
        Server server = Server.createServer((req, res) -> {
            res.end();
        });
        this.assertEqual("github.com.jminusminus.core.http.Server", server.getClass().getName());
    }

    public void test_listen_close() {
        this.should("start a server and then close it");
        Server server = Server.createServer((req, res) -> {
            res.end();
        });
        server.listen(3333);
        server.close();
        this.assertEqual(true, server.isClosed());
    }

    public void test_listen_close_close() {
        this.should("start a server and then close it, then close it again");
        Server server = Server.createServer((req, res) -> {
            res.end();
        });
        server.listen(3333);
        server.close();
        server.close();
        this.assertEqual(true, server.isClosed());
    }
}
