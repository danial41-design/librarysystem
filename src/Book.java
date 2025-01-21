public class Book {
    private int id;
    private String name;
    private String author;
    private int year;
    private int quantity;

    public Book(int id, String name, String author, int year, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Author: " + author +
                ", Year: " + year + ", Quantity: " + quantity;
    }
}
