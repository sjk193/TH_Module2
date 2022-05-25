package model;

import java.util.Objects;

public class Product implements Comparable<Product>{
    private long id;
    private String name;
    private double price;
    private int quantity;
    private String describe;
    public Product() {
    }
    public Product( String name, double price, int quantity, String describe) {
        this.id = System.currentTimeMillis() / 1000;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.describe = describe;
    }
    public Product(String str){
        String[] strArr = str.split(",");
        this.id = Long.parseLong(strArr[0]);
        this.name = strArr[1];
        this.price = Double.parseDouble(strArr[2]);
        this.quantity = Integer.parseInt(strArr[3]);
        this.describe = strArr[4];
    }
    public long getId() {
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getDescribe() {
        return describe;
    }
    public void setDescribe(String describe) {
        this.describe = describe;
    }
    @Override
    public String toString() {
        return id + "," + name + "," + price + "," + quantity + "," + describe;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && Double.compare(product.price, price) == 0 && quantity == product.quantity && Objects.equals(name, product.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, quantity);
    }

    public int compareTo(Product o) {
        return (int) (price - o.price);
    }
}
