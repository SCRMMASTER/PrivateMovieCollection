package DAL.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

public class DataBaseConnecter {
    private SQLServerDataSource dataSource;
    /**
     *Connector for the database.
     */
    public DataBaseConnecter(){
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("Private_Movie_Collection");
        dataSource.setUser("CSe22A_25");
        dataSource.setPassword("CSe22A_25");
        dataSource.setTrustServerCertificate(true);
    }

    /**
     * Establishes a connection to the database.
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }
}
