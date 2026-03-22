package model.user;

public class DocGia extends Nguoi {
    private String maDG;
    private static int dem = 0;

    // Constructor
    public DocGia(String hoTen, String diaChi, String sdt, String email) {
        super(hoTen, diaChi, sdt, email);
        // tao ma doc gia tu dong
        dem++;
        this.maDG = String.format("DG%03d", dem);
    }

	public void hienThiTTCN() {
System.out.println("Mã Độc Giả: " + this.maDG);
        
        // goi lai phuong thuc goc cua lop cha (Nguoi) de hien thi
        super.hienThiTTCN();
        System.out.println("-------------------------");
    }

    // Getters
    public String getMaDG() {
        return maDG;
    }

    // --- MỚI THÊM: Hàm set để khôi phục ID cũ khi đọc file ---
    public void setMaDG(String maDG) {
        this.maDG = maDG;
    }
}