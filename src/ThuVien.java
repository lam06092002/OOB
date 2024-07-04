

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ThuVien {
    private static ThuVien instance;
    private Connection connection;

    private ThuVien() {
        // Khởi tạo kết nối đến cơ sở dữ liệu MySQL
        try {
            String url = "jdbc:mysql://localhost:3306/library_db";
            String user = "root"; 
            String password = "06092002"; 
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to MySQL database.");
            
        } catch (SQLException e) {
            System.out.println("Error connecting to MySQL database: " + e.getMessage());
        }
    }

    public static synchronized ThuVien getInstance() {
        if (instance == null) {
            instance = new ThuVien();
        }
        return instance;
    }


    public void themsach(Book book) {
        String sql = "INSERT INTO books(Masach, TenSach, Tacgia, Theloai, Soluong) VALUES(?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getmasach());
            statement.setString(2, book.getten());
            statement.setString(3, book.gettacgia());
            statement.setString(4, book.gettheloai());
            statement.setInt(5, book.getsoluong());
            statement.executeUpdate();
            System.out.println("Them thanh cong.");
        } catch (SQLException e) {
            System.out.println("\nLoi: " + e.getMessage());
        }
    }



    public void xoasach(String Masach) {
        String sql = "DELETE FROM books WHERE Masach = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Masach);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xoa thanh cong.");
            } else {
                System.out.println("Sach ko co trong thu vien.");
            }
        } catch (SQLException e) {
            System.out.println("\nLoi: " + e.getMessage());
        }
    }

    public void capnhatsach(String masach, String tensach, String tacgia, String theloai, int soluong) {

        StringBuilder sql = new StringBuilder("UPDATE books SET ");
        List<Object> parameters = new ArrayList<>();


        if (tensach != null) {
            sql.append("Tensach = ?, ");
            parameters.add(tensach);

        }
        if (tacgia != null) {
            sql.append("Tacgia = ?, ");
            parameters.add(tacgia);
        }
        if (theloai!= null) {
            sql.append("Theloai = ?, ");
            parameters.add(theloai);
        }
        if (soluong != -1) {
            sql.append("Soluong = ?, ");
            parameters.add(soluong);
        }

       
        sql.delete(sql.length() - 2, sql.length());
        sql.append(" WHERE Masach = ?");
        parameters.add(masach);

        try (PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cap nhat thanh cong.");
            } else {
                System.out.println("Ko co trong thu vien.");
            }
        } catch (SQLException e) {
            System.out.println("\nLoi: " + e.getMessage());
        }
    }

    public List<Book> timsach(String keyword) {
        List<Book> results = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE Tensach LIKE ? OR Tacgia LIKE ? OR Theloai LIKE ? OR Masach LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String likeKeyword = "%" + keyword + "%";
            statement.setString(1, likeKeyword);
            statement.setString(2, likeKeyword);
            statement.setString(3, likeKeyword);
            statement.setString(4, likeKeyword);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {

                String masach = resultSet.getString("Masach");
                String tensach = resultSet.getString("Tensach");
                String tacgia = resultSet.getString("Tacgia");
                String theloai = resultSet.getString("Theloai");
                int soluong = resultSet.getInt("Soluong");
                results.add(new Book(masach, tensach, tacgia, theloai, soluong));

            }
        } catch (SQLException e) {
            System.out.println("\nLoi: " + e.getMessage());
        }
        return results;
    }

