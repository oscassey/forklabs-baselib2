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

import static ca.forklabs.baselib2.io.Streams.asDataInputStream;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import ca.forklabs.baselib2.util.Arrays;
import ca.forklabs.baselib2.util.Bits;
import lombok.NonNull;

/**
 * Class {@code LittleEndianInputStream} reads from its underlying stream in a
 * little-endian manner, least-significant byte first. This class is an
 * adaptation from the class in: <blockquote> Elliote Rusty Harold,
 * <i><a target="_blank" href="http://www.oreilly.com/catalog/javaio/">Java
 * I/O</a></i> (p.31), O'Reilly, March 1999, ISBN 1-56592-485-1. </blockquote>
 *
 * @see ca.forklabs.baselib2.io.LittleEndianOutputStream
 */
public class LittleEndianInputStream extends FilterInputStream implements DataInput {

//---------------------------
// Instance variable
//---------------------------

    /** Buffer for the make methods of class {@link Bits}. */
    private final byte[] _bytes = new byte[Bits.NUM_OCTETS_IN_LONG];


//---------------------------
// Constructor
//---------------------------

    /**
     * Constructs a {@code LittleEndianinputStream} build on top of the underlying
     * {@link InputStream}.
     *
     * @param in the input stream to read from.
     */
    public LittleEndianInputStream(@NonNull InputStream in) {
        super(asDataInputStream(in));
        clearBytes();
    }


//---------------------------
// Accessors and mutator
//---------------------------

    /**
     * Gets the underlying data input stream.
     *
     * @return the underlying data input stream.
     */
    protected DataInputStream in() {
        return (DataInputStream) this.in;
    }


//---------------------------
// Instance methods
//---------------------------

    /**
     * Fills the internal buffer {@link #_bytes} with <em>numBytes</em>
     * starting at index 0.
     *
     * @param  numBytes  the number of bytes to read
     * @exception  EOFException  if we did not read <em>numBytes</em> bytes.
     * @exception  IOException  if anything goes wrong with I/O.
     */
    protected void fillBytes(int numBytes) throws IOException {
        int bytesRead = read(_bytes, 0, numBytes);
        if (bytesRead != numBytes) {
            String message = "Expected to read " + numBytes + " but read " + bytesRead + " bytes!";
            throw new EOFException(message);
        }
    }

    /**
     * Zeroes the internal buffer {@link #_bytes}.
     */
    protected void clearBytes() {
        Arrays.memset(_bytes, (byte) 0);
    }


//---------------------------
// Implemented methods from java.io.DataInput
//---------------------------

    /**
     * Reads a {@code boolean}.
     *
     * @return the {@code boolean} read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public boolean readBoolean() throws IOException {
        boolean z = in().readBoolean();
        return z;
    }

    /**
     * Reads a {@code byte}.
     *
     * @return the {@code byte} read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public byte readByte() throws IOException {
        byte b = in().readByte();
        return b;
    }

    /**
     * Reads an {@code unsigned byte}.
     *
     * @return the {@code unsigned byte} read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public int readUnsignedByte() throws IOException {
        byte b = readByte();
        int i = Bits.unsignedByte(b);
        return i;
    }

    /**
     * Reads a {@code char}.
     *
     * @return the {@code char} read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public char readChar() throws IOException {
        try {
            fillBytes(Bits.NUM_OCTETS_IN_CHAR);
            char c = Bits.makeChar(_bytes[1], _bytes[0]);
            return c;
        }
        finally {
            clearBytes();
        }
    }

    /**
     * Reads a {@code short}.
     *
     * @return the {@code short} read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public short readShort() throws IOException {
        try {
            fillBytes(Bits.NUM_OCTETS_IN_SHORT);
            short s = Bits.makeShort(_bytes[1], _bytes[0]);
            return s;
        }
        finally {
            clearBytes();
        }
    }

    /**
     * Reads an {@code unsigned short}.
     *
     * @return the {@code unsigned short} read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public int readUnsignedShort() throws IOException {
        short s = readShort();
        int i = Bits.unsignedShort(s);
        return i;
    }

    /**
     * Reads an {@code int}.
     *
     * @return the {@code int} read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public int readInt() throws IOException {
        try {
            fillBytes(Bits.NUM_OCTETS_IN_INT);
            int i = Bits.makeInt(_bytes[3], _bytes[2], _bytes[1], _bytes[0]);
            return i;
        }
        finally {
            clearBytes();
        }
    }

    /**
     * Reads a {@code long}.
     *
     * @return the {@code long} read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public long readLong() throws IOException {
        try {
            fillBytes(Bits.NUM_OCTETS_IN_LONG);
            long l = Bits.makeLong(_bytes[7], _bytes[6], _bytes[5], _bytes[4], _bytes[3], _bytes[2], _bytes[1], _bytes[0]);
            return l;
        }
        finally {
            clearBytes();
        }
    }

    /**
     * Reads a {@code float}.
     *
     * @return the {@code float} read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public float readFloat() throws IOException {
        int i = readInt();
        float f = Float.intBitsToFloat(i);
        return f;
    }

    /**
     * Reads a {@code double}.
     *
     * @return the {@code double} read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public double readDouble() throws IOException {
        long l = readLong();
        double d = Double.longBitsToDouble(l);
        return d;
    }

    /**
     * Reads some bytes from an input stream and stores them into the buffer array.
     * For this method, the concept of endian-ness does not exist.
     *
     * @param data the buffer array.
     * @param off  the initial index.
     * @param len  the number of bytes to read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public void readFully(byte[] data, int off, int len) throws IOException {
        in().readFully(data, off, len);
    }

    /**
     * Reads some bytes from an input stream and stores them into the buffer array.
     * For this method, the concept of endian-ness does not exist.
     *
     * @param data the buffer array.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public void readFully(byte[] data) throws IOException {
        int off = 0;
        int len = data.length;
        readFully(data, off, len);
    }

    /**
     * Reads the next line of text from the input stream. For this method, the
     * concept of endian-ness does not exist.
     *
     * @return the line read.
     * @deprecated see {@link DataInputStream}.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    @Deprecated
    public String readLine() throws IOException {
        String line = in().readLine();
        return line;
    }

    /**
     * Reads in a string that has been encoded using a modified UTF-8 format. For
     * this method, the concept of endian-ness does not exist.
     *
     * @return the string read.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public String readUTF() throws IOException {
        String line = in().readUTF();
        return line;
    }

    /**
     * Makes an attempt to skip over {@code n} bytes of data from the input stream,
     * discarding the skipped bytes.
     *
     * @param n the number of bytes to skip.
     * @return the number of bytes skipped.
     * @exception IOException if anything goes wrong with I/O.
     */
    @Override
    public int skipBytes(int n) throws IOException {
        int skipped = in().skipBytes(n);
        return skipped;
    }

}
