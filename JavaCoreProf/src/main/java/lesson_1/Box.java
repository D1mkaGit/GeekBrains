package lesson_1;

public class Box {
    private Object obj;

    public Box(Object obj) {
        this.obj = obj;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public void info() {
        System.out.println("obj " + obj);
        System.out.println("type " + obj.getClass());
    }
}

class BoxMain {
    public static void main(String[] args) {
//        Box box1 = new Box(1);
//        Box box2 = new Box("sd");
//
////        box1.info();
////        box2.info();
//
////        int x = 10;
////        x = x + (Integer) box1.getObj();
////        System.out.println(" x = " + x);
//
//        int x = 10;
//        x = x + (Integer) box2.getObj();
//        System.out.println(" x = " + x);
//
//        BoxUltimate<String> bStr = new BoxUltimate<>("Hello");
//        BoxUltimate<Integer> bInt = new BoxUltimate<>(1);

//        int x = 10;
//        x = x + bStr.getObj();
//
//        int x1 = 10;
//        x1 = x1 + bInt.getObj();

//        HashMap<Integer, String> map = new HashMap<>();


//        int res = 1;
//
//        switch (res) {
//            case 1 :
//                TwoGen<D1, D1responseAll> parser1 = new TwoGen<>();
//                parser1.parse();
//                break;
//            case 2 :
//                TwoGen<D2, D2responseAll> parser2 = new TwoGen<>();
//                parser2.parse();
//                break;
//        }
    }
}


class BoxInt {
    private Integer obj;

    public BoxInt(Integer obj) {
        this.obj = obj;
    }

    public Integer getObj() {
        return obj;
    }

    public void setObj(Integer obj) {
        this.obj = obj;
    }
}

class BoxString {
    private String obj;

    public BoxString(String obj) {

        this.obj = obj;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }
}