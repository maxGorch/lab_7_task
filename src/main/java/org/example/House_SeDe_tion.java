package org.example;

import org.example.obj.House;

import java.io.*;

public class House_SeDe_tion
{
    //@TODO№6 :Напишите сервисный класс с методами,
    //  которые сериализуют и десериализуют объект типа House
    //  в заданный поток средствами Java.
    public static void serializeHouse(House house, OutputStream os)
    {
        try(ObjectOutputStream oos = new ObjectOutputStream(os))
        {
            oos.writeObject(house);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static House deserializeHouse(InputStream is)
    {
        try(ObjectInputStream ois = new ObjectInputStream((is)))
        {
            return(House) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
