package threads;

import java.io.IOException;
import java.util.LinkedList;


/**
 * Created by Tian on 2017/1/23.
 */
public class WaitNotifyProdCons {
    // This is the object we synchronize on so this is also the object we wait() and notifyAll() on
    protected LinkedList<Object> list = new LinkedList<>();
    protected int MAX = 10;
    protected boolean done = false; // Also protected by lock on list.

    class Producer extends Thread {
	
        public void run() {
            while (true) {
                Object justProduced = getRequestFromNetwork();
                synchronized (list) {
                    while (list.size() == MAX) { // queue "full"
                        try {
                            System.out.println("Producer WAITING");
                            list.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Producer INTERRUPTED");
                        }
                    }
                    list.addFirst(justProduced);
                    list.notifyAll(); // must own the lock
                    System.out.println("Produced 1; List size now " + list.size());
                    if (done)
                        break;
                    // yield();  // Useful for green threads & demo programs.
                }
            }
        }

        Object getRequestFromNetwork() { // Simulation of reading from client
//            try {
//                Thread.sleep(10); // simulate time passing during read
//            } catch (InterruptedException e) {
//                System.out.println("Producer Read INTERRUPTED");
//            }
            return new Object();
        }
    }

    class Consumer extends Thread {
        public void run() {
            while (true) {
                Object obj = null;
                synchronized (list) {
                    while (list.size() == 0) { // queue "empty"
                        try {
                            System.out.println("Consumer WAITING");
                            list.wait(); // must own the lock
                        } catch (InterruptedException e) {
                            System.out.println("Consumer INTERRUPTED");
                        }
                    }
                    obj = list.removeLast();
                    list.notifyAll();
                    System.out.println("List size now " + list.size());
                    if (done) break;
                }
                process(obj); // 可能比较耗时，不要放到同步块里
                // yield();
            }
        }

        void process(Object obj) {
//            try {
//                Thread.sleep(1234); // Simulate time passing
//            } catch (InterruptedException e) {
//                System.out.println("Consumer Process INTERRUPTED");
//            }
            System.out.println("Consuming object " + obj);
        }
    }

    WaitNotifyProdCons(int nP, int nC) {
        for (int i = 0; i < nP; i++) {
            new Producer().start();
        }
        for (int i = 0; i < nC; i++) {
            new Consumer().start();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int numProducers = 4;
        int numConsumers = 3;
        WaitNotifyProdCons pc = new WaitNotifyProdCons(numProducers, numConsumers);

        // Let it run 10 seconds
        Thread.sleep(10 * 1000);

        synchronized (pc.list) {
            pc.done = true;
            pc.list.notifyAll();
        }
    }
}
