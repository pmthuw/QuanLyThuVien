package data;

import model.items.Items;
import model.items.Sach;
import model.items.TapChi;
import model.transaction.PhieuMuon;
import model.user.DocGia;
import model.user.NhanVien;
import service.QuanLyDocGia;
import service.QuanLyTaiLieu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocGhiFile {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    // --- 1. XỬ LÝ TÀI LIỆU ---
    public static void luuTaiLieu(List<Items> dsTaiLieu, String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            for (Items item : dsTaiLieu) {
                String line = "";
                if (item instanceof Sach) {
                    Sach sach = (Sach) item;
                 
                    line = "SACH," + sach.getTaiLieu() + "," + sach.getTenNXB() + ","
                            + sach.getSoBPH() + "," + sach.getTenSach() + "," 
                            + sach.getTenTG() + "," + sach.getSoTrang();
                } else if (item instanceof TapChi) {
                    TapChi tapChi = (TapChi) item;
                 
                    line = "TAPCHI," + tapChi.getTaiLieu() + "," + tapChi.getTenNXB() + ","
                            + tapChi.getSoBPH() + "," + tapChi.getTenTapChi() + "," 
                            + tapChi.getSoPH() + "," + tapChi.getThangPH();
                }
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Đã lưu (Text) thành công vào file: " + tenFile);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file " + tenFile);
        }
    }

    public static List<Items> docTaiLieu(String tenFile) {
        List<Items> dsTaiLieu = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); 
                if (parts.length == 0) continue;

                String loai = parts[0];
                String idCu = parts[1];
                String nxb = parts[2];
                int soBan = Integer.parseInt(parts[3]);

                if ("SACH".equals(loai) && parts.length >= 7) {
                    String tenSach = parts[4];
                    String tacGia = parts[5];
                    int soTrang = Integer.parseInt(parts[6]);
                    
                    Sach sach = new Sach(tenSach, nxb, soBan, tacGia, soTrang);
                    sach.setTaiLieu(idCu); 
                    dsTaiLieu.add(sach);
                    
                } else if ("TAPCHI".equals(loai) && parts.length >= 7) {
                    String tenTapChi = parts[4];
                    int soPH = Integer.parseInt(parts[5]);
                    int thangPH = Integer.parseInt(parts[6]);
                    
                    TapChi tapChi = new TapChi(tenTapChi, nxb, soBan, soPH, thangPH);
                    tapChi.setTaiLieu(idCu);
                    dsTaiLieu.add(tapChi);
                }
            }
            System.out.println("Đã đọc (Text) thành công từ file: " + tenFile);
        } catch (Exception e) {
            System.err.println("Lỗi khi đọc file " + tenFile);
        }
        return dsTaiLieu;
    }

    // --- 2. XỬ LÝ ĐỘC GIẢ ---
    public static void luuDocGia(List<DocGia> dsDocGia, String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            for (DocGia docGia : dsDocGia) {
                String line = docGia.getMaDG() + "," + docGia.getHoTen() + "," + docGia.getDiaChi() + ","
                        + docGia.getSdt() + "," + docGia.getEmail();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Đã lưu (Text) thành công vào file: " + tenFile);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file " + tenFile);
        }
    }

    public static List<DocGia> docDocGia(String tenFile) {
        List<DocGia> dsDocGia = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    String idCu = parts[0];
                    String hoTen = parts[1];
                    String diaChi = parts[2];
                    String sdt = parts[3];
                    String email = parts[4];
                    
                    DocGia dg = new DocGia(hoTen, diaChi, sdt, email);
                    dg.setMaDG(idCu);
                    dsDocGia.add(dg);
                }
            }
            System.out.println("Đã đọc (Text) thành công từ file: " + tenFile);
        } catch (Exception e) {
            System.err.println("Lỗi khi đọc file " + tenFile);
        }
        return dsDocGia;
    }

    // --- 3. XỬ LÝ NHÂN VIÊN ---
    public static void luuNhanVien(List<NhanVien> dsNhanVien, String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            for (NhanVien nv : dsNhanVien) {
                String line = nv.getMaNV() + "," + nv.getHoTen() + "," + nv.getDiaChi() + ","
                        + nv.getSdt() + "," + nv.getEmail() + "," + nv.getChucVu() + ","
                        + nv.getLuongCoBan();
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Đã lưu (Text) thành công vào file: " + tenFile);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file " + tenFile);
        }
    }

    public static List<NhanVien> docNhanVien(String tenFile) {
        List<NhanVien> dsNhanVien = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 7) {
                    String idCu = parts[0];
                    String hoTen = parts[1];
                    String diaChi = parts[2];
                    String sdt = parts[3];
                    String email = parts[4];
                    String chucVu = parts[5];
                    double luongCB = Double.parseDouble(parts[6]);
                    
                    NhanVien nv = new NhanVien(hoTen, diaChi, sdt, email, chucVu, luongCB);
                    nv.setMaNV(idCu);
                    dsNhanVien.add(nv);
                }
            }
            System.out.println("Đã đọc (Text) thành công từ file: " + tenFile);
        } catch (Exception e) {
            System.err.println("Lỗi khi đọc file " + tenFile);
        }
        return dsNhanVien;
    }

    // --- 4. XỬ LÝ PHIẾU MƯỢN (ĐÂY LÀ PHẦN BẠN ĐANG THIẾU) ---
    public static void luuPhieuMuon(List<PhieuMuon> dsPhieuMuon, String tenFile) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tenFile))) {
            for (PhieuMuon phieu : dsPhieuMuon) {
                String strNgayTraThucTe = "null";
                if (phieu.getNgayTraThucTe() != null) {
                    strNgayTraThucTe = DATE_FORMAT.format(phieu.getNgayTraThucTe());
                }

                String line = phieu.getMaPhieu() + "," + phieu.getDocGia().getMaDG() + ","
                        + DATE_FORMAT.format(phieu.getNgayMuon()) + "," 
                        + DATE_FORMAT.format(phieu.getNgayTraDuKien()) + ","
                        + strNgayTraThucTe;
                
                for (Items item : phieu.getDsTaiLieuMuon()) {
                    line += "," + item.getTaiLieu();
                }
                bw.write(line);
                bw.newLine();
            }
            System.out.println("Đã lưu (Text) thành công vào file: " + tenFile);
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu file " + tenFile);
        }
    }

    public static List<PhieuMuon> docPhieuMuon(String tenFile, QuanLyTaiLieu qlTaiLieu, QuanLyDocGia qlDocGia) {
        List<PhieuMuon> dsPhieuMuon = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split(",");
                if (parts.length < 5) continue;

                String idPhieuCu = parts[0].trim();
                String idDocGia = parts[1].trim();
                
                DocGia docGia = qlDocGia.timKiemTheoID(idDocGia);
                
                if (docGia == null) {
                    System.err.println(">> Cảnh báo: Bỏ qua phiếu " + idPhieuCu + " vì không tìm thấy Độc giả ID: " + idDocGia);
                    continue; 
                }
                
                Date ngayMuon = DATE_FORMAT.parse(parts[2].trim());
                Date ngayTraDuKien = DATE_FORMAT.parse(parts[3].trim());
                String strNgayTraThucTe = parts[4].trim();

                PhieuMuon phieu = new PhieuMuon(docGia, ngayMuon, ngayTraDuKien);
                phieu.setMaPhieu(idPhieuCu); 
                
                if (!"null".equalsIgnoreCase(strNgayTraThucTe)) {
                    phieu.setNgayTraThucTe(DATE_FORMAT.parse(strNgayTraThucTe));
                }
                
                for (int i = 5; i < parts.length; i++) {
                    String idTaiLieu = parts[i].trim();
                    Items item = qlTaiLieu.timKiemTheoID(idTaiLieu);
                    if (item != null) {
                        phieu.themTaiLieu(item);
                    }
                }
                dsPhieuMuon.add(phieu);
            }
            System.out.println("Đã đọc (Text) thành công từ file: " + tenFile);
        } catch (Exception e) {
            System.err.println("Lỗi khi đọc file " + tenFile + ": " + e.getMessage());
        }
        return dsPhieuMuon;
    }
}