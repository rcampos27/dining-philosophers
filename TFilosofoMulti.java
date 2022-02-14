import java.util.Random;
import java.util.concurrent.Semaphore;

public class TFilosofoMulti implements Runnable {
    int n;
    Filosofo f;
    Semaphore s;
    Long start;
    Long[] tesp, ttotal;
    boolean[] forks;

    public TFilosofoMulti(Filosofo _f, Long[] _tesp, Long[] _texec, Long _start, int _n, Semaphore _s, boolean[] _forks) {
        f = _f;
        start = _start;
        n = _n;
        s = _s;
        tesp = _tesp;
        ttotal = _texec;
        forks = _forks;
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
                f.pegaGarfos(i, forks);

                calculateWaitingTime();
            }
            s.release();
        }

        try {
            Random rand = new Random();
            long r = (long) (rand.nextInt(6) * 1000) + 1000;

            Thread.sleep(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        f.soltaGarfos(i, forks);

        calculateTotalTime();
    }

    public void calculateWaitingTime() {
        tesp[f.i] = System.currentTimeMillis() - start;
    }


    public void calculateTotalTime() {
        ttotal[f.i] = System.currentTimeMillis() - start;
    }
}