package com.wora.common.utils;

import com.wora.common.infrastructure.persistence.SQLConsumer;
import com.wora.config.DatabaseConnection;

import java.sql.*;

public class QueryExecutor {
    private final static Connection CONNECTION = DatabaseConnection.getInstance().getConnection();

    private QueryExecutor() {
    }

    public static void executeUpdatePreparedStatement(final String query, final SQLConsumer<PreparedStatement> executor) {
        try (final PreparedStatement stmt = CONNECTION.prepareStatement(query)) {
            executor.accept(stmt);

            // after
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new RuntimeException("database operation failed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void executeQueryStatement(final String query, final SQLConsumer<ResultSet> executor) {
        try (final Statement stmt = CONNECTION.createStatement()) {
            final ResultSet resultSet = stmt.executeQuery(query);
            executor.accept(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeQueryPreparedStatement(final String query, final SQLConsumer<PreparedStatement> executor) {
        try (final PreparedStatement stmt = CONNECTION.prepareStatement(query)) {
            executor.accept(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
