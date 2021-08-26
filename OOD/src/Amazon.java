import java.util.Date;
import java.util.HashMap;
import java.util.List;

/*
1.search catalog
customer --------- catalog ---------- product
searchProducts---->
                   getInfo----------->
                           <----------Full Product Info
         <-------- Matching Product info

2. add item to the shopping cart
customer -------------- shoppingCart --------- item
addItem(id, quantity)->
        <-------------- error(1)
                        verifyItem() --------->
                                     <--------- Verification Status
        <-------------- Operation success/fail

3. checking out to place an order

 */

// Enums, data types, and constants
class AmazonAddress {
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
}

enum OrderStatus {
    UNSHIPPED, PENDING, SHIPPED, COMPLETED, CANCELED, REFUND_APPLIED
}

//enum AccountStatus {
//    ACTIVE, BLOCKED, BANNED, COMPROMISED, ARCHIVED, UNKNOWN
//}

enum ShipmentStatus {
    PENDING, SHIPPED, DELIVERED, ON_HOLD,
}

enum PaymentStatus {
    UNPAID, PENDING, COMPLETED, FILLED, DECLINED, CANCELLED, ABANDONED, SETTLING, SETTLED, REFUNDED
}

//Account, Customer, Admin, and Guest
class AmazonAccount {
    private String userName;
    private String password;
    private AccountStatus status;
    private String name;
    private Address shippingAddress;
    private String email;
    private String phone;

    private List<CreditCard> creditCards;
    private List<ElectronicBankTransfer> bankAccounts;

    public boolean addProduct(Product product) {
        return false;
    }

    public boolean addProductReview(ProductReview review) {
        return false;
    }

    public boolean resetPassword() {
        return false;
    }
}

abstract class Customer {
    private ShoppingCart cart;
    private Order order;

    public ShoppingCart getShoppingCart() {
        return null;
    }

    public boolean addItemToCart(Item item) {
        return false;
    }

    public boolean removeItemFromCart(Item item) {
        return false;
    }
}

class Guest extends Customer {
    public boolean registerAccount() {
        return false;
    }
}

class Member extends Customer {
    private Account account;

    public OrderStatus placeOrder(Order order) {
        return null;
    }
}

//ProductCategory, Product, and ProductReview
class ProductCategory {
    private String name;
    private String description;
}

class ProductReview {
    private int rating;
    private String review;

    private Member reviewer;
}

class AmazonProduct {
    private String productID;
    private String name;
    private String description;
    private double price;
    private ProductCategory category;
    private int availableItemCount;

    private Account seller;

    public int getAvailableCount() {
        return 0;
    }

    public boolean updatePrice(double newPrice) {
        return false;
    }
}

// ShoppingCart, Item, Order, and OrderLog:
// Users will add items to the shopping cart and place an order to buy all the items in the cart.
class Item {
    private String productID;
    private int quantity;
    private double price;

    public boolean updateQuantity(int quantity) {
        return false;
    }
}

class ShoppingCart {
    private List<Item> items;

    public boolean addItem(Item item) {
        return false;
    }

    public boolean removeItem(Item item) {
        return false;
    }

    public boolean updateItemQuantity(Item item, int quantity) {
        return false;
    }

    public List<Item> getItems() {
        return null;
    }

    public boolean checkout() {
        return false;
    }
}

class OrderLog {
    private String orderNumber;
    private Date creationDate;
    private OrderStatus status;
}

class Order {
    private String orderNumber;
    private OrderStatus status;
    private Date orderDate;
    private List<OrderLog> orderLog;

    public boolean sendForShipment() {
        return false;
    }

    public boolean makePayment(Payment payment) {
        return false;
    }

    public boolean addOrderLog(OrderLog orderLog) {
        return false;
    }
}

// Shipment, ShipmentLog, and Notification:
// After successfully placing an order, a shipment record will be created:
class ShipmentLog {
    private String shipmentNumber;
    private ShipmentStatus status;
    private Date creationDate;
}

class Shipment {
    private String shipmentNumber;
    private Date shipmentDate;
    private Date estimatedArrival;
    private String shipmentMethod;
    private List<ShipmentLog> shipmentLogs;

    public boolean addShipmentLog(ShipmentLog shipmentLog) {
        return false;
    }
}

abstract class Notification {
    private int notificationId;
    private Date createdOn;
    private String content;

    public boolean sendNotification(Account account) {
        return false;
    }
}

// Search interface and Catalog: Catalog will implement Search to facilitate searching of products.
interface Search {
    public List<Product> searchProductsByName(String name);
    public List<Product> searchProductsByCategory(String category);
}

class Catalog implements Search {
    HashMap<String, List<Product>> productNames;
    HashMap<String, List<Product>> productCategories;

    public List<Product> searchProductsByName(String name) {
        return productNames.get(name);
    }

    public List<Product> searchProductsByCategory(String category) {
        return productCategories.get(category);
    }
}

class CreditCard{}
class ElectronicBankTransfer{}
