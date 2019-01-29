package iutmetz.wmce.dmcd_store_dev.modele;

public class Taille {
    private int id_taille;
    private String libelle;

    public Taille(int id_produit, String libelle) {
        this.id_taille = id_produit;
        this.libelle = libelle;
    }

    public int getId_produit() {
        return id_taille;
    }

    public void setId_produit(int id_produit) {
        this.id_taille = id_produit;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return this.libelle;
    }
}
