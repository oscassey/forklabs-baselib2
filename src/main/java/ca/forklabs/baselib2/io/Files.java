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

package ca.forklabs.baselib2.io;

import static ca.forklabs.baselib2.io.Streams.asPrintWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Class {@code Files} provides utility classes to make it easier to interact
 * with files by offering factory methods for streams and reader/writers as
 * well as one-liners to read and write.
 * <p>
 * <b>NOTE</b>: appending using charset {@link #UTF_16} will put a
 * <a href="http://en.wikipedia.org/wiki/UTF-16/UCS-2#Byte_order_encoding_schemes">Zero-width non-breaking space</a>
 * for each append call.
 */
public abstract class Files {

//---------------------------
// Constructors
//---------------------------

    /**
     * Constructor.
     */
    protected Files() {
        // allow sub-classes only
    }


//---------------------------
// Class methods
//---------------------------

    /**
     * Gets the system end-of-line token as a {@link String}.
     * @return   the end-of-line token.
     */
    protected static String eol() {
        var eol = System.getProperty("line.separator");
        return eol;
    }


//---------------------------
// openAppend()
//---------------------------

    /**
     * Opens a file to append to.
     * @param   filename   the file to open.
     * @return   a stream to write in.
     * @throws   IOException   if anything goes wrong with I/O.
     */
    public static OutputStream openAppend(String filename) throws IOException {
        var file = new File(filename);
        var os = openAppend(file);
        return os;
    }

    /**
     * Opens a file to append to.
     * @param   file   the file to open.
     * @return   a stream to write in.
     * @throws   IOException   if anything goes wrong with I/O.
     */
    public static OutputStream openAppend(File file) throws IOException {
        var append = true;
        var os = new FileOutputStream(file, append);
        return os;
    }

    /**
     * Opens a text file to append to.
     * @param   filename   the file to open.
     * @return   a writer to write in.
     * @throws   IOException   if anything goes wrong with I/O.
     */
    public static Writer openAppendText(String filename) throws IOException {
        var charset = Charset.defaultCharset();
        var writer = openAppendText(filename, charset);
        return writer;
    }

    /**
     * Opens a text file to append to.
     * @param   filename   the file to open.
     * @param   charset   the encoding of the text.
     * @return   a writer to write in.
     * @throws   IOException   if anything goes wrong with I/O.
     */
    public static PrintWriter openAppendText(String filename, Charset charset) throws IOException {
        var file = new File(filename);
        var pw = openAppendText(file, charset);
        return pw;
    }

    /**
     * Opens a text file to append to.
     * @param   file   the file to open.
     * @return   a writer to write in.
     * @throws   IOException   if anything goes wrong with I/O.
     */
    public static PrintWriter openAppendText(File file) throws IOException {
        var charset = Charset.defaultCharset();
        var pw = openAppendText(file, charset);
        return pw;
    }

    /**
     * Opens a text file to append to.
     * @param   file   the file to open.
     * @param   charset   the encoding of the text.
     * @return   a writer to write in.
     * @throws   IOException   if anything goes wrong with I/O.
     */
    public static PrintWriter openAppendText(File file, Charset charset) throws IOException {
        var append = true;
        var fos = new FileOutputStream(file, append);
        var pw = asPrintWriter(fos, charset);
        return pw;
    }


//---------------------------
// append()
//---------------------------

    // append(filename, byte[])
    // append(file, byte[])


//---------------------------
// append()
//---------------------------

    // appendText(filename, text)
    // appendText(filename, encoding, text)
    // appendText(file, text)
    // appendText(file, encoding, text)

    public static void appendAllLines(String filename, String... lines) throws IOException {
        var charset = Charset.defaultCharset();
        appendAllLines(filename, charset, lines);
    }

    public static void appendAllLines(String filename, Charset charset, String... lines) throws IOException {
        var file = new File(filename);
        appendAllLines(file, charset, lines);
    }

    public static void appendAllLines(String filename, Iterable<String> lines) throws IOException {
        var charset = Charset.defaultCharset();
        appendAllLines(filename, charset, lines);
    }

    public static void appendAllLines(String filename, Charset charset, Iterable<String> lines) throws IOException {
        var file = new File(filename);
        appendAllLines(file, charset, lines);
    }

    public static void appendAllLines(File file, String... lines) throws IOException {
        var charset = Charset.defaultCharset();
        appendAllLines(file, charset, lines);
    }

    public static void appendAllLines(File file, Charset charset, String... lines) throws IOException {
        var listOfLines = Arrays.asList(lines);
        appendAllLines(file, charset, listOfLines);
    }

    public static void appendAllLines(File file, Iterable<String> lines) throws IOException {
        var charset = Charset.defaultCharset();
        appendAllLines(file, charset, lines);
    }

    public static void appendAllLines(File file, Charset charset, Iterable<String> lines) throws IOException {
        try (var writer = openAppendText(file, charset)) {
            lines.forEach(line -> writer.println(line));
        }
    }


//---------------------------
// AppendCollector()
//---------------------------

    // collector to write into a writer?

}
