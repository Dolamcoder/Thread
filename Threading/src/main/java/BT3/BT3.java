package BT3;

public class BT3 {
    public static void main(String[] args) {
        KhoMy kho = new KhoMy(1000); // Kho chứa tối đa 1000 đơn vị mỳ

        // Tạo và khởi chạy luồng sản xuất
        Thread sx1 = new Thread(new sanXuat(50, 302, kho));
        sx1.start();

        // Tạo và khởi chạy các luồng tiêu thụ
        Thread tt1 = new Thread(new tieuThu(3, 101, kho));
        tt1.start();

        Thread tt2 = new Thread(new tieuThu(3, 103, kho));
        tt2.start();

        Thread tt3 = new Thread(new tieuThu(3, 105, kho));
        tt3.start();

        Thread tt4 = new Thread(new tieuThu(3, 104, kho));
        tt4.start();

        Thread tt5 = new Thread(new tieuThu(3, 106, kho));
        tt5.start();

        Thread tt6 = new Thread(new tieuThu(3, 108, kho));
        tt6.start();

    }
}