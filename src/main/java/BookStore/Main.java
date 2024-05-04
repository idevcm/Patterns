package BookStore;

import BookStore.DAO.BookDAO;
import BookStore.DAO.BookDAOImpl;
import BookStore.DTO.BookDTO;
import BookStore.DataSource.PostgresConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = PostgresConnection.getConnection();
        BookDAO bookDao = new BookDAOImpl(connection);
        List<BookDTO> books = bookDao.getAllBooks();
        for (BookDTO book : books) {
            System.out.printf("%s: %s | %.2f€\n", book.getId(), book.getTitle(), book.getDiscountedPrice());
        }

        System.out.println("----------------------------------------------------------------");

        BookDTO book = bookDao.getBook(9);
        System.out.printf("Book with id %d: %s | Precio: %.2f\n",
                book.getId(), book.getTitle(), book.getDiscountedPrice());

        bookDao.updatePriceBook(book, 5);
        System.out.printf("Book with id %s updated\nBook price is now %.2f\n",
                book.getId(), book.getDiscountedPrice());
        System.out.printf("Book with id %d: %s | Precio: %.2f\n",
                book.getId(), book.getTitle(), book.getDiscountedPrice());

        bookDao.createBook(new BookDTO("Lazarillo de Tormes", "Desconocido", 9.99));

        // Imprimir el ultimo libro creado
        System.out.println(books.get(books.size() - 1));
        connection.close();
    }
}
