package io;

import java.io.File;

/**
 * Created by Tian on 2016/12/15.
 */
public class IOTest {
    public static void main(String[] args) {
        File homedir = new File(System.getProperty("user.home"));
        File f = new File(homedir, "app.conf");
        if (f.exists() && f.isFile() && f.canRead()) {
            File configdir = new File(f, ".configdir");
            configdir.mkdir();
            f.renameTo(new File(configdir, ".config"));
        }
    }
}
