/*
 * Copyright (C)  2007  Forklabs Daniel Léonard
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

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Class {@code BitsTest} tests class {@link Bits}.
 */
public class BitsTest {

    /**
     * Tests {@link Bits#rotateLeft(int, int)}.
     */
    @Test
    public void testRotateLeftI() {
        int i = 0xaabbccdd;
        int shift = 8;

        int expected = 0xbbccddaa;
        int got = Bits.rotateLeft(i, shift);
        assertEquals(expected, got);
    }

    /**
     * Tests {@link Bits#rotateRight(int, int)}.
     */
    @Test
    public void testRotateRightI() {
        int i = 0xaabbccdd;
        int shift = 8;

        int expected = 0xddaabbcc;
        int got = Bits.rotateRight(i, shift);
        assertEquals(expected, got);
    }

    /**
     * Tests {@link Bits#rotateLeft(long, int)}.
     */
    @Test
    public void testRotateLeftL() {
        long l = 0x0123456789abcdefL;
        int shift = 16;

        long expected = 0x456789abcdef0123L;
        long got = Bits.rotateLeft(l, shift);
        assertEquals(expected, got);
    }

    /**
     * Tests {@link Bits#rotateRight(long, int)}.
     */
    @Test
    public void testRotateRightL() {
        long l = 0x0123456789abcdefL;
        int shift = 16;

        long expected = 0xcdef0123456789abL;
        long got = Bits.rotateRight(l, shift);
        assertEquals(expected, got);
    }

    /**
     * Tests {@link Bits#unsignedByte(byte)}.
     */
    @Test
    public void testUnsignedByte() {
        byte b = -1;
        int expected = 255;
        int result = Bits.unsignedByte(b);
        assertEquals(expected, result);
    }

    /**
     * Tests {@link Bits#unsignedShort(short)}.
     */
    @Test
    public void testUnsignedShort() {
        short s = -1;
        int expected = 65535;
        int result = Bits.unsignedShort(s);
        assertEquals(expected, result);
    }

    /**
     * Tests {@link Bits#unsignedInt(int)}.
     */
    @Test
    public void testUnsignedInt() {
        int i = -1;
        long expected = 4294967295L;
        long result = Bits.unsignedInt(i);
        assertEquals(expected, result);
    }

    /**
     * Tests {@link Bits#makeShort(byte, byte)} and {@link Bits#breakShort(short)}.
     */
    @Test
    public void testBreakShort() {
        byte b1 = (byte) 0xaa, b2 = (byte) 0xbb;
        short expected = (short) 0xaabb;

        short result = Bits.makeShort(b1, b2);
        assertEquals(result, expected);

        byte[] bytes = Bits.breakShort(result);
        assertEquals(b1, bytes[0]);
        assertEquals(b2, bytes[1]);
    }

    /**
     * Tests {@link Bits#makeChar(byte, byte)} and {@link Bits#breakChar(char)}.
     */
    @Test
    public void testBreakChar() {
        byte b1 = (byte) 0x10, b2 = (byte) 0x01;
        char expected = (char) 0x1001;

        char result = Bits.makeChar(b1, b2);
        assertEquals(expected, result);

        byte[] bytes = Bits.breakChar(result);
        assertEquals(b1, bytes[0]);
        assertEquals(b2, bytes[1]);
    }

    /**
     * Tests {@link Bits#makeInt(byte, byte, byte, byte)} and
     * {@link Bits#breakInt(int)}.
     */
    @Test
    public void testBreakInt() {
        byte b1 = (byte) 0xaa, b2 = (byte) 0xbb, b3 = (byte) 0xcc, b4 = (byte) 0x11;
        int expected = 0xaabbcc11;

        int result = Bits.makeInt(b1, b2, b3, b4);
        assertEquals(expected, result);

        byte[] bytes = Bits.breakInt(result);
        assertEquals(b1, bytes[0]);
        assertEquals(b2, bytes[1]);
        assertEquals(b3, bytes[2]);
        assertEquals(b4, bytes[3]);
    }

