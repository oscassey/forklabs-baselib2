/*
 * Copyright (C)  2006  Forklabs Daniel Léonard
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package ca.forklabs.baselib2.util;

/**
 * Class {@code Bits} contains methods that acts on bits and primitive data
 * types.
 */
public class Bits {

//---------------------------
// Class variables
//---------------------------

    /** The number of octets (8-bit byte) in a {@code byte}. */
    public static final int NUM_OCTETS_IN_BYTE = 1;
    /** The number of octets (8-bit byte) in a {@code short}. */
    public static final int NUM_OCTETS_IN_SHORT = 2;
    /** The number of octets (8-bit byte) in a {@code char}. */
    public static final int NUM_OCTETS_IN_CHAR = 2;
    /** The number of octets (8-bit byte) in a {@code int}. */
    public static final int NUM_OCTETS_IN_INT = 4;
    /** The number of octets (8-bit byte) in a {@code long}. */
    public static final int NUM_OCTETS_IN_LONG = 8;
    /** The number of octets (8-bit byte) in a {@code float}. */
    public static final int NUM_OCTETS_IN_FLOAT = 4;
    /** The number of octets (8-bit byte) in a {@code double}. */
    public static final int NUM_OCTETS_IN_DOUBLE = 8;


//---------------------------
// Constructor
//---------------------------

    /**
     * Let no one instantiate this class.
     */
    private Bits() {
        // nothing
    }

//---------------------------
// Class methods
//---------------------------

    /**
     * Performs a left rotation of the bits.
     *
     * @param i     the {@code int} to rotate.
     * @param shift the distance to rotate.
     * @return the result of the rotation.
     */
    public static int rotateLeft(int i, int shift) {
        int rotation = (i << shift) | (i >>> (32 - shift));
        return rotation;
    }

    /**
     * Performs a right rotation of the bits.
     *
     * @param i     the {@code int} to rotate.
     * @param shift the distance to rotate.
     * @return the result of the rotation.
     */
    public static int rotateRight(int i, int shift) {
        int rotation = (i >>> shift) | (i << (32 - shift));
        return rotation;
    }

    /**
     * Performs a left rotation of the bits.
     *
     * @param l     the {@code long} to rotate.
     * @param shift the distance to rotate.
     * @return the result of the rotation.
     */
    public static long rotateLeft(long l, int shift) {
        long rotation = (l << shift) | (l >>> (64 - shift));
        return rotation;
    }

    /**
     * Performs a right rotation of the bits.
     *
     * @param l     the {@code long} to rotate.
     * @param shift the distance to rotate.
     * @return the result of the rotation.
     */
    public static long rotateRight(long l, int shift) {
        long rotation = (l >>> shift) | (l << (64 - shift));
        return rotation;
    }

    /**
     * Promotes a {@code byte} to an {@code unsigned byte} as an {@code int}.
     * Promoted {@code byte}s without check can give negative {@code int}.
     *
     * @param b the {@code byte} to promote.
     * @return the value unsigned.
     */
    public static int unsignedByte(byte b) {
        int i = b & 0xff;
        return i;
    }

    /**
     * Promotes a {@code short} to an {@code unsigned short} as an {@code int}.
     * Promoted {@code short}s without check can give negative {@code int}.
     *
     * @param s the {@code short} to promote.
     * @return the value unsigned.
     */
    public static int unsignedShort(short s) {
        int i = s & 0xffff;
        return i;
    }

    /**
     * Promotes an {@code int} to an {@code unsigned int} as an {@code long}.
     * Promoted {@code int}s without check can give negative {@code long}.
     *
     * @param i the {@code int} to promote.
     * @return the value unsigned.
     */
    public static long unsignedInt(int i) {
        long l = i & 0xffffffffL;
        return l;
    }

    /**
     * Returns a {@code short} assembled from two bytes, big-endian.
     *
     * @param b1 the highest byte.
     * @param b2 the lowest byte.
     * @return a {@code short} made from two bytes.
     */
    public static short makeShort(byte b1, byte b2) {
        int ub1 = unsignedByte(b1);
        int ub2 = unsignedByte(b2);
        short s = (short) ((ub1 << 8) | ub2);
        return s;
    }

