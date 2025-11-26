package models;

import java.util.ArrayList;
import java.util.List;

public class Seance {
    private int id;
    private Film film;
    private String horaire;
    private String salle;
    private int capaciteMax;


    private List<Spectateur> spectateurs = new ArrayList<>();

    public Seance(int id, Film film, String horaire, String salle, int capaciteMax) {
        this.id = id;
        this.film = film;
        this.horaire = horaire;
        this.salle = salle;
        this.capaciteMax = capaciteMax;
    }

    public int getId() { return id; }
    public Film getFilm() { return film; }
    public String getHoraire() { return horaire; }
    public String getSalle() { return salle; }
    public int getCapaciteMax() { return capaciteMax; }

    public void setFilm(Film film) { this.film = film; }
    public void setHoraire(String horaire) { this.horaire = horaire; }
    public void setSalle(String salle) { this.salle = salle; }
    public void setCapaciteMax(int capaciteMax) { this.capaciteMax = capaciteMax; }

    public boolean ajouterSpectateur(Spectateur s) {
        if (spectateurs.size() < capaciteMax) {
            spectateurs.add(s);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Seance { id=" + id +
                ", film=" + (film != null ? film.getTitre() : "null") +
                ", horaire='" + horaire + '\'' +
                ", salle='" + salle + '\'' +
                ", capaciteMax=" + capaciteMax +
                " }";
    }
}
