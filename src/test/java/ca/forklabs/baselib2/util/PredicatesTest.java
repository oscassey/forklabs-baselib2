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

package ca.forklabs.baselib2.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

class PredicatesTest {

    private final Comparator<Double> _compareDoubleAscending = Comparator.naturalOrder();

    @Test
    void testIsSmallerThan() {
        var isSmallerThan = Predicates.isSmallerThan(_compareDoubleAscending);

        assertTrue(isSmallerThan.test(Math.E, Math.PI));    // E  < PI
        assertFalse(isSmallerThan.test(Math.E, Math.E));    // E  < E
        assertFalse(isSmallerThan.test(Math.PI, Math.E));   // PI < E
    }

    @Test
    void testIsSmallerThanOrEqualTo() {
        var isSmallerThanOrEqualTo = Predicates.isSmallerThanOrEqualTo(_compareDoubleAscending);

        assertTrue(isSmallerThanOrEqualTo.test(Math.E, Math.PI));    // E  <= PI
        assertTrue(isSmallerThanOrEqualTo.test(Math.E, Math.E));     // E  <= E
        assertFalse(isSmallerThanOrEqualTo.test(Math.PI, Math.E));   // PI <= E
    }

    @Test
    void testIsEqualTo() {
        var isEqualTo = Predicates.isEqualTo(_compareDoubleAscending);

        assertFalse(isEqualTo.test(Math.E, Math.PI));   // E  == PI
        assertTrue(isEqualTo.test(Math.E, Math.E));     // E  == E
        assertFalse(isEqualTo.test(Math.PI, Math.E));   // PI == E
    }

    @Test
    void testIsGreaterThanOrEqualTo() {
        var isGreaterThanOrEqualTo = Predicates.isGreaterThanOrEqualTo(_compareDoubleAscending);

        assertFalse(isGreaterThanOrEqualTo.test(Math.E, Math.PI));    // E  >= PI
        assertTrue(isGreaterThanOrEqualTo.test(Math.E, Math.E));      // E  >= E
        assertTrue(isGreaterThanOrEqualTo.test(Math.PI, Math.E));     // PI >= E
    }

    @Test
    void testIsGreaterThan() {
        var isGreaterThan = Predicates.isGreaterThan(_compareDoubleAscending);

        assertFalse(isGreaterThan.test(Math.E, Math.PI));    // E  > PI
        assertFalse(isGreaterThan.test(Math.E, Math.E));     // E  > E
        assertTrue(isGreaterThan.test(Math.PI, Math.E));     // PI > E
    }

}
