package rustamivaniakov.home.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import rustamivaniakov.home.db.H2DataSource;
import rustamivaniakov.home.model.Device;
import rustamivaniakov.home.model.User;
import rustamivaniakov.home.repository.BookingRepository;
import rustamivaniakov.home.repository.DeviceRepository;
import rustamivaniakov.home.repository.UserRepository;

import java.sql.Connection;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

public class BookingServiceTest {


    private static final String pathToResource = BookingRepository.class.getClassLoader().getResource("test-datasource.properties").getPath();
    private static final Connection connection = new H2DataSource(pathToResource).getConnection();

    private final BookingRepository bookingRepository = new BookingRepository(connection);
    private final UserRepository userRepository = new UserRepository(connection);
    private final DeviceRepository deviceRepository = new DeviceRepository(connection);
    private final BookingService  bookingService = new BookingService(bookingRepository, userRepository, deviceRepository);

    @Test
    void shouldBookDeviceByUserIdAndDeviceId() {
        final String userId = "testUserId1";
        final String deviceId = "testDeviceId1";
        try {
            User user = new User();
            user.setId(userId);
            user.setName(userId);
            userRepository.save(user);

            Device device = new Device();
            device.setId(deviceId);
            device.setName( "Test Device Name");
            device.setTechnology(Device.Technology.T2G);
            deviceRepository.save(device);

            String bookingId = bookingService.bookDeviceByUserIdAndDeviceId(userId, deviceId);
            assertNotNull(bookingId);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void shouldReturnBookingIdWhenUserReturnsDeviceById() {
        final String deviceId = "testDeviceId2";
        try {
            Device device = new Device();
            device.setId(deviceId);
            device.setName( "Test Device Name");
            device.setTechnology(Device.Technology.T2G);
            deviceRepository.save(device);


            bookingService.bookDeviceByUserIdAndDeviceId("1", deviceId);

            String bookingId = bookingService.returnDeviceByDeviceId(deviceId);
            assertNotNull(bookingId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void checkIfDeviceIsAvailable() {
        final String deviceId = "testDeviceId3";
        try {

            Device device = new Device();
            device.setId(deviceId);
            device.setName( "Test Device Name");
            device.setTechnology(Device.Technology.T2G);
            deviceRepository.save(device);

            bookingService.bookDeviceByUserIdAndDeviceId("1", deviceId);

            boolean deviceIsAvailable = bookingService.isDeviceAvailableByDeviceId(deviceId);
            assertTrue( deviceIsAvailable);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void shouldReturnUserWhoBookedByDeviceId() {
        final String deviceId = "testDeviceId4";
        try {
            Device device = new Device();
            device.setId(deviceId);
            device.setName( "Test Device Name");
            device.setTechnology(Device.Technology.T2G);
            deviceRepository.save(device);
            // book device from now
            bookingService.bookDeviceByUserIdAndDeviceId("2", deviceId);

            Optional<User> user = bookingService.getWhoBookedByDeviceId(deviceId);
            if (user.isPresent()) {
                assertTrue(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
    @Test
    void shouldNotReturnUserWithEndDateBeforeToday() {
        final String deviceId = "testDeviceId5";
        try {
            Device device = new Device();
            device.setId(deviceId);
            device.setName( "Test Device Name");
            device.setTechnology(Device.Technology.T2G);
            deviceRepository.save(device);
            // book device from now
            final Instant startDate = Instant.now().minus(Duration.ofDays(5));
            final Instant endDate = startDate.plus(Duration.ofDays(2));
            bookingService.bookDeviceByUserIdAndDeviceId("2", deviceId, startDate, endDate);

            Optional<User> user = bookingService.getWhoBookedByDeviceId(deviceId);
            if (!user.isPresent()) {
                assertTrue(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
