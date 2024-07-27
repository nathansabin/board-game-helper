package com.boardgame.boardGameHelper.dao;

import jakarta.annotation.Nullable;

import java.sql.*;

public class config {
    private Connection conn;
    private String connectStr = System.getenv("BOARDGAME_CONNECTION");

    @Nullable
    public Connection getConn(){
        try {
            this.conn = DriverManager.getConnection(this.connectStr);
            return conn;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}