import models.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO {

    public void ajouterFilm(Film film) {
        String sql = "INSERT INTO film VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, film.getId());
            ps.setString(2, film.getTitre());
            ps.setInt(3, film.getDuree());
            ps.setString(4, film.getCategorie());
            ps.executeUpdate();

            System.out.println("Film ajouté !");
        } catch (Exception e) {
            System.out.println("Erreur ajout film : " + e.getMessage());
        }
    }

    public List<Film> getAllFilms() {
        List<Film> films = new ArrayList<>();
        String sql = "SELECT * FROM film";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                films.add(new Film(
                        rs.getInt("id"),
                        rs.getString("titre"),
                        rs.getInt("duree"),
                        rs.getString("categorie")
                ));
            }

        } catch (Exception e) {
            System.out.println("Erreur affichage films : " + e.getMessage());
        }

        return films;
    }
}
