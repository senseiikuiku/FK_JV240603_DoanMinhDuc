package extend;

import entity.BookType;

public class BookTypeAndBook extends BookType {
    private int count;

    public BookTypeAndBook() {
    }

    public BookTypeAndBook(int id, String name, String description, boolean isDeleted, int count) {
        super(id, name, description, isDeleted);
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public void displayData() {
        System.out.println("Name: " + getName());
        System.out.println("Số lượng: " + this.count);
    }
}
