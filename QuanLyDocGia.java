package service;
import model.user.DocGia;
import java.util.ArrayList;
import java.util.List;
public class QuanLyDocGia {
private List<DocGia> dsDocGia;
//khoi tao
public QuanLyDocGia() {
	 this.dsDocGia = new ArrayList<>();
}
//phuong thuc nghiep vu
//them 1 doc gia
public void themDocGia(DocGia docGia) {
        this.dsDocGia.add(docGia);
        System.out.println("Đã thêm độc giả '" + docGia.getHoTen() + "' thành công.");
    }
//Hien thi
public void hienThiDanhSach() {
        if (dsDocGia.isEmpty()) {
            System.out.println("Chưa có độc giả nào trong hệ thống.");
            return;
        }
        
        System.out.println("\n===== DANH SÁCH ĐỘC GIẢ =====");
        for (DocGia docGia : dsDocGia) {
            // Gọi phương thức hienThiTTCN() da duoc Override
            // trong lớp DocGia
            docGia.hienThiTTCN();
        }
        System.out.println("==============================");
    }
//xoa 1 doc gia khoi danh sach
public boolean xoaDocGia(String maDG) {
        DocGia docGiaCanXoa = null;
        for (DocGia docGia : dsDocGia) {
            if (docGia.getMaDG().equalsIgnoreCase(maDG)) {
                docGiaCanXoa = docGia;
                break;
            }
        }

        if (docGiaCanXoa != null) {
            dsDocGia.remove(docGiaCanXoa);
            System.out.println("Đã xóa độc giả có mã: " + maDG);
            return true;
        } else {
            System.out.println("Không tìm thấy độc giả có mã: " + maDG);
            return false;
        }
    }
//tim kiem
public DocGia timKiemTheoID(String maDocGia) {
    for (DocGia docGia : dsDocGia) {
        if (docGia.getMaDG().equalsIgnoreCase(maDocGia)) {
            return docGia;
        }
    }
    return null; // ko tim thay tra ve null
}
//sua thong tin
public boolean suaDocGia(String maDocGia, String diaChiMoi, String sdtMoi, String emailMoi) {
    DocGia docGiaCanSua = timKiemTheoID(maDocGia);
    
    if (docGiaCanSua != null) {
        // update thong tin
        docGiaCanSua.setDiaChi(diaChiMoi);
        docGiaCanSua.setSdt(sdtMoi);
        docGiaCanSua.setEmail(emailMoi);
        System.out.println("Đã cập nhật thông tin cho độc giả: " + maDocGia);
        return true;
    } else {
        System.out.println("Không tìm thấy độc giả để cập nhật.");
        return false;
    }
}
public List<DocGia> getDsDocGia() {
    return dsDocGia;
}
}