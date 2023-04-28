package library.manager;
import library.db.DBConnectionProvider;
import library.model.Role;
import library.model.User;
import java.sql.*;
import java.text.ParseException;
public class UserManager {
    private final Connection connection = DBConnectionProvider.getInstance().getConnection();
    public void add(User user) {
        String sql = "Insert into user (name,surname,email,password,user_pic,role) Values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getUserPic());
            ps.setString(6, user.getRole().name());
            ps.executeUpdate();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private User getUserFromResultSet(ResultSet resultSet) throws SQLException, ParseException {
        User user = User.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email((resultSet.getString("email")))
                .password((resultSet.getString("password")))
                .userPic((resultSet.getString("user_pic")))
                .role(Role.valueOf((resultSet.getString("role"))))
                .build();
        return user;
    }
    public User getUserByEmailAndPassword(String useremail, String password) {
        String sql = "SELECT * From user where email = ? and password = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, useremail);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User getUserByEmail(String useremail) {
        String sql = "SELECT * From user where email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, useremail);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
