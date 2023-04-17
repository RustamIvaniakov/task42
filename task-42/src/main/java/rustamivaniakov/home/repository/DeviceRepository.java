package rustamivaniakov.home.repository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import rustamivaniakov.home.model.Device;

import java.sql.Connection;
import java.sql.Statement;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
public class DeviceRepository {
    private Connection connection;

    
    public Device save(Device device) {
        try {
            Statement st = connection.createStatement();
            int result = st.executeUpdate( "insert into devices (id, name, technology, fonoId) " +
                    "values( '" + device.getId() +"', '" + device.getName() +"', '" + device.getTechnology() +"', null)");
            log.info("execution result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return device;
    }

    public Device update(Device device) {
        //TODO: TBD
        return device;
    }
    
    public Device findById(String id) {
        return null;
    }

    
    public Stream<Device> findAll() {
        return Stream.empty();
    }

    
    public String delete(Device device) {
        return null;
    }
}
