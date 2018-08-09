import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class URLPathTest {
    @Test
    public void test() throws MalformedURLException {
        final URL url = new URL("http://a.b.c.com/d/e/f.txt");
        System.out.println(url.getPath());
    }
}
