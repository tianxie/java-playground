package net;

import java.io.*;
import java.net.Socket;

/* WARNING: this code can deadlock if a large file (more than a few
 * 10's of thousands of bytes) is sent.
 */
public class CompressClient {
    public static final int BUFSIZE = 256; // Size of read buffer

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Parameter(s): <Server> <Port> <File>");
        }

        String server = args[0];
        int port = Integer.parseInt(args[1]);
        String filename = args[2];

        FileInputStream fileIn = new FileInputStream(filename);
        FileOutputStream fileOut = new FileOutputStream(filename + ".gz");

        Socket sock = new Socket(server, port);

        // Send uncompressed byte stream to server
        sendBytes(sock, fileIn);

        // Receive compressed byte stream from server
        final InputStream sockIn = sock.getInputStream();
        int bytesRead;
        byte[] buffer = new byte[BUFSIZE];
        while ((bytesRead = sockIn.read(buffer)) != -1) {
            fileOut.write(buffer, 0, bytesRead);
            System.out.print("R");
        }
        System.out.println();

        sock.close();
        fileIn.close();
        fileOut.close();
    }

    private static void sendBytes(Socket sock, FileInputStream fileIn) throws IOException {
        final OutputStream sockOut = sock.getOutputStream();
        int bytesRead;
        byte[] buffer = new byte[BUFSIZE];
        while ((bytesRead = fileIn.read(buffer)) != -1) {
            sockOut.write(buffer, 0, bytesRead);
            System.out.print("W");
        }
        sock.shutdownOutput(); // Finished sending
    }

}
