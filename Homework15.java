import java.io.*;
import java.util.*;

public class Homework15 {
    public static void main(String[] args) throws IOException {

        String path = "D:\\JAVA\\ШАГ\\HW\\15_23.08.2023\\Homework15\\src\\main\\java\\file.txt"; // путь к файлу

        createFile(path, "Hello world!"); // создаем и заполняем файл текстом

        //readFile(path); // читаем и выводим текст из файла

        //updateFile(path, "Hi", 5);

        //fillFileWithRandomNumbers(path, 5);

        //sortFileWithNumbers(path);

        //findTextInFile(path, "text");

    }


    // Создать файл исп. класс File. Записать в файл текст.
    public static void createFile(String path, String text) {
        try {
            File file = new File(path); // создаем объект файла по указанному пути
            if (file.createNewFile()) { // если файл не существует, создаем его
                System.out.println("Файл создан: " + file.getName());
            } else {
                System.out.println("Файл уже существует: " + file.getName());
            }
            FileWriter writer = new FileWriter(file); // создаем объект для записи в файл
            writer.write(text); // записываем текст в файл
            writer.close(); // закрываем поток записи
            System.out.println("Текст записан в файл: " + text);
        } catch (IOException e) {
            System.out.println("Произошла ошибка при работе с файлом: " + e.getMessage());
        }
    }

    // Прочитать файл. Вывести весь существующий текст в консоль в верхнем регистре. Если файл пустой,
// написать об этом в консоль.
    public static void readFile(String path) {
        try {
            File file = new File(path); // создаем объект файла по указанному пути
            if (file.exists()) { // если файл существует
                Scanner scanner = new Scanner(file); // создаем объект для чтения из файла
                if (scanner.hasNextLine()) { // если файл не пустой
                    System.out.println("Текст из файла в верхнем регистре:");
                    while (scanner.hasNextLine()) { // пока есть строки в файле
                        String line = scanner.nextLine(); // читаем строку из файла
                        System.out.println(line.toUpperCase()); // выводим строку в верхнем регистре
                    }
                } else {
                    System.out.println("Файл пустой.");
                }
                scanner.close(); // закрываем поток чтения
            } else {
                System.out.println("Файл не существует.");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при работе с файлом: " + e.getMessage());
        }
    }

    // Создать метод который ДОПОЛНИТ существующий файл введенной информацией столько раз сколько
// введет пользователь.  updateFile(String path , String textForUpdate, int num){…}
    public static void updateFile(String path, String textForUpdate, int num) {
        try {
            File file = new File(path); // создаем объект файла по указанному пути
            if (file.exists()) { // если файл существует
                FileWriter writer = new FileWriter(file, true); // создаем объект для дополнения файла
                for (int i = 0; i < num; i++) { // повторяем столько раз, сколько ввел пользователь
                    writer.write(textForUpdate); // дописываем информацию в файл
                }
                writer.close(); // закрываем поток записи
                System.out.println("Файл дополнен информацией: " + textForUpdate + " " + num + " раз(а).");
            } else {
                System.out.println("Файл не существует.");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при работе с файлом: " + e.getMessage());
        }
    }

    // Создать метод который заполнит файл случайными числами (каждое с новой строки). Если генерируется
// число которое уже было записано, дополнять звёздочкой . Например –  34, 54, 65, 54, 23, 54**.
    public static void fillFileWithRandomNumbers(String path, int num) {
        try {
            File file = new File(path); // создаем объект файла по указанному пути
            if (file.exists()) { // если файл существует
                FileWriter writer = new FileWriter(file); // создаем объект для записи в файл
                Random random = new Random(); // создаем объект для генерации случайных чисел
                HashSet<Integer> set = new HashSet<>(); // создаем множество для хранения уникальных чисел
                for (int i = 0; i < num; i++) { // повторяем столько раз, сколько нужно заполнить файл
                    int number = random.nextInt(100); // генерируем случайное число от 0 до 99
                    if (set.contains(number)) { // если число уже было записано
                        writer.write(number + "*\n"); // дописываем звездочку и перенос строки
                    } else {
                        writer.write(number + "\n"); // иначе просто пишем число и перенос строки
                        set.add(number); // добавляем число в множество
                    }
                }
                writer.close(); // закрываем поток записи
                System.out.println("Файл заполнен случайными числами.");
            } else {
                System.out.println("Файл не существует.");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при работе с файлом: " + e.getMessage());
        }
    }

    // Создать метод, который отсортирует файл с числами по убыванию.
    public static void sortFileWithNumbers(String path) {
        try {
            File file = new File(path); // создаем объект файла по указанному пути
            if (file.exists()) { // если файл существует
                Scanner scanner = new Scanner(file); // создаем объект для чтения из файла
                ArrayList<Integer> list = new ArrayList<>(); // создаем список для хранения чисел из файла
                while (scanner.hasNextLine()) { // пока есть строки в файле
                    String line = scanner.nextLine(); // читаем строку из файла
                    line = line.replaceAll("\\*", ""); // удаляем звездочки, если есть
                    int number = Integer.parseInt(line); // преобразуем строку в число
                    list.add(number); // добавляем число в список
                }
                scanner.close(); // закрываем поток чтения
                Collections.sort(list, Collections.reverseOrder()); // сортируем список по убыванию
                FileWriter writer = new FileWriter(file); // создаем объект для записи в файл
                for (int number : list) { // для каждого числа в списке
                    writer.write(number + "\n"); // пишем число и перенос строки в файл
                }
                writer.close(); // закрываем поток записи
                System.out.println("Файл отсортирован по убыванию.");
            } else {
                System.out.println("Файл не существует.");
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Произошла ошибка при работе с файлом: " + e.getMessage());
        }
    }

    // Создать метод, который найдет указанный текст в файле. Выведите количество повторений в консоль.
    public static void findTextInFile(String path, String text) {
        try {
            File file = new File(path); // создаем объект файла по указанному пути
            if (file.exists()) { // если файл существует
                Scanner scanner = new Scanner(file); // создаем объект для чтения из файла
                int count = 0; // создаем переменную для подсчета количества повторений текста
                while (scanner.hasNextLine()) { // пока есть строки в файле
                    String line = scanner.nextLine(); // читаем строку из файла
                    if (line.contains(text)) { // если строка содержит указанный текст
                        count++; // увеличиваем счетчик на единицу
                    }
                }
                scanner.close(); // закрываем поток чтения
                System.out.println("Текст \"" + text + "\" найден в файле " + count + " раз(а).");
            } else {
                System.out.println("Файл не существует.");
            }
        } catch (IOException e) {
            System.out.println("Произошла ошибка при работе с файлом: " + e.getMessage());
        }
    }
}


