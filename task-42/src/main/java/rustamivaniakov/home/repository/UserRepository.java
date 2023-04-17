package rustamivaniakov.home.repository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rustamivaniakov.home.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
public class UserRepository {

    @NonNull
    private Connection connection;

    private final AtomicLong nextId = new AtomicLong(1L);

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(String.valueOf(nextId.getAndAdd(1)));
        }
        try {
            Statement st = connection.createStatement();
            int result = st.executeUpdate( "insert into users(id, name) " +
                    "values( '" + user.getId() +"', '" + user.getName() + "')");
            log.info("execution result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    public User update(User user) {
        //TODO: TBD
        return user;
    }

    public Optional<User> findById(String userId) {
        Optional<User> result = Optional.empty();
        try {
            String SQL_QUERY = "SELECT * FROM users WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(SQL_QUERY);
            pst.setString(1, userId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                result = Optional.of(user);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return result;
    }

    public Stream<User> findAll() {
        Stream.Builder<User> streamBuilder = Stream.builder();
        try {
            String SQL_QUERY = "SELECT * FROM users";
            PreparedStatement pst = connection.prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                streamBuilder.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return streamBuilder.build();
    }

    public String delete(User user) {
        try {
            Statement st = connection.createStatement();
            int result = st.executeUpdate( "UPDATE users SET isDeleted = TRUE WHERE id = '" + user.getId() +"')");
            log.info("execution result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user.getId();
    }
    public String delete(String userId) {
        try {
            Statement st = connection.createStatement();
            int result = st.executeUpdate( "UPDATE users SET isDeleted = TRUE WHERE id = '" + userId +"'");
            log.info("execution result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return userId;
    }
}
