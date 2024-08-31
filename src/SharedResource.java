import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;

import static java.lang.Thread.sleep;

public class SharedResource {
    private AtomicBoolean fooTurn;
    private Lock fooLock,barLock;
    public SharedResource(AtomicBoolean fooTurn,Lock fooLock,Lock barLock){
        this.fooTurn = fooTurn;
        this.fooLock = fooLock;
        this.barLock = barLock;
    }
    public void printFoo(){
       // lock.lock();
        fooLock.lock();
        while(fooTurn.get()==false){
            try {
                //fooLock.unlock();
                sleep(1000);
            }
            catch(Exception exc){}
        }

        System.out.print("foo");
        fooTurn.set(false);
        fooLock.unlock();


            }
    public  void printBar(){
        barLock.lock();
        while(fooTurn.get()==true){
            //System.out.println(fooTurn.get());
            try {
                //barLock.unlock();
                sleep(1000);
            }
            catch(Exception exc){
                System.out.println(exc);
            }
        }
      //  System.out.println("acquiring lock!!"+lock.tryLock());

        System.out.print("bar, ");
        fooTurn.set(true);
        barLock.unlock();

    }
}
