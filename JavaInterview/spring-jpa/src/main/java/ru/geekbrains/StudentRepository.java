package ru.geekbrains;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class StudentRepository {

    // Identity Map
    private final Map<Long, Student> studentMap = new ConcurrentHashMap<>();

    private final AtomicLong identity = new AtomicLong(0);

    @PostConstruct
    public void init() {
        this.insert(new Student("Student1"));
        this.insert(new Student("Student2"));
        this.insert(new Student("Student3"));
    }

    public List<Student> findAll() {
        return new ArrayList<>(studentMap.values());
    }

    public Student findById(long id) {
        return studentMap.get(id);
    }

    public void insert(Student student) {
        long id = identity.incrementAndGet();
        student.setId(id);
        studentMap.put(id, student);
    }

    public void update(Student student) {
        studentMap.put(student.getId(), student);
    }

    public void delete(long id) {
        studentMap.remove(id);
    }

}
