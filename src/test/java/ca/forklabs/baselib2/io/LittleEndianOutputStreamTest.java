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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Class {@codeLittleEndianOutputStreamTest} tests class
 * {@link LittleEndianOutputStream}.
 */
public class LittleEndianOutputStreamTest {

    /**
     * Tests {@link LittleEndianOutputStream#writeChar(int)}.
     */
    @Test
    public void testWriteChar() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             LittleEndianOutputStream leos = new LittleEndianOutputStream(baos)) {
            char c = '\uff88';

            leos.writeChar(c);
            leos.close();

            byte[] expected = new byte[] { (byte) 0x88, (byte) 0xff };
            byte[] got = baos.toByteArray();

            assertEquals(expected.length, got.length);
            for (int i = 0; i < got.length; i++) {
                assertEquals(expected[i], got[i]);
            }
        }
    }

    /**
     * Tests {@link LittleEndianOutputStream#writeDouble(double)}.
     */
    @Test
    public void testWriteDouble() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             LittleEndianOutputStream leos = new LittleEndianOutputStream(baos)) {
            double d = Math.PI;
            long l = Double.doubleToRawLongBits(d);

            leos.writeDouble(d);
            leos.close();

            byte[] expected = new byte[8];
            expected[0] = (byte) (l & 0xff);
            expected[1] = (byte) ((l >> 8) & 0xff);
            expected[2] = (byte) ((l >> 16) & 0xff);
            expected[3] = (byte) ((l >> 24) & 0xff);
            expected[4] = (byte) ((l >> 32) & 0xff);
            expected[5] = (byte) ((l >> 40) & 0xff);
            expected[6] = (byte) ((l >> 48) & 0xff);
            expected[7] = (byte) ((l >> 56) & 0xff);
            byte[] got = baos.toByteArray();

            assertEquals(expected.length, got.length);
            for (int i = 0; i < got.length; i++) {
                assertEquals(expected[i], got[i]);
            }
        }
    }

    /**
     * Tests {@link LittleEndianOutputStream#writeFloat(float)}.
     */
    @Test
    public void testWriteFloat() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             LittleEndianOutputStream leos = new LittleEndianOutputStream(baos)) {
            float f = (float) Math.PI;
            int i = Float.floatToRawIntBits(f);

            leos.writeFloat(f);
            leos.close();

            byte[] expected = new byte[4];
            expected[0] = (byte) (i & 0xff);
            expected[1] = (byte) ((i >> 8) & 0xff);
            expected[2] = (byte) ((i >> 16) & 0xff);
            expected[3] = (byte) ((i >> 24) & 0xff);
            byte[] got = baos.toByteArray();

            assertEquals(expected.length, got.length);
            for (int j = 0; j < got.length; j++) {
                assertEquals(expected[j], got[j]);
            }
        }
    }

    /**
     * Tests {@link LittleEndianOutputStream#writeInt(int)}.
     */
    @Test
    public void testWriteInt() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             LittleEndianOutputStream leos = new LittleEndianOutputStream(baos)) {
            int i = 0x12345678;

            leos.writeInt(i);
            leos.close();

            byte[] expected = new byte[] { (byte) 0x78, (byte) 0x56, (byte) 0x34, (byte) 0x12 };
            byte[] got = baos.toByteArray();

            assertEquals(expected.length, got.length);
            for (int j = 0; j < got.length; j++) {
                assertEquals(expected[j], got[j]);
            }
        }
    }

    /**
     * Tests {@link LittleEndianOutputStream#writeLong(long)}.
     */
    @Test
    @SuppressWarnings("resource")
    public void testWriteLong() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             LittleEndianOutputStream leos = new LittleEndianOutputStream(baos)) {
            long l = 0x1234567890abcdefL;

            leos.writeLong(l);
            leos.close();

            byte[] expected = new byte[] { (byte) 0xef, (byte) 0xcd, (byte) 0xab, (byte) 0x90, (byte) 0x78, (byte) 0x56,
                    (byte) 0x34, (byte) 0x12, };
            byte[] got = baos.toByteArray();

            assertEquals(expected.length, got.length);
            for (int i = 0; i < got.length; i++) {
                assertEquals(expected[i], got[i]);
            }
        }
    }

    /**
     * Tests {@link LittleEndianOutputStream#writeShort(int)}.
     */
    @Test
    public void testWriteShort() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             LittleEndianOutputStream leos = new LittleEndianOutputStream(baos)) {
            short s = (short) 0xff88;

            leos.writeShort(s);
            leos.close();

            byte[] expected = new byte[] { (byte) 0x88, (byte) 0xff };
            byte[] got = baos.toByteArray();

            assertEquals(expected.length, got.length);
            for (int i = 0; i < got.length; i++) {
                assertEquals(expected[i], got[i]);
            }
        }
    }

    /**
     * Tests {@link LittleEndianOutputStream#writeChars(String)}.
     */
    @Test
    public void testWriteChars() throws IOException {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             LittleEndianOutputStream leos = new LittleEndianOutputStream(baos)) {
            String s = "test";

            leos.writeChars(s);
            leos.close();

            byte[] expected = new byte[8];
            expected[0] = (byte) ('t' & 0xff);
            expected[1] = (byte) (('t' >> 8) & 0xff);
            expected[2] = (byte) ('e' & 0xff);
            expected[3] = (byte) (('e' >> 8) & 0xff);
            expected[4] = (byte) ('s' & 0xff);
            expected[5] = (byte) (('s' >> 8) & 0xff);
            expected[6] = (byte) ('t' & 0xff);
            expected[7] = (byte) (('t' >> 8) & 0xff);

            byte[] got = baos.toByteArray();

            assertEquals(expected.length, got.length);
            for (int i = 0; i < got.length; i++) {
                assertEquals(expected[i], got[i]);
            }
        }
    }

    /**
     * Tests {@link LittleEndianOutputStream#size()}.
     */
    @Test
    public void testSize() throws IOException {
        LittleEndianOutputStream leos = new LittleEndianOutputStream(new NullOutputStream());
        boolean z = false;
        byte b = (byte) 110;
        char c = '\u1234';
        short s = (short) 62344;
        int i = 876344;
        long l = 129994885L;
        float f = 27.0f + 2.0f / 3.0f;
        double d = Math.exp(243798.98234);
        String string = "this is a test";

        leos.writeBoolean(z);
        leos.writeByte(b);
        leos.writeChar(c);
        leos.writeShort(s);
        leos.writeInt(i);
        leos.writeLong(l);
        leos.writeFloat(f);
        leos.writeDouble(d);
        leos.writeBytes(string);
        leos.writeChars(string);
        leos.writeUTF(string);
        leos.close();

        int len = string.length();

        DataOutputStream dos = new DataOutputStream(new NullOutputStream());
        dos.writeUTF(string);
        dos.close();
        int utf_len = dos.size();

        int expected = 1 + 1 + 2 + 2 + 4 + 8 + 4 + 8 + len + 2 * len + utf_len;
        int got = leos.size();

        assertEquals(expected, got);
    }

}
