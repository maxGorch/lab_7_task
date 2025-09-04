import org.example.Input_Output;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class testIO {

    private final Input_Output io = new Input_Output();


    /**
     * Что делает:
     * @FileOutputStream Создаёт файл test.bin для записи.
     * @io.outputIntArray Записывает в него массив {0,1,2,3} через метод outputIntArray (в бинарном виде).
     * @inputStream Считывает из файла массив обратно через inputIntArray.
     * @assertArrayEquals Проверяет, что считанный массив точно совпадает с исходным.
     * Идея: проверить правильность записи и чтения массива в бинарном формате через файл.
     */
    @Test
    public void outputIntArrayTest0() throws IOException {
        try (var outputStream = new FileOutputStream("test.bin")) {
            io.outputIntArray(outputStream, new int[]{0, 1, 2, 3});
            var inputStream = new FileInputStream("test.bin");
            int[] arr = new int[4];
            assertArrayEquals(new int[]{0, 1, 2, 3}, io.inputIntArray(inputStream, arr));
        }
    }
    /**
     * inputIntArrayTest0
     * Что делает:
     * @FileInputStream Открывает файл test.bin для чтения.
     * @FileOutputStream Перезаписывает файл массивом {0,1,2,3} через outputIntArray.
     * @resultArray Создаёт пустой массив [0,0,0,0].
     * @io.inputIntArray Считывает массив из файла в resultArray.
     * @assertArrayEquals Проверяет, что массив теперь содержит правильные числа.
     * Идея: проверить корректность чтения массива из бинарного файла.
     */
    @Test
    public void inputIntArrayTest0() throws IOException {
        try (var bs = new FileInputStream("test.bin")) {
            try (var outputStream = new FileOutputStream("test.bin")) {
                io.outputIntArray(outputStream, new int[]{0, 1, 2, 3});
            }
            int[] resultArray = new int[]{0, 0, 0, 0};
            io.inputIntArray(bs, resultArray);
            assertArrayEquals(new int[]{0, 1, 2, 3}, resultArray);
        }
    }
    /**
     * textWriterStreamTest12241
     * Что делает:
     * @StringWriter  Создаёт текстовый поток в памяти.
     * @io.textWriterStream  Записывает массив {1,17,178,17809} как текст через пробелы.
     * @StringReader  Создаёт поток чтения из полученной строки.
     * @io.textReaderStream  Считывает массив обратно в int[].
     * @assertArrayEquals Проверяет совпадение с исходным массивом.
     * Идея: проверить корректность записи и чтения массива в текстовом формате.
     */
    @Test
    public void textWriterStreamTest12241() throws IOException {
        try (var cs = new StringWriter()) {
            io.textWriterStream(cs, new int[]{1, 17, 178, 17809});
            var reader = new StringReader(cs.toString());
            int[] arr = new int[4];
            assertArrayEquals(new int[]{1, 17, 178, 17809}, io.textReaderStream(reader, arr));
        }
    }
    /**
     * textWriterStreamTest0
     * Что делает:
     * @CharArrayWriter Создаёт текстовый поток в памяти (массив символов).
     * @io.textWriterStream Записывает массив {0,1,2,3} как текст.
     * @CharArrayReader Создаёт поток чтения из массива символов.
     * @io.textReaderStream Считывает массив обратно в int[].
     * @assertArrayEquals Проверяет совпадение с исходным массивом.
     * Идея: тестирует текстовую запись/чтение через массив символов.
     */
    @Test
    public void textWriterStreamTest0() throws IOException {
        try (var cs = new CharArrayWriter()) {
            io.textWriterStream(cs, new int[]{0, 1, 2, 3});
            var reader = new CharArrayReader(cs.toCharArray());
            int[] arr = new int[4];
            assertArrayEquals(new int[]{0, 1, 2, 3}, io.textReaderStream(reader, arr));
        }
    }
    /**
     * textReaderStreamTest0
     * Что делает:
     * @StringReader Создаёт поток из текста "0 1 2 3".
     * @resultArray Создаёт пустой массив [0,0,0,0].
     * @io.textReaderStream Считывает числа из текста в массив.
     * @assertArrayEquals Проверяет, что массив теперь содержит [0,1,2,3].
     * Идея: проверить, что метод корректно читает числа из текста.
     */
    @Test
    public void textReaderStreamTest0() throws IOException {
        try (var cs = new StringReader("0 1 2 3")) {
            int[] resultArray = new int[]{0, 0, 0, 0};
            io.textReaderStream(cs, resultArray);
            assertArrayEquals(new int[]{0, 1, 2, 3}, resultArray);
        }
    }
    /**
     * getAllFileWithExtensionTest0
     * Что делает:
     * @files Создаёт список с одним файлом pom.xml.
     * @io.getAllFileWithExtension Ищет все файлы с расширением "xml" в текущей директории.
     * @assertEquals Проверяет совпадение найденных файлов со списком.
     * Идея: тестирует поиск файлов с указанным расширением.
     */
    @Test
    public void getAllFileWithExtensionTest0() {
        ArrayList<File> files = new ArrayList<>();
        Collections.addAll(files, new File("C:\\Users\\mgorc\\IdeaProjects\\lab_7\\pom.xml"));
        assertEquals(files, io.getAllFileWithExtension(".", "xml"));
    }

    /**
     * getAllFileWithExtensionTest1
     * Что делает:
     * @files Создаёт список с тремя файлами .java в папке obj.
     * @io.getAllFileWithExtension Ищет все файлы с расширением "java" в указанной папке.
     * @assertEquals Проверяет, что метод вернул все файлы, которые есть в папке.
     * Идея: убедиться, что метод корректно находит все файлы с указанным расширением в директории.
     */
    @Test
    public void getAllFileWithExtensionTest1() {
        ArrayList<File> files = new ArrayList<>();
        Collections.addAll(files,
                new File("C:\\Users\\mgorc\\IdeaProjects\\lab_7\\src\\main\\java\\org\\example\\obj\\Flat.java"),
                new File("C:\\Users\\mgorc\\IdeaProjects\\lab_7\\src\\main\\java\\org\\example\\obj\\House.java"),
                new File("C:\\Users\\mgorc\\IdeaProjects\\lab_7\\src\\main\\java\\org\\example\\obj\\Person.java"));
        assertEquals(files, io.getAllFileWithExtension("C:\\Users\\mgorc\\IdeaProjects\\lab_7\\src\\main\\java\\org\\example\\obj", "java"));
    }
}