    /**
     * Returns a {@code short} as a sequence of two bytes, big-endian.
     *
     * @param s the {@code short} to break.
     * @return the two bytes.
     * @see #breakShort(short, byte[], int)
     */
    public static byte[] breakShort(short s) {
        byte[] b = breakShort(s, null, 0);
        return b;
    }

    /**
     * Returns a {@code short} as a sequence of two bytes, big-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param s   the {@code short} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakShort(short s, byte[] b, int off) {
        byte[] bytes = b;
        if (null == bytes) {
            bytes = new byte[2 + off];
        } else {
            Arrays.checkArray(bytes, off, 2);
        }

        int offset = off;
        bytes[offset] = breakShort1(s);
        offset++;
        bytes[offset] = breakShort2(s);

        return bytes;
    }

    /**
     * Returns the highest 8 bits of a {@code short}.
     *
     * @param s a {@code short}.
     * @return the highest 8 bits.
     */
    public static byte breakShort1(short s) {
        byte b = (byte) (s >>> 8);
        return b;
    }

    /**
     * Returns the lowest 8 bits of a {@code short}.
     *
     * @param s a {@code short}.
     * @return the lowest 8 bits.
     */
    public static byte breakShort2(short s) {
        byte b = (byte) s;
        return b;
    }

    /**
     * Returns a {@code char} assembled from two bytes, big-endian.
     *
     * @param b1 the highest byte.
     * @param b2 the lowest byte.
     * @return a {@code char} made from two bytes.
     */
    public static char makeChar(byte b1, byte b2) {
        short s = makeShort(b1, b2);
        char c = (char) s;
        return c;
    }

    /**
     * Returns a {@code char} as a sequence of two bytes, big-endian.
     *
     * @param c the {@code char} to break.
     * @return the bytes.
     * @see #breakChar(char, byte[], int)
     */
    public static byte[] breakChar(char c) {
        byte[] b = breakChar(c, null, 0);
        return b;
    }

    /**
     * Returns a {@code char} as a sequence of two bytes, big-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param c   the {@code char} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakChar(char c, byte[] b, int off) {
        byte[] bytes = b;
        if (null == bytes) {
            bytes = new byte[2 + off];
        } else {
            Arrays.checkArray(bytes, off, 2);
        }

        int offset = off;
        bytes[offset] = breakChar1(c);
        offset++;
        bytes[offset] = breakChar2(c);

        return bytes;
    }

    /**
     * Returns the highest 8 bits of a {@code char}.
     *
     * @param c a {@code char}.
     * @return the highest 8 bits.
     */
    public static byte breakChar1(char c) {
        byte b = (byte) (c >>> 8);
        return b;
    }

    /**
     * Returns the lowest 8 bits of a {@code char}.
     *
     * @param c a {@code char}.
     * @return the lowest 8 bits.
     */
    public static byte breakChar2(char c) {
        byte b = (byte) c;
        return b;
    }

    /**
     * Returns an {@code int} from four bytes, big-endian.
     *
     * @param b1 the highest byte.
     * @param b2 bits 23 down to 16.
     * @param b3 bits 15 down to 8.
     * @param b4 the lowest byte.
     * @return an {@code int} made from four bytes.
     */
    public static int makeInt(byte b1, byte b2, byte b3, byte b4) {
        int ub1 = unsignedByte(b1);
        int ub2 = unsignedByte(b2);
        int ub3 = unsignedByte(b3);
        int ub4 = unsignedByte(b4);
        int i = (ub1 << 24) | (ub2 << 16) | (ub3 << 8) | ub4;
        return i;
    }

    /**
     * Returns an {@code int} as a sequence of four bytes, big-endian.
     *
     * @param i the {@code int} to break.
     * @return the bytes.
     * @see #breakInt(int, byte[], int)
     */
    public static byte[] breakInt(int i) {
        byte[] b = breakInt(i, null, 0);
        return b;
    }

