package entity;

import java.util.Scanner;

public class BookType implements IBookManger {
    private int id;
    private String name;
    private String description;
    private boolean isDeleted;

    public BookType(int id, String name, String description, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public BookType() {
    }

    @Override
    public void displayData() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Deleted: " + (isDeleted ? "Yes" : "No"));
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.print("Nhập name: ");
        this.name = sc.nextLine();
        System.out.print("Nhập description: ");
        this.description = sc.nextLine();
        System.out.print("Nhập isDeleted(true hoặc false): ");
        this.isDeleted = Boolean.parseBoolean(sc.nextLine());
    }
}
