/*
 * Copyright (C) 2006  Daniel Léonard
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

import static ca.forklabs.baselib.junit.ResourceBundleTests.assertAllKeysArePresent;

import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * Class {@code ResourcesTest} tests class {@link Resources}.
 */
public class ResourcesTest {

    /**
     * Tests that all the keys are present.
     * @throws   Exception   if anything goes wrong.
     */
    @Test
    public void testAreAllKeysPresent() throws Exception {
        var clazz = Resources.class;
        var bundle = Resources.getResourceBundle();
        var locales = new Locale[] {
            Locale.ENGLISH,
            Locale.FRENCH,
        };

        assertAllKeysArePresent(clazz, bundle, locales);
    }

}
