package Lesson_5.hw;

class MyArr{
    private int[] arr;
    private int size;

    public MyArr(int size) {
        this.size = 0;
        this.arr = new int[size];
    }

    public boolean binaryFind(int search) throws Exception {
        int index = recBinaryFind(search, 0, size-1);
        if(index < size){
            System.out.println("индекс элемента - " + index);
            return true;
        }else{
            throw new Exception("Нет такого элемента в массиве");
        }
    }

    private int recBinaryFind(int searchKey, int low, int high) {
        int curIn;
        curIn = (low + high ) / 2;
        if (arr[curIn] == searchKey)
            return curIn;
        else
        if(low > high)
            return size;
        else{
            if(arr[curIn] < searchKey)
                return recBinaryFind(searchKey, curIn+1, high);
            else
                return recBinaryFind(searchKey, low, curIn-1);
        }
    }

    public void insert(int value){
        int i;
        for(i=0;i<this.size;i++){
            if (this.arr[i]>value)
                break;
        }
        for(int j=this.size;j>i;j--){
            this.arr[j] = this.arr[j-1];
        }
        this.arr[i] = value;
        this.size++;
    }
}

class MyArrApp {
    public static void main(String[] args) throws Exception {
        MyArr arr = new MyArr(3);
        arr.insert(1);
        arr.insert(2);
        arr.insert(3);

        int search = 20;

        System.out.println(arr.binaryFind(search));
    }
}