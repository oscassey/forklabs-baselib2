/*
 * Copyright (C) 2012  Daniel Léonard
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

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * Class {@code Streams} offers methods to convert and manipulate streams and
 * readers.
 */
public abstract class Streams {

//---------------------------
// Constructors
//---------------------------

    /**
     * Constructor.
     */
    protected Streams() {
        // allow sub-classes only
    }


//---------------------------
// Class methods
//---------------------------

    /**
     * Makes sure the reader is now viewed as a {@link BufferedReader}. If the
     * reader is already a buffered reader, it is casted as such. If the reader
     * is not a buffered reader, a buffered reader is wrapped around it.
     *
     * The caller is responsible for closing it.
     *
     * @param   reader   the reader to view as buffered reader.
     * @return   a buffered reader.
     */
    public static BufferedReader asBufferedReader(Reader reader) {
        var br = (reader instanceof BufferedReader) ? (BufferedReader) reader : new BufferedReader(reader);
        return br;
    }

    /**
     * Transforms the input stream into a buffered reader.
     *
     * The caller is responsible for closing it.
     *
     * @param   is   the input stream.
     * @param   charset   the encoding of the text.
     * @return   a buffered reader.
     */
    public static BufferedReader asBufferedReader(InputStream is, Charset charset) {
        var reader = new InputStreamReader(is, charset);
        var br = asBufferedReader(reader);
        return br;
    }

    /**
     * Transforms the input stream into an object input stream.
     *
     * The caller is responsible for closing it.
     *
     * @param   is   the input stream.
     * @return   an object input stream.
     * @throws   IOException   if anything goes wrong with I/O.
     */
    public static ObjectInputStream asObjectInputStream(InputStream is) throws IOException {
        var ois = (is instanceof ObjectInputStream) ? (ObjectInputStream) is : new ObjectInputStream(is);
        return ois;
    }

    /**
     * Transforms the output stream into an object output stream.
     *
     * The caller is responsible for closing it.
     *
     * @param   os   the output stream.
     * @return   an object output stream.
     * @throws   IOException   if anything goes wrong with I/O.
     */
    public static ObjectOutputStream asObjectOutputStream(OutputStream os) throws IOException {
        var oos = (os instanceof ObjectOutputStream) ? (ObjectOutputStream) os : new ObjectOutputStream(os);
        return oos;
    }

    /**
     * Makes sure the writer is now viewed as a {@link PrintWriter}. If the
     * writer is already a print writer, it is casted as such. If the writer
     * is not a print writer, a print writer is wrapped around it.
     *
     * The caller is responsible for closing it.
     *
     * @param   writer   the writer to view as print writer.
     * @return   a print writer.
     */
    public static PrintWriter asPrintWriter(Writer writer) {
        var pw = (writer instanceof PrintWriter) ? (PrintWriter) writer : new PrintWriter(writer);
        return pw;
    }

    /**
     * Transforms the output stream into a print writer.
     *
     * The caller is responsible for closing it.
     *
     * @param   os   the output stream.
     * @param   charset   the encoding of the text.
     * @return   a print writer.
     */
    public static PrintWriter asPrintWriter(OutputStream os, Charset charset) {
        var writer = new OutputStreamWriter(os, charset);
        var pw = asPrintWriter(writer);
        return pw;
    }

    /**
     * Makes sure the stream is now viewed as a {@link DataInputStream}.
     *
     * The caller is responsible for closing it.
     *
     * @param   is   the stream.
     * @return   the data stream.
     */
    public static DataInputStream asDataInputStream(InputStream is) {
        var dis = (is instanceof DataInputStream) ? (DataInputStream) is : new DataInputStream(is);
        return dis;
    }

    /**
     * Makes sure the stream is now viewed as a {@link DataOutputStream}.
     *
     * The caller is responsible for closing it.
     *
     * @param   os   the stream.
     * @return   the data stream.
     */
    public static DataOutputStream asDataOutputStream(OutputStream os) {
        var dos = (os instanceof DataOutputStream) ? (DataOutputStream) os : new DataOutputStream(os);
        return dos;
    }

}
