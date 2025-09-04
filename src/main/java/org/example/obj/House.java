package org.example.obj;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Класс: House, содержит
 * @cNumber: кадастровый номер дома (строка),
 * @address: адрес,
 * @mainPerson: старшего по дому (человек),
 * @flatList: список квартир.
 */
public class House implements Serializable {
    private String cNumber;
    private String address;
    private Person mainPerson;
    private List<Flat> flatList;

    @JsonCreator
    public House(
            @JsonProperty("number") String cNumber,
            @JsonProperty("address") String address,
            @JsonProperty("mainPerson") Person mainPerson,
            @JsonProperty("flatList") List<Flat> flatList
    ) {
        this.cNumber = cNumber;
        this.address = address;
        this.mainPerson = mainPerson;
        this.flatList = flatList;
    }
    public void setNumber(String cNumber) { this.cNumber = cNumber; }
    public void setAddress(String address) { this.address = address; }
    public void setMainPerson(Person mainPerson) { this.mainPerson = mainPerson; }
    public void setFlatList(List<Flat> flatList) { this.flatList = flatList; }


    public String getNumber() { return cNumber; }
    public String getAddress() { return address; }
    public Person getMainPerson() { return mainPerson; }
    public List<Flat> getFlatList() { return flatList; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof House)) return false;
        House house = (House) o;
        return Objects.equals(cNumber, house.cNumber) &&
                Objects.equals(address, house.address) &&
                Objects.equals(mainPerson, house.mainPerson) &&
                Objects.equals(flatList, house.flatList);
    }


    @Override
    public int hashCode() {
        return Objects.hash(cNumber, address, mainPerson, flatList);
    }
}
