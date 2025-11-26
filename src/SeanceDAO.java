import models.Film;
import models.Seance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeanceDAO {

    public void ajouterSeance(Seance seance) {
        String sql = "INSERT INTO seance VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, seance.getId());
            ps.setInt(2, seance.getFilm().getId());
            ps.setString(3, seance.getHoraire());
            ps.setString(4, seance.getSalle());
            ps.setInt(5, seance.getCapaciteMax());

            ps.executeUpdate();
            System.out.println("Séance ajoutée !");

        } catch (Exception e) {
            System.out.println("Erreur ajout séance : " + e.getMessage());
        }
    }

    public List<Seance> getAllSeances() {
        List<Seance> liste = new ArrayList<>();

        String sql = "SELECT seance.*, film.titre, film.duree, film.categorie " +
                "FROM seance " +
                "JOIN film ON seance.film_id = film.id";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                // Create Film object from JOIN
                Film film = new Film(
                        rs.getInt("film_id"),
                        rs.getString("titre"),
                        rs.getInt("duree"),
                        rs.getString("categorie")
                );

                // Create Seance object
                Seance se = new Seance(
                        rs.getInt("id"),
                        film,
                        rs.getString("horaire"),
                        rs.getString("salle"),
                        rs.getInt("capacite_max")
                );

                liste.add(se);
            }

        } catch (Exception e) {
            System.out.println("Erreur affichage séances : " + e.getMessage());
        }

        return liste;
    }
}
