import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/*
 * 用文本编码投票协议
 * Wire Format "VOTE_PROTO" <"v"|"i"> [<RESP_FLAG] <CANDIDATE> [<VOTE_CNT>]
 * Charset is fixed by the wire format.
 */
public class VoteMsgTextCoder implements VoteMsgCoder {

    // Manifest constants for encoding
    public static final String MAGIC = "Voting";
    public static final String VOTE_STR = "v";
    public static final String INQ_STR = "i";
    public static final String RESP_STR = "R";

    public static final String CHARSET_NAME = "US-ASCII";
    public static final String DELIMITER = " ";
    public static final int MAX_WIRE_LENGTH = 2000;


    @Override
    public byte[] toWire(VoteMsg msg) throws IOException {
        String msgString = MAGIC + DELIMITER + (msg.isInquiry() ? INQ_STR : VOTE_STR)
                + DELIMITER + (msg.isResponse() ? RESP_STR + DELIMITER : "");
        return msgString.getBytes(CHARSET_NAME);
    }

    @Override
    public VoteMsg fromWire(byte[] message) throws IOException {
        final ByteArrayInputStream msgStream = new ByteArrayInputStream(message);
        final Scanner s = new Scanner(new InputStreamReader(msgStream, CHARSET_NAME));
        boolean isInquiry;
        boolean isResponse;
        int candidateID;
        long voteCount;
        String token;

        try {
            token = s.next();
            if (!token.equals(MAGIC)) {
                throw new IOException("Bad magic string: " + token);
            }
            token = s.next();
            if (token.equals(VOTE_STR)) {
                isInquiry = false;
            } else if (!token.equals(INQ_STR)) {
                throw new IOException("Bad vote/inq indicator: " + token);
            } else {
                isInquiry = true;
            }

            token = s.next();
            if (token.equals(RESP_STR)) {
                isResponse = true;
                token = s.next();
            } else {
                isResponse = false;
            }

            // Current token is candidateID
            // Note: isResponse now valid
            candidateID = Integer.parseInt(token);
            if (isResponse) {
                token = s.next();
                voteCount = Long.parseLong(token);
            } else {
                voteCount = 0;
            }
        } catch (IOException ioe) {
            throw new IOException("Parse error...");
        }
        return new VoteMsg(isResponse, isInquiry, candidateID, voteCount);
    }
}
