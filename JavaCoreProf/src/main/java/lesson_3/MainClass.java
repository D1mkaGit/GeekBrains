package lesson_3;

import java.io.*;

public class MainClass {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        File file = new File("123/5");
//        file.mkdirs();

//        File file = new File("123/file.txt");

//        File fileNew = new File("123/fileNew.txt");
//        System.out.println(fileNew.isHidden());

//        file.renameTo(fileNew);

       // fileNew.getParent()

       // file.createNewFile();

//        String[] str = file.list();
//
//        for (String fileName:str) {
//            System.out.println(fileName);
//        }

//        long t = System.currentTimeMillis();
//
//        try (FileInputStream in = new FileInputStream("123/123.txt")) {
//            byte[] arr = new byte[512];
//            int x;
//            while ((x = in.read(arr)) != -1) {
//                System.out.print(new String(arr, 0, x, "UTF-8"));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(System.currentTimeMillis() - t);


//        try (InputStreamReader in = new InputStreamReader(new FileInputStream("123/123.txt"), "UTF-8")) {
//            int x;
//            while ((x = in.read()) != -1) {
//                System.out.println((char) x);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        FileReader fr = null;
//        BufferedReader br = null;

//        try (BufferedReader br = new BufferedReader(new FileReader("123/123.txt"))) {
////            fr = new FileReader("123/123.txt");
////            br = new BufferedReader(fr);
////
//            String currentStr;
//
//            while ((currentStr = br.readLine()) != null) {
//                System.out.println(currentStr);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
////        finally {
////            br.close();
////        }


//        PipedInputStream in = null;
//        PipedOutputStream out = null;
//
//        try {
//            in = new PipedInputStream();
//            out = new PipedOutputStream();
//
//            out.connect(in);
//
//            for (int i = 0; i < 100; i++) {
//                out.write(i);
//            }
//
//            int x;
//
//            while ((x = in.read()) != -1) {
//                System.out.println(x);
//            }
//
//            in.close();
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            in.close();
//            out.close();
//        }

//        ArrayList<InputStream> ali = new ArrayList<>();
//
//        ali.add(new FileInputStream("123/1.txt"));
//        ali.add(new FileInputStream("123/2.txt"));
//        ali.add(new FileInputStream("123/3.txt"));
//
//        SequenceInputStream in  = new SequenceInputStream(Collections.enumeration(ali));
//
//        int x;
//        while ((x = in.read()) != -1) {
//            System.out.print((char)x);
//        }
//
//        in.close();

//        try (RandomAccessFile raf = new RandomAccessFile("123/1.txt", "r")) {
//            raf.seek(2);
//            System.out.println((char)raf.read());
//            raf.seek(0);
//            System.out.println((char)raf.read());
//        }


//        Student student = new Student(1, "Bob");
//        Book book = new Book("Jungle Book");
//        student.book = book;
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("stur.ser"));
//        oos.writeObject(student);
//        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stur.ser"));
        Student s1 = (Student) ois.readObject();
        ois.close();
        s1.info();
    }
}
