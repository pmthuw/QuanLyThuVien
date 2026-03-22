package main;

import java.util.Scanner;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    public static void hienThiMenuChinh() {
        System.out.println("\n===== CHƯƠNG TRÌNH QUẢN LÝ THƯ VIỆN =====");
        System.out.println("1. Quản lý Tài Liệu (Sách/Tạp chí)");
        System.out.println("2. Quản lý Độc Giả");
        System.out.println("3. Quản lý Nhân Viên");
        System.out.println("4. Quản lý Mượn/Trả sách");
        System.out.println("----------------------------------------");
        System.out.println("0. Lưu và Thoát chương trình");
        System.out.println("=========================================");
    }

    public static void hienThiMenuQuanLyTaiLieu() {
        System.out.println("\n--- Menu Quản lý Tài Liệu ---");
        System.out.println("1. Thêm Sách mới");
        System.out.println("2. Thêm Tạp Chí mới");
        System.out.println("3. Hiển thị tất cả tài liệu");
        System.out.println("4. Xóa tài liệu theo ID");
        System.out.println("5. Tìm tài liệu theo ID");
        System.out.println("6. Báo cáo Thống kê chi tiết");
        System.out.println("0. Quay lại menu chính");
        System.out.println("-----------------------------");
    }

    public static void hienThiMenuQuanLyDocGia() {
        System.out.println("\n--- Menu Quản lý Độc Giả ---");
        System.out.println("1. Thêm Độc Giả mới");
        System.out.println("2. Hiển thị danh sách Độc Giả");
        System.out.println("3. Sửa thông tin Độc Giả");
        System.out.println("4. Xóa Độc Giả");
        System.out.println("0. Quay lại menu chính");
        System.out.println("----------------------------");
    }
    
    public static void hienThiMenuQuanLyNhanVien() {
        System.out.println("\n--- Menu Quản lý Nhân Viên ---");
        System.out.println("1. Thêm Nhân Viên mới");
        System.out.println("2. Hiển thị danh sách Nhân Viên");
        System.out.println("3. Sửa thông tin Nhân Viên");
        System.out.println("4. Xóa Nhân Viên");
        System.out.println("0. Quay lại menu chính");
        System.out.println("------------------------------");
    }
    
    public static void hienThiMenuQuanLyMuonTra() {
        System.out.println("\n--- Menu Quản lý Mượn Trả ---");
        System.out.println("1. Tạo Phiếu Mượn mới");
        System.out.println("2. Hiển thị tất cả Phiếu Mượn");
        System.out.println("3. Trả sách & Tính phí phạt"); 
        System.out.println("0. Quay lại menu chính");
        System.out.println("-----------------------------");
    }

  

    public static int layLuaChon() {
        System.out.print("Nhập lựa chọn của bạn: ");
        int luaChon = -1;
        try {
            
            String input = scanner.nextLine();
            if (!input.trim().isEmpty()) {
                luaChon = Integer.parseInt(input.trim());
            }
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Vui lòng nhập một số.");
        }
        return luaChon;
    }

    public static String layChuoi(String thongBao) {
        System.out.print(thongBao);
        return scanner.nextLine();
    }
    
    public static int laySoNguyen(String thongBao) {
        while (true) {
            System.out.print(thongBao);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ.");
            }
        }
    }
    
    public static double laySoThuc(String thongBao) {
        while (true) {
            System.out.print(thongBao);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Lỗi: Vui lòng nhập một số thực hợp lệ.");
            }
        }
    }
}