//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.util;

public interface Map {
    void put(Object k, Object v);
    MapItem get(Object k);
    Boolean delete(Object k);
    int length();
    void forEach(MapForEach fn);
}
