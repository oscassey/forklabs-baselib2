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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * Class {@code ArraysTest} tests class {@link Arrays}.
 */
public class ArraysTest {

    /**
     * Tests {@link Arrays#memset(boolean[], boolean)}.
     */
    @Test
    public void testMemsetZ() {
        var array = new boolean[] { false, true, true, false };

        Arrays.memset(array, false, 1, 2);
        for (var i = 0; i < array.length; i++) {
            assertEquals(false, array[i]);
        }

        Arrays.memset(array, true);
        for (var i = 0; i < array.length; i++) {
            assertEquals(true, array[i]);
        }
    }

    /**
     * Tests {@link Arrays#memset(byte[], byte)}.
     */
    @Test
    public void testMemsetB() {
        byte zero = 0;
        byte one = 1;
        byte two = 2;
        byte three = 3;
        byte four = 4;
        byte ten = 10;
        var array = new byte[] { one, two, three, four };

        Arrays.memset(array, zero, 1, 2);
        assertEquals(one, array[0]);
        assertEquals(zero, array[1]);
        assertEquals(zero, array[2]);
        assertEquals(four, array[3]);

        Arrays.memset(array, ten);
        for (var i = 0; i < array.length; i++) {
            assertEquals(ten, array[i]);
        }
    }

    /**
     * Tests {@link Arrays#memset(char[], char)}.
     */
    @Test
    public void testMemsetC() {
        var array = new char[] { 'a', 'b', 'c', 'd' };

        Arrays.memset(array, '0', 1, 2);
        assertEquals('a', array[0]);
        assertEquals('0', array[1]);
        assertEquals('0', array[2]);
        assertEquals('d', array[3]);

        Arrays.memset(array, 'z');
        for (var i = 0; i < array.length; i++) {
            assertEquals('z', array[i]);
        }
    }

    /**
     * Tests {@link Arrays#memset(short[], short)}.
     */
    @Test
    public void testMemsetS() {
        short zero = 0;
        short one = 1;
        short two = 2;
        short three = 3;
        short four = 4;
        short ten = 10;
        var array = new short[] { one, two, three, four };

        Arrays.memset(array, zero, 1, 2);
        assertEquals(one, array[0]);
        assertEquals(zero, array[1]);
        assertEquals(zero, array[2]);
        assertEquals(four, array[3]);

        Arrays.memset(array, ten);
        for (var i = 0; i < array.length; i++) {
            assertEquals(ten, array[i]);
        }
    }

    /**
     * Tests {@link Arrays#memset(int[], int)}.
     */
    @Test
    public void testMemsetI() {
        var array = new int[] { 1, 2, 3, 4 };

        Arrays.memset(array, 0, 1, 2);
        assertEquals(1, array[0]);
        assertEquals(0, array[1]);
        assertEquals(0, array[2]);
        assertEquals(4, array[3]);

        Arrays.memset(array, 10);
        for (var i = 0; i < array.length; i++) {
            assertEquals(10, array[i]);
        }
    }

    /**
     * Tests {@link Arrays#memset(long[], long)}.
     */
    @Test
    public void testMemsetL() {
        var array = new long[] { 1L, 2L, 3L, 4L };

        Arrays.memset(array, 0L, 1, 2);
        assertEquals(1L, array[0]);
        assertEquals(0L, array[1]);
        assertEquals(0L, array[2]);
        assertEquals(4L, array[3]);

        Arrays.memset(array, 10L);
        for (var i = 0; i < array.length; i++) {
            assertEquals(10L, array[i]);
        }
    }

    /**
     * Tests {@link Arrays#memset(float[], float)}.
     */
    @Test
    public void testMemsetF() {
        var array = new float[] { 1.0f, 2.0f, 3.0f, 4.0f };

        Arrays.memset(array, 0.0f, 1, 2);
        assertEquals(1.0f, array[0], 1e-10);
        assertEquals(0.0f, array[1], 1e-10);
        assertEquals(0.0f, array[2], 1e-10);
        assertEquals(4.0f, array[3], 1e-10);

        Arrays.memset(array, 10.0f);
        for (var i = 0; i < array.length; i++) {
            assertEquals(10.0f, array[i], 1e-10f);
        }
    }

