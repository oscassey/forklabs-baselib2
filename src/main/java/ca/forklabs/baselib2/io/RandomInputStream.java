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

import java.io.InputStream;
import java.util.Random;

import ca.forklabs.baselib2.util.Arrays;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Class {@code RandomInputStream} mimics {@code /dev/random} of UNIX systems.
 * This streams has an unlimited number of bytes that can be read. It does not
 * support marking and resetting.
 */
@Getter
@RequiredArgsConstructor
@Accessors(prefix = {"_"})
public class RandomInputStream extends InputStream {

//---------------------------
// Instance variable
//---------------------------

    /** The random source. */
    @NonNull
    private final Random _source;


//---------------------------
// Overridden methods from java.io.InputStream
//---------------------------

    /**
     * Gives the amount of bytes left.
     * @return   always {@link Integer#MAX_VALUE}.
     */
    @Override
    public int available() {
        return Integer.MAX_VALUE;
    }

    /**
     * Returns a random value between {@code 0x00} and {@code 0xff}.
     * @return   a random value between {@code 0x00} and {@code 0xff}.
     */
    @Override
    public int read() {
        synchronized (_source) {
            var value = _source.nextInt(256);
            return value;
        }
    }

    /**
     * Fills the sub-array with random values.
     * @param   b   the array.
     * @param   off   the index from where to begin.
     * @param   len   the length of the sub-array.
     * @return  {@code len}.
     */
    @Override
    public int read(byte[] b, int off, int len) {
        Arrays.checkArray(b, off, len);

        synchronized (_source) {
            var begin = off;
            var end = off + len;
            for (int i = begin; i < end; i++) {
                b[i] = (byte) _source.nextInt(256);
            }
        }

        return len;
    }

    /**
     * Skips {@code n} bytes. This method can be slow because it will
     * generate and discard {@code n} random bytes.
     * @param   n   the number of bytes to skip.
     * @return   {@code n}.
     * @exception   IllegalArgumentException   if {@code n < 0}.
     */
    @Override
    public long skip(long n) {
        if (0L > n) {
            var message = this.getNegativeSkipSizeErrorMessage(n);
            throw new IllegalArgumentException(message);
        }

        synchronized (_source) {
            for (long l = 0L; l < n; l++) {
                _source.nextInt(256);
            }
        }

        return n;
    }


//---------------------------
// Instance methods
//---------------------------

    /**
     * Gets the formatted error message telling that the source of randomness is
     * {@code null}.
     * @return   the formatted error message.
     */
    protected String getNullRandomErrorMessage() {
        var key = Resources.RANDOM_INPUT_STREAM_NULL_RANDOM;
        var message = Resources.getLocalizedString(key);
        return message;
    }

    /**
     * Gets the formatted error message telling that the skip size is negative.
     * @param   n   the skip size.
     * @return   the formatted error message.
     */
    @SuppressWarnings("boxing")
    protected String getNegativeSkipSizeErrorMessage(long n) {
        var key = Resources.INPUT_STREAM_NEGATIVE_SKIP;
        var message = Resources.getLocalizedString(key, n);
        return message;
    }

}
