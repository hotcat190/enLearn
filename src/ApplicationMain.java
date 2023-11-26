import java.io.IOException;

public class ApplicationMain {
    public static void main(String[] args) {
        try {

            if (System.getProperty("os.name").contains("Windows"))

                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

            else

                Runtime.getRuntime().exec("clear");

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        DictionaryCommandline dc = new DictionaryCommandline();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                dc.cmdShutdownRecover();
            }
        });
        dc.dictionaryAdvanced();
    }
}
