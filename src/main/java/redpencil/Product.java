package redpencil;

public class Product {

    private Currency price;

    public Product(Currency initialPrice) {
        this.price = initialPrice;
    }

    public Currency currentPrice() {
        return price;
    }

    public void changePrice(Currency price) {
        this.price = price;
    }

    public boolean currentDiscount() {
        return false;
    }
}
