package dz.pesron;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person()
                .setFirstName("Vasja")
                .setMiddleName("Borisovich")
                .setLastName("Pupkin")
                .setCountry("Sweden")
                .setAddress("Crown Castle 1")
                .setPhone("+46771793336")
                .setAge(33)
                .setGender("Male");
        System.out.println(person1);
    }
}
