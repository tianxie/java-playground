import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ListSliceTest {
    @Test
    public void test() {
        List<Integer> a = Arrays.asList(1, 2, 3, 4, 5);
        final int totalSize = a.size();
        int sliceSize = 2;

        int count = (int) Math.ceil((double) totalSize / sliceSize);
        System.out.println(count);

        for (int i = 0; i < count; i++) {
            int start = i * sliceSize;
            int end = Math.min(start + sliceSize, totalSize);
            List<Integer> subList = a.subList(start, end);
            System.out.println(Arrays.toString(subList.toArray()));
        }
    }
}
