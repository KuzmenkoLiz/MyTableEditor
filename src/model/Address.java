package model;

/**
 * Created by Лиза on 22.05.2017.
 */
public class Address {
    public String street;
    public int house;
    public int flat;

    public Address(String street, int house, int flat) {

        this.street = street;
        this.house = house;
        this.flat = flat;
    }

    public Address() {

    }
}
