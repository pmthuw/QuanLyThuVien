package model.items;

public class Sach extends Items {
    private String tenSach;
    private String tenTG; // Tác giả
    private int soTrang;

    // Constructor (5 tham số: tenSach, tenNXB, soBPH, tenTG, soTrang)
    public Sach(String tenSach, String tenNXB, int soBPH, String tenTG, int soTrang) {
        super(tenNXB, soBPH); // Gọi lớp cha
        this.tenSach = tenSach;
        this.tenTG = tenTG;
        this.soTrang = soTrang;
    }

    @Override
    public void hienThi() {
        System.out.println("--- Thông Tin Sách ---");
        // Truy cập thuộc tính protected từ lớp cha Items
        System.out.println("ID Tài Liệu: " + taiLieu);
        System.out.println("Tên Sách: " + tenSach);
        System.out.println("Nhà xuất bản: " + tenNXB);
        System.out.println("Số bản phát hành: " + soBPH);
        
        // Truy cập thuộc tính private của lớp Sach
        System.out.println("Tên tác giả: " + this.tenTG);
        System.out.println("Số trang: " + this.soTrang);
        System.out.println("------------------------");
    }

    // Getters / Setters
    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public int getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(int soTrang) {
        this.soTrang = soTrang;
    }
}