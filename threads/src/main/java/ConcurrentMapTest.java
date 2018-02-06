import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Tian on 2017/2/15.
 */
public class ConcurrentMapTest {
    public static void main(String[] args) {
        ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
        System.out.println(map.putIfAbsent("1", "2"));
        System.out.println(map.putIfAbsent("1", "3"));
    }
}
