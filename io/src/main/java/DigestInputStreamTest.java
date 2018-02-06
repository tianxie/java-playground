import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CountingInputStream;
import org.apache.commons.io.output.NullOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tian on 2017/6/15.
 */
public class DigestInputStreamTest {
    public static void main(String[] args) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            try (InputStream is = Files.newInputStream(Paths.get("e:\\workspace\\redang\\build\\libs\\redang-v1.6.1.RELEASE.jar"));
                 DigestInputStream dis = new DigestInputStream(is, digest);
                 EssCountingInputStream etis = new EssCountingInputStream(dis)) {
                IOUtils.copy(etis, new NullOutputStream());
                System.out.println(Hex.encodeHexString(digest.digest()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static class EssCountingInputStream extends CountingInputStream {

        EssCountingInputStream(InputStream in) {
            super(in);
        }

        @Override
        protected synchronized void afterRead(final int n) {
            super.afterRead(n);
            System.out.println(getByteCount());
        }
    }
}
