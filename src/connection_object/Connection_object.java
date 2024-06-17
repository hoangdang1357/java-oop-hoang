/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package connection_object;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author ADMIN
 */
public class Connection_object {


    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        boolean lap = true;

        while (lap) {

            System.out.println("""
                                                   
                    He thong quan ly diem hoc sinh THCS Huu Hoa
                    1. Phan he giao vien
                    2. Phan he hoc sinh
                    3. Thoat
                    """);
            System.out.println("Moi chon:");
            int chon = input.nextInt();
            input.nextLine();
            switch (chon) {
                case 1:
                    System.out.println("PHAN HE GIAO VIEN");
                    String userName = "admin";
                    String password = "123456";
                    boolean should_continue = true;
                    XuLyDiem xuLyDiem;
                    boolean should_continue_hoc_sinh;
                    while (should_continue) {
                        System.out.println("Ten dang nhap: ");
                        String userNameInput = input.next();
                        if (userNameInput.equals("exit")) {

                            break;
                        }
                        System.out.println("Mat khau: ");
                        String passwordInput = input.next();
                        if (userNameInput.equals(userName)) {
                            if (passwordInput.equals(password)) {
                                should_continue = false;
                                xuLyDiem = new XuLyDiem();
                                should_continue_hoc_sinh = true;
                                while (should_continue_hoc_sinh == true) {
                                    System.out.print("""
                                            1. Danh sach diem hoc sinh
                                            2. Them diem hoc sinh
                                            3. Sua diem hoc sinh
                                            4. Xoa diem hoc sinh
                                            5. Tim kiem hoc sinh theo ma hoc sinh
                                            6. Thoat
                                            Moi chon:
                                            """);

                                    int chonHocSinh = input.nextInt();
                                    input.nextLine();
                                    Student st;
                                    switch (chonHocSinh) {
                                        case 1: // hien thi
                                            xuLyDiem.List();
                                            break;
                                        case 2: // them
                                            System.out.print("Nhap ma hoc sinh:");
                                            String maHS = input.nextLine();
                                            System.out.print("Nhap ten hoc sinh:");
                                            String hoTen = input.nextLine();
                                            System.out.println("Nhap gioi tinh(true/false): ");
                                            boolean gioiTinh = input.nextBoolean();
                                            System.out.println("Nhap diem toan: ");
                                            int diemToan = input.nextInt();
                                            System.out.println("Nhap diem van: ");
                                            int diemVan = input.nextInt();
                                            System.out.println("Nhap diem anh: ");
                                            int diemAnh = input.nextInt();

                                            Student obj = new Student(maHS, hoTen, gioiTinh, diemToan, diemVan, diemAnh);
                                            xuLyDiem.Add(obj);
                                            break;

                                        case 3: // sua
                                            System.out.print("Nhap ma cua hoc sinh muon sua: ");
                                            maHS = input.next();
                                            xuLyDiem.SearchMaHS(maHS);
                                            st = new Student(maHS);
                                            System.out.print("Nhap ten hoc sinh moi:");
                                            String newHoTen = input.next();
                                            System.out.print("Nhap gioi tinh moi(true/false): ");
                                            boolean newGioiTinh = input.nextBoolean();
                                            System.out.print("Nhap diem toan moi: ");
                                            int newDiemToan = input.nextInt();
                                            System.out.print("Nhap diem van moi: ");
                                            int newDiemVan = input.nextInt();
                                            System.out.print("Nhap diem anh moi: ");
                                            int newDiemAnh = input.nextInt();

                                            st.setHoTen(newHoTen);
                                            st.setGioiTinh(newGioiTinh);
                                            st.setDiemToan(newDiemToan);
                                            st.setDiemVan(newDiemVan);
                                            st.setDiemAnh(newDiemAnh);
                                            xuLyDiem.editData(st, maHS);
                                            break;

                                        case 4: // xoa
                                            System.out.print("Nhap ma cua hoc sinh muon xoa: ");
                                            maHS = input.next();
                                            xuLyDiem.Del(maHS);
                                            break;

                                        case 5: // tim kiem
                                            System.out.print("Nhap ma cua hoc sinh muon tim: ");
                                            maHS = input.next();
                                            xuLyDiem.SearchMaHS(maHS);
                                            break;
                                        case 6:
                                            should_continue_hoc_sinh = false;
                                            break;
                                        default:
                                            System.out.println("Chon dung chuc nang");
                                    }
                                }
                                break;
                            }

                        }
                        System.out.println("Ten dang nhap hoac mat khau khong dung, vui long thu lai, nhap ten dang nhap la exit de thoat");
                    }
                    break;
                case 2: // phan he hoc sinh
                    System.out.println("PHAN HE HOC SINH");
                    xuLyDiem = new XuLyDiem();
                    should_continue_hoc_sinh = true;
                    while (should_continue_hoc_sinh) {
                        System.out.print("""
                                1. Xem diem
                                2. Thoat
                                Moi chon:
                                """);

                        int chonHocSinh = input.nextInt();
                        input.nextLine();
                        Student st;
                        switch (chonHocSinh) {
                            case 1:
                                System.out.println("Nhap ma hoc sinh cua ban");
                                String maHS = input.nextLine();
                                xuLyDiem.SearchMaHS(maHS);
                                break;

                            case 2:
                                should_continue_hoc_sinh = false;
                                break;
                            default:
                                System.out.println("Chon dung chuc nang");
                        }
                    }
                    break;
                case 3:
                    lap = false;
                    break;
                default:
                    System.out.println("Hay chon dung chuc nang");
            }
        }
    }

}
