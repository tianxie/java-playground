import java.io.*;

/**
 * Delimiter-based framing using the “newline” character
 */
public class DelimFramer implements Framer {

    private InputStream in; // Data source
    private static final byte DELIMITER = '\n'; // Message delimiter

    public DelimFramer(InputStream in) {
        this.in = in;
    }

    @Override
    public void frameMsg(byte[] message, OutputStream out) throws IOException {
        // Ensure that the message does not contain the delimiter
        for (byte b : message) {
            if (b == DELIMITER) {
                throw new IOException("Message contains delimiter");
            }
        }
        out.write(message);
        out.write(DELIMITER);
        out.flush();
    }

    @Override
    public byte[] nextMsg() throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        int nextByte;

        // Fetch bytes until find delimiter
        while ((nextByte = in.read()) != DELIMITER) {
            if (nextByte == -1) { // End of stream?
                if (messageBuffer.size() == 0) { // If no byte read
                    return null;
                } else {  // If bytes followed by end of stream: framing error
                    throw new EOFException("Non-empty message without delimiter");
                }
            }
            messageBuffer.write(nextByte); // Write byte to buffer
        }
        return messageBuffer.toByteArray();
    }
}
