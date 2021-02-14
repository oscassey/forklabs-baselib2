/*
 * Copyright (C)  2021  Daniel Léonard
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

import java.util.Comparator;

import ca.forklabs.baselib2.util.comparator.MagnitudeComparator;

/**
 * Class {@code Comparators} provides factory methods for {@link Comparator}s.
 */
public class Comparators {

//---------------------------
// Constructor
//---------------------------

    /**
     * Let no one instantiate this class.
     */
    protected Comparators() {
        // nothing
    }


//---------------------------
// Class methods
//---------------------------

    /**
     * Creates a new magnitude comparator.
     * @return   a new comparator.
     */
    public static Comparator<Number> magnitude() {
        var comparator = MagnitudeComparator.create();
        return comparator;
    }

}
