/*
 * Copyright (C)  2011  Daniel Léonard
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

import ca.forklabs.baselib2.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

/**
 * Class {@code ConstantInputStream} provides an unlimited number of bytes that
 * can be read, all of them with the same value. It does not support marking nor
 * resetting.
 */
@Getter
@RequiredArgsConstructor
@Accessors(prefix = {"_"})
public class ConstantInputStream extends InputStream {

//---------------------------
// Instance variables
//---------------------------

    /** The constant value. */
    private final byte _constant;


//---------------------------
// Overridden methods from java.io.InputStream
//---------------------------

    /**
     * Gives the amount of bytes left.
     * @return   always {@link Integer#MAX_VALUE}.
     */
    @Override
    public int available() {
        var available = Integer.MAX_VALUE;
        return available;
    }

    /**
     * Gets the next byte of input.
     * @return   always the constant set at construction.
     */
    @Override
    public int read() {
        return _constant;
    }

    /**
     * Fills the section the array with the constant value.
     * @param   bytes   the array.
     * @param   off   the index from where to begin.
     * @param   len   the length of the sub-array.
     * @return  <code>len</code>.
     */
    @Override
    public int read(byte[] bytes, int off, int len) {
        Arrays.memset(bytes, _constant, off, len);
        return len;
    }

    /**
     * Skips <code>n</code> bytes.
     * @param   n   the number of bytes to skip.
     * @return   <code>n</code>.
     * @exception   IllegalArgumentException   if <code>n < 0</code>.
     */
    @Override
    public long skip(long n) {
        if (n < 0L) {
            String message = this.getNegativeSkipSizeErrorMessage(n);
            throw new IllegalArgumentException(message);
        }
        return n;
    }


//---------------------------
// Instance methods
//---------------------------

    /**
     * Gets the formatted error message telling that the skip size is negative.
     * @param   n   the skip size.
     * @return   the formatted error message.
     */
    protected String getNegativeSkipSizeErrorMessage(long n) {
        String key = Resources.INPUT_STREAM_NEGATIVE_SKIP;
        String message = Resources.getLocalizedString(key, n);
        return message;
    }

}
