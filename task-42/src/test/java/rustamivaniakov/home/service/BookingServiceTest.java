package rustamivaniakov.home.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import rustamivaniakov.home.model.User;

public class BookingServiceTest {

    private final BookingService  bookingService = new BookingService();

    @Test
    void shouldBookDeviceByUserIdAndDeviceId() {
        final String userId = "testUserId";
        final String deviceId = "testDeviceId";
        try {
            String bookingId = bookingService.bookDeviceByUserIdAndDeviceId(userId, deviceId);
            assertNotNull(bookingId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldReturnBookingIdWhenUserReturnsDeviceById() {
        final String deviceId = "testDeviceId";
        try {
            String bookingId = bookingService.returnDeviceByDeviceId(deviceId);
            assertNotNull(bookingId);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void checkIfDeviceIsAvailable() {
        final String deviceId = "testDeviceId";
        try {
            boolean deviceIsAvailable = bookingService.isDeviceAvailableByDeviceId(deviceId);
            assertTrue( deviceIsAvailable);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    void shouldReturnUserWhoBookedByDeviceId() {
        final String deviceId = "testDeviceId";
        try {
            User user = bookingService.getWhoBookedByDeviceId(deviceId);
            assertNotNull(user);
        } catch (Exception e) {
            fail();
        }
    }
}
