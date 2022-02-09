import java.util.concurrent.Semaphore;


public class FilosofosFamintosMultithread {
    int n;
    Long wait;
    Thread[] threads;
    Long[] ti;
    Semaphore[] g;
    Semaphore s = new Semaphore(1);

    public FilosofosFamintosMultithread(int _n, Long _wait) {
        n = _n;
        wait = _wait;
        threads = new Thread[n];
        g = new Semaphore[n];
        ti = new Long[n];

    }

    public void run() {
        for (int i = 0; i < n; i++) {
            g[i] = new Semaphore(1);
        }

        for (int i = 0; i < n; i++) {
            Filosofo f = new Filosofo(i);
            ti[i] = System.currentTimeMillis();
            int j = (i + 1) % n;
            TFilosofoMulti tf = new TFilosofoMulti(f, g[i], g[j], wait, ti[i], n, s);
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
