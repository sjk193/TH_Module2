package view;

import menu.Menu;
import model.Product;
import services.ProductServices;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProductView {
    private static final ProductServices productService = new ProductServices();
    private static final Scanner scanner = new Scanner(System.in);
    public static void runProduct() {
        do {
            productService.getProducts();
            Scanner scanner = new Scanner(System.in);
            ProductView managerProductView = new ProductView();
            Menu.mainMenu();
            try {
//                System.out.println("\nChọn chức năng: ");
//                System.out.println(" ⭆ ");
                int number = Integer.parseInt(scanner.nextLine());
                switch (number) {
                    case 1:
                        managerProductView.showProduct();
                        break;
                    case 2:
                        managerProductView.addProduct();
                        break;
                    case 3:
                        managerProductView.updateProduct();
                        break;
                    case 4:
                        managerProductView.deleteProduct();
                        break;
                    case 5:
                        ArrayList<Product> newProducts = productService.sortProduct();
                        productService.showSortProduct(newProducts);
                        break;
                    case 6:
                        managerProductView.sortProductASC();
                        break;
                    case 7:
                        System.out.println("Ghi từ file");
                        break;
//                    case 8:
//                        Menu.mainMenu();
//                        break;
                    case 0:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Chọn chức năng không hợp lệ! Vui lòng chọn lại!");
            }
        } while (true);
    }

    private void sortProductASC() {

    }




    public void showProduct() {
        try {
            ProductServices.showAllProducts();
        } catch (Exception e) {
            System.out.println("Chọn chức năng không đúng!");
        }
    }
//    public static void showAllProducts() {
//        System.out.println("-------------------------- DANH SÁCH SẢN PHẨM -------------------------");
//        System.out.printf("*%-69s*\n", "");
//        System.out.printf("*        %-16s %-15s %-15s %-12s*\n", "ID", "Tên sản phẩm", "Giá", "Số lượng");
//        System.out.printf("*%-69s*\n", "");
//
//        for (Product product : products) {
//            System.out.printf("*        %-16s %-15s %-17s %-10s*\n", product.getId(), product.getName(), product.getPrice(), product.getQuantity());
//        }
//
//        System.out.println("-----------------------------------------------------------------------");
//    }
    public void addProduct() {
        int quantity = 0;
        long price = 0;
        String name = null;
        String describe = null;
        do {
            try {
                System.out.println("Nhập tên: ");
                name = scanner.nextLine();
//                while (productService.existByName(name)) {
//                    System.out.println("Tên đã tồn tại! Nhập tên khác: ");
//                    name = scanner.nextLine();
//                }
                while (productService.existByName(name) || name.length() == 0) {
                    if (productService.existByName(name)) {
                        System.out.println("Tên đã tồn tại! Nhập tên khác: ");
                    }else {
                        System.out.println("Tên không được để trống");
                    }
                    name = scanner.nextLine();
                }
            } catch (Exception e) {
                System.out.println("Tên không hợp lệ! Vui lòng nhập lại tên!");
            }
        } while (productService.existByName(name) || name.length() == 0);

        do {
            try {
                System.out.println("Nhập giá: ");
                price = Long.parseLong(scanner.nextLine());
                while (price <= 0) {
                    System.out.println("Giá không hợp lý! Nhập lại: ");
                    price = Long.parseLong(scanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Giá không hợp lý! Vui lòng nhập lại giá!");
            }
        } while (price <= 0);

        do {
            try {
                System.out.println("Nhập số lượng: ");
                quantity = Integer.parseInt(scanner.nextLine());
                while (quantity < 0) {
                    System.out.println("Số lượng không hợp lý! Nhập lại: ");
                    quantity = Integer.parseInt(scanner.nextLine());
                }

            } catch (Exception e) {
                System.out.println("Số lượng không hợp lý! Vui lòng nhập lại số luọng!");
            }
        } while (quantity < 0);

        do {
            System.out.println("Nhập mô tả sản phẩm:");
            describe = scanner.nextLine();
            while (describe.length() == 0){
                System.out.println("không được để trống thông tin này!");
                describe = scanner.nextLine();
            }
        }while (describe.length() == 0);

        Product product = new Product( name, price, quantity,describe);
        productService.add(product);
        System.out.println("Sản phẩm đã được thêm thành công!");
        ProductServices.showAllProducts();
    }
    public void updateProduct() {
        try {
            ProductServices.showAllProducts();
            int id = 0;
            do {
                try {
                    System.out.println("Nhập ID muốn sửa: ");
                    id = Integer.parseInt(scanner.nextLine());
                    while (!productService.existById(id)) {
                        if (id == 0) {
                            runProduct();
                        } else {
                            System.out.println("Không tìm được sản phẩm với mã sản phẩm trên! Vui lòng nhập lại ID khác: ");
                        }
                        id = Integer.parseInt(scanner.nextLine());
                    }
                } catch (Exception e) {
                    System.out.println("ID không hợp lệ!");
                }
            } while (!productService.existById(id));
            Product product = productService.getById(id);
            String name = checkNameProduct(product);
            long price = checkPriceProduct(product);
            int quantity = checkQuantity(product);
            System.out.println("Nhập mô tả sản phẩm:");
            String describe = scanner.nextLine();
            while (describe.length() == 0){
                System.out.println("Không đc để trống thông tin này!");
                describe = scanner.nextLine();
            }

            if (name.equals("0") || Long.toString(price).equals("0") || Integer.toString(quantity).equals("0")) {
                System.out.println("Sản phẩm chưa được thay đổi!");
            } else {
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                product.setDescribe(describe);
                System.out.println("Sản phẩm đã được chỉnh sửa thành công!");
                ProductServices.showAllProducts();
                productService.update();
            }
        } catch (Exception e) {
            System.out.println("Thông tin nhập không hợp lệ!");
        }
    }

    private int checkQuantity(Product product) {
        int quantity = 0;
        do {
            try {
                System.out.println("Nhập số lượng sản phẩm muốn sửa (bấm 0 nếu muốn thoát và quay lại menu trước đó): ");
                quantity = Integer.parseInt(scanner.nextLine());

                while (quantity <= 0 || product.getQuantity() == quantity) {
                    if (quantity == 0) {
                        runProduct();
                    } else if (product.getQuantity() == quantity) {
                        System.out.println("Số lượng thay đổi phải khác ban đầu! Vui lòng nhập lại số lượng sản phẩm: ");
                    } else {
                        System.out.println("Số lượng không thể âm! Vui lòng nhập lại số lượng: ");
                    }
                    quantity = Integer.parseInt(scanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Số lượng không hợp lệ!");
            }
        } while (quantity <= 0 || product.getQuantity() == quantity);
        product.setQuantity(quantity);
        return quantity;
    }

    private long checkPriceProduct(Product product) {
        long price = 0;
        do {
            try {
                System.out.println("Nhập giá mới (bấm 0 nếu muốn thoát và quay lại menu trước đó): ");
                price = Long.parseLong(scanner.nextLine());

                while (price <= 0 || product.getPrice() == price) {
                    if (price == 0) {
                        runProduct();
                    } else if (product.getPrice() == price) {
                        System.out.println("Giá thay đổi phải khác ban đầu! Vui lòng nhập lại giá sản phẩm: ");
                    } else {
                        System.out.println("Giá sản phẩm không được âm! Vui lòng nhập lại giá sản phẩm: ");
                    }

                    price = Long.parseLong(scanner.nextLine());
                }
            } catch (Exception e) {
                System.out.println("Giá sản phẩm không hợp lệ");
            }
        } while (price <= 0 || product.getPrice() == price);
        product.setPrice(price);
        return price;
    }

    private String checkNameProduct(Product product) {
        System.out.println("Nhập tên mới ( bấm 0 nếu muốn thoát và quay lại menu trước đó): ");
        String name = scanner.nextLine().toLowerCase();

        while (name.length() < 2 || product.getName().equals(name)) {
            if (name.equals("0")) {
                runProduct();
            } else if (product.getName().toLowerCase().equals(name)) {
                System.out.println("Tên thay đổi không được trùng với tên ban đầu!");
            } else {
                System.out.println("Tên phải ít nhất 2 ký tự! Vui lòng nhập lại tên sản phẩm: ");
            }
            name = scanner.nextLine().toLowerCase();
        }
        return name;
    }
    public void deleteProduct() {
        try {
            showProduct();
            System.out.println("Nhập ID muốn xóa: ");
            int id = Integer.parseInt(scanner.nextLine());
            while (!productService.existById(id)) {
                System.out.println("Không tồn tại sản phẩm có ID này! Vui lòng nhập lại ID khác: ");
                id = Integer.parseInt(scanner.nextLine());
            }
            productService.removeById(id);
            System.out.println("Sản phẩm có ID là " + id + " đã được xóa khỏi danh sách!");
            ProductServices.showAllProducts();
        } catch (Exception e) {
            System.out.println("ID không hợp lệ!");
        }
    }
}
        //    private static final PhoneBookServices phoneBookServices = new PhoneBookServices();
//    private static final Scanner scanner = new Scanner(System.in);
//    public void option(){
//        do {
//            phoneBookServices.getItem();
//            Menu.mainMenu();
//            try {
//                int choice = Integer.parseInt(scanner.nextLine());
//                switch (choice) {
//                    case 1:
//                        phoneBookServices.showPhoneBook();
//                        break;
//                    case 2:
//                        addPhoneDirectory();
//                        break;
//                    case 3:
//                        editPhoneDirectory();
//                        break;
//                    case 4:
//                        removePhoneDirectory();
//                        break;
//                    case 5:
//                        phoneBookServices.searchPhoneDirectory();
//                        break;
//                    case 6:
//                        System.exit(0);
//                        break;
//                    default:
//                        System.out.println("Chon chuc nang khong đung! Vui long chon lai");
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println("Nhap sai! Vui long nhap lai!");
//            }
//        } while (true);

