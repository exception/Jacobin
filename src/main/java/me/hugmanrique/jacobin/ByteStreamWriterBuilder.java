package me.hugmanrique.jacobin;

import me.hugmanrique.jacobin.writer.LittleEndianByteStreamWriter;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.ByteOrder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Creates a new {@link ByteStreamWriter} from different kinds of
 * sources with a defined {@link ByteOrder}.
 *
 * @author Hugo Manrique
 * @since 03/09/2018
 */
public final class ByteStreamWriterBuilder {
    private OutputStream stream;
    private ByteOrder order = ByteOrder.BIG_ENDIAN;

    public ByteStreamWriterBuilder() {}

    /**
     * Creates a new {@link ByteStreamWriter} instance to write to
     * the given {@link OutputStream}.
     */
    public ByteStreamWriterBuilder stream(OutputStream stream) {
        this.stream = stream;
        return this;
    }

    /**
     * Creates a new {@link ByteStreamWriter} instance to write to
     * a {@link ByteArrayOutputStream}.
     */
    public ByteStreamWriterBuilder stream() {
        this.stream = new ByteArrayOutputStream();
        return this;
    }

    /**
     * Creates a new {@link ByteStreamWriter} instance write to a
     * {@link ByteArrayOutputStream} which will hold {@code size} bytes
     * before resizing.
     *
     * @throws IndexOutOfBoundsException if {@code size} is negative
     */
    public ByteStreamWriterBuilder stream(int size) {
        this.stream = new ByteArrayOutputStream(size);
        return this;
    }

    /**
     * Sets the byte order of the {@link ByteStreamWriter}.
     */
    public ByteStreamWriterBuilder order(ByteOrder order) {
        this.order = checkNotNull(order, "byte order");
        return this;
    }

    /**
     * Creates a new {@link ByteStreamWriter} from different kinds of
     * sources with a defined {@link ByteOrder}.
     *
     * @return the new {@link ByteStreamWriter}
     */
    public ByteStreamWriter build() {
        checkNotNull(stream, "stream");

        if (order.equals(ByteOrder.LITTLE_ENDIAN)) {
            return new LittleEndianByteStreamWriter(stream);
        }

        throw new UnsupportedOperationException(order + " byte order not supported");
    }
}