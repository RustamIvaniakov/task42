package rustamivaniakov.home.repository;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import rustamivaniakov.home.db.H2DataSource;
import rustamivaniakov.home.model.User;

import java.sql.Connection;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private static final String pathToResource = BookingRepository.class.getClassLoader().getResource("test-datasource2.properties").getPath();
    private static Connection connection = new H2DataSource(pathToResource).getConnection();

    private final UserRepository userRepository = new UserRepository(connection);

    @Test
    void findById() {
        final String userId = "testUserId1";

        User user = new User();
        user.setId(userId);
        user.setName("TestUserId1");
        userRepository.save(user);

        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    void findAll() {
        Stream<User> userStream = userRepository.findAll();
        assertNotEquals(0, userStream.count());
    }

    @Test
    void delete() {
        final String userId = "testUserId";
        String deletedUserId = userRepository.delete(userId);
        Optional<User> userOptional = userRepository.findById(deletedUserId);
        if (userOptional.isPresent()) {
            assertTrue(!userOptional.get().isDeleted());
        }
    }
}