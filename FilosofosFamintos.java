import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class FilosofosFamintos {
    int n;
    Thread[] threads;
    Long start;
    Long[] tesp, ttotal;
    Semaphore s = new Semaphore(1);

    public FilosofosFamintos(int _n) {
        n = _n;
        threads = new Thread[n];
        tesp = new Long[n];
        ttotal = new Long[n];
    }

    public void run() {
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            Filosofo f = new Filosofo(i, n);
            TFilosofo tf = new TFilosofo(f, s, tesp, ttotal, start);
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

        System.out.print("Tempo total de espera = ");
        System.out.println(Arrays.toString(tesp));
        System.out.print("Tempo total de execução = ");
        System.out.println(Arrays.toString(ttotal));
        for (int i = 0; i < n; i++) {
            System.out.print(" [" + (ttotal[i] - tesp[i]) + "] ");
        }
    }
}
