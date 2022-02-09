import java.util.concurrent.Semaphore;

public class FilosofosFamintos {
    int n;
    Long wait;
    Thread[] threads;
    Long[] ti;
    Semaphore s = new Semaphore(1);

    public FilosofosFamintos(int _n, Long _wait) {
        n = _n;
        wait = _wait;
        threads = new Thread[n];
        ti = new Long[n];
    }

    public void run() {
        for (int i = 0; i < n; i++) {
            Filosofo f = new Filosofo(i);
            ti[i] = System.currentTimeMillis();
            TFilosofo tf = new TFilosofo(f, s, wait, ti[i]);
            threads[i] = new Thread(tf);
        }

        for (int i = 0; i < n; i++)
            threads[i].start();

        for (int i = 0; i < n; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
