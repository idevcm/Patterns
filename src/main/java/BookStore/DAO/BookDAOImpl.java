package BookStore.DAO;

import BookStore.DTO.BookDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    private Connection connection;

    public BookDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDTO> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                double price = resultSet.getDouble("price");

                BookDTO book = new BookDTO(id, title, author, price);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public BookDTO getBook(int id) {
        String sql = "SELECT * FROM books WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                double price = resultSet.getDouble("price");
                return new BookDTO(id, title, author, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("Book with id %d not found\n", id);
        return null;
    }

    @Override
    public void updatePriceBook(BookDTO book, int newPrice) {
        String sql = "UPDATE books SET price = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, newPrice);
            preparedStatement.setInt(2, book.getId());
            preparedStatement.executeUpdate();
            book.setDiscountedPrice(newPrice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createBook(BookDTO book) {
        String sql = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setDouble(3, book.getDiscountedPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
