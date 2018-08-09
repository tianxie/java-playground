import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Tian on 2017/1/23.
 */
public class QueueProdCons {
    protected volatile boolean done = false;

    class Producer implements Runnable {
        protected BlockingQueue<Object> queue;

        Producer(BlockingQueue<Object> queue) {this.queue = queue;}

        @Override
        public void run() {
            try {
                while (true) {
                    Object justProduced = getRequestFromNetwork();
                    queue.put(justProduced);
                    System.out.println("Produced 1 object; List size now " + queue.size());
                    if (done) {
                        return;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Producer INTERRUPTED");
            }
        }

        Object getRequestFromNetwork() {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.out.println("Producer Read INTERRUPTED");
            }
            return new Object();
        }
    }

    class Consumer implements Runnable {
        protected BlockingQueue<Object> queue;

        Consumer(BlockingQueue<Object> queue) {this.queue = queue;}

        @Override
        public void run() {
            try {
                while (true) {
                    final Object obj = queue.take();
                    System.out.println("List size now " + queue.size());
                    process(obj);
                    if (done) {
                        return;
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Consumer INTERRUPTED");
            }
        }

        void process(Object obj) {
//            try {
//                Thread.sleep(123);
//            } catch (InterruptedException e) {
//                System.out.println("Consumer Process INTERRUPTED");
//            }
            System.out.println("Consuming object " + obj);
        }
    }

    QueueProdCons(int nP, int nC) {
        BlockingQueue<Object> myQueue = new LinkedBlockingQueue<>();
        for (int i = 0; i < nP; i++) {
            new Thread(new Producer(myQueue)).start();
        }
        for (int i = 0; i < nC; i++) {
            new Thread(new Consumer(myQueue)).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int numProducers = 4;
        int numConsumers = 3;
        QueueProdCons pc = new QueueProdCons(numProducers, numConsumers);

        Thread.sleep(10*1000);

        pc.done = true;
    }
}
