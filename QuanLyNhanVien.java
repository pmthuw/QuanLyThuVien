package service;


import model.user.NhanVien;

import java.util.ArrayList;
import java.util.List;


public class QuanLyNhanVien {

    // thuoc tinh
    private List<NhanVien> dsNhanVien;

    // khoi tao
    public QuanLyNhanVien() {
        // khoi tao danh sach trong
        this.dsNhanVien = new ArrayList<>();
    }

    // cac phuong thuc nghiep vu

    //them nhan vien
    public void themNhanVien(NhanVien nhanVien) {
        this.dsNhanVien.add(nhanVien);
        System.out.println("Đã thêm nhân viên '" + nhanVien.getHoTen() + "' thành công.");
    }
    
    //hien thi
    public void hienThiDanhSach() {
        if (dsNhanVien.isEmpty()) {
            System.out.println("Chưa có nhân viên nào trong hệ thống.");
            return;
        }
        
        System.out.println("\n===== DANH SÁCH NHÂN VIÊN =====");
        for (NhanVien nhanVien : dsNhanVien) {
            // Gọi phương thức hienThiThongTinCaNhan() đã được @Override
            // trong lớp NhanVien.
            nhanVien.hienThiTTCN();
        }
        System.out.println("================================");
    }

    //xoa nhan vien khoi danh sach
    public boolean xoaNhanVien(String maNV) {
        NhanVien nvCanXoa = null;
        for (NhanVien nhanVien : dsNhanVien) {
            if (nhanVien.getMaNV().equalsIgnoreCase(maNV)) {
                nvCanXoa = nhanVien;
                break;
            }
        }

        if (nvCanXoa != null) {
            dsNhanVien.remove(nvCanXoa);
            System.out.println("Đã xóa nhân viên có mã: " + maNV);
            return true;
        } else {
            System.out.println("Không tìm thấy nhân viên có mã: " + maNV);
            return false;
        }
    }

    //tim kiem
    public NhanVien timKiemTheoID(String maNV) {
        for (NhanVien nhanVien : dsNhanVien) {
            if (nhanVien.getMaNV().equalsIgnoreCase(maNV)) {
                return nhanVien;
            }
        }
        return null; // khong tim thay
    }
    
    //sua thong tin
    public boolean suaNhanVien(String maNV, String chucVuMoi, double luongMoi) {
        NhanVien nvCanSua = timKiemTheoID(maNV);
        
        if (nvCanSua != null) {
            // Cập nhật thông tin
            nvCanSua.setChucVu(chucVuMoi);
            nvCanSua.setLuongCoBan(luongMoi);
            System.out.println("Đã cập nhật thông tin cho nhân viên: " + maNV);
            return true;
        } else {
            System.out.println("Không tìm thấy nhân viên để cập nhật.");
            return false;
        }
    }

    // get set
    
   //lay danh sach cho lop khac su dung
    public List<NhanVien> getDsNhanVien() {
        return dsNhanVien;
    }
}