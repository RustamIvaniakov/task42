package rustamivaniakov.home.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rustamivaniakov.home.model.Booking;
import rustamivaniakov.home.model.Device;
import rustamivaniakov.home.model.User;
import rustamivaniakov.home.repository.BookingRepository;
import rustamivaniakov.home.repository.DeviceRepository;
import rustamivaniakov.home.repository.UserRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
public class BookingService {

    private BookingRepository bookingRepository;
    private UserRepository userRepository;
    private DeviceRepository deviceRepository;

    public Stream<Device> getAllDevices() {
        return Stream.empty();
    }

    public Stream<Device> getAvailableDevices() {
        return Stream.empty();
    }

    public Stream<Device> getBookedDevices() {
        return Stream.empty();
    }

    public Device getDeviceById(String deviceId) {
        return null;
    }

    public Device getDeviceByFonoId(String fonoId) {
        return null;
    }

    public boolean isDeviceAvailableByFonoId(String fonoId) {
        return false;
    }

    public boolean isDeviceAvailableByDeviceId(String deviceId) {
        log.debug("isDeviceAvailableByDeviceId: {} ", deviceId );
        boolean result = false;
        Optional<Booking> booking = bookingRepository.findByDeviceId(deviceId);
        if(booking.isPresent()) {
            if (booking.get().getEndTime().isAfter(Instant.now())) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Books a device for two weeks from now
     * @param userId
     * @param deviceId
     * @return deviceId
     * @throws Exception
     */
    public String bookDeviceByUserIdAndDeviceId(String userId, String deviceId) throws Exception {
        log.debug("bookDeviceByUserIdAndDeviceId: {}, {} ", userId, deviceId );
        Booking booking = new Booking();
        booking.setId(UUID.randomUUID().toString());
        booking.setUserId(userId);
        booking.setDeviceId(deviceId);
        Instant now = Instant.now();
        booking.setStartTime(now);
        booking.setEndTime(now.plus(Duration.ofDays(14)));
        return bookingRepository.save(booking).getId();
    }

    /**
     * Books a device for a given time period
     * @param userId
     * @param deviceId
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    public String bookDeviceByUserIdAndDeviceId(String userId, String deviceId, Instant startDate, Instant endDate) {
        log.debug("bookDeviceByUserIdAndDeviceId: {}, {}, {}, {} ", userId, deviceId, startDate, endDate);
        Booking booking = new Booking();
        booking.setId(UUID.randomUUID().toString());
        booking.setUserId(userId);
        booking.setDeviceId(deviceId);
        booking.setStartTime(startDate);
        booking.setEndTime(endDate);
        return bookingRepository.save(booking).getId();
    }

    public String returnDeviceByDeviceId(String deviceId) throws Exception  {
        log.debug("returnDeviceByDeviceId: {}",  deviceId);
        String result = null;
        Optional<Booking> booking = bookingRepository.findByDeviceId(deviceId);
        if (booking.isPresent()) {
            booking.get().setEndTime(Instant.now());
            result = bookingRepository.update(booking.get()).getId();
        }
        return result;
    }

    public Stream<Device> getBookedDevicesByUserId(String userId) {
        return Stream.empty();
    }

    /**
     * Returns a user who is holding a given device and nothing if end date is passed
     * @param deviceId
     * @return a User
     */
    public Optional<User> getWhoBookedByDeviceId(String deviceId) {
        log.debug("getWhoBookedByDeviceId: {}",  deviceId);
        Optional<User> result = Optional.empty();
        Optional<Booking> booking = bookingRepository.findByDeviceId(deviceId);
        if (booking.isPresent()) {
            if (booking.get().getEndTime().isAfter(Instant.now())){
                result = userRepository.findById(booking.get().getUserId());
            }
        }
        return result;
    }

}
