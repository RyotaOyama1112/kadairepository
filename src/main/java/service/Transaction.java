package service;

import java.sql.Connection;
import java.sql.SQLException;

import DAO.DatabaseConnector;

public class Transaction {

    public static void performTransaction(TransactionOperation operation) {
        Connection con = null;

        try {
            con = DatabaseConnector.getConnection();
            con.setAutoCommit(false); // トランザクションの開始

            // トランザクション処理を実行
            operation.execute(con);

            con.commit(); // トランザクションのコミット
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                if (con != null) {
                    con.rollback(); // トランザクションのロールバック
                }
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
        } finally {
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (SQLException closeException) {
                closeException.printStackTrace();
            }
        }
    }
}
