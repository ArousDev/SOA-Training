package entities;

import java.util.Objects;

public class Module {
    private String matricule;
    private String nom;
    private int coefficient;
    private int volumeHoraire;
    private TypeModule type;
    private UniteEnseignement uniteEnseignement; // Référence vers l'UE associée
    public Module(){}
    // Constructeur
    public Module(String matricule, String nom, int coefficient, int volumeHoraire, TypeModule type) {
        this.matricule = matricule;
        this.nom = nom;
        this.coefficient = coefficient;
        this.volumeHoraire = volumeHoraire;
        this.type = type;
    }
    public Module(String matricule, String nom, int coefficient, int volumeHoraire, TypeModule type,UniteEnseignement uniteEnseignement) {
        this.matricule = matricule;
        this.nom = nom;
        this.coefficient = coefficient;
        this.volumeHoraire = volumeHoraire;
        this.type = type;
        this.uniteEnseignement=uniteEnseignement;
    }
    // Enumération TypeModule
    public enum TypeModule {
        TRANSVERSAL,
        PROFESSIONNEL,
        RECHERCHE
    }

    // Getters et Setters
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getVolumeHoraire() {
        return volumeHoraire;
    }

    public void setVolumeHoraire(int volumeHoraire) {
        this.volumeHoraire = volumeHoraire;
    }

    public TypeModule getType() {
        return type;
    }

    public void setType(TypeModule type) {
        this.type = type;
    }

    public UniteEnseignement getUniteEnseignement() {
        return uniteEnseignement;
    }

    public void setUniteEnseignement(UniteEnseignement uniteEnseignement) {
        this.uniteEnseignement = uniteEnseignement;
    }

    public boolean equals(Object object) {
        if (!(object instanceof Module)) return false;
        if (!super.equals(object)) return false;
        Module module = (Module) object;
        return coefficient == module.coefficient && volumeHoraire == module.volumeHoraire && java.util.Objects.equals(matricule, module.matricule) && java.util.Objects.equals(nom, module.nom) && type == module.type && java.util.Objects.equals(uniteEnseignement, module.uniteEnseignement);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), matricule, nom, coefficient, volumeHoraire, type, uniteEnseignement);
    }
}