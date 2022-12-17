/*
 * @(#) $Header$
 *
 * Copyright (C)  2010  Forklabs Daniel Léonard
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

import java.util.function.Predicate;

/**
 * Class {@code AlwaysTrue} is a predicate that always returns {code true}.
 *
 * @param   <T>   the type of the argument object.
 *
 * This class is thread-safe because it does not have any state.
 */
public class AlwaysTrue<T> implements Predicate<T> {

//---------------------------
// Implemented method from java.util.function.Predicate
//---------------------------

    /**
     * Always returns {@code true}.
     * @param   t   ignored.
     * @return   {@code true}.
     */
    @Override
    public boolean test(T t) {
        return true;
    }

}
