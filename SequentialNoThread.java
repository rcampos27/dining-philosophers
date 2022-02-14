import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class SequentialNoThread {
    int n;
    Long start;
    Long[] tesp, ttotal;
    FilosofoSeq[] filosofos;
    PrintWriter writter;

    public SequentialNoThread(int _n, PrintWriter _w) {
        n = _n;
        filosofos = new FilosofoSeq[n];
        tesp = new Long[n];
        ttotal = new Long[n];
        writter = _w;
    }

    public void run() {
        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            FilosofoSeq f = new FilosofoSeq(i, tesp, ttotal);
            filosofos[i] = f;
        }

        for (int i = 0; i < n; i++) {
            filosofos[i].come(start);
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


class FilosofoSeq {
    int i;
    states state;
    Long[] tesp, ttotal;

    public FilosofoSeq(int _i, Long[] _tesp, Long[] _ttotal) {
        i = _i;
        tesp = _tesp;
        ttotal = _ttotal;
        state = states.FOME;
    }

    public void pegaGarfo(Long start) {
        tesp[i] = System.currentTimeMillis() - start;
        state = states.COMENDO;
    }

    public void soltaGarfo(Long start) {
        ttotal[i] = System.currentTimeMillis() - start;
        state = states.PENSANDO;
    }

    public void come(Long start) {
        pegaGarfo(start);
        try {
            Random rand = new Random();
            long r = (long) (rand.nextInt(6) * 1000) + 1000;

            Thread.sleep(r);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        soltaGarfo(start);
    }
}