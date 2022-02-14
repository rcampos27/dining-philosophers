public class Filosofo {
    int i, n;
    states state;

    public Filosofo(int _i, int _n) {
        i = _i;
        n = _n;
        state = states.FOME;
    }

    public void pegaGarfos(int pos, boolean[] f) {
        int esq = (pos + 1) % n;

        f[pos] = false;
        f[esq] = false;

        state = states.COMENDO;
    }

    public void soltaGarfos(int pos, boolean[] f) {
        int esq = (pos + 1) % n;

        f[pos] = true;
        f[esq] = true;

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
