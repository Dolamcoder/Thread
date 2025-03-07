/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DeadLock;

/**
 *
 * @author ASUS
 */
public class test {
    public static void main(String[] args) {
         Book book = new Book();
        Person nguoiA = new Person("A", book, true);
        Person nguoiB = new Person("B", book, false);
        nguoiA.start();
        nguoiB.start();
    }
}
