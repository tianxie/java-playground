package net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Created by Tian on 2017/7/11.
 */
public class TCPEchoServer {

    private static final int BUFSIZE = 32; // Size of receive buffer

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Parameter(s): <Port>");
        }

        int servPort = Integer.parseInt(args[0]);

        // Create a server socket to accept client connection requests
        ServerSocket servSock = new ServerSocket(servPort);

        int recvMsgSize; // Size of received message
        byte[] recvBuf = new byte[BUFSIZE];

        while (true) { // Run forever, accepting and serving connections
            final Socket clntSock = servSock.accept(); // Get client connection

            final SocketAddress clientAddress = clntSock.getRemoteSocketAddress();
            System.out.println("Handling client at " + clientAddress);

            final InputStream in = clntSock.getInputStream();
            final OutputStream out = clntSock.getOutputStream();

            // Receive until client closes connection, indicated by -1 return
            while ((recvMsgSize = in.read(recvBuf)) != -1) {
                out.write(recvBuf, 0, recvMsgSize);
            }
            clntSock.close();
        }
    }
}
