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

    private AtomicLong nextId = new AtomicLong(1L);

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(String.valueOf(nextId.getAndAdd(1)));
        }
        try {
            Statement st = connection.createStatement();
            int result = st.executeUpdate( "insert into users values( '" + user.getId() +"', '" + user.getName() + "')");
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
            User user = new User();
            while (rs.next()) {
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
        return Stream.empty();
    }

    public String delete(User user) {
        return null;
    }
}
