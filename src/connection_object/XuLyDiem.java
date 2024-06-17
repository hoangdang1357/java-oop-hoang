package connection_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class XuLyDiem extends connectDB {

    Connection con;

    public XuLyDiem() throws SQLException {
        super();
        con = super.getConnection();
    }

    public void List() throws SQLException {
        ResultSet resultSet = super.getTable("Students");
        String newLine = "\n";

        while (resultSet.next()) {
            System.out.format("%10s%10s%20s%20s%20s%20s%20s", "ma HS: " + resultSet.getString("MaHS"),
                    "ho ten :" + resultSet.getString("HoTen"),
                    "gioi tinh: " + resultSet.getString("GioiTinh"),
                    "diem toan:" + resultSet.getString("DiemToan"),
                    "diem van:" + resultSet.getString("DiemVan"),
                    "diem anh:" + resultSet.getString("DiemAnh"),
                    "\n"
            );
        }
        if (resultSet != null) resultSet.close();
    }

    public void Add(Student obj) throws SQLException {
        String query1 = "select * from Students where MaHS=?";
        PreparedStatement pstmt1 = null;
        ResultSet resultSet1 = null;
        pstmt1 = con.prepareStatement(query1);
        pstmt1.setString(1, obj.getMaHS());

        resultSet1 = pstmt1.executeQuery();
        // Xu ly ket qua


        if (resultSet1.next()) {
            System.out.println("khong them duoc vi trung ma hoc sinh");
            return;

        }
        //Them
        String query = "insert into Students values(?,?,?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, obj.getMaHS());
            pstmt.setString(2, obj.getHoTen());
            pstmt.setBoolean(3, obj.isGioiTinh());
            pstmt.setInt(4, obj.getDiemToan());
            pstmt.setInt(5, obj.getDiemVan());
            pstmt.setInt(6, obj.getDiemAnh());

            pstmt.executeUpdate();
            System.out.println("Them thanh cong!");
        } catch (SQLException e) {
            System.out.println("Loi" + e.getMessage());
        }

        if (pstmt != null) pstmt.close();
    }

    public void editData(Student stObject, String studentIdToEdit) throws SQLException {
        Scanner input = new Scanner(System.in);
        boolean isDuplicateMaHS = true;

        while (isDuplicateMaHS) {
            // Check if MaHS exists (excluding the row being edited)
            String checkMaHSQuery = "SELECT * FROM Students WHERE MaHS = ? AND HoTen != ? AND MaHS != ?";
            PreparedStatement checkMaHStatement = con.prepareStatement(checkMaHSQuery);
            checkMaHStatement.setString(1, stObject.getMaHS());
            checkMaHStatement.setString(2, stObject.getHoTen());
            checkMaHStatement.setString(3, studentIdToEdit);


            ResultSet checkMaHSResult = checkMaHStatement.executeQuery();

            if (checkMaHSResult.next()) {
                System.out.println("Ma hoc sinh ban muon sua da ton tai, vui long chon ma hoc sinh moi: ");
                stObject.setMaHS(input.nextLine());
            } else {
                isDuplicateMaHS = false;
            }

            checkMaHStatement.close();
            checkMaHSResult.close();
        }

        // Perform the actual edit using the updated MaHS
        String editQuery = "UPDATE Students SET HoTen = ?, GioiTinh = ?, DiemToan = ?, DiemVan = ?, DiemAnh = ? WHERE MaHS = ?";
        PreparedStatement editStatement = connection.prepareStatement(editQuery);
        editStatement.setString(1, stObject.getHoTen());
        editStatement.setBoolean(2, stObject.isGioiTinh());
        editStatement.setInt(3, stObject.getDiemToan());
        editStatement.setInt(4, stObject.getDiemVan());
        editStatement.setInt(5, stObject.getDiemAnh());
        editStatement.setString(6, stObject.getMaHS()); // Use the updated MaHS after checking for duplicates
        editStatement.executeUpdate();
        System.out.println("Cap nhat thanh cong!");

        editStatement.close();
    }


    public void Del(String MaHS) throws SQLException {
        String deleteQuery = "DELETE FROM Students WHERE MaHS = ?";
        PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
        deleteStatement.setString(1, MaHS);


        int deletedRows = deleteStatement.executeUpdate();

        if (deletedRows == 1) {
            System.out.println("Xoa thanh cong!");
        } else {
            System.out.println("Loi! Khong the xoa ban ghi, co the ma hoc sinh khong ton tai");
        }

        deleteStatement.close();
    }

    public void SearchMaHS(String MaHS) throws SQLException {
        String query = "SELECT * FROM Students WHERE MaHS = ?";
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, MaHS);
            resultSet = pstmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    System.out.format("%10s%10s%20s%20s%20s%20s%20s", "ma HS: " + resultSet.getString("MaHS"),
                            "ho ten :" + resultSet.getString("HoTen"),
                            "gioi tinh: " + resultSet.getString("GioiTinh"),
                            "diem toan:" + resultSet.getString("DiemToan"),
                            "diem van:" + resultSet.getString("DiemVan"),
                            "diem anh:" + resultSet.getString("DiemAnh"),
                            "\n"
                    );

                }
            } else {
                System.out.println("Khong tim thay ma hoc sinh" + MaHS);
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        } finally {
            if (resultSet != null) resultSet.close();
            if (pstmt != null) pstmt.close();
        }
    }

    public void SearchTenLoai(Student studentObject) throws SQLException {
        String query = "SELECT * FROM Students WHERE MaHS = ?";
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setString(1, studentObject.getMaHS());

            resultSet = pstmt.executeQuery();

            // Check if any results were found
            if (resultSet.isBeforeFirst()) {
                // Author found, display book details
                while (resultSet.next()) {
                    System.out.println("ma hoc sinh" + resultSet.getString("MaHS"));
                    System.out.println("ten hoc sinh: " + resultSet.getString("HoTen"));
                    System.out.println("gioi tinh hoc sinh: " + resultSet.getString("GioiTinh"));
                    System.out.println("diem toan: " + resultSet.getInt("DiemToan"));
                    System.out.println("diem van: " + resultSet.getInt("DiemVan"));
                    System.out.println("diem anh: " + resultSet.getInt("DiemAnh"));
                    System.out.println("----------------------------");
                }
            } else {
                // Author not found, display message
                System.out.println("Khong tim thay ten hoc sinh" + studentObject.getHoTen());
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        } finally {
            if (resultSet != null) resultSet.close();
            if (pstmt != null) pstmt.close();
        }
    }
}