    /**
     * Returns an {@code int} as a sequence of four bytes, big-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param i   the {@code int} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakInt(int i, byte[] b, int off) {
        byte[] bytes = b;
        if (null == bytes) {
            bytes = new byte[4 + off];
        } else {
            Arrays.checkArray(bytes, off, 4);
        }

        int offset = off;
        bytes[offset] = breakInt1(i);
        offset++;
        bytes[offset] = breakInt2(i);
        offset++;
        bytes[offset] = breakInt3(i);
        offset++;
        bytes[offset] = breakInt4(i);

        return bytes;
    }

    /**
     * Returns the highest 8 bits of an {@code int}.
     *
     * @param i an {@code int}.
     * @return the highest 8 bits.
     */
    public static byte breakInt1(int i) {
        byte b = (byte) (i >>> 24);
        return b;
    }

    /**
     * Returns bit 23 down to 16 of an {@code int}.
     *
     * @param i an {@code int}.
     * @return bits 23 down to 16.
     */
    public static byte breakInt2(int i) {
        byte b = (byte) (i >>> 16);
        return b;
    }

    /**
     * Returns bit 15 down to 8 of an {@code int}.
     *
     * @param i an {@code int}.
     * @return bits 15 down to 8.
     */
    public static byte breakInt3(int i) {
        byte b = (byte) (i >>> 8);
        return b;
    }

    /**
     * Returns the lowest 8 bits of an {@code int}.
     *
     * @param i an {@code int}.
     * @return the lowest 8 bits.
     */
    public static byte breakInt4(int i) {
        byte b = (byte) i;
        return b;
    }

    /**
     * Returns a {@code long} from eigth bytes, big-endian.
     *
     * @param b1 the highest byte.
     * @param b2 bits 55 down to 48.
     * @param b3 bits 47 down to 40.
     * @param b4 bits 39 down to 32.
     * @param b5 bits 31 down to 24.
     * @param b6 bits 23 down to 16.
     * @param b7 bits 15 down to 8.
     * @param b8 the lowest byte.
     * @return a {@code long} made from eight bytes.
     */
    public static long makeLong(byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        long ub1 = unsignedByte(b1);
        long ub2 = unsignedByte(b2);
        long ub3 = unsignedByte(b3);
        long ub4 = unsignedByte(b4);
        long ub5 = unsignedByte(b5);
        long ub6 = unsignedByte(b6);
        long ub7 = unsignedByte(b7);
        long ub8 = unsignedByte(b8);
        long l = (ub1 << 56) | (ub2 << 48) | (ub3 << 40) | (ub4 << 32) | (ub5 << 24) | (ub6 << 16) | (ub7 << 8) | ub8;
        return l;
    }

    /**
     * Returns a {@code long} as a sequence of eight bytes, big-endian.
     *
     * @param l the {@code long} to break.
     * @return the bytes.
     * @see #breakLong(long, byte[], int)
     */
    public static byte[] breakLong(long l) {
        byte[] b = breakLong(l, null, 0);
        return b;
    }

    /**
     * Returns a {@code long} as a sequence of eight bytes, big-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param l   the {@code long} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakLong(long l, byte[] b, int off) {
        byte[] bytes = b;
        if (null == bytes) {
            bytes = new byte[8 + off];
        } else {
            Arrays.checkArray(bytes, off, 8);
        }

        int offset = off;
        bytes[offset] = breakLong1(l);
        offset++;
        bytes[offset] = breakLong2(l);
        offset++;
        bytes[offset] = breakLong3(l);
        offset++;
        bytes[offset] = breakLong4(l);
        offset++;
        bytes[offset] = breakLong5(l);
        offset++;
        bytes[offset] = breakLong6(l);
        offset++;
        bytes[offset] = breakLong7(l);
        offset++;
        bytes[offset] = breakLong8(l);

        return bytes;
    }

    /**
     * Returns the highest 8 bits of a {@code long}.
     *
     * @param l a {@code long}.
     * @return the highest 8 bits.
     */
    public static byte breakLong1(long l) {
        byte b = (byte) (l >>> 56);
        return b;
    }

    /**
     * Returns bit 55 down to 48 of a {@code long}.
     *
     * @param l a {@code long}.
     * @return bits 55 down to 48.
     */
    public static byte breakLong2(long l) {
        byte b = (byte) (l >>> 48);
        return b;
    }

