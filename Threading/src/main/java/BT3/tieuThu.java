package BT3;

public class tieuThu implements Runnable {
    private int soLuong;
    private int id;
    private KhoMy kho;
    private volatile boolean running = true;

    public tieuThu(int soLuong, int id, KhoMy kho) {
        this.soLuong = soLuong;
        this.id = id;
        this.kho = kho;
    }

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            kho.tieuThu(soLuong, id);
            try {
                Thread.sleep(1000); // Thêm độ trễ giữa các lần tiêu thụ
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}