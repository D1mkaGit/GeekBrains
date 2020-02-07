package lesson_8.dz;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private Integer id;
    private Integer parentId;
    private List<Company> companyList;

    public Company() {
    }

    public Company( Integer id, Integer parentId ) {
        this.id = id;
        this.parentId = parentId;
    }

    public Integer getId() {
        return id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList( List<Company> companyList ) {
        this.companyList = companyList;
    }

    public void setCompanyList( Integer[][] intMass ) {
        this.companyList = new ArrayList<>();
        for (Integer[] mass : intMass) {
            this.companyList.add(new Company(mass[0], mass[1]));
        }
    }

    public void info() {
        System.out.println("Company: " + id + " have parent: " + parentId);
    }

    public void arrangeCompanyList() {
        for (int i = 0; i < getCompanyList().size(); i++) { // повторяем нужное количество раз (рекурсия)
            List<Company> clForWork = new ArrayList<>();
            for (Company cParent : getCompanyList()) {
                if (!clForWork.contains(cParent)) clForWork.add(cParent);
                for (Company cChild : getCompanyList()) {
                    if (cParent.equals(cChild) || cChild.getParentId() == 0) continue;
                    if (cChild.getParentId().equals(cParent.getId())) {
                        clForWork.add(cChild);
                    }
                }
            }
            setCompanyList(clForWork);
        }
    }

    public void printAllCompaniesInfo() {
        for (Company c : getCompanyList()) {
            for (int i = 0; i < c.getParentId(); i++) {
                System.out.print(" ");
            }
            c.info();
        }
    }
}
