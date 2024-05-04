package BookStore.BusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private int id;
    private String title;
    private String author;
    private double originalPrice;
    private double discount;

    public Book(int id, String title, double originalPrice, double discount) {
        this.id = id;
        this.title = title;
        this.originalPrice = originalPrice;
        this.discount = discount;
    }

    public double calculateDiscountedPrice() {
        return originalPrice - (originalPrice * discount / 100);
    }
}