    /**
     * Returns bit 47 down to 40 of a {@code long}.
     *
     * @param l a {@code long}.
     * @return bits 47 down to 40.
     */
    public static byte breakLong3(long l) {
        byte b = (byte) (l >>> 40);
        return b;
    }

    /**
     * Returns bit 39 down to 32 of a {@code long}.
     *
     * @param l a {@code long}.
     * @return bits 39 down to 32.
     */
    public static byte breakLong4(long l) {
        byte b = (byte) (l >>> 32);
        return b;
    }

    /**
     * Returns bit 31 down to 24 of a {@code long}.
     *
     * @param l a {@code long}.
     * @return bits 31 down to 24.
     */
    public static byte breakLong5(long l) {
        byte b = (byte) (l >>> 24);
        return b;
    }

    /**
     * Returns bit 23 down to 16 of a {@code long}.
     *
     * @param l a {@code long}.
     * @return bits 23 down to 16.
     */
    public static byte breakLong6(long l) {
        byte b = (byte) (l >>> 16);
        return b;
    }

    /**
     * Returns bit 15 down to 8 of an {@code int}.
     *
     * @param l a {@code long}.
     * @return bits 15 down to 8.
     */
    public static byte breakLong7(long l) {
        byte b = (byte) (l >>> 8);
        return b;
    }

    /**
     * Returns the lowest 8 bits of a {@code long}.
     *
     * @param l a {@code long}.
     * @return the lowest 8 bits.
     */
    public static byte breakLong8(long l) {
        byte b = (byte) l;
        return b;
    }

    /**
     * Returns a {@code float} from four bytes, big-endian.
     *
     * @param b1 the highest byte.
     * @param b2 bits 23 down to 16.
     * @param b3 bits 15 down to 8.
     * @param b4 the lowest byte.
     * @return a {@code float} made from four bytes.
     */
    public static float makeFloat(byte b1, byte b2, byte b3, byte b4) {
        int i = makeInt(b1, b2, b3, b4);
        float f = Float.intBitsToFloat(i);
        return f;
    }

    /**
     * Returns a {@code float} as a sequence of four bytes, big-endian.
     *
     * @param f the {@code float} to break.
     * @return the bytes.
     * @see #breakFloat(float, byte[], int)
     */
    public static byte[] breakFloat(float f) {
        byte[] b = breakFloat(f, null, 0);
        return b;
    }

    /**
     * Returns a {@code float} as a sequence of four bytes, big-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param f   the {@code float} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakFloat(float f, byte[] b, int off) {
        int i = Float.floatToIntBits(f);
        byte[] bytes = breakInt(i, b, off);
        return bytes;
    }

    /**
     * Returns the highest 8 bits of a {@code float}.
     *
     * @param f a {@code float}.
     * @return the highest 8 bits.
     */
    public static byte breakFloat1(float f) {
        int i = Float.floatToIntBits(f);
        byte b = breakInt1(i);
        return b;
    }

    /**
     * Returns bit 23 down to 16 of an {@code float}.
     *
     * @param f a {@code float}.
     * @return bits 23 down to 16.
     */
    public static byte breakFloat2(float f) {
        int i = Float.floatToIntBits(f);
        byte b = breakInt2(i);
        return b;
    }

    /**
     * Returns bit 15 down to 8 of an {@code float}.
     *
     * @param f a {@code float}.
     * @return bits 15 down to 8.
     */
    public static byte breakFloat3(float f) {
        int i = Float.floatToIntBits(f);
        byte b = breakInt3(i);
        return b;
    }

    /**
     * Returns the lowest 8 bits of a {@code float}.
     *
     * @param f a {@code float}.
     * @return the lowest 8 bits.
     */
    public static byte breakFloat4(float f) {
        int i = Float.floatToIntBits(f);
        byte b = breakInt4(i);
        return b;
    }

    /**
     * Returns a {@code double} from eigth bytes, big-endian.
     *
     * @param b1 the highest byte.
     * @param b2 bits 55 down to 48.
     * @param b3 bits 47 down to 40.
     * @param b4 bits 39 down to 32.
     * @param b5 bits 31 down to 24.
     * @param b6 bits 23 down to 16.
     * @param b7 bits 15 down to 8.
     * @param b8 the lowest byte.
     * @return a {@code double} made from eight bytes.
     */
    public static double makeDouble(byte b1, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8) {
        long l = makeLong(b1, b2, b3, b4, b5, b6, b7, b8);
        double d = Double.longBitsToDouble(l);
        return d;
    }

