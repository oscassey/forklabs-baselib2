/*
 * Copyright (C) 2006  Daniel Léonard
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
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Class {@code RandomInputStreamTest} tests class {@link RandomInputStream}.
 */
public class RandomInputStreamTest {

    /**
     * Tests that the streams returns what it is supposed to return.
     * @exception   IOException   never.
     */
    @Test
    public void testReadByte() throws IOException {
        var seed = 0xcafebabe;
        var internal = new Random(seed);
        var external = new Random(seed);

        var in = new RandomInputStream(internal);
        for (int i = 0; i < 10000; i++) {
            int expected = external.nextInt(256);
            int got = in.read();

            assertEquals(expected, got);
        }
        in.close();
    }

    /**
     * Tests that the streams returns what it is supposed to return.
     * @exception   IOException   never.
     */
    @Test
    public void testReadArray() throws IOException {
        var seed = 0xcafebabe;
        var internal = new Random(seed);
        var external = new Random(seed);

        var in = new RandomInputStream(internal);
        var expected = new byte[1000];
        var got = new byte[1000];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < expected.length; j++) {
                expected[j] = (byte) external.nextInt(256);
            }

            assertEquals(got.length, in.read(got));
            for (int j = 0; j < got.length; j++) {
                assertEquals(expected[j], got[j]);
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
        var seed = 0xcafebabe;
        var internal = new Random(seed);
        var external = new Random(seed);

        var expected = new byte[] {
            (byte) external.nextInt(256),
            (byte) external.nextInt(256),
        };

        var in = new RandomInputStream(internal);
        var bytes = new byte[] {(byte) 1, (byte) 2, (byte) 3, (byte) 4};
        in.close();
        assertEquals(2, in.read(bytes, 1, 2));
        assertEquals((byte) 1, bytes[0]);
        assertEquals(expected[0], bytes[1]);
        assertEquals(expected[1], bytes[2]);
        assertEquals((byte) 4, bytes[3]);
        }

    /**
     * Shows that the stream is infinite (or almost).
     * @exception   IOException   never.
     */
    @Test
    public void testSkipAndAvailable() throws IOException {
        var seed = 0xcafebabe;
        var internal = new Random(seed);
        var external = new Random(seed);

        var in = new RandomInputStream(internal);

        // negative skip
        try {
            in.skip(-10L);
            fail("did not throw IllegalArgumentException");
        }
        catch (IllegalArgumentException iae) {
            // normal behaviour
        }

        // available
        assertEquals(Integer.MAX_VALUE, in.available());

        // skip a lot, but not too much
        var a_lot_but_not_too_much = 1000000;
        var expected = a_lot_but_not_too_much;
        var got = in.skip(a_lot_but_not_too_much);
        assertEquals(expected, got);

        // check that the source skipped
        for (int i = 0; i < a_lot_but_not_too_much; i++) {
            external.nextInt(256);
        }
        assertEquals(external.nextInt(256), in.read());

        assertEquals(Integer.MAX_VALUE, in.available());

        in.close();
    }

    /**
     * Tests {@link RandomInputStream#setRandom(Random)}.
     */
    @Test
    public void testNullRandom() throws IOException {
        try (var in = new RandomInputStream(null)) {
            fail("did not throw NullPointerException");
        }
        catch (NullPointerException npe) {
            // normal behaviour
        }
    }

    /**
     * Tests the English error messages.
     */
    @Test
    public void testErrorMessagesInEnglish() throws IOException {
        var locale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        try (var ris = new RandomInputStream(new Random())) {
            var expected = new String[] {
                "Cannot skip -1 bytes",
                "The source of randomness cannot be null"
            };

            var got = new String[] {
                ris.getNegativeSkipSizeErrorMessage(-1),
                ris.getNullRandomErrorMessage(),
            };

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
     */
    @Test
    public void testErrorMessagesEnFrançais() throws IOException {
        var locale = Locale.getDefault();
        Locale.setDefault(Locale.FRENCH);

        try (var ris = new RandomInputStream(new Random())) {
            var expected = new String[] {
                "Impossible de sauter -1 octets",
                "La source de hazard ne peut être null"
            };

            var got = new String[] {
                ris.getNegativeSkipSizeErrorMessage(-1),
                ris.getNullRandomErrorMessage(),
            };

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
