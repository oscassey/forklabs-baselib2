/*
 * Copyright (C) 2021  Forklabs Daniel Léonard
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

import java.nio.charset.Charset;

/**
 * Class {@code Charsets} provides a place to list commonly used charsets.
 */
public abstract class Charsets {

//---------------------------
// Class variables
//---------------------------

    /** The charset {@code US-ACII}. */
    public static final Charset US_ASCII = Charset.forName("US-ASCII"); //$NON-NLS-1$
    /** The charset {@code ISO-8859-1}. */
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1"); //$NON-NLS-1$
    /** The charset {@code UTF-8}. */
    public static final Charset UTF_8 = Charset.forName("UTF-8");   //$NON-NLS-1$
    /** The charset {@code UTF-16BE}. */
    public static final Charset UTF_16_BE = Charset.forName("UTF-16BE"); //$NON-NLS-1$
    /** The charset {@code UTF-16LE}. */
    public static final Charset UTF_16_LE = Charset.forName("UTF-16LE"); //$NON-NLS-1$
    /** The charset {@code UTF-16}. */
    public static final Charset UTF_16 = Charset.forName("UTF-16"); //$NON-NLS-1$


//---------------------------
// Constructors
//---------------------------

    /**
     * Constructor.
     */
    protected Charsets() {
        // allow sub-classes only
    }

}
