package lesson_2.dz;

import java.util.List;

public class Company {
    Integer id;
    Integer parentId;
    List<Company> list;
}

class Order {
    public static void main(String[] args) {
        int[] listOfCompaniesPid = {0, 0, 1, 3, 4, 0};
        Company company[] = new Company[listOfCompaniesPid.length];

        // создаем компании
        for (int i = 0; i < listOfCompaniesPid.length; i++) {
            company[i] = new Company();
            company[i].id = i;
        }

        // заполняем parentId
        for (int i = 0; i < listOfCompaniesPid.length; i++) {
            if (listOfCompaniesPid[i] > 0) {
                for (int j = 0; j < company.length; j++) {
                    if (company[j].id == listOfCompaniesPid[i] - 1) {
                        company[i].parentId = listOfCompaniesPid[i] - 1;
                    }
                }
            }
        }

        // выводим дерево
        for (int i = 0; i < company.length; i++) {
            if(company[i].parentId != null) break;
            System.out.println(company[i].id);
            int cursor = i;
            for (int j = 0; j < company.length; j++) {
                if (company[i].id == company[j].parentId) {
                    System.out.println(" " + company[j].id);
                    i = j;
                }
            }
            i = cursor;
        }
    }
}