    /**
     * Tests {@link Bits#makeLong(byte, byte, byte, byte, byte, byte, byte, byte)}
     * and {@link Bits#breakLong(long)}.
     */
    @Test
    public void testBreakLong() {
        byte b1 = (byte) 0x11, b2 = (byte) 0x33, b3 = (byte) 0x55, b4 = (byte) 0x77;
        byte b5 = (byte) 0x99, b6 = (byte) 0xbb, b7 = (byte) 0xdd, b8 = (byte) 0xff;
        long expected = 0x1133557799bbddffL;

        long result = Bits.makeLong(b1, b2, b3, b4, b5, b6, b7, b8);
        assertEquals(expected, result);

        byte[] bytes = Bits.breakLong(result);
        assertEquals(b1, bytes[0]);
        assertEquals(b2, bytes[1]);
        assertEquals(b3, bytes[2]);
        assertEquals(b4, bytes[3]);
        assertEquals(b5, bytes[4]);
        assertEquals(b6, bytes[5]);
        assertEquals(b7, bytes[6]);
        assertEquals(b8, bytes[7]);
    }

    /**
     * Tests {@link Bits#makeFloat(byte, byte, byte, byte)} and
     * {@link Bits#breakFloat(float)}.
     */
    @Test
    public void testBreakFloat() {
        float expected = (float) Math.E;
        byte[] bytes = Bits.breakFloat(expected);
        float result = Bits.makeFloat(bytes[0], bytes[1], bytes[2], bytes[3]);
        assertEquals(expected, result, 0.0f);
    }

    /**
     * Tests {@link Bits#makeDouble(byte, byte, byte, byte, byte, byte, byte, byte)}
     * and {@link Bits#breakDouble(double)}.
     */
    @Test
    public void testBreakDouble() {
        double expected = Math.PI;
        byte[] bytes = Bits.breakDouble(expected);
        double result = Bits.makeDouble(bytes[0], bytes[1], bytes[2], bytes[3], bytes[4], bytes[5], bytes[6], bytes[7]);
        assertEquals(expected, result, 0.0f);
    }

    /**
     * Tests {@link Bits#breakShortLE(short)}.
     */
    @Test
    public void testBreakShortLE() {
        byte b1 = (byte) 0xbb, b2 = (byte) 0xaa;
        short value = (short) 0xaabb;
        byte[] bytes = Bits.breakShortLE(value);
        assertEquals(b1, bytes[0]);
        assertEquals(b2, bytes[1]);
    }

    /**
     * Tests {@link Bits#breakCharLE(char)}.
     */
    @Test
    public void testBreakCharLE() {
        byte b1 = (byte) 0xbb, b2 = (byte) 0xaa;
        char value = '\uaabb';
        byte[] bytes = Bits.breakCharLE(value);
        assertEquals(b1, bytes[0]);
        assertEquals(b2, bytes[1]);
    }

    /**
     * Tests {@link Bits#breakIntLE(int)}.
     */
    @Test
    public void testBreakIntLE() {
        byte b1 = (byte) 0xaa, b2 = (byte) 0xbb, b3 = (byte) 0xcc, b4 = (byte) 0x11;
        int value = 0x11ccbbaa;
        byte[] bytes = Bits.breakIntLE(value);
        assertEquals(b1, bytes[0]);
        assertEquals(b2, bytes[1]);
        assertEquals(b3, bytes[2]);
        assertEquals(b4, bytes[3]);
    }

    /**
     * Tests {@link Bits#breakLongLE(long)}.
     */
    @Test
    public void testBreakLongLE() {
        byte b1 = (byte) 0x11, b2 = (byte) 0x33, b3 = (byte) 0x55, b4 = (byte) 0x77;
        byte b5 = (byte) 0x99, b6 = (byte) 0xbb, b7 = (byte) 0xdd, b8 = (byte) 0xff;
        long value = 0xffddbb9977553311L;
        byte[] bytes = Bits.breakLongLE(value);
        assertEquals(b1, bytes[0]);
        assertEquals(b2, bytes[1]);
        assertEquals(b3, bytes[2]);
        assertEquals(b4, bytes[3]);
        assertEquals(b5, bytes[4]);
        assertEquals(b6, bytes[5]);
        assertEquals(b7, bytes[6]);
        assertEquals(b8, bytes[7]);
    }

