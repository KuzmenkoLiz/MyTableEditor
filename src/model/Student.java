package model;

/**
 * Created by Лиза on 12.05.2017.
 */
public class Student {
    public String FirstName;
    public String SecondName;
    public String Surname;
    public int FamilyMembers;
    public Address address;
    public LivingArea livingArea;

    public Student(String Surname, String FirstName, String SecondName, int FamilyMembers,
                   Address address, LivingArea livingArea) {

        this.FirstName = FirstName;
        this.SecondName = SecondName;
        this.Surname = Surname;
        this.FamilyMembers = FamilyMembers;
        this.address = address;
        this.livingArea = livingArea;
    }

    Student() {

    }

}
