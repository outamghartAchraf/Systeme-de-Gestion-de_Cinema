package models;

public class Ticket {
    private int numero;
    private Seance seance;
    private Spectateur spectateur;
    private double prix;

    public Ticket(int numero, Seance seance, Spectateur spectateur, double prix) {
        this.numero = numero;
        this.seance = seance;
        this.spectateur = spectateur;
        this.prix = prix;
    }

    public int getNumero() { return numero; }
    public Seance getSeance() { return seance; }
    public Spectateur getSpectateur() { return spectateur; }
    public double getPrix() { return prix; }

    public void setPrix(double prix) { this.prix = prix; }

    @Override
    public String toString() {
        return "Ticket { numero=" + numero +
                ", film=" + (seance != null ? seance.getFilm().getTitre() : "null") +
                ", spectateur=" + (spectateur != null ? spectateur.getNom() : "null") +
                ", prix=" + prix +
                " }";
    }
}
