package model.user;

public class Nguoi {
protected String hoTen;
protected String diaChi;
protected String sdt;
protected String email;


//nap chong
public Nguoi(String hoTen, String diaChi, String sdt, String email) {
	this.hoTen=hoTen;
	this.diaChi=diaChi;
	this.sdt=sdt;
	this.email=email;
}
//get set
public String getHoTen() {
	return hoTen;
}
;public void setHoTen(String hoTen) {
	this.hoTen=hoTen;
}
public String getDiaChi() {
	return diaChi;
}
public void setDiaChi(String diaChi) {
	this.diaChi=diaChi;
}
public String getSdt() {
	return sdt;
}
public void setSdt(String sdt) {
	this.sdt=sdt;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email=email;
}
//thong tin ca nhan
public void hienThiTTCN() {
    System.out.println("Họ tên: " + hoTen);
    System.out.println("Địa chỉ: " + diaChi);
    System.out.println("SĐT: " + sdt);
    System.out.println("Email: " + email);
}


}