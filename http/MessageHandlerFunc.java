//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.http;

public interface MessageHandlerFunc {
    void call(IncomingMessage req, OutgoingMessage res);
}
