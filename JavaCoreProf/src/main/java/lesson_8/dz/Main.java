package lesson_8.dz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * id parentId
 * 1  0
 * 2  0
 * 3  1
 * 4  3
 * 5  4
 * 6  0
 */

public class Main {
    public static void main( String[] args ) {
        Integer[][] intMass = {{1, 0}, {2, 0}, {3, 1}, {4, 3}, {5, 4}, {6, 0}};

        ApplicationContext context = new ClassPathXmlApplicationContext("dzConfig.xml");
        Company company = context.getBean("company", Company.class);

        company.setCompanyList(intMass);
        company.arrangeCompanyList();
        company.printAllCompaniesInfo();
    }
}
