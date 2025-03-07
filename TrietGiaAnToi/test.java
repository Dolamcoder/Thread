/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TrietGiaAnToi;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author ASUS
 */
public class test {
    public static void main(String[] args) {
        int numPhilosophers = 5;
        ReentrantLock[] forks = new ReentrantLock[numPhilosophers];
        Semaphore maxDiners = new Semaphore(numPhilosophers - 1); // Giới hạn số triết gia ăn cùng lúc

        // Khởi tạo nĩa
        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }

        // Khởi tạo và chạy triết gia
        TrietGia[] philosophers = new TrietGia[numPhilosophers];
        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new TrietGia(i, forks[i], forks[(i + 1) % numPhilosophers], maxDiners);
            philosophers[i].start();
        }
    }
}
