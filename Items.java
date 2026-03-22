package model.items;

public abstract class Items {

    // Các thuộc tính chung
    protected String taiLieu; // Mã tài liệu (ID)
    protected String tenNXB;  // Tên nhà xuất bản
    protected int soBPH;      // Số bản phát hành
    
    // Biến static để tự động tăng mã ID khi tạo mới
    private static int dem = 0;

    // Constructor
    public Items(String tenNXB, int soBPH) {
        dem++;
        // Tự động tạo mã: TL001, TL002...
        this.taiLieu = String.format("TL%03d", dem);
        this.tenNXB = tenNXB;
        this.soBPH = soBPH;
    }

    // Phương thức trừu tượng
    public abstract void hienThi();

    // --- Getters / Setters ---
    
    public String getTaiLieu() {
        return taiLieu;
    }

    // Hàm để khôi phục ID cũ từ file ---
    public void setTaiLieu(String taiLieu) {
        this.taiLieu = taiLieu;
    }
    // -------------------------------------------------------------

    public String getTenNXB() {
        return tenNXB;
    }

    public void setTenNXB(String tenNXB) {
        this.tenNXB = tenNXB;
    }

    public int getSoBPH() {
        return soBPH;
    }

    public void setSoBPH(int soBPH) {
        this.soBPH = soBPH;
    }
}