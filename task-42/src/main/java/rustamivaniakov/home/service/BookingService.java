package rustamivaniakov.home.service;

import rustamivaniakov.home.model.Device;
import rustamivaniakov.home.model.User;

import java.util.stream.Stream;

public class BookingService {

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
        return false;
    }

    public String bookDeviceByUserIdAndDeviceId(String userId, String deviceId) throws Exception {
        return null;
    }

    public String returnDeviceByDeviceId(String deviceId) throws Exception  {
        return null;
    }

    public Stream<Device> getBookedDevicesByUserId(String userId) {
        return Stream.empty();
    }

    public User getWhoBookedByDeviceId(String deviceId) {
        return null;
    }

}
