package model;

public class UserOrderDataModel {
    private String email, isbn, quantity, state, date;

    public UserOrderDataModel(String email, String isbn, String quantity, String state, String date) {
        this.email = email;
        this.isbn = isbn;
        this.quantity = quantity;
        this.state = state;
        this.date = date;
    }
    public UserOrderDataModel(String[] order) {
        this.email = order[0];
        this.isbn = order[1];
        this.quantity = order[2];
        this.state = order[3];
        this.date = order[4];
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
