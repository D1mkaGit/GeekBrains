package iterator;


import java.util.*;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>(Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7}));
        List<Integer> list = new ArrayList<>(set);

        // ArrayList<Integer> list = new ArrayList<>(Arrays.asList(new Integer[]{1,2,3,4,5,6}));
        Iterator<Integer> it = list.iterator();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        it.hasNext();
        System.out.println(it.next());
    }

    class helper<T> {


        //Реализуйте метод добавления элемента в конец ArrayList
        int count = 0;
        T[] array;

        public void add(T item) {
            if (count == array.length - 1)
                resize(array.length * 2);
            array[count++] = item;
        }

        private void resize(int newLength) {
            Object[] newArray = new Object[newLength];
            System.arraycopy(array, 0, newArray, 0, count);
            array = (T[]) newArray;
        }

        //Реализуйте метод удаления элемента из LinkedList
        void deleteNode(int position) {
            if (array == null)
                return;

            T[] temp = array;

            if (position == 0) {
                array = temp;
                return;
            }
            for (int i = 0; temp != null && i < position - 1; i++) {
                temp[i] = temp[i + 1];

                if (temp[i] == null || temp[i + 1] == null)
                    return;

                T next = temp[i + 2];
                temp[i + 1] = next;
            }
        }
    }
}
