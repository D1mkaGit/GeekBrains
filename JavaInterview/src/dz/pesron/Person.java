package dz.pesron;

public class Person {
    String firstName;
    String lastName;
    String middleName;
    String country;
    String address;
    String phone;
    int age;
    String gender; // IMO should be enum

    public Person setFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public Person setLastName(String lastName){
        this.lastName=lastName;
        return this;
    }

    public Person setMiddleName(String middleName){
        this.middleName=middleName;
        return this;
    }

    public Person setCountry(String country){
        this.country=country;
        return this;
    }

    public Person setAddress(String address){
        this.address=address;
        return this;
    }

    public Person setPhone(String phone){
        this.phone=phone;
        return this;
    }

    public Person setAge(int age){
        this.age=age;
        return this;
    }

    public Person setGender(String gender){
        this.gender=gender;
        return this;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", country='" + country + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
