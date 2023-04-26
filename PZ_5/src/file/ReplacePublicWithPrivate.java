package file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ReplacePublicWithPrivate {
    public static void main(String[] args) {
        String inputFilePath = "src/recording/Track.java";
        String outputFilePath = "src/file/classes/ReplacedTrack.java";

        // Читаем исходный файла
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(inputFilePath))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: файл " + inputFilePath + " не найден.");
            return;
        }

        // заменяем все public на private
        String modifiedContent = content.toString().replaceAll("public ", "private ");

        // Записываем модифицированный код в новый файл
        try (PrintWriter writer = new PrintWriter(new File(outputFilePath))) {
            writer.print(modifiedContent);
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: не удалось создать файл " + outputFilePath);
            return;
        }

        System.out.println("Код успешно модифицирован и записан в файл " + outputFilePath);
    }
}
