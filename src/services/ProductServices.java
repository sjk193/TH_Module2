package services;

import model.Product;
import ultis.CSVUltis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductServices implements IProductServices {
    private static final String path = "F:\\MD2\\Module2\\thi_TH2\\data\\product.csv";
    //    private final CSVUtils csvUtils = new CSVUtils();
    private static ArrayList<Product> products = new ArrayList<>();
    //    private static final ProductView productView = new ProductView();
//    private static final Scanner scanner = new Scanner(System.in);
    @Override
    public List<Product> getProducts() {
        if(products.size() == 0){
            if (products.size() == 0) {
                List<String> records = CSVUltis.read(path);
                for (String record : records) {
                    products.add(new Product(record));
                }
                return products;
            }
            return null;
        }
        return null;
    }
    @Override
    public void add(Product newProduct) {
        products.add(newProduct);
        update();
    }

    @Override
    public Product getById(int id) {
        for (Product product : products) {
            if (id == product.getId()) {
                return product;
            }
        }
        return null;
    }
    public void update(){
        CSVUltis.write(path,products);
    }
    @Override
    public boolean existById(int id) {
        for (Product product : products) {
            if (id == product.getId()) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void removeById(int id) {
        int index = findIndexById(id);
        products.remove(index);
        update();
    }
    public int findIndexById (int id) {
        for (int i =0; i< products.size(); i++) {
            if (id == products.get(i).getId()) {
                return i;
            }
        }
        return -1;
    }
    public boolean existByName(String name) {
        for (Product product : products) {
            if (name.equals(product.getName())) {
                return true;
            }
        }
        return false;
    }
    public static void showAllProducts() {
        System.out.println("-------------------------------- DANH SÁCH SẢN PHẨM -------------------------------");
        System.out.printf("*%-82s*\n", "");
        System.out.printf("*        %-16s %-15s %-15s %-12s %-12s*\n", "ID", "Tên sản phẩm", "Giá", "Số lượng","Mô tả");
        System.out.printf("*%-82s*\n", "");
        for (Product product : products) {
            System.out.printf("*        %-16s %-15s %-17s %-10s %-12s*\n", product.getId(), product.getName(), product.getPrice(), product.getQuantity(),product.getDescribe());
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public void showSortProduct(ArrayList<Product> products){
        System.out.println("-------------------------------- DANH SÁCH SẢN PHẨM -------------------------------");
        System.out.printf("*%-82s*\n", "");
        System.out.printf("*        %-16s %-15s %-15s %-12s %-12s*\n", "ID", "Tên sản phẩm", "Giá", "Số lượng","Mô tả");
        System.out.printf("*%-82s*\n", "");
        for (Product product : products) {
            System.out.printf("*        %-16s %-15s %-17s %-10s %-12s*\n", product.getId(), product.getName(), product.getPrice(), product.getQuantity(),product.getDescribe());
        }
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public static ArrayList<Product> sortProduct() {
        ArrayList<Product> newProducts = new ArrayList<>(products);
        Collections.sort(newProducts);
        return newProducts;
    }
}
