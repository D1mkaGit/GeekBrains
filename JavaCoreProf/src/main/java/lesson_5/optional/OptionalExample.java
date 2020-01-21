package lesson_5.Optional;

//Optional - новый класс в пакете java.util, является контейнером (оберткой)
//для значений которая также может безопасно содержать null.

import java.util.Optional;

public class OptionalExample {
    public static void main( String[] args ) throws Exception {

        // 1 объект с ненулевым значением
//        Optional<String> name = Optional.of(null);
//        System.out.println(name);


        // 2 Optional.ofNullable с возможностью нулевого значения
//        Optional<String> name2 = Optional.ofNullable(null);
//        System.out.println(name2);


        // 3 Optional.empty для создания пустого Optional
//        Optional<String> emptyOptional = Optional.empty();
//        System.out.println(emptyOptional);


        // 4 Optional isPresent
//        isPresent() не дает нам большой выгоды по устранению избыточности кода,
//        но зато придает немного большую информативность написанному коду.
        Optional<Person> person = Optional.of(new Person());

//        if(person != null) {
//            System.out.println(person);
//        } else {
//            System.out.println("Person not found!");
//        }
////////////////
//        if (person.isPresent()) {
//            System.out.println(person.get());
//        } else {
//            System.out.println("Person not found!");
//        }


        // 5 Optional orElse
        // Как было раньше:
        //  Person personNew = person != null ? person : new Person();
        //То же самое, но с использованием Optional:
        //   Person personNew = person.orElse(new Person());


        // 6 Optional orElseThrow
        // Или, если не хотим создавать объект, можно выбросить исключение:
//        Optional<String> name = Optional.of("John"); //Optional.empty()
//        String nameValue = name.orElseThrow(() -> new RuntimeException());
//        System.out.println(nameValue);


        // 7  Optional map map() — преобразовывает объект в другой объект.
//        Optional<String> name = Optional.of("JOHN"); //Optional.empty()
//        System.out.println(name.map(String::toLowerCase));

//        Optional<Integer> age = Rep.findById(10).map(user -> user.getAge()).orElseThrow(() -> new Exception());
//        System.out.println(age);
    }
}
