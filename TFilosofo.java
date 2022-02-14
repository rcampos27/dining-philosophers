import java.util.Random;
import java.util.concurrent.Semaphore;

class TFilosofo implements Runnable {
    Filosofo f;
    Long start;
    Semaphore s;
    Long[] tesp, ttotal;

    public TFilosofo(Filosofo _f, Semaphore _s, Long[] _tesp, Long[] _ttotal, Long _start) {
        f = _f;
        s = _s;
        start = _start;
        tesp = _tesp;
        ttotal = _ttotal;
    }

    @Override
    public void run() {
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        f.pegaGarfo(f.i);
//        f.pegaGarfo((f.i + 1) % 5);

        calculateWaitingTime();

        f.state = states.COMENDO;

        try {
            Random rand = new Random();
            long r = (long) (rand.nextInt(6) * 1000) + 1000;

            Thread.sleep(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        f.soltaGarfo(f.i);
//        f.soltaGarfo((f.i + 1) % 5);

        calculateTotalTime();

        s.release();
    }

    public void calculateWaitingTime() {
        tesp[f.i] = System.currentTimeMillis() - start;
    }


    public void calculateTotalTime() {
        ttotal[f.i] = System.currentTimeMillis() - start;
    }
}