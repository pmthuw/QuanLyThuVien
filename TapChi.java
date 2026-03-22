package model.items;

public class TapChi extends Items {
    private String tenTapChi;
    private int soPH;    // Số Phát Hành
    private int thangPH; // Tháng Phát Hành

    // Constructor (5 tham số: tenTapChi, tenNXB, soBPH, soPH, thangPH)
    public TapChi(String tenTapChi, String tenNXB, int soBPH, int soPH, int thangPH) {
        super(tenNXB, soBPH); // Gọi lớp cha
        this.tenTapChi = tenTapChi;
        this.soPH = soPH;
        this.thangPH = thangPH;
    }

    @Override
    public void hienThi() {
        System.out.println("--- Thông Tin Tạp Chí ---");
        // Truy cập thuộc tính protected từ lớp cha Items
        System.out.println("ID Tài Liệu: " + taiLieu);
        System.out.println("Tên Tạp Chí: " + tenTapChi);
        System.out.println("Nhà xuất bản: " + tenNXB);
        System.out.println("Số bản phát hành: " + soBPH);
        
        // Truy cập thuộc tính private của lớp TapChi
        System.out.println("Số phát hành: " + this.soPH);
        System.out.println("Tháng phát hành: " + this.thangPH);
        System.out.println("-------------------------");
    }

    // Getters / Setters
    public String getTenTapChi() {
        return tenTapChi;
    }

    public void setTenTapChi(String tenTapChi) {
        this.tenTapChi = tenTapChi;
    }

    public int getSoPH() {
        return soPH;
    }

    public void setSoPH(int soPH) {
        this.soPH = soPH;
    }

    public int getThangPH() {
        return thangPH;
    }

    public void setThangPH(int thangPH) {
        this.thangPH = thangPH;
    }
}