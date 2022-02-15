import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

enum states {PENSANDO, FOME, COMENDO};

public class Main {
    public static void main(String[] args) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("teste.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert writer != null;

        for(int t = 0; t <= 2; t ++){

            int[] n = {4, 8, 16, 32, 64, 128, 256, 512};
            for (int i : n) {
                System.out.printf("Loop %d com n = %d", t, i);

                writer.printf("FILOSOS FAMINTOS SEQUENCIAL %d\n", i);
                SequentialNoThread snt = new SequentialNoThread(i, writer);
                snt.run();

                writer.printf("FILOSOS FAMINTOS MULTITHREAD %d\n", i);
                FilosofosFamintosMultithread ffm = new FilosofosFamintosMultithread(i, writer);
                ffm.run();
            }

            writer.println();
            writer.println("--------------------------------");
            writer.println();
        }
        writer.close();
    }
}

