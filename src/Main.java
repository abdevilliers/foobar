import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       // SharedResource sharedResource = new SharedResource();
        int n = 10;
        AtomicBoolean fooTurn = new AtomicBoolean(true);
        Lock fooLock = new ReentrantLock();
        Lock barLock = new ReentrantLock();
        for(int i=0;i<n;i++) {
            SharedResource sharedResource = new SharedResource(fooTurn, fooLock,barLock);
            Thread prod = new Thread(() -> {
                sharedResource.printFoo();
            });
            Thread cons = new Thread(() -> {
                sharedResource.printBar();
            });
            prod.start();
            cons.start();
        }


    }
}