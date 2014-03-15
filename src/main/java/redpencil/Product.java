package redpencil;

public class Product {

    private final Currency price;

    public Product(Currency initialPrice) {
        this.price = initialPrice;
    }

    public Currency currentPrice() {
        return price;
    }
}
