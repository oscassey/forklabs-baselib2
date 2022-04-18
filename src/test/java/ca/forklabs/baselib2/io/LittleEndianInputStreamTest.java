/*
 * Copyright (C) 2006  Forklabs Daniel Léonard
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

package ca.forklabs.baselib2.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

/**
 * Class {@code LittleEndianInputStreamTest} tests class
 * {@link LittleEndianInputStream}.
 */
public class LittleEndianInputStreamTest {

    /**
     * Tests {@link LittleEndianInputStream#readUnsignedByte()}.
     */
    @Test
    public void testReadUnsignedByte() throws IOException {
        byte[] data = new byte[] { -1 };

        try (InputStream is = new ByteArrayInputStream(data);
             LittleEndianInputStream leis = new LittleEndianInputStream(is)) {
            int expected = 255;
            int got = leis.readUnsignedByte();

            assertEquals(expected, got);
        }
    }

    /**
     * Tests {@link LittleEndianInputStream#readChar()}.
     */
    @Test
    public void testReadChar() throws IOException {
        byte[] data = new byte[] { (byte) 0xaa, (byte) 0xbb };

        try (InputStream is = new ByteArrayInputStream(data);
             LittleEndianInputStream leis = new LittleEndianInputStream(is)) {
            char expected = '\ubbaa';
            char got = leis.readChar();

            assertEquals(expected, got);
        }
    }

    /**
     * Tests {@link LittleEndianInputStream#readShort()}.
     */
    @Test
    public void testReadShort() throws IOException {
        byte[] data = new byte[] { (byte) 0xaa, (byte) 0xbb };

        try (InputStream is = new ByteArrayInputStream(data);
             LittleEndianInputStream leis = new LittleEndianInputStream(is)) {
            short expected = (short) 0xbbaa;
            short got = leis.readShort();

            assertEquals(expected, got);
        }
    }

    /**
     * Tests {@link LittleEndianInputStream#readUnsignedShort()}.
     */
    @Test
    public void testReadUnsignedShort() throws IOException {
        byte[] data = new byte[] { -1, -1 };

        try (InputStream is = new ByteArrayInputStream(data);
             LittleEndianInputStream leis = new LittleEndianInputStream(is)) {
            int expected = 65535;
            int got = leis.readUnsignedShort();

            assertEquals(expected, got);
        }
    }

    /**
     * Tests {@link LittleEndianInputStream#readInt()}.
     */
    @Test
    public void testReadInt() throws IOException {
        byte[] data = new byte[] {
            (byte) 0x78, (byte) 0x56, (byte) 0x34, (byte) 0x12
        };

        try (InputStream is = new ByteArrayInputStream(data);
             LittleEndianInputStream leis = new LittleEndianInputStream(is)) {
            int expected = 0x12345678;
            int got = leis.readInt();

            assertEquals(expected, got);
        }
    }

    /**
     * Tests {@link LittleEndianInputStream#readLong()}.
     */
    @Test
    public void testReadLong() throws IOException {
        byte[] data = new byte[] {
            (byte) 0xef, (byte) 0xcd, (byte) 0xab, (byte) 0x90,
            (byte) 0x78, (byte) 0x56, (byte) 0x34, (byte) 0x12
        };

        try (InputStream is = new ByteArrayInputStream(data);
             LittleEndianInputStream leis = new LittleEndianInputStream(is)) {
            long expected = 0x1234567890abcdefL;
            long got = leis.readLong();

            assertEquals(expected, got);
        }
    }

    /**
     * Tests {@link LittleEndianInputStream#readFloat()}.
     */
    @Test
    public void testReadFloat() throws IOException {
        float expected = (float) Math.E;
        int i = Float.floatToRawIntBits(expected);

        byte[] data = new byte[4];
        data[0] = (byte) (i & 0xff);
        data[1] = (byte) ((i >> 8) & 0xff);
        data[2] = (byte) ((i >> 16) & 0xff);
        data[3] = (byte) ((i >> 24) & 0xff);

        try (InputStream is = new ByteArrayInputStream(data);
             LittleEndianInputStream leis = new LittleEndianInputStream(is)) {
            float got = leis.readFloat();
            float epsilon = 10e-10f;

            assertEquals(expected, got, epsilon);
        }
    }

    /**
     * Tests {@link LittleEndianInputStream#readDouble()}.
     */
    @Test
    public void testReadDouble() throws IOException {
        double expected = Math.E;
        long l = Double.doubleToRawLongBits(expected);

        byte[] data = new byte[8];
        data[0] = (byte) (l & 0xff);
        data[1] = (byte) ((l >> 8) & 0xff);
        data[2] = (byte) ((l >> 16) & 0xff);
        data[3] = (byte) ((l >> 24) & 0xff);
        data[4] = (byte) ((l >> 32) & 0xff);
        data[5] = (byte) ((l >> 40) & 0xff);
        data[6] = (byte) ((l >> 48) & 0xff);
        data[7] = (byte) ((l >> 56) & 0xff);

        try (InputStream is = new ByteArrayInputStream(data);
             LittleEndianInputStream leis = new LittleEndianInputStream(is)) {
            double got = leis.readDouble();
            double epsilon = 10e-10;

            assertEquals(expected, got, epsilon);
        }
    }

}
