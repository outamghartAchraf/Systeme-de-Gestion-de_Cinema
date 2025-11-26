package models;

public class Film {
    private int id;
    private String titre;
    private int duree;
    private String categorie;

    public Film(int id, String titre, int duree, String categorie) {
        this.id = id;
        this.titre = titre;
        this.duree = duree;
        this.categorie = categorie;
    }

    public int getId() { return id; }
    public String getTitre() { return titre; }
    public int getDuree() { return duree; }
    public String getCategorie() { return categorie; }

    public void setTitre(String titre) { this.titre = titre; }
    public void setDuree(int duree) { this.duree = duree; }
    public void setCategorie(String categorie) { this.categorie = categorie; }

    @Override
    public String toString() {
        return "Film { id=" + id +
                ", titre='" + titre + '\'' +
                ", duree=" + duree +
                ", categorie='" + categorie + '\'' +
                " }";
    }
}
