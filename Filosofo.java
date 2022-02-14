import java.util.concurrent.Semaphore;

public class Filosofo {
    int i, n;
    states state;

    public Filosofo(int _i, int _n) {
        i = _i;
        n = _n;
        state = states.FOME;
    }

    public void pegaGarfos(int pos, Semaphore[] g, boolean[] f) {
        int esq = (pos + 1) % n;

        try {
            g[pos].acquire();
            g[esq].acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        f[pos] = false;
        f[esq] = false;

        System.out.printf("Filosofo %d pegou [%d] e [%d]\n", i, pos, esq);
        state = states.COMENDO;
    }

    public void soltaGarfos(int pos, Semaphore[] g, boolean[] f) {
        int esq = (pos + 1) % n;

        g[pos].release();
        g[esq].release();
        f[pos] = true;
        f[esq] = true;

        System.out.printf("Filosofo %d soltou [%d] e [%d]\n", i, pos, esq);
        state = states.PENSANDO;
    }

    public void pegaGarfo(int pos) {
        System.out.printf("Filosofo %d pegou [%d]\n", i, pos);
    }

    public void soltaGarfo(int pos) {
        state = states.PENSANDO;
        System.out.printf("Filosofo %d soltou [%d]\n", i, pos);
    }

}
