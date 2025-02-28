/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.io.Serializable;

/**
 *
 * @author Miloš
 */
public class Vozilo implements Serializable{
    private int idVozilo;
    private String model;
    private double cenaDana;
    private String karoserija;
    private String konjaza;
    private String kubikaza;
    private String boja;
    

    public Vozilo() {
    }

    public Vozilo(int idVozilo, String model, double cenaDana, String karoserija, String konjaza, String kubikaza, String boja) {
        this.idVozilo = idVozilo;
        this.model = model;
        this.cenaDana = cenaDana;
        this.karoserija = karoserija;
        this.konjaza = konjaza;
        this.kubikaza = kubikaza;
        this.boja = boja;
    }

    

    public double getCenaDana() {
        return cenaDana;
    }

    public void setCenaDana(double cenaDana) {
        this.cenaDana = cenaDana;
    }

    public int getIdVozilo() {
        return idVozilo;
    }

    public void setIdVozilo(int idVozilo) {
        this.idVozilo = idVozilo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKaroserija() {
        return karoserija;
    }

    public void setKaroserija(String karoserija) {
        this.karoserija = karoserija;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public String getKonjaza() {
        return konjaza;
    }

    public void setKonjaza(String konjaza) {
        this.konjaza = konjaza;
    }

    public String getKubikaza() {
        return kubikaza;
    }

    public void setKubikaza(String kubikaza) {
        this.kubikaza = kubikaza;
    }

    @Override
    public String toString() {
        return model;
    }
    
    
    
}