    /**
     * Returns a {@code double} as a sequence of eight bytes, big-endian.
     *
     * @param d the {@code long} to break.
     * @return the bytes.
     * @see #breakDouble(double, byte[], int)
     */
    public static byte[] breakDouble(double d) {
        byte[] b = breakDouble(d, null, 0);
        return b;
    }

    /**
     * Returns a {@code double} as a sequence of eight bytes, big-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param d   the {@code long} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakDouble(double d, byte[] b, int off) {
        long l = Double.doubleToLongBits(d);
        byte[] bytes = breakLong(l, b, off);
        return bytes;
    }

    /**
     * Returns the highest 8 bits of a {@code double}.
     *
     * @param d a {@code double}.
     * @return the highest 8 bits.
     */
    public static byte breakDouble1(double d) {
        long l = Double.doubleToLongBits(d);
        byte b = breakLong1(l);
        return b;
    }

    /**
     * Returns bit 55 down to 48 of a {@code double}.
     *
     * @param d a {@code double}.
     * @return bits 55 down to 48.
     */
    public static byte breakDouble2(double d) {
        long l = Double.doubleToLongBits(d);
        byte b = breakLong2(l);
        return b;
    }

    /**
     * Returns bit 47 down to 40 of a {@code double}.
     *
     * @param d a {@code double}.
     * @return bits 47 down to 40.
     */
    public static byte breakDouble3(double d) {
        long l = Double.doubleToLongBits(d);
        byte b = breakLong3(l);
        return b;
    }

    /**
     * Returns bit 39 down to 32 of a {@code double}.
     *
     * @param d a {@code double}.
     * @return bits 39 down to 32.
     */
    public static byte breakDouble4(double d) {
        long l = Double.doubleToLongBits(d);
        byte b = breakLong4(l);
        return b;
    }

    /**
     * Returns bit 31 down to 24 of a {@code double}.
     *
     * @param d a {@code double}.
     * @return bits 31 down to 24.
     */
    public static byte breakDouble5(double d) {
        long l = Double.doubleToLongBits(d);
        byte b = breakLong5(l);
        return b;
    }

    /**
     * Returns bit 23 down to 16 of a {@code double}.
     *
     * @param d a {@code double}.
     * @return bits 23 down to 16.
     */
    public static byte breakDouble6(double d) {
        long l = Double.doubleToLongBits(d);
        byte b = breakLong6(l);
        return b;
    }

    /**
     * Returns bit 15 down to 8 of a {@code double}.
     *
     * @param d a {@code double}.
     * @return bits 15 down to 8.
     */
    public static byte breakDouble7(double d) {
        long l = Double.doubleToLongBits(d);
        byte b = breakLong7(l);
        return b;
    }

    /**
     * Returns the lowest 8 bits of a {@code double}.
     *
     * @param d a {@code double}.
     * @return the lowest 8 bits.
     */
    public static byte breakDouble8(double d) {
        long l = Double.doubleToLongBits(d);
        byte b = breakLong8(l);
        return b;
    }

    /**
     * Returns a {@code short} as a sequence of two bytes, little-endian.
     *
     * @param s the {@code short} to break.
     * @return the bytes.
     */
    public static byte[] breakShortLE(short s) {
        byte[] b = breakShortLE(s, null, 0);
        return b;
    }

    /**
     * Returns a {@code short} as a sequence of two bytes, little-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param s   the {@code short} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakShortLE(short s, byte[] b, int off) {
        byte[] bytes = b;
        if (null == bytes) {
            bytes = new byte[2 + off];
        } else {
            Arrays.checkArray(bytes, off, 2);
        }

        int offset = off;
        bytes[offset] = breakShort2(s);
        offset++;
        bytes[offset] = breakShort1(s);

        return bytes;
    }

    /**
     * Returns a {@code char} as a sequence of two bytes, little-endian.
     *
     * @param c the {@code char} to break.
     * @return the bytes.
     * @see #breakChar(char, byte[], int)
     */
    public static byte[] breakCharLE(char c) {
        byte[] b = breakCharLE(c, null, 0);
        return b;
    }

