package lesson_5.optional;

//optional - новый класс в пакете java.util, является контейнером (оберткой)
//для значений которая также может безопасно содержать null.

import java.util.Optional;

public class OptionalExample {
    public static void main( String[] args ) throws Exception {

        // 1 объект с ненулевым значением
//        optional<String> name = optional.of(null);
//        System.out.println(name);


        // 2 optional.ofNullable с возможностью нулевого значения
//        optional<String> name2 = optional.ofNullable(null);
//        System.out.println(name2);


        // 3 optional.empty для создания пустого optional
//        optional<String> emptyOptional = optional.empty();
//        System.out.println(emptyOptional);


        // 4 optional isPresent
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


        // 5 optional orElse
        // Как было раньше:
        //  Person personNew = person != null ? person : new Person();
        //То же самое, но с использованием optional:
        //   Person personNew = person.orElse(new Person());


        // 6 optional orElseThrow
        // Или, если не хотим создавать объект, можно выбросить исключение:
//        optional<String> name = optional.of("John"); //optional.empty()
//        String nameValue = name.orElseThrow(() -> new RuntimeException());
//        System.out.println(nameValue);


        // 7  optional map map() — преобразовывает объект в другой объект.
//        optional<String> name = optional.of("JOHN"); //optional.empty()
//        System.out.println(name.map(String::toLowerCase));

//        optional<Integer> age = Rep.findById(10).map(user -> user.getAge()).orElseThrow(() -> new Exception());
//        System.out.println(age);
    }
}