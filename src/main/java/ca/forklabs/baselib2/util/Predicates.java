/*
 * Copyright (C) 2022  Forklabs Daniel Léonard
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
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import ca.forklabs.baselib2.util.predicates.AlwaysTrue;
import ca.forklabs.baselib2.util.predicates.IsEqualTo;
import ca.forklabs.baselib2.util.predicates.IsGreaterThan;
import ca.forklabs.baselib2.util.predicates.IsSmallerThan;


/**
 * Class {@code Predicates} provides factory methods for {@link Predicate}s and
 * {@link BiPredicate}s.
 */
public class Predicates {

//---------------------------
// Constructor
//---------------------------

    /**
     * Let no one instantiate this class.
     */
    protected Predicates() {
        // nothing
    }


//---------------------------
// Class methods
//---------------------------

    /**
     * Generates a predicate that always evaluates to {@code true}.
     *
     * @param   <T>   the type of the argument.
     * @return   the always true predicate.
     */
    public static <T> Predicate<T> alwaysTrue() {
        var alwaysTrue = new AlwaysTrue<T>();
        return alwaysTrue;
    }

    /**
     * Generates a predicate that always evaluates to {@code false}.
     *
     * @param   <T>   the type of the argument.
     * @return   the always false predicate.
     */
    public static <T> Predicate<T> alwaysFalse() {
        var alwaysTrue = (Predicate<T>) alwaysTrue();
        var alwaysFalse = alwaysTrue.negate();
        return alwaysFalse;
    }

    /**
     * Generates a predicate that determines if the first argument is smaller than the second
     * argument according to the given comparator.
     *
     * @param   <T>   the type of the argument.
     * @param   comparator   the comparator.
     * @return   a predicate that mimics the function <em>is smaller than</em>.
     */
    public static <T> BiPredicate<T, T> isSmallerThan(Comparator<T> comparator) {
        var isSmallerThan = new IsSmallerThan<>(comparator);
        return isSmallerThan;
    }

    /**
     * Generates a predicate that determines if the first argument is equal to the second argument
     * according to the given comparator.
     *
     * @param   <T>   the type of the argument.
     * @param   comparator   the comparator.
     * @return   a predicate that mimics the function <em>is equal to</em>.
     */
    public static <T> BiPredicate<T, T> isEqualTo(Comparator<T> comparator) {
        var isEqualTo = new IsEqualTo<>(comparator);
        return isEqualTo;
    }

    /**
     * Generates a predicate that determines if the first argument is greater than the second
     * argument according to the given comparator.
     *
     * @param   <T>   the type of the argument.
     * @param   comparator   the comparator.
     * @return   a predicate that mimics the function <em>is greater than</em>.
     */
    public static <T> BiPredicate<T, T> isGreaterThan(Comparator<T> comparator) {
        var isGreaterThan = new IsGreaterThan<>(comparator);
        return isGreaterThan;
    }

    /**
     * Generates a predicate that determines if the first argument is smaller than or equal to the
     * second argument according to the given comparator.
     *
     * @param   <T>   the type of the argument.
     * @param   comparator   the comparator.
     * @return   a predicate that mimics the function <em>is smaller than or equal to</em>.
     */
    public static <T> BiPredicate<T, T> isSmallerThanOrEqualTo(Comparator<T> comparator) {
        var isSmallerThan = isSmallerThan(comparator);
        var isEqualTo = isEqualTo(comparator);
        var isSmallerThanOrEqualTo = isSmallerThan.or(isEqualTo);
        return isSmallerThanOrEqualTo;
    }

    /**
     * Generates a predicate that determines if the first argument is greater than or equal to the
     * second argument according to the given comparator.
     *
     * @param   <T>   the type of the argument.
     * @param   comparator   the comparator.
     * @return   a predicate that mimics the function <em>is greater than or equal to</em>.
     */
    public static <T> BiPredicate<T, T> isGreaterThanOrEqualTo(Comparator<T> comparator) {
        var isGreaterThan = isGreaterThan(comparator);
        var isEqualTo = isEqualTo(comparator);
        var isGreaterThanOrEqualTo = isGreaterThan.or(isEqualTo);
        return isGreaterThanOrEqualTo;
    }

}
