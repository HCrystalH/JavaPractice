package org.example.config;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInitializer {
    public static void init(){
        String sql =
                """
                CREATE TABLE employees(
                    id INT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    age INT NOT NULL,
                    department VARCHAR(100) NOT NULL,
                    salary BIGINT NOT NULL,
                )
                """;

        try(Connection connection = Database.getConnection();
            Statement statement = connection.createStatement()
        ){
            statement.execute(sql);
        } catch (SQLException e){
            throw new RuntimeException("Failed to initialize database",e);
        }
    }
}
