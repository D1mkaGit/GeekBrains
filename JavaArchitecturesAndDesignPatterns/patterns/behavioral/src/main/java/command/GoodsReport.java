package command;

public class GoodsReport extends Report {

    public GoodsReport(Reports reports) {
        super(reports);
    }

    @Override
    public void Generate() {
        reports.generateGoodsReport();
    }
}
