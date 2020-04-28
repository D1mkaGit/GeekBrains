package com.geekbrains.nio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class MainApp {
    public static void main(String[] args) {
    }

    private static void bufferExample() {
        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.put((byte)10);
        buf.put((byte)20);
        buf.put((byte)30);
        buf.put((byte)40);
        buf.flip(); // -> to read
        System.out.println(buf.get());
        System.out.println(buf.get());
        buf.rewind(); // set position to 0
        System.out.println(buf.get());
        System.out.println(buf.get());
        buf.compact(); // save all unread data
        buf.put((byte)50);
        buf.put((byte)60);
        buf.flip(); // -> to read
        System.out.println(buf.get());
        System.out.println(buf.get());
        System.out.println(buf.get());
        System.out.println(buf.get());
    }

    private static void transferFileEx() throws InterruptedException, IOException {
        CountDownLatch serverStarted = new CountDownLatch(1);
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(8189)) {
                System.out.println("Сервер запущен");
                serverStarted.countDown();
                Socket socket = serverSocket.accept();
                System.out.println("Клиент подключился");
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                FilePackage inputPackage = (FilePackage) in.readObject();
                Files.write(Paths.get("server_storage", inputPackage.getFilename()), inputPackage.getData(), StandardOpenOption.CREATE);
                in.close();
                socket.close();
                System.out.println("Файл получен, завершаем работу");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        serverStarted.await();
        Socket socket = new Socket("localhost", 8189);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        FilePackage filePackage = new FilePackage("client_storage", "abc.txt");
        out.writeObject(filePackage);
        out.close();
        socket.close();
    }

    private static void ioReadFileExample() {
        try (FileInputStream in = new FileInputStream("2.txt")) {
            int x;
            while ((x = in.read()) != -1) {
                System.out.print((char) x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void nioReadFileExample() {
        try (RandomAccessFile raf = new RandomAccessFile("read_file_ex.txt", "rw")) {
            FileChannel inChannel = raf.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(4);

            while (inChannel.read(buf) != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char)buf.get());
                }
                buf.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fileChannelExample() {
        try (
                RandomAccessFile srcFile = new RandomAccessFile("channel_copy_ex_src.txt", "rw");
                RandomAccessFile dstFile = new RandomAccessFile("channel_copy_ex_dst.txt", "rw")
        ) {
            FileChannel srcChannel = srcFile.getChannel();
            FileChannel dstChannel = dstFile.getChannel();

            long position = 0;
            long size = srcChannel.size();

//            dstChannel.transferFrom(srcChannel, position, size);
            srcChannel.transferTo(position, size, dstChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void classFilesMethodsExample() {
        try {
            System.out.println("\n>>> Move Example");
            Files.deleteIfExists(Paths.get("1.txt"));
            Files.createFile(Paths.get("1.txt"));
            Files.move(Paths.get("1.txt"), Paths.get("move_ex.txt"), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("\n>>> Copy Example");
            Files.deleteIfExists(Paths.get("2.txt"));
            Files.createFile(Paths.get("2.txt"));
            Files.copy(Paths.get("2.txt"), Paths.get("copy_2.txt"), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("\n>>> Print all lines (stream api) from file where str.length > 2");
            Files.lines(Paths.get("text.txt"))
                    .filter(s -> s.length() > 2)
                    .forEach(System.out::println);

            System.out.println("\n>>> Print only all files in current project directory");
            Files.list(Paths.get("."))
                    .filter(p -> !Files.isDirectory(p))
                    .forEach(System.out::println);

            System.out.println("\n>>> Iterator files list example");
            Iterator<Path> iter = Files.newDirectoryStream(Paths.get(".")).iterator();
            while (iter.hasNext()) {
                Path p = iter.next();
                System.out.println(p);
            }

            System.out.println("\n>>> Read all bytes from file example");
            byte[] data = Files.readAllBytes(Paths.get("text.txt"));
            System.out.println("String: " + new String(data));

            System.out.println("\n>>> Write byte[] example / file size");
            Files.write(Paths.get("bytes_array_w.txt"), new byte[]{65, 66, 67}, StandardOpenOption.CREATE);
            System.out.println("bytes_array_w.txt size = " + Files.size(Paths.get("bytes_array_w.txt")) + " bytes");

            System.out.println("\n>>> Read all lines to list example");
            List<String> allLinesList = Files.readAllLines(Paths.get("text.txt"));
            System.out.println(allLinesList);

            System.out.println("\n>>> walkFileTree() example");
            Files.walkFileTree(Paths.get("1"), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    System.out.println("preVisitDir: " + dir);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    System.out.println("visitFile: " + file);
                    if (file.getFileName().toString().equals("3.txt")) {
                        System.out.println("Ok - 3.txt");
                        return FileVisitResult.TERMINATE;
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}