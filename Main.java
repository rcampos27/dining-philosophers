enum states {PENSANDO, FOME, COMENDO};

public class Main {
    public static void main(String[] args) {
//        System.out.print("FILOSOS FAMINTOS SEQUENCIAL\n");
//        FilosofosFamintos ff = new FilosofosFamintos(5, 1000L);
//        ff.run();
        System.out.print("FILOSOS FAMINTOS MULTITHREAD\n");
        FilosofosFamintosMultithread ffm = new FilosofosFamintosMultithread(5, 2000L);
        ffm.run();
    }
}

