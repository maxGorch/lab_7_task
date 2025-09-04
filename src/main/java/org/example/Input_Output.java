package org.example;


import java.io.*;
import java.util.ArrayList;

public class Input_Output {
    //@TODO№1:
    //  1.Записать массив целых чисел в двоичный поток.
    //  2.Прочитать массив целых чисел из двоичного потока.
    //  3.Предполагается, что до чтения массив уже создан,
    //  нужно прочитать чисел, где n — длина массива.
    /**
     * outputIntArray
     * Что делает:
     * @OutputStream Принимает поток вывода, куда будут записаны данные.
     * @DataOutputStream Обёртка для записи примитивов в бинарном виде.
     * @for Цикл проходит по массиву int[].
     * @dos.writeInt Записывает каждое число в поток по 4 байта.
     * Идея: записать массив целых чисел в бинарный поток.
     */
    public void outputIntArray(OutputStream os, int[] ptr) {
        try (DataOutputStream dos = new DataOutputStream((os))) {
            for (int i : ptr) {
                dos.writeInt(i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * inputIntArray
     * Что делает:
     * @InputStream Принимает поток для чтения бинарных данных.
     * @DataInputStream Обёртка для чтения примитивов из бинарного потока.
     * @for Цикл проходит по массиву и считывает столько чисел, сколько длина массива.
     * @dis.readInt Читает по 4 байта и помещает число в массив.
     * @return Возвращает заполненный массив.
     * Идея: прочитать массив целых чисел из бинарного потока.
     */
    public int[] inputIntArray(InputStream is, int[] ptr) {
        try (DataInputStream dis = new DataInputStream((is))) {
            for (int i = 0; i < ptr.length; i++) {
                ptr[i] = dis.readInt();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ptr;
    }

    //@TODO№2:
    //  Аналогично, используя символьные потоки.
    //  В потоке числа должны разделяться пробелами.
    /**
     * textReaderStream
     * Что делает:
     * @Reader Принимает символьный поток для чтения текста.
     * @BufferedReader Обёртка для удобного построчного чтения.
     * @readLine Считывает одну строку текста.
     * @split(" ") Разделяет строку на числа, используя пробел как разделитель.
     * @Integer.parseInt Преобразует каждую строку в число и записывает в массив.
     * @return Возвращает заполненный массив.
     * Идея: прочитать массив чисел из текстового потока, где числа разделены пробелами.
     */
    public int[] textReaderStream(Reader reader, int[] arr) {
        try (BufferedReader stringReader = new BufferedReader(reader)) {
            String[] str = stringReader.readLine().split(" ");
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return arr;
    }
    /**
     * textWriterStream
     * Что делает:
     * @Writer Принимает символьный поток для записи текста.
     * @PrintWriter Обёртка для удобной записи текста.
     * @for Цикл проходит по массиву int[].
     * @printWriter.write Записывает число как текст.
     * @if Добавляет пробел после числа, кроме последнего.
     * Идея: записать массив чисел в текстовый поток через пробелы.
     */
    public void textWriterStream(Writer writer, int[] arr) {
        try (PrintWriter printWriter = new PrintWriter(writer)) {
            for (int i = 0; i < arr.length; i++) {
                printWriter.write(String.valueOf(arr[i]));
                if (i < arr.length - 1) {
                    printWriter.write(" ");
                }
            }
        }
    }

    //@TODO№3:
    //  Используя класс RandomAccessFile,
    //  прочитайте массив целых чисел, начиная с заданной позиции
    /**
     * readWithRandomAccessFile
     * Что делает:
     * @RandomAccessFile Открывает файл для чтения.
     * @seek Перемещает указатель чтения на заданную позицию offset*2.
     * @readLine Считывает строку текста из файла.
     * @split(" ") Разделяет строку на числа.
     * @Integer.parseInt Заполняет массив числами из файла.
     * @return Возвращает заполненный массив.
     * Идея: прочитать массив чисел из файла, начиная с заданной позиции, используя RandomAccessFile.
     */
    public int[] readWithRandomAccessFile(String filename,int[] arr,int offset)
    {
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(filename,"r"))
        {
            if(offset + arr.length>=randomAccessFile.length())
            {
                throw new IllegalArgumentException();
            }
            randomAccessFile.seek(offset*2L);
            String[] str = randomAccessFile.readLine().split(" ");
            for (int i = 0; i < arr.length && i<str.length; i++) {
                arr[i] = Integer.parseInt(str[i]);
            }
        }catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return arr;
    }

    //@TODO№4:
    //  Используя класс File, получите список всех файлов
    //  с заданным расширением в заданном каталоге
    //  (поиск в подкаталогах выполнять не надо)
    /**
     * getAllFileWithExtension
     * Что делает:
     * @File Создаёт объект каталога.
     * @listFiles Получает все файлы в директории (без подкаталогов).
     * @for Проходит по файлам и проверяет расширение.
     * @file.getCanonicalPath Добавляет найденный файл в результат с полным путём.
     * @return Возвращает список файлов с указанным расширением.
     * Идея: получить список всех файлов с заданным расширением в указанной директории.
     */
    public ArrayList<File> getAllFileWithExtension(String dirName, String extension)
    {
        File [] files = new File(dirName).listFiles(File::isFile);
        if(files == null)
        {
            throw new IllegalArgumentException("Каталог не содержит файлов");
        }

        ArrayList<File> result = new ArrayList<>();
        for (File file:files)
        {
            if(file.getName().endsWith("."+extension))
            {
                try{
                    result.add(new File(file.getCanonicalPath()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return result;

    }


}