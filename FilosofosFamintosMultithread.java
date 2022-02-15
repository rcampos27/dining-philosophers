import java.io.PrintWriter;
import java.util.concurrent.Semaphore;


public class FilosofosFamintosMultithread {
    int n;
    Thread[] threads;
    Long[] tesp, ttotal;
    long start;
    Semaphore s = new Semaphore(1);
    boolean[] forks;
    PrintWriter writer;

    public FilosofosFamintosMultithread(int _n, PrintWriter _w) {
        n = _n;
        threads = new Thread[n];
        tesp = new Long[n];
        ttotal = new Long[n];
        forks = new boolean[n];
        writer = _w;
    }

    public void run() {
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            forks[i] = true;
        }

        for (int i = 0; i < n; i++) {
            Filosofo f = new Filosofo(i, n);
            TFilosofoMulti tf = new TFilosofoMulti(f, tesp, ttotal, start, n, s, forks);
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
        writer.println("Tempo médio de espera = ");
        writer.println(totalEsp / n);

        writer.print("Tempo médio de execução = ");
        writer.println(totalExec / n);

        long total = 0L;
        for (int i = 0; i < n; i++) {
            total += ttotal[i];
        }

        writer.print("Tempo total de execução = ");
        writer.println(total);
    }
}
