package com.ouc;

import java.sql.*;

public class SQLiteCreate {

    public static void main(String args[] )
    {
        String sheet_sql = "CREATE TABLE sheet " +
                "(id INTEGER PRIMARY KEY  AUTOINCREMENT   NOT NULL," +
                " name           TEXT    NOT NULL, " +
                " dateCreated    TEXT    NOT NULL, " +
                " creatorId      CHAR(50), " +
                " picture        CHAR(50))";

        String music_sql = "CREATE TABLE music " +
                "(id INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL," +
                "name     TEXT    NOT NULL," +
                "sheetId INT NOT NULL," +
                "md5value TEXT  NOT NULL," +
                "namePath TEXT NOT NULL)";

        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:music.db");
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            stmt.executeUpdate(sheet_sql);
            stmt.executeUpdate(music_sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}
