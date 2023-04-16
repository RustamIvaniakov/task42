package rustamivaniakov.home.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

public class H2DataSource implements Closeable {
    private final HikariDataSource dataSource;

    int maximumPoolSize = 3;
    int minimumIdle = 1;

    public H2DataSource(String baseName, String username, String password, String initBlock) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:h2:mem:" + baseName + ";DB_CLOSE_DELAY=-1;INIT=" + initBlock);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(maximumPoolSize);
        config.setMinimumIdle(minimumIdle);

        dataSource = new HikariDataSource(config);
    }
    public H2DataSource(String propertyFileName) {
        HikariConfig config = new HikariConfig(propertyFileName);

        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws RuntimeException {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        dataSource.close();
    }
}