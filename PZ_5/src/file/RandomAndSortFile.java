package file;

import java.io.*;
import java.util.*;

public class RandomAndSortFile {
    public static void main(String[] args) {
        String PATH = "./src/file/txt";
        File folder = new File(PATH);

        try {
            if (!folder.exists()) {
                throw new FileNotFoundException("Directory " + PATH + " not found");
            }

            File randomFile = new File(folder, "random.txt");
            FileWriter randomWriter = new FileWriter(randomFile);

            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                randomWriter.write(random.nextInt(100) + " ");
            }

            randomWriter.close();

            // Чтение чисел из файла и сортировка
            List<Integer> numbers = new ArrayList<Integer>();
            Scanner scanner = new Scanner(randomFile);
            while (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number < 0 || number > 100) {
                    throw new IllegalArgumentException("Недопустимое значение поля: " + number);
                }
                numbers.add(number);
            }
            scanner.close();
            Collections.sort(numbers);

            File sortedFile = new File(folder, "sorted.txt");
            FileWriter sortedWriter = new FileWriter(sortedFile);

            // Записываем отсортированные числа в файл "sorted.txt"
            for (int number : numbers) {
                sortedWriter.write(number + " ");
            }

            sortedWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
