import java.io.*;
import java.util.ArrayList;

/**
 * Osztály, amely játékmenet betöltését és fájlba írását végzi
 */
public class IO {

    /**
     * Játékmenet mentése
     *
     * @param fileName fájl, ahova mentünk
     * @param commands parancsok, amiket mentünk
     */
    public void SaveGame(String fileName, ArrayList<String[]> commands) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName), "utf-8"))) {
            for (int i = 0; i < commands.size(); i++) {
                for (int j = 0; j < commands.get(i).length; j++) {
                    writer.write(commands.get(i)[j] + " ");
                }
                writer.write("\n");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Játék betöltése
     *
     * @param fileName fájl, ahonnan betültjük a játékmenetet
     */
    public void LoadGame(String fileName) {
        Game.GetInstance().Reset();
        String file = fileName;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                String[] command = line.split(" ");
                Controller.Interpreter(command);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}