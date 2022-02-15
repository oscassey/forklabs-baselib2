/*
 * Copyright (C) 2006  Forklabs Daniel Léonard
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

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Class {@code Resources} is the default bundle for the resources used by
 * classes in the {@link ca.forklabs.baselib2.util} package.
 */
class Resources {

//---------------------------
// Class variables
//---------------------------

    /** The key when the iterator cannot iterate over an array. */
    public static final String ARRAYS_NULL = "arrays.null";
    /** The key when the iterator is not positioned within array bounds. */
    public static final String ARRAYS_ZERO_LENGTH = "arrays.0.length";
    /** The key for the message when add() is not supported. */
    public static final String ARRAYS_NOT_WITHIN_BOUNDS = "arrays.not.within.bounds";
    /** The key for the message when remove() is not supported. */
    public static final String ARRAYS_NEGATIVE_LENGTH = "arrays.negative.length";
    /** The key for the message when next() does not have elements. */
    public static final String ARRAYS_END_NOT_WITHIN_BOUNDS = "arrays.end.not.within.bounds";


//---------------------------
// Constructor
//---------------------------

    /**
     * Let no one instantiate this class.
     */
    private Resources() {
        // nothing
    }


//---------------------------
// Class method
//---------------------------

    /**
     * Gets the resource bundle itself.
     * @return   the resource bundle.
     */
    public static ResourceBundle getResourceBundle() {
        var name = Resources.class.getName();
        var bundle = ResourceBundle.getBundle(name, CharsetPropertiesControl.UTF_8_CONTROL);
        return bundle;
    }

    /**
     * Gets and formats the specified localized string from the menu resource
     * bundle.
     * @param   key   the key.
     * @param   arguments   the arguments to format the string.
     * @return   the value.
     */
    public static String getLocalizedString(String key, Object... arguments) {
        var bundle = Resources.getResourceBundle();
        var pattern = bundle.getString(key);
        var message = MessageFormat.format(pattern, arguments);
        return message;
    }

}
