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

package ca.forklabs.baselib2.io;

import static ca.forklabs.baselib2.io.Streams.asDataOutputStream;
import static ca.forklabs.baselib2.util.Bits.breakIntLE;
import static ca.forklabs.baselib2.util.Bits.breakLongLE;
import static ca.forklabs.baselib2.util.Bits.breakShortLE;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ca.forklabs.baselib2.util.Arrays;
import ca.forklabs.baselib2.util.Bits;
import lombok.NonNull;

/**
 * Class {@code LittleEndianOutputStream} writes to its underlying stream in a
 * little-endian manner, least-significant byte first. This class is an
 * adaptation from the class in: <blockquote> Elliote Rusty Harold,
 * <i><a target="_blank" href="http://www.oreilly.com/catalog/javaio/">Java
 * I/O</a></i> (p.31), O'Reilly, March 1999, ISBN 1-56592-485-1. </blockquote>
 *
 * @see ca.forklabs.baselib2.io.LittleEndianInputStream
 */
public class LittleEndianOutputStream extends FilterOutputStream implements DataOutput {

//---------------------------
// Instance variable
//---------------------------

    /** Buffer for the break methods of class {@link Bits}. */
    private final byte[] _bytes = new byte[Bits.NUM_OCTETS_IN_LONG];


//---------------------------
// Constructor
//---------------------------

    /**
     * Constructs a {@code LittleEndianOutputStream} build on top of the underlying
     * {@link OutputStream}.
     *
     * @param out the output stream to write to.
     */
    public LittleEndianOutputStream(@NonNull OutputStream out) {
        super(asDataOutputStream(out));
        clearBytes();
    }


//---------------------------
// Accessors and mutator
//---------------------------

    /**
     * Gets the underlying data output stream.
     *
     * @return the underlying data output stream.
     */
    protected DataOutputStream out() {
        return (DataOutputStream) this.out;
    }


//---------------------------
// Instance methods
//---------------------------

    /**
     * Zeroes the internal buffer {@link #_bytes}.
     */
    protected void clearBytes() {
        Arrays.memset(_bytes, (byte) 0);
    }


//---------------------------
// Implemented methods from java.io.DataOutput
//---------------------------

    /**
     * Gets the number of bytes written to this stream so far.
     *
     * @return the number of bytes written.
     */
    public int size() {
        int size = out().size();
        return size;
    }

    /**
     * Writes the {@code boolean} argument.
     *
     * @param z the {@code boolean} value to be written.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void writeBoolean(boolean z) throws IOException {
        out().writeBoolean(z);
    }

    /**
     * Writes the {@code byte} argument.
     *
     * @param b the {@code byte} value to be written.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void writeByte(int b) throws IOException {
        out().writeByte(b);
    }

    /**
     * Writes the {@code short} argument lowest byte first.
     *
     * @param s the {@code short} value to be written.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public synchronized void writeShort(int s) throws IOException {
        try {
            breakShortLE((short) s, _bytes, 0);
            write(_bytes, 0, Bits.NUM_OCTETS_IN_SHORT);
        }
        finally {
            clearBytes();
        }
    }

    /**
     * Writes the {@code char} argument lowest byte first.
     *
     * @param c the {@code char} value to be written.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void writeChar(int c) throws IOException {
        writeShort(c);
    }

    /**
     * Writes the {@code int} argument lowest byte first.
     *
     * @param i the {@code int} value to be written.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public synchronized void writeInt(int i) throws IOException {
        try {
            breakIntLE(i, _bytes, 0);
            write(_bytes, 0, Bits.NUM_OCTETS_IN_INT);
        }
        finally {
            clearBytes();
        }
    }

    /**
     * Writes the {@code long} argument lowest byte first.
     *
     * @param l the {@code long} value to be written.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public synchronized void writeLong(long l) throws IOException {
        try {
            breakLongLE(l, _bytes, 0);
            write(_bytes, 0, Bits.NUM_OCTETS_IN_LONG);
        }
        finally {
            clearBytes();
        }
    }

    /**
     * Writes the {@code float} argument lowest byte first.
     *
     * @param f the {@code float} value to be written.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void writeFloat(float f) throws IOException {
        int i = Float.floatToRawIntBits(f);
        writeInt(i);
    }

    /**
     * Writes the {@code double} argument lowest byte first.
     *
     * @param d the {@code double} value to be written.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void writeDouble(double d) throws IOException {
        long l = Double.doubleToRawLongBits(d);
        writeLong(l);
    }

    /**
     * Writes out the string to the underlying output stream as a sequence of
     * {@code byte}s.
     *
     * @param s the string to write.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void writeBytes(String s) throws IOException {
        out().writeBytes(s);
    }

    /**
     * Writes out the string to the underlying output stream as a sequence of
     * {@code char}s.
     *
     * @param s the string to write.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void writeChars(String s) throws IOException {
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            writeChar(c);
        }
    }

    /**
     * Writes a string to the underlying output stream using UTF-8 encoding in a
     * machine-independent manner.
     *
     * @param s the string to write.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void writeUTF(String s) throws IOException {
        out().writeUTF(s);
    }

}
