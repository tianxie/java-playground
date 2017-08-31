package net.vote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class VoteServerTCP {

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Parameter(s): <Port>");
        }

        int port = Integer.parseInt(args[0]);

        final ServerSocket serverSocket = new ServerSocket(port);
        // Change Bin to Text on both client and server for different encoding
        final VoteMsgCoder coder = new VoteMsgBinCoder();
        final VoteService service = new VoteService();

        while (true) {
            final Socket clientSocket = serverSocket.accept();
            System.out.println("Handling client at " + clientSocket.getRemoteSocketAddress());
            // Change Length to Delim for a different framing strategy
            final Framer framer = new LengthFramer(clientSocket.getInputStream());
            try {
                byte[] req;
                while ((req = framer.nextMsg()) != null) {
                    System.out.println("Received message (" + req.length + " bytes)");
                    final VoteMsg responseMsg = service.handleRequest(coder.fromWire(req));
                    framer.frameMsg(coder.toWire(responseMsg), clientSocket.getOutputStream());
                }
            } catch (IOException ioe) {
                System.err.println("Error handling client: " + ioe.getMessage());
            } finally {
                System.out.println("Closing connection");
                clientSocket.close();
            }

        }
    }
}
