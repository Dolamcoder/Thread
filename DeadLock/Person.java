/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DeadLock;

/**
 *
 * @author ASUS
 */
public class Person extends Thread{
    private String name;
    private Book book;
    private boolean laySachToanTruoc;

   
    public Person(String name, Book book, boolean laySachToanTruoc) {
        this.name = name;
        this.book = book;
        this.laySachToanTruoc = laySachToanTruoc;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (laySachToanTruoc) {
                    // Người này lấy sách Toán trước
                    if (book.sachToan.tryLock()) {
                        System.out.println(name + " lay sach toan, bat dau doc");
                        Thread.sleep(100);

                        if (book.sachLy.tryLock()) {
                            System.out.println(name + " lay sach ly, bat dau doc");
                            Thread.sleep(500);
                            book.sachLy.unlock();
                        }

                        book.sachToan.unlock();
                    }
                } else {
                    // Người này lấy sách Lý trước
                    if (book.sachLy.tryLock()) {
                        System.out.println(name + " lay sach ly, bat dau doc");
                        Thread.sleep(100);

                        if (book.sachToan.tryLock()) {
                            System.out.println(name + " lay sach toan, nay dau doc");
                            Thread.sleep(500);
                            book.sachToan.unlock();
                        }

                        book.sachLy.unlock();
                    }
                }
                
                // Nghỉ 1 chút để tạo vòng lặp
                Thread.sleep(500);
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
    

