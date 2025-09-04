import org.example.House_JacksonSeDe_tion;
import org.example.House_SeDe_tion;
import org.example.obj.Flat;
import org.example.obj.House;
import org.example.obj.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import java.util.Arrays;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SerializeTest {
    /**
     * serializeHouseWithJavaTest
     * Что делает:
     * @Person Создаёт объекты person0 и person1 с именами и датами рождения.
     * @Flat Создаёт квартиры flat0 и flat1 с площадью и списком владельцев.
     * @House Создаёт дом house с адресом, главным человеком и списком квартир.
     * @File.createTempFile Создаёт временный файл для сериализации.
     * @FileOutputStream Открывает поток для записи в файл.
     * @FileInputStream Открывает поток для чтения из файла.
     * @House_SeDe_tion.serializeHouse Сериализует объект house в бинарный поток.
     * @House_SeDe_tion.deserializeHouse Читает объект из потока обратно в объект House.
     * @assertEquals Сравнивает исходный и десериализованный объект, проверяя, что они одинаковые.
     * Идея: проверить корректность **сериализации и десериализации объекта House** через Java потоки.
     */
    @Test
    public void serializeHouseWithJavaTest() throws IOException {
        Person person0 = new Person("Mobi", "Dick", "2000-06-15");
        Person person1 = new Person("Alan", "Lone", "1987-01-02");Flat flat0 = new Flat(1, 89, List.of(person0));
        Flat flat1 = new Flat(2, 64, List.of(person1));
        List<Flat> flats = Arrays.asList(flat0, flat1);
        House house = new House("0", "yl.Pushkina", person0, flats);

        // Создаем временный файл
        File tempFile = File.createTempFile("house", ".tmp");
        tempFile.deleteOnExit(); // Убедимся, что файл удалится после тестов

        try (FileOutputStream fos = new FileOutputStream(tempFile);
             FileInputStream fis = new FileInputStream(tempFile)) {

            // Сериализуем и десериализуем
            House_SeDe_tion.serializeHouse(house, fos);
            House deserializedHouse = House_SeDe_tion.deserializeHouse(fis);

            // Сравниваем объекты
            assertEquals(house, deserializedHouse, "Десериализованный объект не совпадает с исходным");
        }
    }
    /**
     * serializeHouseWithJacksonToStringTest
     * Что делает:
     * @Person Создаёт объекты person0 и person1 с именами и датами рождения.
     * @Flat Создаёт квартиры flat0 и flat1 с площадью и списком владельцев.
     * @House Создаёт дом house с адресом, главным человеком и списком квартир.
     * @House_JacksonSeDe_tion.serializeToJson Превращает объект house в JSON строку.
     * @assertNotNull Проверяет, что JSON строка не равна null.
     * @assertFalse Проверяет, что JSON строка не пустая.
     * @House_JacksonSeDe_tion.deserializeFromJson Превращает JSON строку обратно в объект House.
     * @assertEquals Сравнивает исходный и десериализованный объект, проверяя, что они одинаковые.
     * Идея: проверить корректность **сериализации и десериализации объекта House через JSON** с использованием Jackson.
     */
    @Test
    public void serializeHouseWithJacksonToStringTest() throws IOException {
        // Создаем простые даты без LocalDate
        Person person0 = new Person("Mobi", "Dick", "2000-06-15");
        Person person1 = new Person("Alan", "Lone", "1987-01-02");

        // Создаем квартиры
        Flat flat0 = new Flat(1, 89, List.of(person0));
        Flat flat1 = new Flat(2, 64, List.of(person1));

        // Создаем список квартир
        List<Flat> flats = List.of(flat0, flat1);

        // Создаем дом
        House house = new House("0", "yl.Pushkina", person0, flats);

        // Превращаем объект в JSON строку
        String json = House_JacksonSeDe_tion.serializeToJson(house);

        // Проверяем что JSON не пустой
        Assertions.assertNotNull(json);
        Assertions.assertFalse(json.isEmpty());

        // Превращаем JSON обратно в объект
        House deserializedHouse = House_JacksonSeDe_tion.deserializeFromJson(json);

        // Проверяем что объекты одинаковые
        assertEquals(house,
                deserializedHouse, "Объекты до и после сериализации должны быть одинаковыми");
    }
}
