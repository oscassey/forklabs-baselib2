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

import java.io.Writer;

/**
 * Class {@code NullWriter} is to writers what {@link NullOutputStream} is to
 * output streams.
 */
public class NullWriter extends Writer {

//---------------------------
// Overridden methods from java.io.Writer
//---------------------------

    /**
     * Does nothing.
     * @param   c   ignored.
     */
    @Override
    public void write(int c) {
        // nothing
    }

    /**
     * Does nothing.
     * @param   characters   ignored.
     */
    @Override
    public void write(char[] characters) {
        // nothing
    }

    /**
     * Does nothing.
     * @param   characters   ignored.
     * @param   off   ignored.
     * @param   len   ignored.
     */
    @Override
    public void write(char[] characters, int off, int len) {
        // nothing
    }

    /**
     * Does nothing.
     * @param   string   ignored.
     */
    @Override
    public void write(String string) {
        // nothing
    }

    /**
     * Does nothing.
     * @param   string   ignored.
     * @param   off   ignored.
     * @param   len   ignored.
     */
    @Override
    public void write(String string, int off, int len) {
        // nothing
    }

    /**
     * Does nothing. This writer cannot be closed.
     */
    @Override
    public void close() {
        // nothing
    }

    /**
     * Does nothing. Flushing has no effect.
     */
    @Override
    public void flush() {
        // nothing
    }

}
