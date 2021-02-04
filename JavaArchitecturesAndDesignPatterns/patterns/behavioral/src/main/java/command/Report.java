package command;

public abstract class Report implements ReportI {
    Reports reports;

    public Report(Reports reports) {
        this.reports = reports;
    }
}