    /**
     * Tests {@link Bits#breakFloatLE(float)}.
     */
    @Test
    public void testBreakFloatLE() {
        float f = (float) Math.E;
        byte[] bebytes = Bits.breakFloat(f);
        byte[] lebytes = Bits.breakFloatLE(f);
        assertEquals(bebytes[0], lebytes[3]);
        assertEquals(bebytes[1], lebytes[2]);
        assertEquals(bebytes[2], lebytes[1]);
        assertEquals(bebytes[3], lebytes[0]);
    }

    /**
     * Tests {@link Bits#breakDoubleLE(double)}.
     */
    @Test
    public void testBreakDoubleLE() {
        double d = Math.PI;
        byte[] bebytes = Bits.breakDouble(d);
        byte[] lebytes = Bits.breakDoubleLE(d);
        assertEquals(bebytes[0], lebytes[7]);
        assertEquals(bebytes[1], lebytes[6]);
        assertEquals(bebytes[2], lebytes[5]);
        assertEquals(bebytes[3], lebytes[4]);
        assertEquals(bebytes[4], lebytes[3]);
        assertEquals(bebytes[5], lebytes[2]);
        assertEquals(bebytes[6], lebytes[1]);
        assertEquals(bebytes[7], lebytes[0]);
    }

    /**
     * Tests the individual break float methods.
     */
    @Test
    public void testBreakFloat1234() {
        float f = (float) Math.E;
        int i = Float.floatToIntBits(f);

        assertEquals(Bits.breakInt1(i), Bits.breakFloat1(f));
        assertEquals(Bits.breakInt2(i), Bits.breakFloat2(f));
        assertEquals(Bits.breakInt3(i), Bits.breakFloat3(f));
        assertEquals(Bits.breakInt4(i), Bits.breakFloat4(f));
    }

    /**
     * Tests the individual break double methods.
     */
    @Test
    public void testBreakDouble12345678() {
        double d = Math.PI;
        long l = Double.doubleToLongBits(d);

        assertEquals(Bits.breakLong1(l), Bits.breakDouble1(d));
        assertEquals(Bits.breakLong2(l), Bits.breakDouble2(d));
        assertEquals(Bits.breakLong3(l), Bits.breakDouble3(d));
        assertEquals(Bits.breakLong4(l), Bits.breakDouble4(d));
        assertEquals(Bits.breakLong5(l), Bits.breakDouble5(d));
        assertEquals(Bits.breakLong6(l), Bits.breakDouble6(d));
        assertEquals(Bits.breakLong7(l), Bits.breakDouble7(d));
        assertEquals(Bits.breakLong8(l), Bits.breakDouble8(d));
    }

