package services;

import model.Product;

import java.util.List;

public interface IProductServices {
    List<Product> getProducts();
    void add(Product newProduct);
    Product getById(int id);
    boolean existById(int id);
    void removeById(int id);
}
