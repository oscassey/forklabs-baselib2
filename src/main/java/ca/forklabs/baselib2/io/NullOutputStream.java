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

import java.io.OutputStream;

/**
 * Class {@code NullOuputStream} mimics {@code /dev/null} of UNIX systems.
 * Data written in this stream is lost. From the book:
 * <blockquote>
 * Elliote Rusty Harold,
 * <i><a target="_blank" href="http://www.oreilly.com/catalog/javaio/">Java I/O</a></i> (p.31),
 * O'Reilly, March 1999, ISBN 1-56592-485-1.
 * </blockquote>
 */
public class NullOutputStream extends OutputStream {

//---------------------------
// Overridden methods from java.io.OutputStream
//---------------------------

    /**
     * Does nothing.
     * @param   b   ignored.
     */
    @Override
    public void write(int b) {
        // nothing
    }

    /**
     * Does nothing.
     * @param   bytes   ignored.
     */
    @Override
    public void write(byte[] bytes) {
        // nothing
    }

    /**
     * Does nothing.
     * @param   bytes   ignored.
     * @param   off   ignored.
     * @param   len   ignored.
     */
    @Override
    public void write(byte[] bytes, int off, int len) {
        // nothing
    }

}
