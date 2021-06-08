package hiberexample;

import org.hibernate.LockMode;
import org.hibernate.Session;

public class PersonRealization {
    public static void main(String[] args) {
        Session session = null;
        Person p = session.load(Person.class, 3L, LockMode.PESSIMISTIC_READ);

        session.lock(p, LockMode.PESSIMISTIC_WRITE);

        session.createCriteria(Person.class)
                .setLockMode(LockMode.PESSIMISTIC_READ)
                .uniqueResult();
    }
}
