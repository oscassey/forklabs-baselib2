/*
 * Copyright (C) 2011  Forklabs Daniel Léonard
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
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class ConstantInputStreamTest {

    /**
     * Tests that the streams returns 0 at least 10,000 times in a row with
     * {@link ZeroInputStream#read()}.
     * @exception   IOException   never.
     */
     @Test
     public void testReadByte() throws IOException {
        var constant = 8;
        var in = new ConstantInputStream((byte) constant);
        for (int i = 0; i < 10000; i++) {
            assertEquals(constant, in.read());
        }
        in.close();
    }

    /**
     * Tests that the streams returns 0 at least 10,000 times in a row with
     * {@link ZeroInputStream#read(byte[])}.
     * @exception   IOException   never.
     */
     @Test
     public void testReadArray() throws IOException {
         var constant = 27;
         var in = new ConstantInputStream((byte) constant);
         var bytes = new byte[1000];
         for (int i = 0; i < 10; i++) {
             assertEquals(bytes.length, in.read(bytes));
             for (int j = 0; j < bytes.length; j++) {
                 assertEquals((byte) constant, bytes[j]);
             }
         }
         in.close();
     }

    /**
     * Tests that the streams returns 0 at least 2 times in a row with
     * {@link ZeroInputStream#read(byte[], int, int)}.
     * @exception   IOException   never.
     */
     @Test
     public void testReadPartArray() throws IOException {
         var constant = 64;
         var in = new ConstantInputStream((byte) constant);
         var bytes = new byte[] {(byte) 1, (byte) 2, (byte) 3, (byte) 4};
         in.close();
         assertEquals(2, in.read(bytes, 1, 2));
         assertEquals((byte) 1, bytes[0]);
         assertEquals((byte) constant, bytes[1]);
         assertEquals((byte) constant, bytes[2]);
         assertEquals((byte) 4, bytes[3]);
         }

    /**
     * Shows that the stream is infinite (or almost).
     * @exception   IOException   never.
     */
     @Test
     public void testSkipAndAvailable() throws IOException {
         var in = new ConstantInputStream((byte) 125);

         try {
            in.skip(-10L);
            fail("did not throw IllegalArgumentException");
            }
         catch (IllegalArgumentException iae) {
            // normal behaviour
            in.close();
            }

         assertEquals(Integer.MAX_VALUE, in.available());

         var expected = Long.MAX_VALUE;
         var got = in.skip(Long.MAX_VALUE);
         assertEquals(expected, got);

         assertEquals(Integer.MAX_VALUE, in.available());
         }

    /**
     * Tests the English error messages.
     * @exception   IOException   never.
     */
     @Test
     public void testErrorMessagesInEnglish() throws IOException {
         var locale = Locale.getDefault();
         Locale.setDefault(Locale.ENGLISH);

         try {
             var expected = new String[] {
                 "Cannot skip -1 bytes",
             };

             var in = new ConstantInputStream((byte) 216);
             var got = new String[] {
                 in.getNegativeSkipSizeErrorMessage(-1),
             };
             in.close();

             assertEquals(expected.length, got.length);

             for (int i = 0; i < expected.length; i++) {
                 assertEquals(expected[i], got[i], "[" + i + "]");
             }
         }
         finally {
             Locale.setDefault(locale);
         }
     }

    /**
     * Tests the French error messages.
     * @exception   IOException   never.
     */
     @Test
     public void testErrorMessagesEnFrançais() throws IOException {
         var locale = Locale.getDefault();
         Locale.setDefault(Locale.FRENCH);

         try {
            var expected = new String[] {
                "Impossible de sauter -1 octets",
            };

            var in = new ConstantInputStream((byte) 216);
            var got = new String[] {
                in.getNegativeSkipSizeErrorMessage(-1),
            };
            in.close();

            assertEquals(expected.length, got.length);

            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], got[i], "[" + i + "]");
            }
        }
        finally {
            Locale.setDefault(locale);
        }
    }

}
