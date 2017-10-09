package net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;

public class CompressProtocol implements Runnable {
    public static final int BUFSIZE = 1024;
    private Socket clntSock;
    private Logger logger;

    public CompressProtocol(Socket clntSock, Logger logger) {
        this.clntSock = clntSock;
        this.logger = logger;
    }

    public static void handleCompressClient(Socket clntSock, Logger logger) {
        try {
            final InputStream in = clntSock.getInputStream();
            GZIPOutputStream out = new GZIPOutputStream(clntSock.getOutputStream());
            int bytesRead;
            byte[] buffer = new byte[BUFSIZE];
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
            out.finish();
            logger.info("Client " + clntSock.getRemoteSocketAddress() + " finished");
        } catch (IOException e) {
            logger.log(Level.WARNING, "Exception in echo protocol", e);
        }

        try {
            clntSock.close();
        } catch (IOException e) {
            logger.info("Exception = " + e.getMessage());
        }
    }

    @Override
    public void run() {
        handleCompressClient(clntSock, logger);
    }
}
