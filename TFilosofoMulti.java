import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class TFilosofoMulti implements Runnable {
    int n;
    Filosofo f;
    Semaphore[] g;
    Semaphore s;
    Long ti, wait;
    boolean[] forks, comeu;

    public TFilosofoMulti(Filosofo _f, Semaphore[] _g, Long _wait, Long _ti, int _n, Semaphore _s, boolean[] _forks, boolean[] _comeu) {
        f = _f;
        g = _g;

        wait = _wait;
        ti = _ti;
        n = _n;
        s = _s;
        forks = _forks;
        comeu = _comeu;
    }

    @Override
    public void run() {
        int i = f.i;
        int j = (f.i + 1) % n;

        while (f.state == states.FOME) {
            try {
                s.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (forks[i] && forks[j]) {
                forks[i] = false;
                forks[j] = false;
                f.pegaGarfos(i, g, forks);
            }
            s.release();
        }

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f.soltaGarfos(i,g, forks);
        comeu[i] = true;

//        try {
//            Thread.sleep(wait);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        executionTime(ti);
//        g1.release();
//        g2.release();

    }

    public void executionTime(Long ti) {
        Long tf = System.currentTimeMillis();
        System.out.print("Tempo espera = ");
        System.out.println(tf - ti);
        System.out.printf("!!! Filosofo %d comeu !!!\n", f.i);
    }
}