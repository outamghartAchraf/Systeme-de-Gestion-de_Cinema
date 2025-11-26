import models.Spectateur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpectateurDAO {

    public void ajouterSpectateur(Spectateur s) {
        String sql = "INSERT INTO spectateur VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, s.getId());
            ps.setString(2, s.getNom());
            ps.setString(3, s.getEmail());
            ps.executeUpdate();

            System.out.println("Spectateur ajoute ");
        } catch (Exception e) {
            System.out.println("Erreur ajout spectateur : " + e.getMessage());
        }
    }

    public List<Spectateur> getAllSpectateurs() {
        List<Spectateur> liste = new ArrayList<>();
        String sql = "SELECT * FROM spectateur";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Spectateur s = new Spectateur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email")
                );
                liste.add(s);
            }

        } catch (Exception e) {
            System.out.println("Erreur affichage spectateurs : " + e.getMessage());
        }

        return liste;
    }

    public boolean deleteSpectateur(int id) {

        String sql = "DELETE FROM spectateur WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {
            System.out.println("Erreur Delete spectateur : " + e.getMessage());
            return false;
        }
    }


    public boolean updateSpectateur(Spectateur s) {
        String sql = "UPDATE spectateur SET nom = ?, email = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.getNom());
            ps.setString(2, s.getEmail());
            ps.setInt(3, s.getId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Erreur update spectateur : " + e.getMessage());
            return false;
        }
    }


}
