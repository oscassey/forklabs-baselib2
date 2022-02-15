/*
 * Copyright (C)  2022  Forklabs Daniel Léonard
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
 * Class {@code Arrays} contains methods that acts on arrays. It complements the
 * core library class {@link java.util.Arrays}.
 */
public class Arrays {

//---------------------------
// Constructor
//---------------------------

    /**
     * Let no one instanciate this class.
     */
    private Arrays() {
        // nothing
    }


//---------------------------
// memset()
//---------------------------

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @see #memset(boolean[], boolean, int, int)
     */
    public static void memset(boolean[] array, boolean value) {
        memset(array, value, 0, array.length);
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @see #memset(byte[], byte, int, int)
     */
    public static void memset(byte[] array, byte value) {
        memset(array, value, 0, array.length);
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @see #memset(char[], char, int, int)
     */
    public static void memset(char[] array, char value) {
        memset(array, value, 0, array.length);
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @see #memset(short[], short, int, int)
     */
    public static void memset(short[] array, short value) {
        memset(array, value, 0, array.length);
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @see #memset(int[], int, int, int)
     */
    public static void memset(int[] array, int value) {
        memset(array, value, 0, array.length);
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @see #memset(long[], long, int, int)
     */
    public static void memset(long[] array, long value) {
        memset(array, value, 0, array.length);
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @see #memset(float[], float, int, int)
     */
    public static void memset(float[] array, float value) {
        memset(array, value, 0, array.length);
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @see #memset(double[], double, int, int)
     */
    public static void memset(double[] array, double value) {
        memset(array, value, 0, array.length);
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @see #memset(Object[], Object, int, int)
     */
    public static void memset(Object[] array, Object value) {
        memset(array, value, 0, array.length);
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @param   offset   the offset in the array.
     * @param   len   the length of the interval.
     * @see #memset(int[], int, int, int)
     */
    public static void memset(boolean[] array, boolean value, int offset, int len) {
        checkArray(array, offset, len);
        if (len > 0) {
            array[offset] = value;
        }
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, offset, array, i + offset, ((len - i) < i) ? (len - i) : i);
        }
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @param   offset   the offset in the array.
     * @param   len   the length of the interval.
     * @see #memset(int[], int, int, int)
     */
    public static void memset(byte[] array, byte value, int offset, int len) {
        checkArray(array, offset, len);
        if (len > 0) {
            array[offset] = value;
        }
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, offset, array, i + offset, ((len - i) < i) ? (len - i) : i);
        }
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @param   offset   the offset in the array.
     * @param   len   the length of the interval.
     * @see #memset(int[], int, int, int)
     */
    public static void memset(char[] array, char value, int offset, int len) {
        checkArray(array, offset, len);
        if (len > 0) {
            array[offset] = value;
        }
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, offset, array, i + offset, ((len - i) < i) ? (len - i) : i);
        }
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @param   offset   the offset in the array.
     * @param   len   the length of the interval.
     * @see #memset(int[], int, int, int)
     */
    public static void memset(short[] array, short value, int offset, int len) {
        checkArray(array, offset, len);
        if (len > 0) {
            array[offset] = value;
        }
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, offset, array, i + offset, ((len - i) < i) ? (len - i) : i);
        }
    }

    /**
     * Similar to the C function {@code <a target="_blank" href="http://www.dinkumware.com/htm_cl/string.html#memset">memset</a>}.
     * Adaption from <a target="_blank" href="http://javafaq.com/cjlfara.html">the Java FAQ</a>.
     * @param   array   the array to set each element at {@code value}.
     * @param   value   the value to set each element of {@code array}.
     * @param   offset  the index of the first element of the interval.
     * @param   len     the length of the interval.
     */
    public static void memset(int[] array, int value, int offset, int len) {
        checkArray(array, offset, len);
        if (len > 0) {
            array[offset] = value;
        }
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, offset, array, i + offset, ((len - i) < i) ? (len - i) : i);
        }
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @param   offset   the offset in the array.
     * @param   len   the length of the interval.
     * @see #memset(int[], int, int, int)
     */
    public static void memset(long[] array, long value, int offset, int len) {
        checkArray(array, offset, len);
        if (len > 0) {
            array[offset] = value;
        }
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, offset, array, i + offset, ((len - i) < i) ? (len - i) : i);
        }
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @param   offset   the offset in the array.
     * @param   len   the length of the interval.
     * @see #memset(int[], int, int, int)
     */
    public static void memset(float[] array, float value, int offset, int len) {
        checkArray(array, offset, len);
        if (len > 0) {
            array[offset] = value;
        }
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, offset, array, i + offset, ((len - i) < i) ? (len - i) : i);
        }
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @param   offset   the offset in the array.
     * @param   len   the length of the interval.
     * @see #memset(int[], int, int, int)
     */
    public static void memset(double[] array, double value, int offset, int len) {
        checkArray(array, offset, len);
        if (len > 0) {
            array[offset] = value;
        }
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, offset, array, i + offset, ((len - i) < i) ? (len - i) : i);
        }
    }

    /**
     * Sets each element of the array to the specified value.
     * @param   array   the array.
     * @param   value   the value.
     * @param   offset   the offset in the array.
     * @param   len   the length of the interval.
     * @param   <E>   the type of the elements in the array.
     * @see #memset(int[], int, int, int)
     */
    public static <E> void memset(E[] array, E value, int offset, int len) {
        checkArray(array, offset, len);
        if (len > 0) {
            array[offset] = value;
        }
        for (int i = 1; i < len; i += i) {
            System.arraycopy(array, offset, array, i + offset, ((len - i) < i) ? (len - i) : i);
        }
    }


