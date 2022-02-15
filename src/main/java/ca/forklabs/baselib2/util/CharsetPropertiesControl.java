/*
 * Copyright (C) 2011  Forklabs Daniel Léonard
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

import ca.forklabs.baselib2.io.Charsets;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;


/**
 * Class {@code CharsetPropertiesControl} is a {@code ResourceBundle.Control} to
 * be able to use properties file not encoded in the default charset.
 * <p>
 * The typical usage is:
 * <pre>   ResourceBundle bundle = ResourceBundle.getBundle(name, CharsetPropertiesControl.UTF_8_CONTROL);</pre>
 * to read resources encoded in UTF-8.
 * <p>
 * Resource bundles are cached. That means that if a resource bundle is first
 * loaded without a control and then loaded afterward with a control, the first
 * instance will be used and the string values will not be correctly encoded. If
 * you are using any control, use controls for all your calls to
 * {@code getBundle()};
 */
@Getter
@RequiredArgsConstructor
@Accessors(prefix = {"_"})
public class CharsetPropertiesControl extends ResourceBundle.Control {

//---------------------------
// Class variables
//---------------------------

    /** A predefined instance for {@link FileUtil#ISO_8859_1}. */
    public static final CharsetPropertiesControl ISO_8859_1_CONTROL = new CharsetPropertiesControl(Charsets.ISO_8859_1);
    /** A predefined instance for {@link FileUtil#UTF_8}. */
    public static final CharsetPropertiesControl UTF_8_CONTROL = new CharsetPropertiesControl(Charsets.UTF_8);
    /** A predefined instance for {@link FileUtil#UTF_16}. */
    public static final CharsetPropertiesControl UTF_16_CONTROL = new CharsetPropertiesControl(Charsets.UTF_16);
    /** v instance for {@link FileUtil#UTF_16_BE}. */
    public static final CharsetPropertiesControl UTF_16_BE_CONTROL = new CharsetPropertiesControl(Charsets.UTF_16_BE);
    /** A predefined instance for {@link FileUtil#UTF_16_LE}. */
    public static final CharsetPropertiesControl UTF_16_LE_CONTROL = new CharsetPropertiesControl(Charsets.UTF_16_LE);


//---------------------------
// Instance variables
//---------------------------

   /** The charset. */
   private final Charset _charset;


//---------------------------
// Overridden methods from java.util.ResourceBundle$Control
//---------------------------

   /**
    * {@inheritDoc}
    */
   @Override
   public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IllegalAccessException, InstantiationException, IOException {
       ResourceBundle bundle = null;

       // quick escape here
       var isPropertiesFormat = Control.FORMAT_PROPERTIES.contains(format);
       if (false == isPropertiesFormat) {
           bundle = super.newBundle(baseName, locale, format, loader, reload);
       }
       else {
           InputStream stream = null;
           try {
               var bundleName = toBundleName(baseName, locale);
               var resourceName = toResourceName(bundleName, "properties");

               stream = AccessController.doPrivileged(new PrivilegedExceptionAction<InputStream>() {
                   @Override
                   public InputStream run() throws IOException {
                       InputStream is = null;
                       if (reload) {
                           var url = loader.getResource(resourceName);
                           if (url != null) {
                               var connection = url.openConnection();
                               if (connection != null) {
                                   // Disable caches to get fresh data for reloading.
                                   connection.setUseCaches(false);
                                   is = connection.getInputStream();
                               }
                           }
                       }
                       else {
                           is = loader.getResourceAsStream(resourceName);
                       }
                       return is;
                   }
               });
           }
           catch (PrivilegedActionException pae) {
               var ioe = (IOException) pae.getException();
               throw ioe;
           }
           if (stream != null) {
               try (var reader = new InputStreamReader(stream, _charset)) {
                   bundle = new PropertyResourceBundle(reader);
               }
           }
       }
       return bundle;
    }

}
