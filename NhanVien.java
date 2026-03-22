package model.user;

public class NhanVien extends Nguoi implements IPhuCap {
    private String maNV;
    private String chucVu;
    private double luongCoBan;
    
    // Tao ma nhan vien tu dong
    private static int dem = 0;

    // Constructor
    public NhanVien(String hoTen, String diaChi, String sdt, String email, String chucVu, double luongCoBan) {
        super(hoTen, diaChi, sdt, email);

        this.chucVu = chucVu;
        this.luongCoBan = luongCoBan;

        // Tu dong tao ma NV
        dem++;
        this.maNV = String.format("NV%03d", dem);
    }

    // Interface
    @Override
    public double tinhPhuCap() {
        // Truong phong 500k, nhan vien 200k
        if ("Truong phong".equalsIgnoreCase(this.chucVu)) {
            return 500000;
        } else {
            return 200000;
        }
    }

    // Luong nhan vien
    public double tinhTongLuong() {
        return this.luongCoBan + this.tinhPhuCap();
    }

    @Override
    public void hienThiTTCN() {
        System.out.println("--- Thông Tin Nhân Viên ---");
        System.out.println("Mã NV: " + this.maNV);
        
        // Goi lai phuong thuc goc cua lop cha (Nguoi) de hien thi
        super.hienThiTTCN(); 
        
        // Hien thi thong tin cua nhan vien
        System.out.println("Chức vụ: " + this.chucVu);
        System.out.println("Lương cơ bản: " + this.luongCoBan);
        System.out.println("Phụ cấp: " + this.tinhPhuCap());
        System.out.println("Tổng lương: " + this.tinhTongLuong());
        System.out.println("---------------------------");
    }

    // Get set
    public String getMaNV() {
        return maNV;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    // --- MỚI THÊM: Hàm set để khôi phục ID cũ khi đọc file ---
    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }
}