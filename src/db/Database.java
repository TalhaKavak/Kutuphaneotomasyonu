package db;

import models.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String DB_URL = "jdbc:sqlite:resources/library.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void initialize() {
        String userTable = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT NOT NULL, password TEXT NOT NULL, role TEXT NOT NULL)";
        String bookTable = "CREATE TABLE IF NOT EXISTS books (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, author TEXT NOT NULL, year INTEGER, isbn TEXT, status TEXT)";
        String memberTable = "CREATE TABLE IF NOT EXISTS members (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, memberType TEXT NOT NULL)";
        String loanTable = "CREATE TABLE IF NOT EXISTS loans (id INTEGER PRIMARY KEY AUTOINCREMENT, bookId INTEGER NOT NULL, memberId INTEGER NOT NULL, loanDate TEXT, returnDate TEXT)";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(bookTable);
            stmt.execute(memberTable);
            stmt.execute(loanTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addUser(String username, String password, String role) {
        String sql = "INSERT INTO users(username, password, role) VALUES(?,?,?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static User authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        User user = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    

    public static void updateUser(int id, String username, String password, String role) {
        String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, role);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return users;
    }

    public static User getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        User user = null;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("role"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }
}
