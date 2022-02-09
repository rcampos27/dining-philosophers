import java.util.concurrent.Semaphore;

class TFilosofo implements Runnable {
    Filosofo f;
    Semaphore s;
    Long ti, wait;

    public TFilosofo(Filosofo _f, Semaphore _s, Long _wait, Long _ti) {
        f = _f;
        s = _s;
        wait = _wait;
        ti = _ti;
    }

    @Override
    public void run() {
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        f.pegaGarfo(f.i);
        f.pegaGarfo((f.i + 1) % 5);
        f.state = states.COMENDO;

        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        f.soltaGarfo(f.i);
        f.soltaGarfo((f.i + 1) % 5);

        executionTime(ti);

        s.release();
    }

    public void executionTime(Long ti) {
        Long tf = System.currentTimeMillis();
        System.out.print("Tempo espera = ");
        System.out.print(tf - ti);
        System.out.print("\n");
    }
}