package rustamivaniakov.home;

import lombok.extern.slf4j.Slf4j;
import rustamivaniakov.home.db.H2DataSource;

import java.sql.Connection;

@Slf4j
public class Task42 {

    private Connection connection;

    public Task42(Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) {
        try (H2DataSource h2DataSource
                     = new H2DataSource(
                "task42",
                "sa",
                "",
                "runscript from 'classpath:/db.sql'")) {
            Task42 task42 = new Task42(h2DataSource.getConnection());
            task42.start();
        } catch (Exception e) {
            log.error("Something went wrong", e);
        }
    }

    public void start() {

    }
}