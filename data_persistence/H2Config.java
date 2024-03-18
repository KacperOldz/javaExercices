package pl.clockworkjava.advanced.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Config {
    private static String h2Driver = "org.h2.Driver";
    private static String h2Addr = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static String user = "";
    private static String password = "";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection conn = getDBConnection();
        if(conn != null) {
            System.out.println("Dziala");
        }
    }

    private static Connection getDBConnection() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Class.forName(h2Driver);
        connection = DriverManager.getConnection(h2Addr, user, password);

        return connection;
    }

}

/*
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>pl.clockworkjava</groupId>
    <artifactId>advanced</artifactId>
    <version>1.0-SNAPSHOT</version>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <dependencies>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.200</version>
        </dependency>
    </dependencies>
</project>
 */
