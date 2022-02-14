public class Filosofo {
    int i;
    states state;

    public Filosofo(int _i) {
        i = _i;
        state = states.FOME;
    }

    public void pegaGarfo(int pos) {
        System.out.printf("Filosofo %d pegou [%d]\n", i, pos);
    }

    public void soltaGarfo(int pos) {
        state = states.PENSANDO;
        System.out.printf("Filosofo %d soltou [%d]\n", i, pos);
    }

}
