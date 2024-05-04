package BookStore.DAO;

import BookStore.DTO.BookDTO;

import java.util.List;

public interface BookDAO {
    List<BookDTO> getAllBooks();
    BookDTO getBook(int id);
    void updatePriceBook(BookDTO book, int newPrice);
    void createBook(BookDTO book);
}
