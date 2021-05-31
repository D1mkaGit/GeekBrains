package memory;

public class ex {

    private int b;
    private int c;
    private int d;
    volatile boolean a = false;



    public void ex() {
        b = 5;
        c = 6;
        d = b + c;
        a = true;

    }


}
