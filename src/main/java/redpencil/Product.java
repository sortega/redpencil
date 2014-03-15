package redpencil;

public class Product {

    private static final Discount MINIMUM_DISCOUNT = Discount.of(5);
    private static final Discount MAXIMUM_DISCOUNT = Discount.of(30);

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
        if (!isDiscountInRange(discount)) {
            return Discount.none();
        }
        return discount;
    }

    private boolean isDiscountInRange(Discount discount) {
        return discount.compare(MINIMUM_DISCOUNT) >= 0 &&
                discount.compare(MAXIMUM_DISCOUNT) < 0;
    }
}
