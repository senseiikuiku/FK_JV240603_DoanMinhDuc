package Business;

import entity.BookType;
import extend.BookTypeAndBook;
import until.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookTypeBusiness {
    public static List<BookType> getAll() {
        List<BookType> books = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "select * from BookType where isDeleted=false";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BookType bookType = new BookType();
                bookType.setId(rs.getInt("TypeId"));
                bookType.setName(rs.getString("TypeName"));
                bookType.setDescription(rs.getString("Description"));
                bookType.setDeleted(rs.getBoolean("isDeleted"));
                books.add(bookType);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return books;
    }

    //    Xây dựng phương thức thêm
    public static boolean addBook(BookType book) {
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();

            String sql = "insert into BookType(TypeName,Description,isDeleted) values(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setString(2, book.getDescription());
            ps.setBoolean(3, book.isDeleted());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static BookType getBookById(int id) {
        Connection conn = null;
        BookType bookType = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "select * from BookType where TypeId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
                bookType = new BookType();
                bookType.setId(rs.getInt("TypeId"));
                bookType.setName(rs.getString("TypeName"));
                bookType.setDescription(rs.getString("Description"));
                bookType.setDeleted(rs.getBoolean("isDeleted"));
            }
            if (count == 0) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return bookType;
    }

    public static boolean updateBook(BookType book) {
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "update BookType set TypeName = ?,Description = ?,isDeleted = ? where TypeId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setString(2, book.getDescription());
            ps.setBoolean(3, book.isDeleted());
            ps.setInt(4, book.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static boolean deleteBook(int id) {
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "delete from BookType where TypeId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static List<BookTypeAndBook> count() {
        List<BookTypeAndBook> books = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "select b.TypeName, count(Book.BookId) AS Total " +
                    "from BookType b " +
                    "Left join Book ON b.TypeId=Book.TypeId " +
                    "Group by b.TypeName;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BookTypeAndBook bookTypeAndBook = new BookTypeAndBook();
                bookTypeAndBook.setName(rs.getString("TypeName"));
                bookTypeAndBook.setCount(rs.getInt("Total"));
                books.add(bookTypeAndBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return books;
    }
}
