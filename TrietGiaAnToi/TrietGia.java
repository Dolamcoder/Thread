package TrietGiaAnToi;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class TrietGia extends Thread {
    private static final int NUM_PHILOSOPHERS = 5;
    private static boolean[] lastEater = new boolean[NUM_PHILOSOPHERS]; // Mảng đánh dấu ai vừa ăn xong

    private int id;
    private ReentrantLock leftFork, rightFork;
    private Semaphore max;

    public TrietGia(int id, ReentrantLock leftFork, ReentrantLock rightFork, Semaphore max) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.max = max;
    }

    public void eat() throws InterruptedException {
        System.out.println("Triet gia " + id + " --> dang an...");
        Thread.sleep((long) (Math.random() * 1000));
        lastEater[id] = true; // Đánh dấu triết gia này vừa ăn xong
    }

    public void think() throws InterruptedException {
        System.out.println("Triet gia " + id + " --> dang suy nghi...");
        Thread.sleep((long) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();

                // Đợi nếu vừa ăn xong, nhường quyền ăn cho người khác
                while (lastEater[id]) {
                    Thread.sleep(100); // Chờ 100ms để đảm bảo không ăn liên tiếp
                }

                max.acquire(); // Giới hạn số triết gia ăn cùng lúc
                leftFork.lock();
                System.out.println("Triet gia " + id + " --> da lay nia trai");
                rightFork.lock();
                System.out.println("Triet gia " + id + " --> da lay nia ben phai");
                
                eat();

                rightFork.unlock();
                System.out.println("Triet gia " + id + " --> da tra nia ben phai");
                leftFork.unlock();
                System.out.println("Triet gia " + id + " --> da tra nia ben trai");
                max.release(); // Cho phép triết gia khác ăn

                // Sau khi ăn xong, hủy đánh dấu để người này có thể ăn lại trong lượt sau
                Thread.sleep(200); // Tạo một khoảng nghỉ để tránh ăn liên tục
                lastEater[id] = false;
            }
        } catch (InterruptedException e) {
            System.out.println("Triet gia " + id + " bi gián đoạn!");
            Thread.currentThread().interrupt();
        }
    }
}