    /**
     * Tests the behaviour when the results are sent into an existing array.
     */
    @Test
    public void testSameArray() {
        byte[] bytes = new byte[8];

        Bits.breakChar('\u1221', bytes, 6);
        assertEquals((byte) 0x00, bytes[0]);
        assertEquals((byte) 0x00, bytes[1]);
        assertEquals((byte) 0x00, bytes[2]);
        assertEquals((byte) 0x00, bytes[3]);
        assertEquals((byte) 0x00, bytes[4]);
        assertEquals((byte) 0x00, bytes[5]);
        assertEquals((byte) 0x12, bytes[6]);
        assertEquals((byte) 0x21, bytes[7]);

        Bits.breakCharLE('\u1221', bytes, 4);
        assertEquals((byte) 0x00, bytes[0]);
        assertEquals((byte) 0x00, bytes[1]);
        assertEquals((byte) 0x00, bytes[2]);
        assertEquals((byte) 0x00, bytes[3]);
        assertEquals((byte) 0x21, bytes[4]);
        assertEquals((byte) 0x12, bytes[5]);
        assertEquals((byte) 0x12, bytes[6]);
        assertEquals((byte) 0x21, bytes[7]);

        Bits.breakShort((short) 0xcafe, bytes, 2);
        assertEquals((byte) 0x00, bytes[0]);
        assertEquals((byte) 0x00, bytes[1]);
        assertEquals((byte) 0xca, bytes[2]);
        assertEquals((byte) 0xfe, bytes[3]);
        assertEquals((byte) 0x21, bytes[4]);
        assertEquals((byte) 0x12, bytes[5]);
        assertEquals((byte) 0x12, bytes[6]);
        assertEquals((byte) 0x21, bytes[7]);

        Bits.breakShortLE((short) 0xcafe, bytes, 0);
        assertEquals((byte) 0xfe, bytes[0]);
        assertEquals((byte) 0xca, bytes[1]);
        assertEquals((byte) 0xca, bytes[2]);
        assertEquals((byte) 0xfe, bytes[3]);
        assertEquals((byte) 0x21, bytes[4]);
        assertEquals((byte) 0x12, bytes[5]);
        assertEquals((byte) 0x12, bytes[6]);
        assertEquals((byte) 0x21, bytes[7]);

        Bits.breakInt(0xcafebabe, bytes, 4);
        assertEquals((byte) 0xfe, bytes[0]);
        assertEquals((byte) 0xca, bytes[1]);
        assertEquals((byte) 0xca, bytes[2]);
        assertEquals((byte) 0xfe, bytes[3]);
        assertEquals((byte) 0xca, bytes[4]);
        assertEquals((byte) 0xfe, bytes[5]);
        assertEquals((byte) 0xba, bytes[6]);
        assertEquals((byte) 0xbe, bytes[7]);

        Bits.breakIntLE(0xcafebabe, bytes, 0);
        assertEquals((byte) 0xbe, bytes[0]);
        assertEquals((byte) 0xba, bytes[1]);
        assertEquals((byte) 0xfe, bytes[2]);
        assertEquals((byte) 0xca, bytes[3]);
        assertEquals((byte) 0xca, bytes[4]);
        assertEquals((byte) 0xfe, bytes[5]);
        assertEquals((byte) 0xba, bytes[6]);
        assertEquals((byte) 0xbe, bytes[7]);

        Bits.breakLong(0xcafebabe12345678L, bytes, 0);
        assertEquals((byte) 0xca, bytes[0]);
        assertEquals((byte) 0xfe, bytes[1]);
        assertEquals((byte) 0xba, bytes[2]);
        assertEquals((byte) 0xbe, bytes[3]);
        assertEquals((byte) 0x12, bytes[4]);
        assertEquals((byte) 0x34, bytes[5]);
        assertEquals((byte) 0x56, bytes[6]);
        assertEquals((byte) 0x78, bytes[7]);

        Bits.breakLongLE(0xcafebabe12345678L, bytes, 0);
        assertEquals((byte) 0x78, bytes[0]);
        assertEquals((byte) 0x56, bytes[1]);
        assertEquals((byte) 0x34, bytes[2]);
        assertEquals((byte) 0x12, bytes[3]);
        assertEquals((byte) 0xbe, bytes[4]);
        assertEquals((byte) 0xba, bytes[5]);
        assertEquals((byte) 0xfe, bytes[6]);
        assertEquals((byte) 0xca, bytes[7]);
    }

    /**
     * Tests the number of octets in each type.
     */
    @Test
    public void testNumOctets() {
        assertEquals(1, Bits.NUM_OCTETS_IN_BYTE);
        assertEquals(2, Bits.NUM_OCTETS_IN_SHORT);
        assertEquals(2, Bits.NUM_OCTETS_IN_CHAR);
        assertEquals(4, Bits.NUM_OCTETS_IN_INT);
        assertEquals(8, Bits.NUM_OCTETS_IN_LONG);
        assertEquals(4, Bits.NUM_OCTETS_IN_FLOAT);
        assertEquals(8, Bits.NUM_OCTETS_IN_DOUBLE);
    }

}
