package lesson_1;

public class Stats<T extends Number> {
    private T[] nums;

    public Stats(T[] nums) {
        this.nums = nums;
    }

    public double avg() {
        double sum = 0.0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i].doubleValue();
        }

        return sum / nums.length;
    }

    public boolean sameAvg(Stats<? super Double> another) {
        return Math.abs(this.avg() - another.avg()) < 0.0001;
    }
}

class MainStats {
    public static void main(String[] args) {
        Integer[] intMass = {1, 2, 3, 4, 5};
        Double[] dobMass = {1.0, 2.0, 3.0, 4.0, 5.0};

        Stats<Integer> iob1 = new Stats<>(intMass);
        Stats<Double> iob2 = new Stats<>(dobMass);

        if (iob1.sameAvg(iob2)) {
            System.out.println("Средние значения равны");
        } else {
            System.out.println("Средние значения не равны");
        }

        // List<? extends Animal> al = new ArrayList<Cat>();

        //  Animal[] mass = new Cat[]{};

//        Stats<Integer> iob1 = new Stats<>(intMass);
//        double res1 = iob1.avg();
//        System.out.println(res1);
//
//        Double[] dobMass = {1.0,2.0,3.0,4.0,5.0};
//        Stats<Double> iob2 = new Stats<>(dobMass);
//        double res2 = iob2.avg();
//        System.out.println(res2);

//        Float[] imass = {1.0f,2.0f,3.0f,4.0f,5.0f};
//        Stats<Float> iob = new Stats<>(imass);
//        double res = iob.avg();
//        System.out.println(res);

//        String[] imass = {"abc1", "abc2"};
//        Stats<String> iob = new Stats<>(imass);
//        double res = iob.avg();
//        System.out.println(res);
    }
}
