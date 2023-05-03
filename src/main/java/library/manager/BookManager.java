package library.manager;
import library.db.DBConnectionProvider;
import library.model.Author;
import library.model.Book;
import library.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class BookManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    private final AuthorManager authorManager = new AuthorManager();
    private final UserManager userManager = new UserManager();
    public void add(Book book) {
        String sql = "Insert into book (title,description,price,author_id,book_pic,user_id) Values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.setString(5, book.getBookPic());
            ps.setInt(6, book.getUser().getId());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                book.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removeBookById(int id) {
        String sql = "delete from book where id =" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Book> getAll() {
        String sql = "SELECT * From book";
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setId(resultSet.getInt("id"));
        book.setTitle((resultSet.getString("title")));
        book.setDescription((resultSet.getString("description")));
        book.setPrice(resultSet.getDouble("price"));
        int authorId = resultSet.getInt("author_id");
        Author author = authorManager.getById(authorId);
        book.setAuthor(author);
        book.setBookPic(resultSet.getString("book_pic"));
        int userId = resultSet.getInt("user_id");
        User user = userManager.getById(userId);
        book.setUser(user);
        return book;
    }
    public Book getById(int bookId) {
        String sql = "SELECT * From book where id = " + bookId;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getBookFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void edit(Book book) {
        String sql = "update book set title=?,description=?,price=?,author_id=?,book_pic=?,user_id=? where  id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setDouble(3, book.getPrice());
            ps.setInt(4, book.getAuthor().getId());
            ps.setString(5, book.getBookPic());
            ps.setInt(6, book.getUser().getId());
            ps.setInt(7, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Book> getAllBYSearchedName(String searchedName) {
        String sql = "SELECT * From book where title =" + "'" + searchedName + "'";
        List<Book> books = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                books.add(getBookFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
