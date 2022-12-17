/*
 * @(#) $Header$
 *
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

package ca.forklabs.baselib2.util.predicates;

import java.util.Comparator;
import java.util.function.BiPredicate;

import lombok.AllArgsConstructor;
import lombok.NonNull;

/**
 * Class {@code IsSmallerThan} is a predicate that mimics the function <em>is smaller than</em>.
 *
 * @param   <T>   the type of the argument object.
 *
 * This class is thread-safe if and only if the comparator is thread-safe.
 */
@AllArgsConstructor
public class IsSmallerThan<T> implements BiPredicate<T, T> {

    @NonNull
    private final Comparator<T> _comparator;


//---------------------------
// Implemented method from java.util.function.BiPredicate
//---------------------------

    /**
     * Determines if {@code t1 < t2}.
     * @param   t1   the first input argument.
     * @param   t2   the second input argument.
     * @return   {@code true} if  {@code t1 < t2}, {@code false} otherwise.
     */
    @Override
    public boolean test(T t1, T t2) {
        int comparison = _comparator.compare(t1, t2);
        boolean isSmallerThan = comparison < 0;
        return isSmallerThan;
    }

}
