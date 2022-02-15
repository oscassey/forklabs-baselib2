/*
 * Copyright (C) 2012  Forklabs Daniel Léonard
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
package ca.forklabs.baselib.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class {@code ResourceBundleTests} provides tests to verify resource bundles
 * keys and values.
 */
public class ResourceBundleTests {

    /**
     * Gets the value of all {@code public static final String} variables from
     * the given class.
     * @param   clazz   the class.
     * @return   the values.
     * @throws   Exception   if anything goes wrong.
     */
    public static List<String> extractAllKeys(Class<?> clazz) throws Exception {
        var fields = clazz.getDeclaredFields();

        var keys = new LinkedList<String>();
        for (var field : fields) {
            var type = field.getType();
            var is_string = type.equals(String.class);

            var modifiers = field.getModifiers();
            var is_static = Modifier.isStatic(modifiers);
            var is_public = Modifier.isPublic(modifiers);
            var is_final = Modifier.isFinal(modifiers);

            if (is_string && is_static && is_public && is_final) {
                field.setAccessible(true);
                var value = (String) field.get(null);
                keys.add(value);
            }
        }

        return keys;
    }

    /**
     * Asserts that all the keys are present in all the locales.
     * @param   clazz   the class with the keys as
     *                  {@code public static final String} variables.
     * @param   bundle   the resource bundle with the values.
     * @param   locales   the locales to test.
     * @throws   Exception   if anything goes wrong.
     * @see   #extractAllKeys(Class)
     */
    public static void assertAllKeysArePresent(Class<?> clazz, ResourceBundle bundle, Locale... locales) throws Exception {
        var default_locale = Locale.getDefault();

        try {
            var keys = extractAllKeys(clazz);

            for (var locale : locales) {
                Locale.setDefault(locale);

                var i = 0;
                for (var enumeration = bundle.getKeys(); enumeration.hasMoreElements(); i++) {
                    var key = enumeration.nextElement();
                    assertTrue(keys.contains(key), locale.toString() + " - " + key);
                }
                assertEquals(keys.size(), i, locale.toString());
            }
        }
        finally {
           Locale.setDefault(default_locale);
        }
    }

}