// Member
    public void themthanhvien(Member member) {
        String sql = "INSERT INTO members(Mathanhvien, Tenthanhvien, Diachi) VALUES(?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, member.getMathanhvien());
            statement.setString(2, member.getTen());
            statement.setString(3, member.getDiachi());
            statement.executeUpdate();
            System.out.println("Them thanh cong.");
        } catch (SQLException e) {

            System.out.println("\nLoi : " + e.getMessage());
        }

    }


    public void xoathanhvien(String mathanhvien) {
        String sql = "DELETE FROM members WHERE Mathanhvien = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, mathanhvien);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("xoa thanh cong.");
            } else {
                System.out.println("thanh vien ko co trong thu vien.");
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
    


    public void capnhatthanhvien(String mathanhvien, String tenthanhvien, String diachi) {
        String sql = "UPDATE members SET Tenthanhvien = ?, Diachi = ? WHERE Mathanhvien = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tenthanhvien);
            statement.setString(2, diachi);
            statement.setString(3, mathanhvien);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cap nhat thanh cong.");
            } else {
                System.out.println("Thanh vien ko co trong thu vien.");
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }

    public List<Member> timthanhvien(String keyword) {
        List<Member> results = new ArrayList<>();
        String sql = "SELECT * FROM members WHERE Tenthanhvien LIKE ? OR Mathanhvien LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String likeKeyword = "%" + keyword + "%";
            statement.setString(1, likeKeyword);
            statement.setString(2, likeKeyword);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String memberId = resultSet.getString("Mathanhvien");
                String name = resultSet.getString("Tenthanhvien");
                String address = resultSet.getString("Diachi");
                results.add(new Member(memberId, name, address));
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return results;
    }

    public void themthuthu(Librarian Librarian) {
        String sql = "INSERT INTO librarians(Mathuthu, Tenthuthu, Calamviec) VALUES(?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, Librarian.getMathuthu());
            statement.setString(2, Librarian.getTenthuthu());
            statement.setString(3, Librarian.getCalamviec());
            statement.executeUpdate();
            System.out.println("Them thanh cong.");
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }

    public void xoathuthu(String mathuthu) {
        String sql = "DELETE FROM librarians WHERE Mathuthu = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, mathuthu);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Xoa thanh cong.");
            } else {
                System.out.println("Ko co trong thu vien.");
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }

    public void capnhatthuthu(String mathuthu, String tenthuthu, String calamviec) {
        String sql = "UPDATE librarians SET Tenthuthu = ?, Calamviec = ? WHERE Mathuthu = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, tenthuthu);
            statement.setString(2, calamviec);
            statement.setString(3, mathuthu);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Cap nhat thanh cong.");
            } else {
                System.out.println("ko co trong thu vien.");
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
    }
    public List<Librarian> timthuthu(String keyword) {
        List<Librarian> results = new ArrayList<>();
        String sql = "SELECT * FROM librarians WHERE Tenthuthu LIKE ? OR Mathuthu LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String likeKeyword = "%" + keyword + "%";
            statement.setString(1, likeKeyword);
            statement.setString(2, likeKeyword);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String maso = resultSet.getString("mathuthu");
                String ten = resultSet.getString("tenthuthu");
                String calam = resultSet.getString("calamviec");
                results.add(new Librarian(maso, ten,calam));
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return results;
    }
    
    public void muonsach(String mathanhvien, String masach) {
    String checkBookAvailability = "SELECT Soluong FROM books WHERE Masach = ?";
    String checkMemberExists = "SELECT * FROM members WHERE Mathanhvien = ?";
    String insertBorrowing = "INSERT INTO borrowings (Mathanhvien, Masach, Ngaymuon, Ngaytra,Duedate) VALUES (?, ?, ?, ?,?)";
    String updateBookCopies = "UPDATE books SET Soluong = Soluong - 1 WHERE Masach = ?";

    try (PreparedStatement checkBookStmt = connection.prepareStatement(checkBookAvailability);
         PreparedStatement checkMemberStmt = connection.prepareStatement(checkMemberExists);
         PreparedStatement insertBorrowingStmt = connection.prepareStatement(insertBorrowing);
         PreparedStatement updateBookStmt = connection.prepareStatement(updateBookCopies)) {

        // Kiểm tra nếu sách có sẵn
        checkBookStmt.setString(1, masach);
        ResultSet bookResult = checkBookStmt.executeQuery();
        if (!bookResult.next() || bookResult.getInt("Soluong") <= 0) {
            System.out.println("Sach da het.");
            return;
        }
        

        // Kiểm tra nếu thành viên tồn tại
        checkMemberStmt.setString(1, mathanhvien);
        ResultSet memberResult = checkMemberStmt.executeQuery();
        if (!memberResult.next()) {
            System.out.println("Ko co thanh vien do.");
            return;
        }

        LocalDate ngayMuon = LocalDate.now();
        LocalDate hanTra = ngayMuon.plusDays(30);


        // Thêm bản ghi mượn sách mới

        insertBorrowingStmt.setString(1, mathanhvien);
        insertBorrowingStmt.setString(2, masach);
        insertBorrowingStmt.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
        insertBorrowingStmt.setNull(4, java.sql.Types.DATE); // Ngày trả sách chưa xác định
        insertBorrowingStmt.setDate(5, java.sql.Date.valueOf(hanTra)); 
        insertBorrowingStmt.executeUpdate();


        // Cập nhật số lượng sách
        updateBookStmt.setString(1, masach);
        updateBookStmt.executeUpdate();

        System.out.println("Muon sach thanh cong.");
    } catch (SQLException e) {
        System.out.println("Loi: " + e.getMessage());
    }
}
    public void trasach(String mathanhvien, String masach) {
    String checkBorrowingExists = "SELECT * FROM borrowings WHERE Mathanhvien = ? AND Masach = ? AND Ngaytra IS NULL";
    String updateBorrowing = "UPDATE borrowings SET Ngaytra = ? WHERE Mathanhvien = ? AND Masach = ?";
    String updateBookCopies = "UPDATE books SET Soluong = Soluong + 1 WHERE Masach = ?";

    try (PreparedStatement checkBorrowingStmt = connection.prepareStatement(checkBorrowingExists);
         PreparedStatement updateBorrowingStmt = connection.prepareStatement(updateBorrowing);
         PreparedStatement updateBookStmt = connection.prepareStatement(updateBookCopies)) {

        // Kiểm tra nếu bản ghi mượn sách tồn tại và chưa trả
        checkBorrowingStmt.setString(1, mathanhvien);
        checkBorrowingStmt.setString(2, masach);
        ResultSet borrowingResult = checkBorrowingStmt.executeQuery();
        if (!borrowingResult.next()) {

            System.out.println("\nKhong co du lieu muon sach.");
            return;
        }

        // Cập nhật ngày trả sách

        updateBorrowingStmt.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
        updateBorrowingStmt.setString(2, mathanhvien);
        updateBorrowingStmt.setString(3, masach);
        updateBorrowingStmt.executeUpdate();


        // Cập nhật số lượng sách
        updateBookStmt.setString(1, masach);
        updateBookStmt.executeUpdate();

        System.out.println("Tra sach thanh cong.");
    } catch (SQLException e) {
        System.out.println("Loi: " + e.getMessage());
    }

    
}

    public List<Borrowing> muontrasach() {

        List<Borrowing> borrowings = new ArrayList<>();
        String sql = "SELECT * FROM borrowings";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String mathanhvien = resultSet.getString("Mathanhvien");
                String masach = resultSet.getString("Masach");
                LocalDate ngaymuon = resultSet.getDate("Ngaymuon").toLocalDate();
                LocalDate ngaytra = resultSet.getDate("Ngaytra") != null ? resultSet.getDate("Ngaytra").toLocalDate() : null;
                LocalDate hantra = resultSet.getDate("DueDate").toLocalDate();
                Borrowing borrowing = new Borrowing(masach, mathanhvien, ngaymuon, ngaytra, hantra);

                borrowings.add(borrowing);
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return borrowings;
    }
  


}

