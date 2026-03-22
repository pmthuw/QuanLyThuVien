package service;

import model.items.Items;
import model.items.Sach;
import model.items.TapChi;
import model.transaction.PhieuMuon;

import java.util.ArrayList;
import java.util.List;

public class QuanLyTaiLieu {
    private List<Items> dsTaiLieu;

    // Khoi tao
    public QuanLyTaiLieu() {
        this.dsTaiLieu = new ArrayList<>();
    }

    // --- CAC PHUONG THUC NGHIEP VU (CRUD) ---

    // Them tai lieu
    public void themTaiLieu(Items item) {
        this.dsTaiLieu.add(item);
        System.out.println("Đã thêm tài liệu '" + item.getTaiLieu() + "' thành công.");
    }

    // Hien Thi
    public void hienThiDanhSach() {
        if (dsTaiLieu.isEmpty()) {
            System.out.println("Thư viện chưa có tài liệu");
            return;
        }
        System.out.println("\n===== DANH SÁCH TÀI LIỆU TRONG THƯ VIỆN =====");
        for (Items item : dsTaiLieu) {
            // Đa hình: gọi hienThi() của Sach hay hienThi() của TapChi
            // tùy thuộc vào đối tượng thực tế
            item.hienThi();
        }
        System.out.println("===============================================");
    }

    // Xoa Tai Lieu khoi danh sach
    public boolean xoaTaiLieu(String taiLieu) {
        // tim doi tuong qua id
        Items itemCanXoa = null;
        for (Items item : dsTaiLieu) {
            if (item.getTaiLieu().equalsIgnoreCase(taiLieu)) {
                itemCanXoa = item;
                break;
            }
        }

        // neu tim thay thuc hien xoa 
        if (itemCanXoa != null) {
            dsTaiLieu.remove(itemCanXoa);
            System.out.println("Đã xóa tài liệu có ID: " + taiLieu);
            return true;
        } else {
            System.out.println("Không tìm thấy tài liệu có ID: " + taiLieu);
            return false;
        }
    }

    // Tim Kiem tai lieu
    public Items timKiemTheoID(String taiLieu) {
        for (Items item : dsTaiLieu) {
            if (item.getTaiLieu().equalsIgnoreCase(taiLieu)) {
                return item; // tra ve doi tuong tim thay
            }
        }
        return null; // khong tim thay
    }

    // --- CHUC NANG THONG KE CHI TIET (MOI THEM) ---
    
    public void thongKeChiTiet(List<PhieuMuon> dsPhieuMuon) {
        System.out.println("\n===== BÁO CÁO THỐNG KÊ CHI TIẾT =====");
        
        // 1. Tinh TONG KHO (Dua tren so ban phat hanh)
        int tongSachKho = 0;
        int tongTapChiKho = 0;

        for (Items item : dsTaiLieu) {
            if (item instanceof Sach) {
                tongSachKho += item.getSoBPH();
            } else if (item instanceof TapChi) {
                tongTapChiKho += item.getSoBPH();
            }
        }

        // 2. Tinh SO LUONG DANG MUON (Dua tren danh sach phieu muon)
        int sachDaMuon = 0;
        int tapChiDaMuon = 0;

        // Duyet qua tat ca phieu muon
        for (PhieuMuon phieu : dsPhieuMuon) {
            // Chi tinh cac phieu CHUA TRA (ngayTraThucTe == null)
            if (phieu.getNgayTraThucTe() == null) {
                for (Items item : phieu.getDsTaiLieuMuon()) {
                    if (item instanceof Sach) {
                        sachDaMuon++; 
                    } else if (item instanceof TapChi) {
                        tapChiDaMuon++;
                    }
                }
            }
        }

        // 3. Tinh SO LUONG CON LAI
        int sachConLai = tongSachKho - sachDaMuon;
        int tapChiConLai = tongTapChiKho - tapChiDaMuon;

        // 4. Hien thi ket qua
        System.out.println("--- SÁCH ---");
        System.out.println("+ Tổng số bản trong kho: " + tongSachKho);
        System.out.println("+ Số bản đang được mượn: " + sachDaMuon);
        System.out.println("+ Số bản còn lại trên kệ: " + sachConLai);
        
        System.out.println("\n--- TẠP CHÍ ---");
        System.out.println("+ Tổng số bản trong kho: " + tongTapChiKho);
        System.out.println("+ Số bản đang được mượn: " + tapChiDaMuon);
        System.out.println("+ Số bản còn lại trên kệ: " + tapChiConLai);
        
        System.out.println("=====================================");
    }

    // Getters
    public List<Items> getDsTaiLieu() {
        return dsTaiLieu;
    }
}