    /**
     * Tests {@link Arrays#memset(double[], double)}.
     */
    @Test
    public void testMemsetD() {
        var array = new double[] { 1.0, 2.0, 3.0, 4.0 };

        Arrays.memset(array, 0.0, 1, 2);
        assertEquals(1.0, array[0], 1e-10);
        assertEquals(0.0, array[1], 1e-10);
        assertEquals(0.0, array[2], 1e-10);
        assertEquals(4.0, array[3], 1e-10);

        Arrays.memset(array, 10.0);
        for (var i = 0; i < array.length; i++) {
            assertEquals(10.0, array[i], 1e-10);
        }
    }

    /**
     * Tests {@link Arrays#memset(Object[], Object)}.
     */
    @Test
    public void testMemsetO() {
        var array = new String[] { "one", "two", "three", "four" };

        Arrays.memset(array, "this", 1, 2);
        assertEquals("one", array[0]);
        assertEquals("this", array[1]);
        assertEquals("this", array[2]);
        assertEquals("four", array[3]);
        assertSame(array[1], array[2]);

        Arrays.memset(array, "again");
        for (var i = 0; i < array.length; i++) {
            assertEquals("again", array[i]);
        }
        for (var i = 0; i < array.length - 1; i++) {
            assertSame(array[i], array[i + 1]);
        }
    }

    /**
     * Tests {@link Arrays#checkOffLen(int, int, int)}.
     */
    @Test
    public void testCheckOffLen() {
        try {
            Arrays.checkOffLen(-1, 0, 2);
            fail("did not throw ArrayIndexOutOfBoundsException");
        }
        catch (ArrayIndexOutOfBoundsException aioobe) {
            // normal behaviour
        }

        try {
            Arrays.checkOffLen(2, -1, 2);
            fail("did not throw ArrayIndexOutOfBoundsException");
        }
        catch (ArrayIndexOutOfBoundsException aioobe) {
            // normal behaviour
        }
        try {
            Arrays.checkOffLen(2, 2, 2);
            fail("did not throw ArrayIndexOutOfBoundsException");
        }
        catch (ArrayIndexOutOfBoundsException aioobe) {
            // normal behaviour
        }

        try {
            Arrays.checkOffLen(2, 1, -1);
            fail("did not throw IllegalArgumentException");
        }
        catch (IllegalArgumentException iae) {
            // normal behaviour
        }
        try {
            Arrays.checkOffLen(2, 1, 2);
            fail("did not throw IllegalArgumentException");
        }
        catch (IllegalArgumentException iae) {
            // normal behaviour
        }
    }

    /**
     * Tests the English error messages.
     */
    @Test
    public void testErrorMessagesInEnglish() {
        var locale = Locale.getDefault();
        Locale.setDefault(Locale.ENGLISH);

        try {
            var expected = new String[] {
                "Object is null",
                "Array length is 0, no interval is valid",
                "Parameter off (4) is not within array bounds [0, 3[",
                "Parameter len (-1) cannot be negative",
                "End of interval outside array bound [0, 4[ => (3 + 2) > 4",
            };

            var got = new String[] {
                Arrays.getObjectNullErrorMessage(),
                Arrays.getNoValidIntervalErrorMessage(0),
                Arrays.getBadOffsetErrorMessage(4, 3),
                Arrays.getBadLenghtErrorMessage(-1),
                Arrays.getEndNotWithinBoundsErrorMessage(4, 3, 2),
            };

            assertEquals(expected.length, got.length);

            for (var i = 0; i < expected.length; i++) {
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
    public void testErrorMessagesEnFrançais() {
        var locale = Locale.getDefault();
        Locale.setDefault(Locale.FRENCH);

        try {
            var expected = new String[] {
                "L'objet est null",
                "La taille du tableau est 0, aucun intervalle n'est valide",
                "Le paramêtre off (4) n'est pas dans l'intervalle [0, 3[",
                "Le paramêtre len (-1) ne peut être négatif",
                "La fin de l'intervalle n'est pas dans les bornes du tableau [0, 4[ => (3 + 2) > 4",
            };

            var got = new String[] {
                Arrays.getObjectNullErrorMessage(),
                Arrays.getNoValidIntervalErrorMessage(0),
                Arrays.getBadOffsetErrorMessage(4, 3),
                Arrays.getBadLenghtErrorMessage(-1),
                Arrays.getEndNotWithinBoundsErrorMessage(4, 3, 2),
            };

            assertEquals(expected.length, got.length);

            for (var i = 0; i < expected.length; i++) {
                assertEquals(expected[i], got[i], "[" + i + "]");
            }
        }
        finally {
            Locale.setDefault(locale);
        }
    }

}
