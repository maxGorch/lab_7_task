package org.example;


import java.io.*;

public class Input_Output {
    //@TODO:
    //  1.Записать массив целых чисел в двоичный поток.
    //  2.Прочитать массив целых чисел из двоичного потока.
    //  3.Предполагается, что до чтения массив уже создан,
    //  нужно прочитать чисел, где n — длина массива.
    public void outputIntArray(OutputStream os, int[] ptr) {
        try (DataOutputStream dos = new DataOutputStream((os))) {
            for (int i : ptr) {
                dos.writeInt(i);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void inputIntArray(InputStream is, int[] ptr) {
        try (DataInputStream dis = new DataInputStream((is))) {
            for (int i = 0; i < ptr.length; i++) {
                ptr[i] = dis.readInt();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //@TODO:
    //  Аналогично, используя символьные потоки.
    //  В потоке числа должны разделяться пробелами.

    public void textReaderStream(Reader reader) {
        try () {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void textWriterStream(Writer writer) {
        try () {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //@TODO:
    //  Используя класс RandomAccessFile,
    //  прочитайте массив целых чисел, начиная с заданной позиции

}