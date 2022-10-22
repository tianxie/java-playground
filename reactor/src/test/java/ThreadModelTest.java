import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.Duration;

public class ThreadModelTest {

    @Test
    public void test() {
        Flux.interval(Duration.ofMillis(100))
                .log()
                .take(20)
                .subscribe(i -> System.out.println(Thread.currentThread().getName()));
    }
}