    /**
     * Returns a {@code char} as a sequence of two bytes, little-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param c   the {@code char} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakCharLE(char c, byte[] b, int off) {
        byte[] bytes = b;
        if (null == bytes) {
            bytes = new byte[2 + off];
        } else {
            Arrays.checkArray(bytes, off, 2);
        }

        int offset = off;
        bytes[offset] = breakChar2(c);
        offset++;
        bytes[offset] = breakChar1(c);

        return bytes;
    }

    /**
     * Returns an {@code int} as a sequence of four bytes, little-endian.
     *
     * @param i the {@code int} to break.
     * @return the bytes.
     * @see #breakInt(int, byte[], int)
     */
    public static byte[] breakIntLE(int i) {
        byte[] b = breakIntLE(i, null, 0);
        return b;
    }

    /**
     * Returns an {@code int} as a sequence of four bytes, little-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param i   the {@code int} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakIntLE(int i, byte[] b, int off) {
        byte[] bytes = b;
        if (null == bytes) {
            bytes = new byte[4 + off];
        } else {
            Arrays.checkArray(bytes, off, 4);
        }

        int offset = off;
        bytes[offset] = breakInt4(i);
        offset++;
        bytes[offset] = breakInt3(i);
        offset++;
        bytes[offset] = breakInt2(i);
        offset++;
        bytes[offset] = breakInt1(i);

        return bytes;
    }

    /**
     * Returns a {@code long} as a sequence of eight bytes, little-endian.
     *
     * @param l the {@code long} to break.
     * @return the bytes.
     * @see #breakLong(long, byte[], int)
     */
    public static byte[] breakLongLE(long l) {
        byte[] b = breakLongLE(l, null, 0);
        return b;
    }

    /**
     * Returns a {@code long} as a sequence of eight bytes, little-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param l   the {@code long} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakLongLE(long l, byte[] b, int off) {
        byte[] bytes = b;
        if (null == bytes) {
            bytes = new byte[8 + off];
        } else {
            Arrays.checkArray(bytes, off, 8);
        }

        int offset = off;
        bytes[offset] = breakLong8(l);
        offset++;
        bytes[offset] = breakLong7(l);
        offset++;
        bytes[offset] = breakLong6(l);
        offset++;
        bytes[offset] = breakLong5(l);
        offset++;
        bytes[offset] = breakLong4(l);
        offset++;
        bytes[offset] = breakLong3(l);
        offset++;
        bytes[offset] = breakLong2(l);
        offset++;
        bytes[offset] = breakLong1(l);

        return bytes;
    }

    /**
     * Returns a {@code float} as a sequence of four bytes, little-endian.
     *
     * @param f the {@code float} to break.
     * @return the bytes.
     * @see #breakFloat(float, byte[], int)
     */
    public static byte[] breakFloatLE(float f) {
        byte[] b = breakFloatLE(f, null, 0);
        return b;
    }

    /**
     * Returns a {@code float} as a sequence of four bytes, little-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param f   the {@code float} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakFloatLE(float f, byte[] b, int off) {
        int i = Float.floatToIntBits(f);
        byte[] bytes = breakIntLE(i, b, off);
        return bytes;
    }

    /**
     * Returns a {@code double} as a sequence of eight bytes, little-endian.
     *
     * @param d the {@code long} to break.
     * @return the bytes.
     * @see #breakDouble(double, byte[], int)
     */
    public static byte[] breakDoubleLE(double d) {
        byte[] b = breakDoubleLE(d, null, 0);
        return b;
    }

    /**
     * Returns a {@code double} as a sequence of eight bytes, little-endian. If the
     * specified array is {@code null}, it will be created.
     *
     * @param d   the {@code long} to break.
     * @param b   the destination array.
     * @param off the offset in the array.
     * @return {@code b}.
     */
    public static byte[] breakDoubleLE(double d, byte[] b, int off) {
        long l = Double.doubleToLongBits(d);
        byte[] bytes = breakLongLE(l, b, off);
        return bytes;
    }

}
