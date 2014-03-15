package redpencil;

public class Product {

    private Currency lastPrice;
    private Currency price;

    public Product(Currency initialPrice) {
        this.lastPrice = initialPrice;
        this.price = initialPrice;
    }

    public Currency currentPrice() {
        return price;
    }

    public void changePrice(Currency price) {
        this.lastPrice = this.price;
        this.price = price;
    }

    public Discount currentDiscount() {
        return this.price.discountFrom(this.lastPrice);
    }
}
