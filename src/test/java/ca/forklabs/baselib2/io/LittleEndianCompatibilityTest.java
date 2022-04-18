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
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Class {@code LittleEndianCompatibilityTest} tests the compatibility between
 * {@link LittleEndianInputStream} and {@link LittleEndianOutputStream}.
 */
public class LittleEndianCompatibilityTest {

    /**
     * Tests the compatibility.
     */
    @Test
    public void testCompatibility() throws IOException {
        boolean z1 = true;
        boolean z2 = false;
        byte b = 45;
        char c = 'c';
        short s = -165;
        int i = 0xcafebabe;
        long l = 0x8877665544332211L;
        float f = (float) Math.PI;
        double d = Math.E;

        byte[] bytes;

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             LittleEndianOutputStream leos = new LittleEndianOutputStream(baos)) {
            leos.writeBoolean(z1);
            leos.writeByte(b);
            leos.writeChar(c);
            leos.writeShort(s);
            leos.writeInt(i);
            leos.writeLong(l);
            leos.writeFloat(f);
            leos.writeDouble(d);
            leos.writeBoolean(z2);
            leos.close();

            bytes = baos.toByteArray();
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             LittleEndianInputStream leis = new LittleEndianInputStream(bais)) {
            float epsilon_f = 10e-10f;
            double epsilon_d = 10e-10;

            assertEquals(z1, leis.readBoolean());
            assertEquals(b, leis.readByte());
            assertEquals(c, leis.readChar());
            assertEquals(s, leis.readShort());
            assertEquals(i, leis.readInt());
            assertEquals(l, leis.readLong());
            assertEquals(f, leis.readFloat(), epsilon_f);
            assertEquals(d, leis.readDouble(), epsilon_d);
            assertEquals(z2, leis.readBoolean());

            int eof = leis.read();
            assertEquals(-1, eof);
        }
    }

}
