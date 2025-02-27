package BT3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class KhoMy {
    private final ReentrantLock lock = new ReentrantLock(true); // Lock công bằng
    private final Condition coHang = lock.newCondition(); // Điều kiện có hàng
    private final Condition conCho = lock.newCondition(); // Điều kiện còn chỗ trống
    private int myMax; // Số lượng mỳ tối đa trong kho
    private int soMy = 0; // Số lượng mỳ hiện tại trong kho

    public KhoMy(int max) {
        this.myMax = max;
    }

    // Phương thức sản xuất mỳ
    public void sanXuat(int soLuong, int idMaySanXuat) {
        lock.lock();
        try {
            while (soMy + soLuong > myMax) {
                conCho.await(); // Chờ nếu kho đầy
            }
            soMy += soLuong;
            System.out.println(">>>>>>****************************************************");
            System.out.println("May san xuat so " + idMaySanXuat + " da san xuat " + soLuong + " My");
            System.out.println("So my ton kho la: " + soMy);
            coHang.signalAll(); // Đánh thức tất cả luồng tiêu thụ
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // Phương thức tiêu thụ mỳ
    public void tieuThu(int soLuong, int IDkhachHang) {
        lock.lock();
        try {
            while (soMy < soLuong) {
                coHang.await(); // Chờ nếu không đủ hàng
            }
            soMy -= soLuong;
            System.out.println(">>>>>>>>*************************************************");
            System.out.println("Khach hang so " + IDkhachHang + " da mua " + soLuong + " o My");
            System.out.println("So my ton kho la: " + soMy);
            conCho.signalAll(); // Đánh thức tất cả luồng sản xuất
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // Lấy số lượng mỳ tối đa
    public int getMyMax() {
        return myMax;
    }

    // Lấy số lượng mỳ hiện tại trong kho
    public int getSoMy() {
        return soMy;
    }
}