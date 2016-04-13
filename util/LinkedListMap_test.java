//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.util;

import github.com.jminusminus.simplebdd.Test;

public class LinkedListMap_test extends Test {

    public static void main(String[] args) {
        LinkedListMap_test test = new LinkedListMap_test();
        test.run();
    }

    public void test_key() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", "1");
        this.should("populate the LinkedListMap and retrieve the key");
        this.assertEqual((String)m.get("key").key(), "key");
    }

    public void test_get() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", "1");
        this.should("populate the map and retrieve the value from the given key");
        this.assertEqual(m.get("key").asString(), "1");
    }

    public void test_put_get() {
        LinkedListMap m = new LinkedListMap();
        String key = "1";
        String val = "one";
        m.put(key, val);
        this.should("put a value in the map and retrieve the value from the given key");
        this.assertEqual(val.equals(m.get(key).asString()), true);
    }

    public void test_get_many() {
        LinkedListMap m = new LinkedListMap();
        m.put("1", "one");
        m.put("2", "two");
        m.put("3", "three");
        this.should("put 3 strings into the map and retrieve the second");
        this.assertEqual("two", m.get("2").asString());
    }

    public void test_length() {
        LinkedListMap m = new LinkedListMap();
        m.put("1", "one");
        m.put("2", "two");
        m.put("3", "three");
        this.should("return a length of 3");
        this.assertEqual(3, m.length());
    }

    public void test_delete_first() {
        LinkedListMap m = new LinkedListMap();
        m.put("1", "one");
        m.put("2", "two");
        m.put("3", "three");
        boolean test = m.delete("1");
        this.should("delete the first item in the map");
        this.assertEqual(2, m.length());
        this.should("return an empty string");
        this.assertEqual(true, m.get("1") == null);
        this.assertEqual(true, test);
    }

    public void test_delete_second() {
        LinkedListMap m = new LinkedListMap();
        m.put("1", "one");
        m.put("2", "two");
        m.put("3", "three");
        boolean test = m.delete("2");
        this.should("delete the second item in the map");
        this.assertEqual(2, m.length());
        this.should("return an empty string");
        this.assertEqual(true, m.get("2") == null);
        this.assertEqual(true, test);
    }

    public void test_delete_last() {
        LinkedListMap m = new LinkedListMap();
        m.put("1", "one");
        m.put("2", "two");
        m.put("3", "three");
        boolean test = m.delete("3");
        this.should("delete the second item in the map");
        this.assertEqual(2, m.length());
        this.should("return an empty string");
        this.assertEqual(true, m.get("3") == null);
        this.assertEqual(true, test);
    }

    public void test_delete_empty() {
        LinkedListMap m = new LinkedListMap();
        m.put("1", "one");
        m.put("2", "two");
        m.put("3", "three");
        boolean test = m.delete("4");
        this.should("delete the second item in the map");
        this.assertEqual(3, m.length());
        this.should("return an empty string");
        this.assertEqual(false, test);
    }

    public void test_value() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", "string");
        this.should("return a object value");
        this.assertEqual("string", (String)m.get("key").value());
    }

    public void test_asString() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", "string");
        this.should("return a string value");
        this.assertEqual("string", m.get("key").asString());
    }

    public void test_asByte() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", (byte)101);
        this.should("return a byte value");
        this.assertEqual((byte)101, m.get("key").asByte());
    }

    public void test_asShort() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", (short)101);
        this.should("return a short value");
        this.assertEqual((short)101, m.get("key").asShort());
    }

    public void test_asInt() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", 101);
        this.should("return an int value");
        this.assertEqual(101, m.get("key").asInt());
    }

    public void test_asLong() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", 9999999999L);
        this.should("return a long value");
        this.assertEqual(9999999999L, m.get("key").asLong());
    }

    public void test_asFloat() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", (float)0.12);
        this.should("return a float value");
        this.assertEqual((float)0.12, m.get("key").asFloat());
    }

    public void test_asDouble() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", 0.12);
        this.should("return a double value");
        this.assertEqual(0.12, m.get("key").asDouble());
    }

    public void test_asBoolean() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", true);
        this.should("return a boolean value");
        this.assertEqual(true, m.get("key").asBoolean());
    }

    public void test_asChar() {
        LinkedListMap m = new LinkedListMap();
        m.put("key", (char)123);
        this.should("return a char value");
        this.assertEqual((char)123, m.get("key").asChar());
    }

    public void test_asMap() {
        LinkedListMap m1 = new LinkedListMap();
        m1.put("1", "one");
        m1.put("2", "two");
        m1.put("3", "three");
        LinkedListMap m2 = new LinkedListMap();
        m2.put("key", m1);
        this.should("return a Map value");
        this.assertEqual("three", m2.get("key").asMap().get("3").asString());
    }

    public void test_next() {
        LinkedListMap m = new LinkedListMap();
        m.put("1", "one");
        m.put("2", "two");
        m.put("3", "three");
        this.should("return the next item");
        this.assertEqual("three", m.get("1").next().next().asString());
    }

    public void test_forEach() {
        LinkedListMap m = new LinkedListMap();
        m.put("1", "one");
        m.put("2", "two");
        m.put("3", "three");
        LinkedListMap m1 = new LinkedListMap();
        m.forEach((item) -> {
            m1.put("a", item.asString());
        });
        this.should("iterate through the LinkedListMap and get the value of the last item");
        this.assertEqual("three", m1.get("a").asString());
    }
}
