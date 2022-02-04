import java.util.concurrent.Semaphore;

enum states {PENSANDO, FOME, COMENDO};

public class Main {
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        Long[] ti = {0L, 0L, 0L, 0L, 0L};
        Semaphore s = new Semaphore(1);

        for (int i = 0; i < 5; i++) {
            Filosofo f = new Filosofo(i);

            TFilosofo tf = new TFilosofo(f, s, ti[i]);

            threads[i] = new Thread(tf);
            ti[i] = System.currentTimeMillis();
        }



        for (int i = 0; i < 5; i++) {
            System.out.printf("iniciando thread %d\n", i);
            threads[i].start();
        }
//
//        for (int i = 0; i < 5; i++) {
//            try {
//                threads[i].join();
//            } catch (Exception e) {
//                System.out.print("joined thread");
//            }
//        }
    }
}

class TFilosofo implements Runnable {
    Filosofo f;
    Semaphore s;
    Long ti;

    public TFilosofo(Filosofo _f, Semaphore _s, Long _ti) {
        f = _f;
        s = _s;
        ti = _ti;
    }

    @Override
    public void run() {
        try {
            s.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.print("Filosofo pegou\n");
        }

        f.pegaGarfo(ti);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.print("Filosofo soltou\n");

        }

        f.soltaGarfo();

        s.release();
    }
}

class Filosofo {
    int i;
    states state;

    public Filosofo(int _i) {
        i = _i;
        state = states.PENSANDO;
    }

    public void pegaGarfo(Long ti) {
        state = states.FOME;
        Long tf = System.currentTimeMillis();
        Long waitingTime = tf - ti;
        System.out.printf("Filosofo %d pegou os garfos %d e %d\n", i, i, (i + 1) % 5);
        System.out.printf("Tempo de espera do filosofo %d = %d\n", i, waitingTime);
        state = states.COMENDO;
    }

    public void soltaGarfo() {
        state = states.PENSANDO;
        System.out.printf("Filosofo %d soltou os garfos %d e %d\n", i, i, (i + 1) % 5);
    }
}