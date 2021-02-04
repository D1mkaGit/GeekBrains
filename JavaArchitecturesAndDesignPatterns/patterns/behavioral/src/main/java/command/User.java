package command;

public class User {
    ReportI genClientsRep;
    ReportI genAccountingRep;
    ReportI genGoodsRep;

    public User(ReportI genClientsRep, ReportI genAccountingRep, ReportI genGoogsRep) {
        this.genClientsRep = genClientsRep;
        this.genAccountingRep = genAccountingRep;
        this.genGoodsRep = genGoogsRep;
    }

    void genClientsRep() {
        genClientsRep.Generate();
    }

    void genAccountingRep() {
        genAccountingRep.Generate();
    }

    void genGoodsRep() {
        genGoodsRep.Generate();
    }
}
