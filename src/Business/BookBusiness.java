package Business;

import entity.Book;
import entity.BookType;
import until.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookBusiness {
    public static List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "select * from Book where isDeleted=false";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("BookId"));
                book.setName(rs.getString("BookName"));
                book.setAuthor(rs.getString("Author"));
                book.setTotalPages(rs.getInt("TotalPages"));
                book.setContent(rs.getString("Content"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPrice(rs.getFloat("Price"));
                book.setTypeId(rs.getInt("TypeId"));
                book.setDeleted(rs.getBoolean("isDeleted"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return books;
    }

    public static boolean addBook(Book book) {
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "insert into Book(BookName,Title,Author,TotalPages,Content,Publisher,Price,TypeId,isDeleted) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getTotalPages());
            ps.setString(5, book.getContent());
            ps.setString(6, book.getPublisher());
            ps.setFloat(7, book.getPrice());
            ps.setInt(8, book.getTypeId());
            ps.setBoolean(9, book.isDeleted());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionDB.closeConnection(conn);
        }
    }

    public static Book getBookById(int id) {
        Connection conn = null;
        Book bookType = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "select * from Book where BookId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
                bookType = new Book();
                bookType.setId(rs.getInt("BookId"));
                bookType.setName(rs.getString("BookName"));
                bookType.setTitle(rs.getString("Title"));
                bookType.setAuthor(rs.getString("Author"));
                bookType.setTotalPages(rs.getInt("TotalPages"));
                bookType.setContent(rs.getString("Content"));
                bookType.setPublisher(rs.getString("Publisher"));
                bookType.setPrice(rs.getFloat("Price"));
                bookType.setTypeId(rs.getInt("TypeId"));
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

    public static boolean updateBook(Book book) {
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "update Book set BookName = ?,Title  = ?,Author = ?,TotalPages=?,Content=?,Publisher=?,Price=?,TypeId=?,isDeleted=? where BookId = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getAuthor());
            ps.setInt(4, book.getTotalPages());
            ps.setString(5, book.getContent());
            ps.setString(6, book.getPublisher());
            ps.setFloat(7, book.getPrice());
            ps.setInt(8, book.getTypeId());
            ps.setBoolean(9, book.isDeleted());
            ps.setInt(10, book.getId());
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
            String sql = "delete from Book where TypeId = ?";
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

    public static List<Book> sortDESC() {
        Connection conn = null;
        List<Book> books = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            String sql = "select * from Book order by Price desc";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("BookId"));
                book.setName(rs.getString("BookName"));
                book.setAuthor(rs.getString("Author"));
                book.setTotalPages(rs.getInt("TotalPages"));
                book.setContent(rs.getString("Content"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPrice(rs.getFloat("Price"));
                book.setTypeId(rs.getInt("TypeId"));
                book.setDeleted(rs.getBoolean("isDeleted"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return books;
    }

    public static List<Book> findByName(String name) {
        List<Book> books = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "select * from Book where BookName like ? or Content like ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("BookId"));
                book.setName(rs.getString("BookName"));
                book.setAuthor(rs.getString("Author"));
                book.setTotalPages(rs.getInt("TotalPages"));
                book.setContent(rs.getString("Content"));
                book.setPublisher(rs.getString("Publisher"));
                book.setPrice(rs.getFloat("Price"));
                book.setTypeId(rs.getInt("TypeId"));
                book.setDeleted(rs.getBoolean("isDeleted"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return books;
    }

    public static List<Book> PhanChia() {
        List<Book> books = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionDB.openConnection();
            String sql = "SELECT \n" +
                    "    BookName,\n" +
                    "    Author,\n" +
                    "    TotalPages,\n" +
                    "    CASE\n" +
                    "        WHEN TotalPages < 50 THEN 'Nhóm 1'\n" +
                    "        WHEN TotalPages >= 50 AND TotalPages < 300 THEN 'Nhóm 2'\n" +
                    "        ELSE 'Nhóm 3'\n" +
                    "    END AS BookGroup\n" +
                    "FROM \n" +
                    "    Book;\n";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setName(rs.getString("BookName"));
                book.setAuthor(rs.getString("Author"));
                book.setTotalPages(rs.getInt("TotalPages"));
                book.setGroup(rs.getString("BookGroup"));
                books.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn);
        }
        return books;
    }
}
