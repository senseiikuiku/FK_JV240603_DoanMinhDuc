package entity;

import java.util.Scanner;

public class Book implements IBookManger {
    private int id;
    private String name;
    private String title;
    private String author;
    private int totalPages;
    private String content;
    private String publisher;
    private float price;
    private int typeId;
    private boolean isDeleted;

    private String group;

    public Book() {
    }

    public Book(int id, String name, String title, String author, int totalPages, String content, String publisher, float price, int typeId, boolean isDeleted, String group) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.author = author;
        this.totalPages = totalPages;
        this.content = content;
        this.publisher = publisher;
        this.price = price;
        this.typeId = typeId;
        this.isDeleted = isDeleted;
        this.group = group;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public void displayData() {
        System.out.println("Id: " + this.id);
        System.out.println("Name: " + this.name);
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
        System.out.println("TotalPages: " + this.totalPages);
        System.out.println("Content: " + this.content);
        System.out.println("Publisher: " + this.publisher);
        System.out.println("Price: " + this.price);
        System.out.println("TypeId: " + this.typeId);
        System.out.println("Deleted: " + (this.isDeleted ? "Yes" : "No"));
    }

    @Override
    public void inputData(Scanner sc) {
        System.out.print("Nhập name: ");
        this.name = sc.nextLine();
        System.out.print("Nhập title: ");
        this.title = sc.nextLine();
        System.out.print("Nhập author: ");
        this.author = sc.nextLine();
        System.out.print("Nhập totalPages: ");
        this.totalPages = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập content: ");
        this.content = sc.nextLine();
        System.out.print("Nhập publisher: ");
        this.publisher = sc.nextLine();
        System.out.print("Nhập price: ");
        this.price = Float.parseFloat(sc.nextLine());
        System.out.print("Nhập typeId: ");
        this.typeId = Integer.parseInt(sc.nextLine());
        System.out.print("Nhập isDeleted(true hoặc false): ");
        this.isDeleted = Boolean.parseBoolean(sc.nextLine());
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void showGroupTotalPages() {
        System.out.println("Name: " + this.name);
        System.out.println("Author: " + this.author);
        System.out.println("TotalPages: " + this.totalPages);
        System.out.println("Group: " + this.group);

    }

}
