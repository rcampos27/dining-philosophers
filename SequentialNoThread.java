import java.util.Arrays;

public class SequentialNoThread {

    public static void main(String[] args) {
        Long[] ti = {0L, 0L, 0L, 0L, 0L};
        FilosofoSeq[] filosofos = new FilosofoSeq[5];
        for (int i = 0; i < 5; i++) {
            FilosofoSeq f = new FilosofoSeq(i);
            filosofos[i] = f;
            ti[i] = System.currentTimeMillis();
        }

        System.out.print(Arrays.toString(ti));
        System.out.print("\n");

        for (int i = 0; i < 5; i++) {
            filosofos[i].come(ti[i]);
        }

    }
}


class FilosofoSeq {
    int i;
    states state;

    public FilosofoSeq(int _i) {
        i = _i;
        state = states.PENSANDO;
    }

    public void pegaGarfo(Long ti) {
        state = states.FOME;
        Long tf = System.currentTimeMillis();
        Long waitingTime = tf - ti;
        System.out.printf("Filosofo %d pegou [%d, %d]\n", i, i, (i + 1) % 5);
        System.out.printf("Tempo espera [%d] = ", i);
        System.out.print(waitingTime);
        System.out.print("\n");
        state = states.COMENDO;
    }

    public void soltaGarfo() {
        state = states.PENSANDO;
        System.out.printf("Filosofo %d soltou [%d, %d]\n", i, i, (i + 1) % 5);
    }

    public void come(Long ti) {
        pegaGarfo(ti);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        soltaGarfo();
    }
}