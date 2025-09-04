package org.example.obj;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Flat implements Serializable {
    private int room;
    private double square;
    private List<Person> owners;


    @JsonCreator
    public Flat(
            @JsonProperty("room") int room,
            @JsonProperty("square") double square,
            @JsonProperty("owners") List<Person> owners
    ) {
        this.room = room;
        this.square = square;
        this.owners = owners;
    }


    public int getRoom() { return room; }
    public double getSquare() { return square; }
    public List<Person> getOwners() { return owners; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Flat)) return false;
        Flat flat = (Flat) o;
        return room == flat.room &&
                Double.compare(flat.square, square) == 0 &&
                Objects.equals(owners, flat.owners);
    }


    @Override
    public int hashCode() {
        return Objects.hash(room, square, owners);
    }
}