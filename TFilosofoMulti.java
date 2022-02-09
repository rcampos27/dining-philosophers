import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class TFilosofoMulti implements Runnable {
    int n;
    Filosofo f;
    Semaphore g1, g2, s;
    Long ti, wait;

    public TFilosofoMulti(Filosofo _f, Semaphore _g1, Semaphore _g2, Long _wait, Long _ti, int _n, Semaphore _s) {
        f = _f;
        g1 = _g1;
        g2 = _g2;
        wait = _wait;
        ti = _ti;
        n = _n;
        s = _s;
    }

    @Override
    public void run() {
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        boolean a1 = g1.tryAcquire();
        boolean a2 = g2.tryAcquire();
        s.release();

        if (a1 && a2) {
            f.pegaGarfo(f.i);
            f.pegaGarfo((f.i + 1) % n);

            f.state = states.COMENDO;
            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            f.soltaGarfo(f.i);

            f.soltaGarfo((f.i + 1) % n);

            executionTime(ti);
        }
        g1.release();
        g2.release();

    }

    public void executionTime(Long ti) {
        Long tf = System.currentTimeMillis();
        System.out.print("Tempo espera = ");
        System.out.println(tf - ti);
        System.out.printf("!!! Filosofo %d comeu !!!\n", f.i);
    }
}