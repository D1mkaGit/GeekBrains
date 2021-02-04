package command;

public class AccountingReport extends Report {

    public AccountingReport(Reports reports) {
        super(reports);
    }

    @Override
    public void Generate() {
        reports.generateAccountingReport();
    }
}
