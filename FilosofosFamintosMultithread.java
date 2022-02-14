import java.util.Arrays;
import java.util.concurrent.Semaphore;


public class FilosofosFamintosMultithread {
    int n;
    Long wait;
    Thread[] threads;
    Long[] ti;
    Semaphore[] g;
    Semaphore s = new Semaphore(1);
    boolean[] forks, comeu;

    public FilosofosFamintosMultithread(int _n, Long _wait) {
        n = _n;
        wait = _wait;
        threads = new Thread[n];
        g = new Semaphore[n];
        ti = new Long[n];
        forks = new boolean[n];
        comeu = new boolean[n];

    }

    public void run() {
        for (int i = 0; i < n; i++) {
            g[i] = new Semaphore(1);
            forks[i] = true;
            comeu[i] = false;
        }

        for (int i = 0; i < n; i++) {
            Filosofo f = new Filosofo(i);
            ti[i] = System.currentTimeMillis();
            int j = (i + 1) % n;
            TFilosofoMulti tf = new TFilosofoMulti(f, g[i], g[j], wait, ti[i], n, s, forks, comeu);
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
        System.out.print("Filosofos comeram = ");
        System.out.println(Arrays.toString(comeu));
    }
}
