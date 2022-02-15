import java.io.PrintWriter;
import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class FilosofosFamintos {
    int n;
    Thread[] threads;
    Long start;
    Long[] tesp, ttotal;
    Semaphore s = new Semaphore(1);
    PrintWriter writter;

    public FilosofosFamintos(int _n, PrintWriter _w) {
        n = _n;
        threads = new Thread[n];
        tesp = new Long[n];
        ttotal = new Long[n];
        writter = _w;
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

        Long totalEsp = 0L;
        long totalExec = 0L;
        for (int i = 0; i < n; i++) {
            totalEsp += tesp[i];
            totalExec += (ttotal[i] - tesp[i]);
        }
        writter.println("Tempo médio de espera = ");
        writter.println(totalEsp / n);

        writter.print("Tempo médio de execução = ");
        writter.println(totalExec / n);

        long total = 0L;
        for (int i = 0; i < n; i++) {
            total += ttotal[i];
        }

        writter.print("Tempo total de execução = ");
        writter.println(total);
    }
}
