package command;

public abstract class Report implements ReportI {
    final Reports reports;

    public Report(Reports reports) {
        this.reports = reports;
    }
}
