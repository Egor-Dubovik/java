package file;

import java.io.*;

public class ReverseFile {
    public static void main(String[] args) {
        String inputFilePath = "src/recording/Track.java";
        String outputFilePath = "src/file/classes/ReversedTrack.java";

        try {
            // создаем объект класса File для чтения исходного файла
            File inputFile = new File(inputFilePath);
            if (!inputFile.exists()) {
                throw new FileNotFoundException("Input file not found");
            }

            // создаем объект класса File для записи результата
            File outputFile = new File(outputFilePath);
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }

            // создаем объекты BufferedReader и PrintWriter для чтения и записи файлов
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

            // считываем каждую строку из исходного файла, переворачиваем ее и записываем в выходной файл
            String line;
            while ((line = reader.readLine()) != null) {
                String reversedLine = new StringBuilder(line).reverse().toString();
                writer.println(reversedLine);
            }

            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
