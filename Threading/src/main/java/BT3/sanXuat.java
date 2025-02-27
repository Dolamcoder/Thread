package BT3;

public class sanXuat implements Runnable {
    private KhoMy kho;
    private int soLuong;
    private int id;
    private volatile boolean running = true;

    public sanXuat(int soLuong, int id, KhoMy kho) {
        this.kho = kho;
        this.soLuong = soLuong;
        this.id = id;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            kho.sanXuat(soLuong, id);
            try {
                Thread.sleep(1000); // Thêm độ trễ giữa các lần sản xuất
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}