package service;

import model.items.Items;
import model.user.DocGia;
import model.transaction.PhieuMuon;

import java.util.ArrayList;
import java.util.Date; 
import java.util.List;
import java.util.Scanner; 
import java.text.SimpleDateFormat; 
import java.text.ParseException;

// Lớp Quản Lý Mượn Trả
public class QuanLyMuonTra {

    // 1. Thuộc tính
    private List<PhieuMuon> dsPhieuMuon;
    
    // Tham chiếu đến các kho dữ liệu khác (Dependency Injection)
    private QuanLyTaiLieu qlTaiLieu;
    private QuanLyDocGia qlDocGia;

    // 2. Khởi tạo
    public QuanLyMuonTra(QuanLyTaiLieu qlTaiLieu, QuanLyDocGia qlDocGia) {
        this.dsPhieuMuon = new ArrayList<>();
        this.qlTaiLieu = qlTaiLieu;
        this.qlDocGia = qlDocGia;
    }

    // --- CÁC PHƯƠNG THỨC NGHIỆP VỤ ---

    // A. TẠO PHIẾU MƯỢN (Có nhập ngày hạn trả)
    public void taoPhieuMuon(Scanner scanner) {
        System.out.println("--- Tạo Phiếu Mượn Mới ---");
        
        // 1. Kiểm tra Độc giả
        System.out.print("Nhập mã độc giả (ví dụ: DG001): ");
        String maDocGia = scanner.nextLine();
        
        DocGia docGia = qlDocGia.timKiemTheoID(maDocGia);
        if (docGia == null) {
            System.out.println("Lỗi: Không tìm thấy độc giả có mã " + maDocGia);
            return;
        }

        // 2. Nhập ngày hạn trả
        Date ngayMuon = new Date(); // Mặc định ngày mượn là hôm nay
        Date ngayTraDuKien = null;
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.println("Ngày mượn: " + df.format(ngayMuon));
        
        // Vòng lặp bắt buộc nhập đúng định dạng ngày
        while (ngayTraDuKien == null) {
            System.out.print("Nhập ngày hẹn trả (dd/MM/yyyy): ");
            String strNgayTra = scanner.nextLine();
            try {
                // Chuyển chuỗi nhập vào thành đối tượng Date
                ngayTraDuKien = df.parse(strNgayTra);
                
                // Kiểm tra logic: Ngày trả phải sau ngày mượn
                if (ngayTraDuKien.before(ngayMuon)) {
                    System.out.println("Lỗi: Ngày hẹn trả phải sau ngày mượn!");
                    ngayTraDuKien = null; // Reset để bắt nhập lại
                }
            } catch (ParseException e) {
                System.out.println("Lỗi: Định dạng ngày sai (ví dụ đúng: 25/12/2025). Vui lòng nhập lại.");
            }
        }

        // 3. Tạo phiếu mượn
        PhieuMuon phieuMoi = new PhieuMuon(docGia, ngayMuon, ngayTraDuKien);
        
        // 4. Thêm tài liệu vào phiếu
        String maTaiLieu;
        while (true) {
            System.out.print("Nhập mã tài liệu (ví dụ: TL001), nhập '0' để kết thúc: ");
            maTaiLieu = scanner.nextLine();
            if ("0".equals(maTaiLieu)) {
                break;
            }
            
            Items item = qlTaiLieu.timKiemTheoID(maTaiLieu);
            if (item != null) {
                phieuMoi.themTaiLieu(item);
                System.out.println("-> Đã thêm '" + item.getTenNXB() + "' vào phiếu."); // In tạm NXB hoặc Tên sách nếu ép kiểu
            } else {
                System.out.println("Không tìm thấy tài liệu có mã: " + maTaiLieu);
            }
        }

        // 5. Lưu phiếu mượn nếu có sách
        if (!phieuMoi.getDsTaiLieuMuon().isEmpty()) {
            this.dsPhieuMuon.add(phieuMoi);
            System.out.println("==> Tạo phiếu mượn " + phieuMoi.getMaPhieu() + " thành công!");
            phieuMoi.inPhieu(); 
        } else {
            System.out.println("Phiếu mượn rỗng (không có sách), đã hủy.");
        }
    }

    // B. XỬ LÝ TRẢ SÁCH
    public void xuLyTraSach(Scanner scanner) {
        System.out.println("--- Xử Lý Trả Sách ---");
        System.out.print("Nhập Mã Phiếu Mượn cần trả (VD: PM001): ");
        String maPhieu = scanner.nextLine();

        PhieuMuon phieu = timKiemPhieuTheoID(maPhieu);
        
        // Kiểm tra tồn tại
        if (phieu == null) {
            System.out.println("Lỗi: Không tìm thấy phiếu mượn này.");
            return;
        }

        // Kiểm tra đã trả chưa
        if (phieu.getNgayTraThucTe() != null) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Lỗi: Phiếu này ĐÃ TRẢ rồi vào ngày " + df.format(phieu.getNgayTraThucTe()));
            return;
        }

        // Nhập ngày trả thực tế
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Ngày hạn trả là: " + df.format(phieu.getNgayTraDuKien()));
        
        System.out.print("Nhập ngày khách trả thực tế (dd/MM/yyyy) hoặc gõ 'now' để lấy hôm nay: ");
        String inputDate = scanner.nextLine();
        
        Date ngayTraThucTe = new Date(); // Mặc định là hôm nay (now)
        
        if (!"now".equalsIgnoreCase(inputDate)) {
            try {
                ngayTraThucTe = df.parse(inputDate);
            } catch (ParseException e) {
                System.out.println("Định dạng ngày sai! Hệ thống tự động lấy ngày hôm nay.");
            }
        }

        // Cập nhật trạng thái
        phieu.setNgayTraThucTe(ngayTraThucTe);
        
        // Thông báo kết quả
        System.out.println("-> Đã cập nhật trạng thái: ĐÃ TRẢ");
        long tienPhat = phieu.tinhTienPhat();
        
        if (tienPhat > 0) {
            System.out.println("!!! KHÁCH TRẢ MUỘN !!!");
            System.out.println("Số tiền phạt: " + tienPhat + " VNĐ");
        } else {
            System.out.println("Khách trả đúng hạn. Không phạt.");
        }
    }
    
    // C. HIỂN THỊ DANH SÁCH
    public void hienThiDanhSachPhieuMuon() {
        if (dsPhieuMuon.isEmpty()) {
            System.out.println("Chưa có phiếu mượn nào trong hệ thống.");
            return;
        }
        
        System.out.println("\n===== DANH SÁCH PHIẾU MƯỢN =====");
        for (PhieuMuon phieu : dsPhieuMuon) {
            phieu.inPhieu();
        }
        System.out.println("================================");
    }
    
    // D. TÌM KIẾM
    public PhieuMuon timKiemPhieuTheoID(String maPhieu) {
        for (PhieuMuon phieu : dsPhieuMuon) {
            if (phieu.getMaPhieu().equalsIgnoreCase(maPhieu)) {
                return phieu;
            }
        }
        return null;
    }

    // Getter
    public List<PhieuMuon> getDsPhieuMuon() {
        return dsPhieuMuon;
    }
}