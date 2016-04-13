//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.util;

public class LinkedListMapItem implements MapItem {

    protected Object key;
    protected Object val;
    protected LinkedListMapItem prev;
    protected LinkedListMapItem next;

    public LinkedListMapItem (Object k) {
        this.key = k;
    }

    public Object key() {
        return this.key;
    }

    public Object value() {
        return this.val;
    }

    public void set(Object v) {
        this.val = v;
    }
    
    public byte asByte() {
        return (byte) this.val;
    }

    public short asShort() {
        return (short) this.val;
    }

    public int asInt() {
        return (int) this.val;
    }

    public long asLong() {
        return (long) this.val;
    }

    public float asFloat() {
        return (float) this.val;
    }

    public double asDouble() {
        return (double) this.val;
    }

    public boolean asBoolean() {
        return (boolean) this.val;
    }

    public char asChar() {
        return (char) this.val;
    }

    public String asString() {
        return this.val.toString();
    }

    public Map asMap() {
        return (Map) this.val;
    }

    public void prev(LinkedListMapItem p) {
        this.prev = p;
    }

    public LinkedListMapItem prev() {
        return this.prev;
    }

    public void next(LinkedListMapItem n) {
        this.next = n;
    }

    public LinkedListMapItem next() {
        return this.next;
    }
}
