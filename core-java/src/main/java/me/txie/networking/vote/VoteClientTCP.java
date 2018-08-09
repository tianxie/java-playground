package me.txie.networking.vote;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class VoteClientTCP {
    public static final int CANDIDATE_ID = 888;

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Parameter(s): <Server> <Port>");
        }

        final String destAddr = args[0];
        final int destPort = Integer.parseInt(args[1]);

        final Socket socket = new Socket(destAddr, destPort);
        final OutputStream out = socket.getOutputStream();

        // Change Bin to Text for a different framing strategy
        final VoteMsgCoder coder = new VoteMsgBinCoder();
        // Change Length to Delim for a different encoding strategy
        final Framer framer = new LengthFramer(socket.getInputStream());

        // Create an inquiry request (2nd arg = true)
        VoteMsg msg = new VoteMsg(false, true, CANDIDATE_ID, 0);
        byte[] encodedMsg = coder.toWire(msg);

        // Send request
        System.out.println("Sending Inquiry (" + encodedMsg.length + " bytes): ");
        System.out.println(msg);
        framer.frameMsg(encodedMsg, out);

        // Now send a vote
        msg.setInquiry(false);
        encodedMsg = coder.toWire(msg);
        System.out.println("Sending Vote (" + encodedMsg.length + " bytes): ");
        System.out.println(msg);
        framer.frameMsg(encodedMsg, out);

        // Receive inquiry response
        encodedMsg = framer.nextMsg();
        msg = coder.fromWire(encodedMsg);
        System.out.println("Received Response (" + encodedMsg.length + " bytes): ");
        System.out.println(msg);

        // Receive vote response
        msg = coder.fromWire(framer.nextMsg());
        System.out.println("Received Response (" + encodedMsg.length + " bytes): ");
        System.out.println(msg);

        socket.close();

    }
}