//---------------------------
// checkArray()
//---------------------------

    /**
     * Throws an exception is the region {@code [off, off + len]} is not
     * within the array bounds
     * @param   array   the array.
     * @param   off   the begin offset in the array.
     * @param   len   the length of the interval
     * @see  #checkNull(Object)
     * @see  #checkOffLen(int, int, int)
     */
    public static void checkArray(boolean[] array, int off, int len) {
        checkNull(array);
        checkOffLen(array.length, off, len);
    }

    /**
     * Throws an exception is the region {@code [off, off + len]} is not
     * within the array bounds
     * @param   array   the array.
     * @param   off   the begin offset in the array.
     * @param   len   the length of the interval
     * @see  #checkNull(Object)
     * @see  #checkOffLen(int, int, int)
     */
    public static void checkArray(byte[] array, int off, int len) {
        checkNull(array);
        checkOffLen(array.length, off, len);
    }

    /**
     * Throws an exception is the region {@code [off, off + len]} is not
     * within the array bounds
     * @param   array   the array.
     * @param   off   the begin offset in the array.
     * @param   len   the length of the interval
     * @see  #checkNull(Object)
     * @see  #checkOffLen(int, int, int)
     */
    public static void checkArray(char[] array, int off, int len) {
        checkNull(array);
        checkOffLen(array.length, off, len);
    }

    /**
     * Throws an exception is the region {@code [off, off + len]} is not
     * within the array bounds
     * @param   array   the array.
     * @param   off   the begin offset in the array.
     * @param   len   the length of the interval
     * @see  #checkNull(Object)
     * @see  #checkOffLen(int, int, int)
     */
    public static void checkArray(short[] array, int off, int len) {
        checkNull(array);
        checkOffLen(array.length, off, len);
    }

    /**
     * Throws an exception is the region {@code [off, off + len]} is not
     * within the array bounds
     * @param   array   the array.
     * @param   off   the begin offset in the array.
     * @param   len   the length of the interval
     * @see  #checkNull(Object)
     * @see  #checkOffLen(int, int, int)
     */
    public static void checkArray(int[] array, int off, int len) {
        checkNull(array);
        checkOffLen(array.length, off, len);
    }

    /**
     * Throws an exception is the region {@code [off, off + len]} is not
     * within the array bounds
     * @param   array   the array.
     * @param   off   the begin offset in the array.
     * @param   len   the length of the interval
     * @see  #checkNull(Object)
     * @see  #checkOffLen(int, int, int)
     */
    public static void checkArray(long[] array, int off, int len) {
        checkNull(array);
        checkOffLen(array.length, off, len);
    }

    /**
     * Throws an exception is the region {@code [off, off + len]} is not
     * within the array bounds
     * @param   array   the array.
     * @param   off   the begin offset in the array.
     * @param   len   the length of the interval
     * @see  #checkNull(Object)
     * @see  #checkOffLen(int, int, int)
     */
    public static void checkArray(float[] array, int off, int len) {
        checkNull(array);
        checkOffLen(array.length, off, len);
    }

    /**
     * Throws an exception is the region {@code [off, off + len]} is not
     * within the array bounds
     * @param   array   the array.
     * @param   off   the begin offset in the array.
     * @param   len   the length of the interval
     * @see  #checkNull(Object)
     * @see  #checkOffLen(int, int, int)
     */
    public static void checkArray(double[] array, int off, int len) {
        checkNull(array);
        checkOffLen(array.length, off, len);
    }

    /**
     * Throws an exception is the region {@code [off, off + len]} is not
     * within the array bounds
     * @param <E>
     * @param   array   the array.
     * @param   off   the begin offset in the array.
     * @param   len   the length of the interval
     * @see  #checkNull(Object)
     * @see  #checkOffLen(int, int, int)
     */
    public static <E> void checkArray(E[] array, int off, int len) {
        checkNull(array);
        checkOffLen(array.length, off, len);
    }


