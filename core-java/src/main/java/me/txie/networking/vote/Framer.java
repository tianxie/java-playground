package me.txie.networking.vote;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Tian on 2017/7/13.
 */
public interface Framer {
    void frameMsg(byte[] message, OutputStream out) throws IOException;

    byte[] nextMsg() throws IOException;
}
