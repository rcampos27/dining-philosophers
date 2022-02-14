import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

enum states {PENSANDO, FOME, COMENDO};

public class Main {
    public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("the-file-name.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert writer != null;


        int[] n = {4, 8, 16, 32, 64, 128, 256, 512};
        for (int j : n) {

            System.out.printf("FILOSOS FAMINTOS SEQUENCIAL %d\n", j);
            SequentialNoThread snt = new SequentialNoThread(j, writer);
            snt.run();

            System.out.printf("FILOSOS FAMINTOS MULTITHREAD %d\n", j);
            FilosofosFamintosMultithread ffm = new FilosofosFamintosMultithread(j, writer);
            ffm.run();
        }

        writer.close();

    }
}

