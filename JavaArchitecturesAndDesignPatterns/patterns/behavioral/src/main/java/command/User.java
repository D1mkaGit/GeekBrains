package command;

public class User {
    final ReportI genClientsRep;
    final ReportI genAccountingRep;
    final ReportI genGoodsRep;

    public User(ReportI genClientsRep, ReportI genAccountingRep, ReportI genGoogsRep) {
        this.genClientsRep = genClientsRep;
        this.genAccountingRep = genAccountingRep;
        this.genGoodsRep = genGoogsRep;
    }

    void genClientsRep() {
        genClientsRep.generate();
    }

    void genAccountingRep() {
        genAccountingRep.generate();
    }

    void genGoodsRep() {
        genGoodsRep.generate();
    }
}
