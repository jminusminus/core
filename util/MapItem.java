//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.util;

public interface MapItem {
    Object key();
    Object value();
    void set(Object v);
    byte asByte();
    short asShort();
    int asInt();
    long asLong();
    float asFloat();
    double asDouble();
    boolean asBoolean();
    char asChar();
    String asString();
    Map asMap();
}
