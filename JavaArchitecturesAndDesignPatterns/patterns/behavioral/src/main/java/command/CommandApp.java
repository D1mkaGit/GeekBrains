package command;

public class CommandApp {
    public static void main(String[] args) {
        Reports reports = new Reports();
        User user = new User(new ClientsReport(reports), new GoodsReport(reports), new AccountingReport(reports));

        user.genClientsRep();
        user.genGoodsRep();
        user.genAccountingRep();

    }
}
