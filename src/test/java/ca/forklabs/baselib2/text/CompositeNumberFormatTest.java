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

package ca.forklabs.baselib2.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Locale;

import org.junit.jupiter.api.Test;

public class CompositeNumberFormatTest {

    private final NumberFormat _rootNumberFormat = NumberFormat.getInstance(Locale.ROOT);
    private final NumberFormat _frenchNumberFormat = NumberFormat.getInstance(Locale.CANADA_FRENCH);
    private final NumberFormat _compositeNumberFormat;

    public CompositeNumberFormatTest() {
        // In these tests we will be testing the parsing of two texts:
        //
        // 1) "3.1415926"
        // 2) "3,1415926"
        //
        // which are PI to the seventh decimal in English and in French. It so
        // happens also that , (comma) is the grouping symbol in English,
        // which means that the root number format will read 1) as PI but will
        // read 2) as 3141596 (three million one hundred forty thousands five
        // hundred ninety-six) which is not what we want, we want it to stop at
        // the , like the French and read only 3 (three).
        //
        // One could say this is a bug, I will say it is a configuration problem,
        // that is solved by telling the root number format to ignore its grouping
        // character.
        _rootNumberFormat.setGroupingUsed(false);

        // we also want six decimal places when formatting
        _rootNumberFormat.setMinimumFractionDigits(6);
        _frenchNumberFormat.setMinimumFractionDigits(6);

        _compositeNumberFormat = new CompositeNumberFormat(_frenchNumberFormat, _rootNumberFormat);
    }

    @Test
    public void testParseWithDecimalDot() throws ParseException {
        var textWithDot = "3.1415926";
        var number = _compositeNumberFormat.parse(textWithDot);
        assertEquals(Math.PI, number.doubleValue(), 10e-6);
    }

    @Test
    public void testParseWithDecimalComma() throws ParseException {
        var textWithDot = "3,1415926";
        var number = _compositeNumberFormat.parse(textWithDot);
        assertEquals(Math.PI, number.doubleValue(), 10e-6);
    }

    @Test
    public void testFormatWithDecimalComma() throws ParseException {
        var actual = _compositeNumberFormat.format(Math.PI);
        var expected = "3,141593";
        assertEquals(actual, expected);
    }

    @Test
    public void testFormatWithDecimalDot() throws ParseException {
        var compositeNumberFormat = new CompositeNumberFormat(_rootNumberFormat, _frenchNumberFormat);
        var actual = compositeNumberFormat.format(Math.PI);
        var expected = "3.141593";
        assertEquals(actual, expected);
    }

    @Test
    public void testNullComparator() {
        Comparator<Number> nullComparator = null;
        /* var exception = */ assertThrows(NullPointerException.class, () -> new CompositeNumberFormat(nullComparator, _rootNumberFormat, _frenchNumberFormat));
    }

    @Test
    public void testNullPrimaryNumberFormat() {
        NumberFormat nullPrimaryNumberFormat = null;
        /* var exception = */ assertThrows(NullPointerException.class, () -> new CompositeNumberFormat(nullPrimaryNumberFormat, _rootNumberFormat, _frenchNumberFormat));
    }

}
