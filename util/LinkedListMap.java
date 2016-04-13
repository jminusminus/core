//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.util;

public class LinkedListMap implements Map {

    protected LinkedListMapItem first;
    protected LinkedListMapItem last;
    protected int length = 0;

    public void put(Object k, Object v) {
        LinkedListMapItem item = this.get(k);
        if (item != null) {
            item.set(v);
        }
        item = new LinkedListMapItem(k);
        item.set(v);
        if (this.first == null) {
            this.first = item;
            this.last = item;
        } else {
            item.prev(this.last);
            this.last.next(item);
            this.last = item;
        }
        this.length++;
    }

    public LinkedListMapItem get(Object k) {
        LinkedListMapItem item = this.first;
        while (item != null && !k.equals(item.key())) {
            item = item.next();
        }
        return item;
    }

    public Boolean delete(Object k) {
        LinkedListMapItem item = this.get(k);
        if (item == null) {
            return false;
        }
        if (item.prev() != null) {
            item.prev().next(item.next());
        } else {
            this.first = item.next();
        }
        if (item.next() != null) {
            item.next().prev(item.prev());
        } else {
            this.last = item.prev();
        }
        this.length--;
        return true;
    }

    public int length() {
        return this.length;
    }

    public void forEach(MapForEach fn) {
        LinkedListMapItem item = this.first;
        while (item != null) {
            fn.call(item);
            item = item.next();
        }
    }
}
