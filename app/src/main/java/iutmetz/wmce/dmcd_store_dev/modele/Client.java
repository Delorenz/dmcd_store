package iutmetz.wmce.dmcd_store_dev.modele;

public class Client {
//BdD : Client(id_client, nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays)
    private int id_client;
    private String nom;
    private String prenom;
    private String identifiant;
    private String mot_de_passe;
    private int adr_numero;
    private String adr_voie;
    private int adr_code_postal;
    private String adr_ville;
    private String adr_pays;

    public Client(int id_client, String nom, String prenom, String identifiant, String mot_de_passe, int adr_numero, String adr_voie, int adr_code_postal, String adr_ville, String adr_pays) {
        this.id_client = id_client;
        this.nom = nom;
        this.prenom = prenom;
        this.identifiant = identifiant;
        this.mot_de_passe = mot_de_passe;
        this.adr_numero = adr_numero;
        this.adr_voie = adr_voie;
        this.adr_code_postal = adr_code_postal;
        this.adr_ville = adr_ville;
        this.adr_pays = adr_pays;
    }

    public Client() {

    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public int getAdr_numero() {
        return adr_numero;
    }

    public void setAdr_numero(int adr_numero) {
        this.adr_numero = adr_numero;
    }

    public String getAdr_voie() {
        return adr_voie;
    }

    public void setAdr_voie(String adr_voie) {
        this.adr_voie = adr_voie;
    }

    public int getAdr_code_postal() {
        return adr_code_postal;
    }

    public void setAdr_code_postal(int adr_code_postal) {
        this.adr_code_postal = adr_code_postal;
    }

    public String getAdr_ville() {
        return adr_ville;
    }

    public void setAdr_ville(String adr_ville) {
        this.adr_ville = adr_ville;
    }

    public String getAdr_pays() {
        return adr_pays;
    }

    public void setAdr_pays(String adr_pays) {
        this.adr_pays = adr_pays;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id_client=" + id_client +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", identifiant='" + identifiant + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", adr_numero=" + adr_numero +
                ", adr_voie='" + adr_voie + '\'' +
                ", adr_code_postal=" + adr_code_postal +
                ", adr_ville='" + adr_ville + '\'' +
                ", adr_pays='" + adr_pays + '\'' +
                '}';
    }
}
