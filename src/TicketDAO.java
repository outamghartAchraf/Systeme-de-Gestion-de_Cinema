import models.Film;
import models.Seance;
import models.Spectateur;
import models.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {

    public void ajouterTicket(Ticket t) {
        String sql = "INSERT INTO ticket VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, t.getNumero());
            ps.setInt(2, t.getSeance().getId());
            ps.setInt(3, t.getSpectateur().getId());
            ps.setDouble(4, t.getPrix());

            ps.executeUpdate();
            System.out.println("Ticket ajouté !");
        } catch (Exception e) {
            System.out.println("Erreur ajout ticket : " + e.getMessage());
        }
    }


    public List<Ticket> getAllTickets() {
        List<Ticket> liste = new ArrayList<>();

        String sql = """
                SELECT t.numero, t.prix,
                       s.id AS seance_id, s.horaire, s.salle, s.capacite_max,
                       f.id AS film_id, f.titre, f.duree, f.categorie,
                       sp.id AS spect_id, sp.nom, sp.email
                FROM ticket t
                JOIN seance s ON t.seance_id = s.id
                JOIN film f ON s.film_id = f.id
                JOIN spectateur sp ON t.spectateur_id = sp.id
                """;

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                // Build Film object
                Film film = new Film(
                        rs.getInt("film_id"),
                        rs.getString("titre"),
                        rs.getInt("duree"),
                        rs.getString("categorie")
                );

                // Build Séance object
                Seance seance = new Seance(
                        rs.getInt("seance_id"),
                        film,
                        rs.getString("horaire"),
                        rs.getString("salle"),
                        rs.getInt("capacite_max")
                );

                // Build Spectateur object
                Spectateur spect = new Spectateur(
                        rs.getInt("spect_id"),
                        rs.getString("nom"),
                        rs.getString("email")
                );

                // Build Ticket object
                Ticket ticket = new Ticket(
                        rs.getInt("numero"),
                        seance,
                        spect,
                        rs.getDouble("prix")
                );

                liste.add(ticket);
            }

        } catch (Exception e) {
            System.out.println("Erreur affichage tickets : " + e.getMessage());
        }

        return liste;
    }
}
