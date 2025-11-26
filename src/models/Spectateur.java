package models;

public class Spectateur {
    private int id;
    private String nom;
    private String email;

    public Spectateur(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getEmail() { return email; }

    public void setNom(String nom) { this.nom = nom; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Spectateur { id=" + id +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                " }";
    }
}
