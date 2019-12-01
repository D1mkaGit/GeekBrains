package lesson_5.home_work;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MultiThreadsCalculations {

    private static final int size = 10000000;
    private static final int numOfThreads = 55;
    private static final int h = size / numOfThreads;
    private float[] arr;

    private float[] prepareArray() {
        float[] _arr = new float[size];
        Arrays.fill(_arr, 1);
        return _arr;
    }

    private float[] generateNewFloatBasedOnThreadsCount() {
        return new float[size / numOfThreads];
    }

    private void splitArrays( float[]... args ) {
        for (int i = 0; i < args.length; i++) {
            if (i == 0) System.arraycopy(arr, 0, args[i], 0, h);
            else System.arraycopy(arr, h, args[i], 0, h);
        }
    }

    public void multiThreadCalculations() {
        long b = System.currentTimeMillis();
        arr = prepareArray();

        Map<Integer, float[]> floatsHashMap = new HashMap<>();
        for (int i = 0; i < numOfThreads; i++) {
            floatsHashMap.put(i, generateNewFloatBasedOnThreadsCount());
        }
        /*System.out.println("Заполнили мапу с флоат значениями: " + (System.currentTimeMillis() - b) + " милисекунд");*/
        splitArrays(floatsHashMap.values().iterator().next());

        Map<Integer, Thread> threadsHashMap = new HashMap<>();
        for (int i = 0; i < floatsHashMap.entrySet().size(); i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    calculateSingleThreadSpecifiedArray(floatsHashMap.values().iterator().next());
                }
            });
            threadsHashMap.put(i, thread);
        }
        /*System.out.println("Создали мапу с тредами: " + (System.currentTimeMillis() - b) + " милисекунд");*/

        for (Map.Entry<Integer, Thread> thread : threadsHashMap.entrySet()) {
            thread.getValue().start();
            /*System.out.println("Запустили " + thread.getKey() + " тред: " + (System.currentTimeMillis() - b) + " " +
                    "милисекунд");*/
        }

        for (Map.Entry<Integer, Thread> thread : threadsHashMap.entrySet()) {
            try {
                thread.getValue().join();
                /* System.out.println("Сделали Join " + thread.getKey() + " треду: " + (System.currentTimeMillis() - b) + " милисекунд");*/
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /* System.out.println("Все треды были запущены: " + (System.currentTimeMillis() - b) + " милисекунд");*/

        mergeSplitArrays(floatsHashMap.values().iterator().next());

        System.out.println("На " + numOfThreads + " потоков пошло: " + (System.currentTimeMillis() - b) + " " +
                "милисекунд времени");
    }

    private void mergeSplitArrays( float[]... args ) {
        for (int i = 0; i < args.length; i++) {
            if (i == 0) System.arraycopy(args[i], 0, arr, 0, h);
            else System.arraycopy(args[i], 0, arr, h, h);
        }
    }

    public void calculateSingleThread() {
        arr = prepareArray();
        calculateSingleThreadSpecifiedArray(arr);
    }

    private void calculateSingleThreadSpecifiedArray( float[] _arr ) {
        long a = System.currentTimeMillis();
        for (int i = 0; i < _arr.length; i++) {
            _arr[i] = (float) (_arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Текущий тред выполнился за: " + (System.currentTimeMillis() - a) + " милисекунд");
    }
}

class MainCalculations {

    public static void main( String[] args ) {
        MultiThreadsCalculations singleThreadCalc = new MultiThreadsCalculations();
        singleThreadCalc.calculateSingleThread();

        System.out.println("***************************************");

        MultiThreadsCalculations multiThreadCalc = new MultiThreadsCalculations();
        multiThreadCalc.multiThreadCalculations();
    }
}
