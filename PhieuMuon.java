package model.transaction;

import model.items.Items;
import model.user.DocGia;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit; // Import thư viện để tính khoảng cách ngày

public class PhieuMuon {
    // 1. Thuộc tính
    private String maPhieu;
    private DocGia docGia;
    private List<Items> dsTaiLieuMuon;
    private Date ngayMuon;
    private Date ngayTraDuKien;
    
    // Ngày trả thực tế (null nghĩa là chưa trả)
    private Date ngayTraThucTe; 

    // Biến static để tự tăng mã
    private static int dem = 0;

    // 2. Constructor
    public PhieuMuon(DocGia docGia, Date ngayMuon, Date ngayTraDuKien) {
        dem++;
        this.maPhieu = String.format("PM%03d", dem);
        
        this.docGia = docGia;
        this.ngayMuon = ngayMuon;
        this.ngayTraDuKien = ngayTraDuKien;
        
        // Khởi tạo danh sách rỗng
        this.dsTaiLieuMuon = new ArrayList<>();
        
        // Mặc định khi mới tạo là chưa trả sách
        this.ngayTraThucTe = null; 
    }

    // 3. Các phương thức nghiệp vụ
    
    public void themTaiLieu(Items item) {
        this.dsTaiLieuMuon.add(item);
    }

    // Hàm tính tiền phạt
    public long tinhTienPhat() {
        if (ngayTraThucTe == null) return 0; // Chưa trả thì chưa tính
        
        // Nếu trả trước hoặc đúng hạn -> Phạt = 0
        if (ngayTraThucTe.compareTo(ngayTraDuKien) <= 0) {
            return 0;
        }

        // Tính số ngày trễ (Dùng TimeUnit để đổi từ mili-giây sang ngày)
        long diffInMillies = Math.abs(ngayTraThucTe.getTime() - ngayTraDuKien.getTime());
        long soNgayTre = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

        // Quy định: Phạt 5.000đ / 1 ngày / 1 cuốn sách
        return soNgayTre * 5000 * dsTaiLieuMuon.size();
    }

    // Hàm in phiếu
    public void inPhieu() {
        System.out.println("\n========= PHIẾU MƯỢN SÁCH =========");
        System.out.println("Mã phiếu: " + this.maPhieu);
        System.out.println("Ngày mượn: " + this.ngayMuon);
        System.out.println("Hạn trả: " + this.ngayTraDuKien);
        
        // Logic hiển thị trạng thái
        if (this.ngayTraThucTe != null) {
            System.out.println("Ngày trả thực tế: " + this.ngayTraThucTe);
            System.out.println("Tiền phạt: " + this.tinhTienPhat() + " VNĐ");
            System.out.println("TRẠNG THÁI: [ĐÃ TRẢ]");
        } else {
            System.out.println("TRẠNG THÁI: [ĐANG MƯỢN]");
        }
        
        System.out.println("--- Thông tin độc giả ---");

        this.docGia.hienThiTTCN(); 
        
        System.out.println("--- Danh sách tài liệu mượn ---");
        for (Items item : dsTaiLieuMuon) {
            item.hienThi(); // Đa hình
        }
        System.out.println("===================================");
    }

    // 4. Getters / Setters
    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }
    // -----------------------------------------------------------

    public DocGia getDocGia() {
        return docGia;
    }

    public void setDocGia(DocGia docGia) {
        this.docGia = docGia;
    }

    public List<Items> getDsTaiLieuMuon() {
        return dsTaiLieuMuon;
    }

    public Date getNgayMuon() {
        return ngayMuon;
    }

    public void setNgayMuon(Date ngayMuon) {
        this.ngayMuon = ngayMuon;
    }

    public Date getNgayTraDuKien() {
        return ngayTraDuKien;
    }

    public void setNgayTraDuKien(Date ngayTraDuKien) {
        this.ngayTraDuKien = ngayTraDuKien;
    }

    public Date getNgayTraThucTe() {
        return ngayTraThucTe;
    }

    public void setNgayTraThucTe(Date ngayTraThucTe) {
        this.ngayTraThucTe = ngayTraThucTe;
    }
}