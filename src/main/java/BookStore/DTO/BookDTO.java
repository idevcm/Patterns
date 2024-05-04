package BookStore.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private int id;
    private String title;
    private String author;
    private double discountedPrice;

    public BookDTO(String title, String author, double discountedPrice) {
        this.title = title;
        this.author = author;
        this.discountedPrice = discountedPrice;
    }


    public BookDTO(int id, String title, String author, double discountedPrice) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}
