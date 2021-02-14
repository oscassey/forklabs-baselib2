/*
 * Copyright (C) 2021  Daniel Léonard
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

package ca.forklabs.baselib2.util.comparator;

import java.util.Comparator;

import lombok.NonNull;

/**
 * Class {@code MagnitudeComparator} compares two numbers using their absolute
 * {@link Number#doubleValue()}.
 *
 * This class does not have any state. It is safe to use the same instance in
 * multiple threads.
 */
public class MagnitudeComparator implements Comparator<Number> {

//---------------------------
// Implemented methods from java.util.Comparator
//---------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public int compare(@NonNull Number number1, @NonNull Number number2) {
        var d1 = number1.doubleValue();
        var d2 = number2.doubleValue();

        var abs1 = Math.abs(d1);
        var abs2 = Math.abs(d2);

        int comparison = Double.compare(abs1, abs2);
        return comparison;
    }


//---------------------------
// Class methods
//---------------------------

    /**
     * Creates a new reverse comparator.
     * @param   comparator   the adapted comparator.
     * @param   <T>   the type being compared.
     * @return   a reverse comparator.
     */
    public static MagnitudeComparator create() {
        MagnitudeComparator comparator = new MagnitudeComparator();
        return comparator;
    }

}
