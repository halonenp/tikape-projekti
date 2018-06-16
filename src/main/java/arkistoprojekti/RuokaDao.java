package arkistoprojekti;

import java.util.*;
import java.sql.*;

public class RuokaDao implements Dao<Ruoka, Integer> {

    private Database database;

    public RuokaDao(Database database) {
        this.database = database;
    }

    @Override
    public Ruoka findOne(Integer key) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT id, ruoannimi FROM Ruoka WHERE id = ?");
            stmt.setInt(1, key);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                return new Ruoka(result.getInt("id"), result.getString("ruoannimi"));
            }
        }

        return null;
    }

    @Override
    public List<Ruoka> findAll() throws SQLException {
        List<Ruoka> ruoat = new ArrayList<>();

        try (Connection conn = database.getConnection();
                ResultSet result = conn.prepareStatement("SELECT id, ruoannimi FROM Ruoka").executeQuery()) {

            while (result.next()) {
                ruoat.add(new Ruoka(result.getInt("id"), result.getString("ruoannimi")));
            }
        }

        return ruoat;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Ruoka WHERE id = ?");

        stmt.setInt(1, key);
        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    public Ruoka saveOrUpdate(Ruoka object) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO TaskAssignment (ruoannimi) VALUES (?)");
            stmt.setString(1, object.getRuoannimi());

            stmt.executeUpdate();
        }

        return null;
    }

    @Override
    public void save(Ruoka ruoka) throws SQLException {

        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Ruoka"
                + " (ruoannimi)"
                + " VALUES (?)");
        stmt.setString(1, ruoka.getRuoannimi());

        stmt.executeUpdate();
        stmt.close();

        stmt = conn.prepareStatement("SELECT * FROM Ruoka"
                + " WHERE ruoannimi = ?");
        stmt.setString(1, ruoka.getRuoannimi());

        ResultSet rs = stmt.executeQuery();
        rs.next();

        Ruoka a = new Ruoka(rs.getInt("id"), rs.getString("ruoannimi"));

        stmt.close();
        rs.close();

        conn.close();

    }

    @Override
    public void update(Ruoka ruoka) throws SQLException {

        throw new UnsupportedOperationException("Not supported yet.");
    }
}