//---------------------------
// Class methods
//---------------------------

    /**
     * Checks to see if the given object is {@code null}.
     * @param   object   the object
     * @throws   NullPointerException   if the object is {@code null}.
     */
    public static void checkNull(Object object) throws NullPointerException {
        if (null == object) {
            var message = Arrays.getObjectNullErrorMessage();
            throw new NullPointerException(message);
        }
    }

    /**
     * Given the array length, checks to see if the region
     * {@code [off, off + len]} is within the array bounds. The method
     * throws an exception if the region, or part of it, is not within the
     * bounds of the array.
     * @param  arrayLen  the length of the array.
     * @param  off       the offset in the the array.
     * @param  len       the length of the interval.
     * @exception  ArrayIndexOutOfBoundsException  if {@code arrayLen &lt; 0}.
     * @exception  ArrayIndexOutOfBoundsException  if {@code off} is not within }[0, arrayLen - 1].
     * @exception  IllegalArgumentException        if {@code len &lt; 0}.
     * @exception  IllegalArgumentException        if {@code off + len &gt; arrayLen}.
     */
    public static void checkOffLen(int arrayLen, int off, int len) throws IllegalArgumentException, ArrayIndexOutOfBoundsException {
        if ((arrayLen == 0) && (off == 0) && (len == 0)) {
            return;
        }
        if (arrayLen < 0) {
            var message = getNoValidIntervalErrorMessage(arrayLen);
            throw new ArrayIndexOutOfBoundsException(message);
        }
        if (off < 0 || off > arrayLen - 1) {
            var message = getBadOffsetErrorMessage(off, arrayLen);
            throw new ArrayIndexOutOfBoundsException(message);
        }
        if (len < 0) {
            var message = getBadLenghtErrorMessage(len);
            throw new IllegalArgumentException(message);
        }
        if ((off + len) > arrayLen) {
            var message = getEndNotWithinBoundsErrorMessage(arrayLen, off, len);
            throw new IllegalArgumentException(message);
        }
     }


//---------------------------
// Error message class methods
//---------------------------

    /**
     * Gets the formatted error message that says that the object is
     * {@code null}.
     * @return   the formatted error message.
     */
    protected static String getObjectNullErrorMessage() {
        var key = Resources.ARRAYS_NULL;
        var message = Resources.getLocalizedString(key);
        return message;
    }

    /**
     * Gets the formatted error message that says that there exist no interval.
     * @param   len   the lenght of the array.
     * @return   the formatted error message.
     */
    protected static String getNoValidIntervalErrorMessage(int len) {
        var key = Resources.ARRAYS_ZERO_LENGTH;
        var message = Resources.getLocalizedString(key, len);
        return message;
    }

    /**
     * Gets the formatted error message that says that the offset is out of
     * bounds.
     * @param   off   the offset.
     * @param   len   the len of the array.
     * @return   the formatted error message.
     */
    protected static String getBadOffsetErrorMessage(int off, int len) {
        var key = Resources.ARRAYS_NOT_WITHIN_BOUNDS;
        var message = Resources.getLocalizedString(key, off, len);
        return message;
    }

    /**
     * Gets the formatted error message that says that the length of the interval
     * is negative.
     * @param   len   the length of the interval.
     * @return   the formatted error message.
     */
    protected static String getBadLenghtErrorMessage(int len) {
        var key = Resources.ARRAYS_NEGATIVE_LENGTH;
        var message = Resources.getLocalizedString(key, len);
        return message;
    }

    /**
     * Gets the formatted error message that says that the end of the interval
     * goes past the end of the array.
     * @param   arrayLen   the length of the array.
     * @param   off   the offset in the array.
     * @param   len   the length of the interval.
     * @return   the formatted error message.
     */
    protected static String getEndNotWithinBoundsErrorMessage(int arrayLen, int off, int len) {
        var key = Resources.ARRAYS_END_NOT_WITHIN_BOUNDS;
        var message = Resources.getLocalizedString(key, arrayLen, off, len);
        return message;
    }

}
