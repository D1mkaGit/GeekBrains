package ru.geekbrains;

import java.util.List;

public class App {
    static final Integer numberOfDefaultStudents = 10;

    public static void main(String[] args) {
/*        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        em.getTransaction().begin();
        List<Student> students = em.createQuery("from Student s", Student.class).getResultList();
        if (students.isEmpty()) {
            System.out.println("Students list is empty adding " + numberOfDefaultStudents + " students to database ...");
            for (int i = 1; i <= numberOfDefaultStudents; i++) {
                Student student = new Student();
                String studentName = "Student" + i;
                student.setName(studentName);
                em.persist(student);
                System.out.println("Adding new customer: " + studentName);
            }
        }
        em.getTransaction().commit();

        em.close();*/

        StudentDao studentDao = new StudentDao();
        List<Student> students;

        //Удаляем все из базы данных
        studentDao.createEntityManager();
        students = studentDao.findAll();
        if (!students.isEmpty()) studentDao.deleteAll();
        studentDao.closeCurrentSessionWithTransaction();


        studentDao.createEntityManager();
        students = studentDao.findAll();
        if (students.isEmpty()) {
            System.out.println("Students list is empty adding " + numberOfDefaultStudents + " students to database ...");
            persistStudents(studentDao);
        }
        studentDao.closeCurrentSessionWithTransaction();

        // берем студента по id, если список студентов не пустой
        studentDao.createEntityManager();
        students = studentDao.findAll();
        if (!students.isEmpty()) {
            long id = 1L;
            Student student = studentDao.findById(id);
            if (student != null)
                System.out.println("Getting student by id : " + id + " with name " + student.getName());
            else
                System.out.println("Student with id: " + id + " does not exists");
        }
        studentDao.closeCurrentSessionWithTransaction();

        // берем студента по id и меняем ему имя, если список студентов не пустой
        studentDao.createEntityManager();
        students = studentDao.findAll();
        if (!students.isEmpty()) {
            long id = 1L;
            Student student = studentDao.findById(id);
            if (student != null) {
                System.out.println("Getting student by id : " + id + " with name " + student.getName());
                student.setName("ChangedName");
                System.out.println("Changing name to student with id : " + id + " to: " + student.getName());
                studentDao.update(student);
            } else {
                System.out.println("Student with id: " + id + " does not exists");
            }
        }
        studentDao.closeCurrentSessionWithTransaction();

    }

    private static void persistStudents(StudentDao studentDao) {
        for (int i = 1; i <= numberOfDefaultStudents; i++) {
            String sName = "Student" + i;
            Student student = new Student();
            student.setName(sName);
            studentDao.persist(student);
            System.out.println("Adding new customer: " + sName);
        }
    }
}
