//
// Copyright 2016, Yahoo Inc.
// Copyrights licensed under the New BSD License.
// See the accompanying LICENSE file for terms.
//

package github.com.jminusminus.core.util;

import github.com.jminusminus.simplebdd.Test;

public class ByteArray_test extends Test {
    public static void main(String[] args) {
        ByteArray_test test = new ByteArray_test();
        test.run();
    }

    protected byte[] simpleArray = "abcdefgABCDEFG".getBytes();

    public void testNewInstance() {
        this.should("return an instance of ByteArray");
        ByteArray b = new ByteArray();
        this.assertEqual(true, true);
    }

    public void testIndexOf() {
        this.should("return the index of a");
        this.assertEqual(ByteArray.indexOf(this.simpleArray, this.getByte("a")), 0);
    }

    public void testIndexOfFirst() {
        this.should("return the index of A");
        this.assertEqual(ByteArray.indexOf(this.simpleArray, this.getByte("A")), 7);
    }

    public void testIndexOfLast() {
        this.should("return the index of G");
        this.assertEqual(ByteArray.indexOf(this.simpleArray, this.getByte("G")), 13);
    }

    public void testIndexOfNotThere() {
        this.should("return the index of X");
        this.assertEqual(ByteArray.indexOf(this.simpleArray, this.getByte("X")), -1);
    }

    public void testAppendByte() {
        this.should("return the a new array with the appended byte");
        byte[] test = ByteArray.append(this.simpleArray, this.getByte("X"));
        this.assertEqual(this.getByte("X"), test[test.length - 1]);
    }

    public void testAppendByteArray() {
        this.should("return the a new array with the appended array");
        byte[] test = ByteArray.append(this.simpleArray, this.simpleArray);
        this.assertEqual(this.simpleArray.length * 2, test.length);
    }

    public void testSlice() {
        this.should("return a new array with the length matching the slice to index.");
        byte[] test = ByteArray.slice(this.simpleArray, 6);
        this.assertEqual(this.getByte("a"), test[0]);
        this.assertEqual(this.getByte("g"), test[6]);
        this.assertEqual(7, test.length);
    }
    public void testSliceMiddle() {
        this.should("return a new array created from positive to positive.");
        byte[] test = ByteArray.slice(this.simpleArray, 7, 8);
        this.assertEqual(this.getByte("A"), test[0]);
        this.assertEqual(this.getByte("B"), test[1]);
        this.assertEqual(2, test.length);
    }

    public void testSliceBeforeStart() {
        this.should("return a new array created from negative to positive.");
        byte[] test = ByteArray.slice(this.simpleArray, -3, 4);
        this.assertEqual(this.getByte("a"), test[0]);
        this.assertEqual(this.getByte("d"), test[3]);
        this.assertEqual(5, test.length);
    }

    public void testSliceAfterEnd() {
        this.should("return a new array created from positive to too large.");
        byte[] test = ByteArray.slice(this.simpleArray, 7, 50);
        this.assertEqual(this.getByte("A"), test[0]);
        this.assertEqual(this.getByte("G"), test[6]);
        this.assertEqual(7, test.length);
    }

    public void testSliceJustOne() {
        this.should("return a new array created from too large to too large.");
        byte[] test = ByteArray.slice(this.simpleArray, 0, 0);
        this.assertEqual(this.getByte("a"), test[0]);
        this.assertEqual(1, test.length);
    }

    public void testSliceTooSmall() {
        this.should("return a new array created from too small to too small.");
        byte[] test = ByteArray.slice(this.simpleArray, -10, -5);
        this.assertEqual(0, test.length);
    }

    public void testSliceTooLarge() {
        this.should("return a new array created from too large to too large.");
        byte[] test = ByteArray.slice(this.simpleArray, 50, 55);
        this.assertEqual(0, test.length);
    }

    public void testSliceStartBiggerThanEnd() {
        this.should("return a new array created from too large to too large.");
        byte[] test = ByteArray.slice(this.simpleArray, 50, -1);
        this.assertEqual(0, test.length);
    }

    public void testSliceTooSmallToTooLarge() {
        this.should("return a new array created from too small to too large.");
        byte[] test = ByteArray.slice(this.simpleArray, -55, 55);
        this.assertEqual(this.getByte("a"), test[0]);
        this.assertEqual(this.getByte("G"), test[13]);
        this.assertEqual(14, test.length);
    }

    public void testCopy() {
        this.should("populate the given array from the source array");
        byte[] test = new byte[5];
        int count = ByteArray.copy(this.simpleArray, 0, test);
        this.assertEqual(this.getByte("a"), test[0]);
        this.assertEqual(this.getByte("e"), test[4]);
        this.assertEqual(5, test.length);
        this.assertEqual(5, count);
    }

    public void testCopyStart() {
        this.should("populate the given array from the source array stating at the given index");
        byte[] test = new byte[5];
        int count = ByteArray.copy(this.simpleArray, 7, test);
        this.assertEqual(this.getByte("A"), test[0]);
        this.assertEqual(this.getByte("E"), test[4]);
        this.assertEqual(5, test.length);
        this.assertEqual(5, count);
    }

    public void testCopyTooLong() {
        this.should("populate the given array from the source array stopping when the source array all copied");
        byte[] test = new byte[50];
        int count = ByteArray.copy(this.simpleArray, 7, test);
        this.assertEqual(this.getByte("A"), test[0]);
        this.assertEqual(this.getByte("G"), test[6]);
        this.assertEqual((byte)0, test[7]);
        this.assertEqual(7, count);
    }

    public void testCopyNegativeStart() {
        this.should("not populate the array as the start is negative");
        byte[] test = new byte[5];
        int count = ByteArray.copy(this.simpleArray, -10, test);
        this.assertEqual(-1, count);
    }

    public void testCopyTooBigStart() {
        this.should("not populate the array as the start is too large");
        byte[] test = new byte[5];
        int count = ByteArray.copy(this.simpleArray, 100, test);
        this.assertEqual(-1, count);
    }

    protected byte getByte(String s) {
    	return (byte)s.charAt(0);
    }
}
