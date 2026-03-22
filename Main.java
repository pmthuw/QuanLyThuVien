package main;

import data.DocGhiFile;
import model.items.Items;
import model.items.Sach;
import model.items.TapChi;
import model.transaction.PhieuMuon;
import model.user.*;
import service.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    // Định nghĩa tên file dữ liệu (.txt)
    private static final String FILE_TAI_LIEU = "tailieu.txt";
    private static final String FILE_DOC_GIA = "docgia.txt";
    private static final String FILE_NHAN_VIEN = "nhanvien.txt";
    private static final String FILE_PHIEU_MUON = "phieumuon.txt";

    // Khởi tạo các lớp Service
    private static QuanLyTaiLieu qlTaiLieu = new QuanLyTaiLieu();
    private static QuanLyDocGia qlDocGia = new QuanLyDocGia();
    private static QuanLyNhanVien qlNhanVien = new QuanLyNhanVien();
    
    // qlMuonTra cần 2 kho dữ liệu kia để hoạt động (Dependency Injection)
    private static QuanLyMuonTra qlMuonTra = new QuanLyMuonTra(qlTaiLieu, qlDocGia);

    public static void main(String[] args) {
        // 1. Tải dữ liệu
        taiDuLieu();
        
        // 2. Chạy chương trình
        chayChuongTrinh();
        
        // 3. Lưu dữ liệu trước khi thoát
        luuDuLieu();
        
        System.out.println("\nĐã lưu toàn bộ dữ liệu (Text). Tạm biệt!");
    }

    private static void chayChuongTrinh() {
        boolean running = true;
        while (running) {
            Menu.hienThiMenuChinh();
            int luaChon = Menu.layLuaChon();

            switch (luaChon) {
                case 1: xuLyMenuTaiLieu(); break;
                case 2: xuLyMenuDocGia(); break;
                case 3: xuLyMenuNhanVien(); break;
                case 4: xuLyMenuMuonTra(); break;
                case 0: running = false; break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    // --- XỬ LÝ TÀI LIỆU ---
    private static void xuLyMenuTaiLieu() {
        boolean back = false;
        while (!back) {
            Menu.hienThiMenuQuanLyTaiLieu();
            int luaChon = Menu.layLuaChon();
            switch (luaChon) {
                case 1: // Thêm Sách
                    System.out.println("--- Thêm Sách Mới ---");
                    String tenSach = Menu.layChuoi("Nhập tên sách: ");
                    String nxbSach = Menu.layChuoi("Nhập tên NXB: ");
                    int soBanSach = Menu.laySoNguyen("Nhập số bản phát hành: ");
                    String tacGia = Menu.layChuoi("Nhập tên tác giả: ");
                    int soTrang = Menu.laySoNguyen("Nhập số trang: ");
                    
                    Sach sachMoi = new Sach(tenSach, nxbSach, soBanSach, tacGia, soTrang);
                    qlTaiLieu.themTaiLieu(sachMoi);
                    break;
                    
                case 2: // Thêm Tạp Chí
                    System.out.println("--- Thêm Tạp Chí Mới ---");
                    String tenTapChi = Menu.layChuoi("Nhập tên tạp chí: ");
                    String nxbTapChi = Menu.layChuoi("Nhập tên NXB: ");
                    int soBanTapChi = Menu.laySoNguyen("Nhập số bản phát hành: ");
                    int soPhatHanh = Menu.laySoNguyen("Nhập số phát hành (VD: 50): ");
                    int thangPhatHanh = Menu.laySoNguyen("Nhập tháng phát hành (VD: 10): ");
                    
                    TapChi tapChiMoi = new TapChi(tenTapChi, nxbTapChi, soBanTapChi, soPhatHanh, thangPhatHanh);
                    qlTaiLieu.themTaiLieu(tapChiMoi);
                    break;
                    
                case 3: // Hiển thị
                    qlTaiLieu.hienThiDanhSach();
                    break;
                    
                case 4: // Xóa
                    String idXoa = Menu.layChuoi("Nhập ID tài liệu cần xóa (VD: TL001): ");
                    qlTaiLieu.xoaTaiLieu(idXoa); 
                    break;
                    
                case 5: // Tìm kiếm
                    String idTim = Menu.layChuoi("Nhập ID tài liệu cần tìm (VD: TL001): ");
                    Items item = qlTaiLieu.timKiemTheoID(idTim);
                    if (item != null) {
                        System.out.println("Tìm thấy tài liệu:");
                        item.hienThi(); 
                    } else {
                        System.out.println("Không tìm thấy tài liệu.");
                    }
                    break;
                    
                case 6: // Thống kê chi tiết
                    qlTaiLieu.thongKeChiTiet(qlMuonTra.getDsPhieuMuon());
                    break;
                    
                case 0: back = true; break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    // --- XỬ LÝ ĐỘC GIẢ ---
    private static void xuLyMenuDocGia() {
        boolean back = false;
        while (!back) {
            Menu.hienThiMenuQuanLyDocGia();
            int luaChon = Menu.layLuaChon();
            switch (luaChon) {
                case 1: 
                    System.out.println("--- Thêm Độc Giả Mới ---");
                    String tenDG = Menu.layChuoi("Nhập họ tên: ");
                    String diaChiDG = Menu.layChuoi("Nhập địa chỉ: ");
                    String sdtDG = Menu.layChuoi("Nhập SĐT: ");
                    String emailDG = Menu.layChuoi("Nhập Email: ");
                    DocGia dgMoi = new DocGia(tenDG, diaChiDG, sdtDG, emailDG);
                    qlDocGia.themDocGia(dgMoi);
                    break;
                case 2: qlDocGia.hienThiDanhSach(); break;
                case 3: 
                    String idSua = Menu.layChuoi("Nhập mã Độc Giả cần sửa (VD: DG001): ");
                    String diaChiMoi = Menu.layChuoi("Nhập địa chỉ mới: ");
                    String sdtMoi = Menu.layChuoi("Nhập SĐT mới: ");
                    String emailMoi = Menu.layChuoi("Nhập Email mới: ");
                    qlDocGia.suaDocGia(idSua, diaChiMoi, sdtMoi, emailMoi);
                    break;
                case 4: 
                    String idXoa = Menu.layChuoi("Nhập mã Độc Giả cần xóa (VD: DG001): ");
                    qlDocGia.xoaDocGia(idXoa);
                    break;
                case 0: back = true; break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    // --- XỬ LÝ NHÂN VIÊN ---
    private static void xuLyMenuNhanVien() {
        boolean back = false;
        while (!back) {
            Menu.hienThiMenuQuanLyNhanVien();
            int luaChon = Menu.layLuaChon();
            switch (luaChon) {
                case 1: 
                    System.out.println("--- Thêm Nhân Viên Mới ---");
                    String tenNV = Menu.layChuoi("Nhập họ tên: ");
                    String diaChiNV = Menu.layChuoi("Nhập địa chỉ: ");
                    String sdtNV = Menu.layChuoi("Nhập SĐT: ");
                    String emailNV = Menu.layChuoi("Nhập Email: ");
                    String chucVu = Menu.layChuoi("Nhập chức vụ (Trưởng phòng/Nhân viên): ");
                    double luongCB = Menu.laySoThuc("Nhập lương cơ bản: ");
                    NhanVien nvMoi = new NhanVien(tenNV, diaChiNV, sdtNV, emailNV, chucVu, luongCB);
                    qlNhanVien.themNhanVien(nvMoi);
                    break;
                case 2: qlNhanVien.hienThiDanhSach(); break;
                case 3: 
                    String idSua = Menu.layChuoi("Nhập mã Nhân Viên cần sửa (VD: NV001): ");
                    String chucVuMoi = Menu.layChuoi("Nhập chức vụ mới: ");
                    double luongMoi = Menu.laySoThuc("Nhập lương cơ bản mới: ");
                    qlNhanVien.suaNhanVien(idSua, chucVuMoi, luongMoi);
                    break;
                case 4: 
                    String idXoa = Menu.layChuoi("Nhập mã Nhân Viên cần xóa (VD: NV001): ");
                    qlNhanVien.xoaNhanVien(idXoa);
                    break;
                case 0: back = true; break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    // --- XỬ LÝ MƯỢN TRẢ ---
    private static void xuLyMenuMuonTra() {
        boolean back = false;
        while (!back) {
            Menu.hienThiMenuQuanLyMuonTra();
            int luaChon = Menu.layLuaChon();
            switch (luaChon) {
                case 1: // Tạo Phiếu
                    qlMuonTra.taoPhieuMuon(new Scanner(System.in)); 
                    break;
                case 2: // Hiển thị
                    qlMuonTra.hienThiDanhSachPhieuMuon(); 
                    break;
                case 3: // Trả sách (Đã tích hợp)
                    qlMuonTra.xuLyTraSach(new Scanner(System.in));
                    break;
                case 0: back = true; break;
                default: System.out.println("Lựa chọn không hợp lệ.");
            }
        }
    }

    // --- HÀM LOAD/SAVE DATA ---
    private static void taiDuLieu() {
        System.out.println("Đang tải dữ liệu (Text)...");
        
        // 1. Tải Tài Liệu
        List<Items> dsTaiLieuDaDoc = DocGhiFile.docTaiLieu(FILE_TAI_LIEU);
        if (dsTaiLieuDaDoc != null && !dsTaiLieuDaDoc.isEmpty()) {
            qlTaiLieu.getDsTaiLieu().addAll(dsTaiLieuDaDoc);
        }

        // 2. Tải Độc Giả
        List<DocGia> dsDocGiaDaDoc = DocGhiFile.docDocGia(FILE_DOC_GIA);
        if (dsDocGiaDaDoc != null && !dsDocGiaDaDoc.isEmpty()) {
            qlDocGia.getDsDocGia().addAll(dsDocGiaDaDoc);
        }

        // 3. Tải Nhân Viên
        List<NhanVien> dsNhanVienDaDoc = DocGhiFile.docNhanVien(FILE_NHAN_VIEN);
        if (dsNhanVienDaDoc != null && !dsNhanVienDaDoc.isEmpty()) {
            qlNhanVien.getDsNhanVien().addAll(dsNhanVienDaDoc);
        }
        
        // 4. Tải Phiếu Mượn (Load SAU CÙNG)
        List<PhieuMuon> dsPhieuMuonDaDoc = DocGhiFile.docPhieuMuon(FILE_PHIEU_MUON, qlTaiLieu, qlDocGia);
        if (dsPhieuMuonDaDoc != null && !dsPhieuMuonDaDoc.isEmpty()) {
            qlMuonTra.getDsPhieuMuon().addAll(dsPhieuMuonDaDoc);
        }
        
        System.out.println("Tải dữ liệu (Text) hoàn tất.");
    }

    private static void luuDuLieu() {
        System.out.println("Đang lưu dữ liệu (Text)...");
        DocGhiFile.luuTaiLieu(qlTaiLieu.getDsTaiLieu(), FILE_TAI_LIEU);
        DocGhiFile.luuDocGia(qlDocGia.getDsDocGia(), FILE_DOC_GIA);
        DocGhiFile.luuNhanVien(qlNhanVien.getDsNhanVien(), FILE_NHAN_VIEN);
        DocGhiFile.luuPhieuMuon(qlMuonTra.getDsPhieuMuon(), FILE_PHIEU_MUON);
    }
}