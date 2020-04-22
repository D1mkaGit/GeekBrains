package my_dz;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Company {
    final int id;
    final String name;
    final List<Do> doList;

    public Company( int id, String name ) {
        this.id = id;
        this.name = name;
        this.doList = new ArrayList<>();
    }

    public List<Do> getDoList() {
        return doList;
    }

    public void addDoList( Do doItem ) {
        this.doList.add(doItem);
    }

    @Override
    public String toString() {
        return "Компания: " + name +
                ", Список Дочек: " + doList;
    }
}

class Do {
    final int id;
    final String name;
    final int parentId;
    final double fact;
    final double prognoz;

    public Do( int id, String name, int parentId, double fact, double prognoz ) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.fact = fact;
        this.prognoz = prognoz;
    }

    @Override
    public String toString() {
        return name + ": fact=" + fact +
                ", prognoz=" + prognoz;
    }
}

public class MyExcelReaderApp {
    public static final String file = "CompanyIndicators.xlsx";


    public static void main( String[] args ) throws IOException, InvalidFormatException {
        Workbook workbook = WorkbookFactory.create(new File(file));
        Sheet sheet = workbook.getSheetAt(0);
        Integer companyId = 0;
        List<Company> companies = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.rowIterator();

        rowIterator.next(); // пропускаем строку с наименованиями столбцов

        parseRowData(sheet, companyId, companies, rowIterator);

        System.out.print(companies);

        System.out.println();

        makeCalculations(companies);
    }

    private static void parseRowData( Sheet sheet, Integer companyId, List<Company> companies, Iterator<Row> rowIterator ) {
        int i = 0;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (!companies.toString().contains(row.getCell(0).toString()) && !row.getCell(0).toString().isEmpty()) {
                if (companies.size() > 0) companyId++;
                companies.add(new Company(companyId, row.getCell(0).toString()));
                i = 0;
            }
            for (int k = 0; k < sheet.getNumMergedRegions(); k++) {
                CellRangeAddress mergedRegion = sheet.getMergedRegion(k);
                if (row.getRowNum() >= mergedRegion.getFirstRow() && row.getRowNum() <= mergedRegion.getLastRow())
                    break;
            }
            addDoToListToCompany(companies, row, i++, companyId);
        }
    }

    private static void makeCalculations( List<Company> companies ) {
        for (Company company : companies) {
            double result;
            double a = 0.0;
            double b = 0.0;
            List<Do> compList = company.getDoList();
            for (Do aDo : compList) {
                double f = aDo.fact;
                double p = aDo.prognoz;
                if (p != 0.0) {
                    a += (f * (f * 100 / p));
                    b += f;
                }
            }
            result = a / b;

            System.out.println(company.name + " имеет " + trimDecimal(result) + "% выполнения от плана.");
        }
    }

    private static String trimDecimal( Double result ) {
        return new DecimalFormat("#.##").format(result);
    }


    private static void addDoToListToCompany( List<Company> companies, Row row, int id, int parentId ) {
        String doCompanyName = "N/A";
        double doFact = 0.0;
        double doPrognoz = 0.0;
        if (!row.getCell(1).toString().isEmpty()) doCompanyName = row.getCell(1).toString();
        if (row.getCell(2) != null) doPrognoz = row.getCell(2).getNumericCellValue();
        if (row.getCell(3) != null) doFact = row.getCell(3).getNumericCellValue();

        companies.get(parentId).addDoList(new Do(id, doCompanyName, parentId, doFact, doPrognoz));
    }
}

