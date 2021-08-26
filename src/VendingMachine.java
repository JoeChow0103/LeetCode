import java.util.*;

/*
bad for scaling, add more feature, add more methods
1. Readability: method too long
2. Merge Code - conflict, if two people try to add new method, may conflict on the same position
3. Responsibility, annotation: @Oncall("xxx")
boolean pay(String productID, PaymentType paymentType, double val) { // COIN, CASH
    Product product = getProductById(productID);
    switch (payment) {
        case COIN:
            payByCoin(product, val);
            break;
        case CASH:
            payByCash(product, val);
            break;
        default:
            throw new IllegalArgumentException("un-supported payment type");
        }
}

Interface Payment{};
switch (payment) {
    case COIN:
        payment = new CoinPayment();
        break;
}

continue decoupling

test the credit card
can create a fake credit card class, the same as credit card except for the bank
*/

public class VendingMachine {
    private final Map<String, Product> idToProduct;

    public VendingMachine() {
        idToProduct = new HashMap<>();
    }


    public boolean pay(String productId,
                       PaymentType paymentType,
                       double value) {
        Product product = idToProduct.get(productId);
        Payment payment = PaymentFactory.getPayment(paymentType);

        return payment.pay(product, value);
    }


    public void addProduct(String id, Product product) {
        idToProduct.put(id, product);
    }
    public void removeProduct(String id) {
        idToProduct.remove(id);
    }
}

final class PaymentFactory {
    public static Payment getPayment(PaymentType paymentType) {
        switch (paymentType) {
            case CASH: return new CashPayment();
            case COIN: return new CoinPayment();
            case CARD: return new CardPayment();
            default:
                throw new IllegalArgumentException(
                        "Unsupported payment type.");
        }
    }
}


enum PaymentType {
    CASH,
    COIN,
    CARD
}

interface Payment {
    boolean pay(Product product, double value);
}

final class CashPayment implements Payment {
    @Override
    public boolean pay(Product product, double value) {
        // process cash payment
        return value >= product.getPrice();
    }
}

final class CoinPayment implements Payment {
    @Override
    public boolean pay(Product product, double value) {
        // process coin payment
        return value >= product.getPrice();
    }
}

final class CardPayment implements Payment {
    @Override
    public boolean pay(Product product, double value) {
        // process card payment
        return value >= product.getPrice();
    }
}

final class Product {
    private final String id;
    private final double price;
    private final String name;

    public Product(String id, float price, String name) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
