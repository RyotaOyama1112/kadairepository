package service;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface TransactionOperation {
    void execute(Connection con) throws SQLException, ClassNotFoundException;
}
