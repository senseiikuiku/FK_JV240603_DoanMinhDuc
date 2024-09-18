package presentation;

import Business.BookBusiness;
import Business.BookTypeBusiness;
import entity.Book;
import entity.BookType;
import extend.BookTypeAndBook;

import java.util.List;
import java.util.Scanner;

public class BookManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        do {
            System.out.println("""
                    ******************BOOK-MANAGEMENT******************
                    1. Quản lý loại sách
                    2. Quản lý sách
                    3. Thoát\s
                    """);
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    menuBookType(sc);
                    break;
                case 2:
                    menuBook(sc);
                    break;
                case 3:
                    System.out.println("Thoát chương trình!!");
                    flag = false;
                    break;
                default:
                    System.out.println("Nhập sai. Vui lòng nhập lại!!!");
            }
        } while (flag);
    }

    public static void menuBook(Scanner sc) {
        boolean flag = true;
        do {
            System.out.println("""
                     ***********************BOOK-MENU***********************[40 điểm]\s
                    1. Danh sách sách
                    2. Tạo mới sách
                    3. Cập nhật thông tin sách
                    4. Xóa sách
                    5. Hiển thị danh sách các cuốn sách theo giá giảm dần
                    6. Tìm kiếm sách theo tên hoặc nội dung
                    7. Thống kê số lượng sách theo nhóm\s
                    8. Thoát
                    """);
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    showBook();
                    break;
                case 2:
                    addBook(sc);
                    break;
                case 3:
                    updateBook(sc);
                    break;
                case 4:
                    deleteBook(sc);
                    break;
                case 5:
                    sortBook();
                    break;
                case 6:
                    byNameBook(sc);
                    break;
                case 7:
                    phanchia();
                    break;
                case 8:
                    System.out.println("Thoát");
                    flag = false;
                    break;
                default:
                    System.out.println("Nhập sai. Vui lòng nhập lại");
            }
        } while (flag);
    }

    public static void menuBookType(Scanner sc) {
        boolean flag = true;
        do {
            System.out.print("""
                     **********************BOOKTYPE-MENU********************
                    1. Danh sách loại sách
                    2. Tạo mới loại sách
                    3. Cập nhật thông tin loại sách
                    4. Xóa loại sách
                    5. Thống kê số lượng sách theo mã loại sách
                    6. Thoát\s
                    """);
            System.out.print("Nhập lựa chọn: ");
            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    showDataNotDeleted();
                    break;
                case 2:
                    addBookType(sc);
                    break;
                case 3:
                    updateType(sc);
                    break;
                case 4:
                    deleteBookType(sc);
                    break;
                case 5:
                    countBookTyope();
                    break;
                case 6:
                    System.out.println("Thoát");
                    flag = false;
                    break;
                default:
                    System.out.println("Nhập sai. Vui lòng nhập lại!!");
            }
        } while (flag);
    }

    public static void showDataNotDeleted() {
        List<BookType> books = BookTypeBusiness.getAll();
        for (int i = 0; i < books.size(); i++) {
            books.get(i).displayData();
            System.out.println(i < books.size() - 1 ? "----------" : "");
        }
    }

    //    Xây dựng phương thức thêm
    public static void addBookType(Scanner sc) {
        BookType book = new BookType();
        book.inputData(sc);
        boolean result = BookTypeBusiness.addBook(book);
        if (result) {
            System.out.println("Thêm thành công");
        } else {
            System.err.println("Thêm thất bại");
        }
    }

    public static void updateType(Scanner sc) {
        System.out.print("Nhập id cần sửa: ");
        int id = Integer.parseInt(sc.nextLine());
        BookType book = BookTypeBusiness.getBookById(id);
        if (book != null) {
            book.displayData();
            boolean check = false;
            do {
                System.out.print("""
                        Chọn trường cần sửa:
                        1. Nhập tên mới
                        2. Nhập description mới
                        3. Thoát\s
                        """);
                System.out.print("Nhập lựa chọn: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên mới: ");
                        book.setName(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Nhập description: ");
                        book.setDescription(sc.nextLine());
                        break;
                    case 3:
                        System.out.println("Thoát");
                        check = true;
                        break;
                    default:
                        System.out.println("Nhập sai. Vui lòng nhập lại!!");
                }
            } while (!check);
            boolean result = BookTypeBusiness.updateBook(book);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Cập nhật thất bại");
            }
        } else {
            System.err.println("Id không tồn tại");
        }
    }

    public static void deleteBookType(Scanner sc) {
        System.out.println("Nhập id cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());
        BookType book = BookTypeBusiness.getBookById(id);
        if (book != null) {
            book.setDeleted(false);
            boolean result = BookTypeBusiness.updateBook(book);
            if (result) {
                System.out.println("Xóa thành công");
            } else {
                System.err.println("Xóa thất bại");
            }
        } else {
            System.err.println("Id không tồn tại");
        }
    }

    public static void countBookTyope() {
        List<BookTypeAndBook> bookTypeAndBooks = BookTypeBusiness.count();
        for (int i = 0; i < bookTypeAndBooks.size(); i++) {
            bookTypeAndBooks.get(i).displayData();
            System.out.println(i < bookTypeAndBooks.size() - 1 ? "----------" : "");
        }
    }


    //    ================= Book ======================
    public static void showBook() {
        List<Book> books = BookBusiness.getAll();
        for (int i = 0; i < books.size(); i++) {
            books.get(i).displayData();
            System.out.println(i < books.size() - 1 ? "----------" : "");
        }
    }

    public static void addBook(Scanner sc) {
        Book book = new Book();
        book.inputData(sc);
        boolean result = BookBusiness.addBook(book);
        if (result) {
            System.out.println("Thêm thành công");
        } else {
            System.err.println("Thêm thất bại");
        }
    }


    public static void updateBook(Scanner sc) {
        System.out.print("Nhập id cần sửa: ");
        int id = Integer.parseInt(sc.nextLine());
        Book book = BookBusiness.getBookById(id);
        if (book != null) {
            book.displayData();
            boolean check = false;
            do {
                System.out.print("""
                        Chọn trường cần sửa:
                        1. Nhập tên mới
                        2. Nhập Title
                        3. Nhập Author
                        4. Nhập TotalPages
                        5. Nhập Content
                        6. Nhập Publisher
                        7. Nhập Price
                        8. Nhập TypeId  
                        9. Nhập isDeleted
                        10. Thoát\s
                        """);
                System.out.print("Nhập lựa chọn: ");
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên mới: ");
                        book.setName(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Nhập Titile: ");
                        book.setTitle(sc.nextLine());
                        break;
                    case 3:
                        System.out.print("Nhập Author: ");
                        book.setAuthor(sc.nextLine());
                        break;
                    case 4:
                        System.out.print("Nhập Tôtal Pages: ");
                        book.setTotalPages(Integer.parseInt(sc.nextLine()));
                        break;
                    case 5:
                        System.out.print("Nhập Content: ");
                        book.setName(sc.nextLine());
                        break;
                    case 6:
                        System.out.print("Nhập Publisher: ");
                        book.setName(sc.nextLine());
                        break;
                    case 7:
                        System.out.print("Nhập Price: ");
                        book.setPrice(Integer.parseInt(sc.nextLine()));
                        break;
                    case 8:
                        System.out.print("Nhập TypeID mới: ");
                        book.setTypeId(Integer.parseInt(sc.nextLine()));
                        break;
                    case 9:
                        System.out.print("Nhập isDeleted: ");
                        book.setDeleted(Boolean.parseBoolean(sc.nextLine()));
                        break;
                    case 10:
                        System.out.println("Thoát");
                        check = true;
                        break;
                    default:
                        System.out.println("Nhập sai. Vui lòng nhập lại!!");
                }
            } while (!check);
            boolean result = BookBusiness.updateBook(book);
            if (result) {
                System.out.println("Cập nhật thành công");
            } else {
                System.err.println("Cập nhật thất bại");
            }
        } else {
            System.err.println("Id không tồn tại");
        }
    }

    public static void deleteBook(Scanner sc) {
        System.out.print("Nhập id cần xóa: ");
        int id = Integer.parseInt(sc.nextLine());
        Book book = BookBusiness.getBookById(id);
        if (book != null) {
            book.setDeleted(false);
            boolean result = BookBusiness.updateBook(book);
            if (result) {
                System.out.println("Xóa thành công");
            } else {
                System.err.println("Xóa thất bại");
            }
        } else {
            System.err.println("Id không tồn tại");
        }
    }

    public static void sortBook() {
        List<Book> books = BookBusiness.sortDESC();
        for (int i = 0; i < books.size(); i++) {
            books.get(i).displayData();
            System.out.println(i < books.size() - 1 ? "----------" : "");
        }
    }

    public static void byNameBook(Scanner sc) {
        System.out.print("Nhập tên cần tìm: ");
        String name = sc.nextLine();
        List<Book> books = BookBusiness.findByName(name);
        if (!books.isEmpty()) {
            for (int i = 0; i < books.size(); i++) {
                books.get(i).displayData();
                System.out.println(i < books.size() - 1 ? "----------" : "");
            }
        } else {
            System.err.println("Không có tên: " + name);
        }
    }

    public static void phanchia() {
        List<Book> books = BookBusiness.PhanChia();
        if (!books.isEmpty()) {
            for (int i = 0; i < books.size(); i++) {
                books.get(i).showGroupTotalPages();
                System.out.println(i < books.size() - 1 ? "----------" : "");

            }
        } else {
            System.err.println("Không có sách nào");
        }
    }
}
