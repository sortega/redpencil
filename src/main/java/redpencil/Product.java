package redpencil;

public class Product {

    private static final Discount MINIMUM_DISCOUNT = Discount.of(5);

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
        Discount discount = price.discountFrom(lastPrice);
        if (discount.compare(MINIMUM_DISCOUNT) < 0) {
            return Discount.none();
        }
        return discount;
    }
}
