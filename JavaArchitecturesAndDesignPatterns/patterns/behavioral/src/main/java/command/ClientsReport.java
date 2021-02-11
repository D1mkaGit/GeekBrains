package command;

public class ClientsReport extends Report {

    public ClientsReport(Reports reports) {
        super(reports);
    }

    @Override
    public void Generate() {
        reports.generateClientsReport();
    }
}
