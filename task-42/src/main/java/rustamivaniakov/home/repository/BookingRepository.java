package rustamivaniakov.home.repository;

import lombok.AllArgsConstructor;
import rustamivaniakov.home.model.Booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Instant;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
public class BookingRepository {
    private Connection connection;

    public Booking save(Booking booking) throws RuntimeException {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO bookings " +
                    "VALUES ('" + booking.getId() + "'," +
                    "'" + booking.getUserId() + "', '" + booking.getDeviceId() + "', '" + booking.getStartTime() + "', '" + booking.getEndTime() + "')");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return booking;
    }

    public Booking update(Booking booking) throws RuntimeException {
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("UPDATE bookings\n" +
                    "SET userId = '" + booking.getUserId() + "', " +
                    "deviceId = '" + booking.getDeviceId() + "', " +
                    "startTime = '" + booking.getStartTime() + "', " +
                    "endTime = '" + booking.getEndTime() + "' " +
                    "WHERE id = '" + booking.getId() + "'" );
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return booking;
    }


    public Booking findById(String id) {
        //TODO: should be soft delete
        return null;
    }


    public Stream<Booking> findAll() {
        return Stream.empty();
    }


    public String delete(Booking booking) {
        return null;
    }


    public Stream<Booking> findByUserId(Long userId) {
        return Stream.empty();
    }


    public Optional<Booking> findByDeviceId(String deviceId) {
        Optional<Booking> result = Optional.empty();
        try {
            String SQL_QUERY = "SELECT *  FROM bookings WHERE deviceID = ?";
            PreparedStatement pst = connection.prepareStatement(SQL_QUERY);
            pst.setString(1, deviceId);
            ResultSet rs = pst.executeQuery();
            Booking booking = new Booking();
            while (rs.next()) {
                booking.setId(rs.getString("id"));
                booking.setUserId(rs.getString("userId"));
                booking.setDeviceId(rs.getString("deviceId"));
                booking.setStartTime(rs.getTimestamp("startTime").toInstant());
                booking.setEndTime(rs.getTimestamp("endTime").toInstant());
                result = Optional.of(booking);
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return result;
    }


    public Stream<Booking> findOverlappingBookings(Instant startTime, Instant endTime) {
        return Stream.empty();
    }

}
