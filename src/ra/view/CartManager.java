package ra.view;

import ra.config.Constans;
import ra.config.InputMethod;
import ra.controller.CartController;
import ra.controller.OrderController;
import ra.controller.ProductController;
import ra.model.CartItem;
import ra.model.Order;
import ra.model.Product;
import ra.model.User;

public class CartManager {
    private static CartController cartController;
    private ProductController productController;

    public CartManager() {
        cartController = new CartController(Navbar.userLogin);
        productController = new ProductController();
        while (true) {
            Navbar.menuCart();
            int choice = InputMethod.getInteger();
            switch (choice) {
                case 1:
                    showCart();
                    break;
                case 2:
                    changeQuantity();
                    break;
                case 3:
                    deleteItem();
                    break;
                case 4:
                    cartController.clearAll();
                    break;
                case 5:
                    checkout(productController);
                    break;
                case 6:
                    new OrderManager();
                    break;
                case 7:
                    Navbar.menuUser();
                    break;
                default:
                    System.out.println("Chọn từ 1 đến 7");
            }
        }
    }

    public static void addCart() {
        ProductController productController1 = new ProductController();
        CartItem cartItem = new CartItem();
        cartController = new CartController(Navbar.userLogin);
        System.out.println("Nhap Id muon them: ");
        int id = InputMethod.getInteger();
        Product product = productController1.findById(id);
        if (product == null || product.isStatus()==false) {
            System.out.println(Constans.NOT_FOUND);
        } else {
            cartItem.setProduct(product);
            cartItem.setId(cartController.getNewId());
            System.out.println("Nhập số lượng: ");
            cartItem.setQuantity(InputMethod.getInteger());
            cartController.save(cartItem);
            System.out.println("Thêm thành công!!!");
        }
    }

    public void showCart() {
        User userLogin = Navbar.userLogin;
        if (userLogin.getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng rỗng");
            return;
        }
        for (CartItem cart : userLogin.getCartItems()) {
            cart.setProduct(productController.findById(cart.getProduct().getId()));
            System.out.println("Id: " + cart.getId() + " | Tên sản phẩm: " + cart.getProduct().getNameProduct() +
                    " | Giá: " + cart.getProduct().getPrice() + "$" + " | Số lượng: " + cart.getQuantity());
        }
    }

    public void changeQuantity() {
        System.out.println("Nhập Id muốn thay đổi: ");
        int cartId = InputMethod.getInteger();
        CartItem cartItem = cartController.findById(cartId);
        if (cartItem == null) {
            System.out.println(Constans.NOT_FOUND);
            return;
        }
        System.out.println("Nhập số lượng muốn thay đổi: ");
        cartItem.setQuantity(InputMethod.getInteger());
        cartController.save(cartItem);
    }

    public void deleteItem() {
        System.out.println("Nhập Id muốn xóa: ");
        int idCart = InputMethod.getInteger();
        if (cartController.findById(idCart) == null) {
            System.out.println(Constans.NOT_FOUND);
            return;
        }
        cartController.delete(idCart);
        System.out.println("Xóa thành công");
    }

    public void checkout(ProductController productController) {
        OrderController orderController = new OrderController();
        User userLogin = Navbar.userLogin;
        if(userLogin.getCartItems().isEmpty()){
            System.err.println("Giỏ hàng rỗng");
            return;
        }
        //  kiểm tra số lượng trong kho
        for (CartItem ci: userLogin.getCartItems()) {
            Product p = productController.findById(ci.getProduct().getId());
            if(ci.getQuantity() >p.getStock() ){
                System.err.println("Sản phẩm "+p.getNameProduct() + " chỉ còn "+ p.getStock() +" sản phẩm, vui lòng giảm số lượng");
                return;
            }
        }

        Order newOrder = new Order();
        newOrder.setId(orderController.getNewId());
        // coppy sp trong gior hàng sang hóa đơn
        newOrder.setOrderDetail(userLogin.getCartItems());
        // cập nhật tổng tiền
        double total = 0;
        for (CartItem ci: userLogin.getCartItems()) {
            total+= ci.getQuantity()*ci.getProduct().getPrice();
        }
        newOrder.setTotal(total);
        newOrder.setIdUser(userLogin.getId());
        System.out.println("Nhập tên: ");
        newOrder.setReceiver(InputMethod.getString());
        System.out.println("Nhập số điện thoại: ");
        newOrder.setPhoneNumber(InputMethod.getPhoneNumber());
        System.out.println("Nhập địa chỉ: ");
        newOrder.setAddress(InputMethod.getString());
        orderController.save(newOrder);
        // giảm số lượng đi
        for (CartItem ci: userLogin.getCartItems()) {
            Product p = productController.findById(ci.getProduct().getId());
            p.setStock(p.getStock()-ci.getQuantity());
            productController.save(p);
        }
        System.out.println(newOrder);
        cartController.clearAll();
    }
}
