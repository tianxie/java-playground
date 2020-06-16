import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Map<String, List<String>> source = new HashMap<>();
        source.put("a1", Arrays.asList("b1", "b2"));
        source.put("a2", Arrays.asList("b2", "b3"));

        Map<String, List<String>> collect = source.entrySet().stream()
                .reduce(
                        new HashMap<>(),
                        (Map<String, List<String>> m, Map.Entry<String, List<String>> e) -> {
                            for (String value : e.getValue()) {
                                if (!m.containsKey(value)) {
                                    m.put(value, new ArrayList<>());
                                }
                                m.get(value).add(e.getKey());
                            }
                            return m;
                        },
                        (Map<String, List<String>> m1, Map<String, List<String>> m2) -> Stream.of(m1, m2)
                                .flatMap(map -> map.entrySet().stream())
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (v1, v2) -> {
                                            List<String> v = new ArrayList<>();
                                            v.addAll(v1);
                                            v.addAll(v2);
                                            return v;
                                        }))
                );
        collect.forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
