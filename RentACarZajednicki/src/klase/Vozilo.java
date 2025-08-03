/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miloš
 */
public class Vozilo extends AbstractDomainObject{

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

    @Override
    public String tableName() {
        return "vozilo";
    }

    @Override
    public String alies() {
        return "";
    }

    @Override
    public String textJoin() {
        return "";
    }

    @Override
    public String insertColumns() {
        return "(model, cenaDana, karoserija, konjaza, kubikaza, boja)";
    }

    @Override
    public String insertValues() {
        return "'" + model + "', '" + cenaDana+"', '" + karoserija +"', '" + konjaza+"', '" + kubikaza +"', '" + boja +"'";
    }

    @Override
    public String updateValues() {
        return "model= '" + model + "', cenaDana= '" + cenaDana+"', karoserija= '" + karoserija +"', konjaza= '" + konjaza+"', kubikaza= '" + kubikaza +"', boja= '" + boja +"'";
    }

    @Override
    public String requiredCondition() {
        return "idVozilo=" + idVozilo;
    }

    @Override
    public String conditionForSelect() {
        if (model == null && cenaDana == 0 && karoserija == null && konjaza == null && kubikaza == null && boja == null) {
            return "";
        }
        return " WHERE model= " + "'" + model + "'";
    }

    @Override
    public String getIdCondition() {
        return " WHERE idVozilo= " + idVozilo;
    }

    @Override
    public AbstractDomainObject getAdo(ResultSet rs) {
        Vozilo v = new Vozilo();
        try {
            v = new Vozilo(rs.getInt("idVozilo"), rs.getString("model"), rs.getDouble("cenaDana"), rs.getString("karoserija"), rs.getString("konjaza"), rs.getString("kubikaza"), rs.getString("boja"));
        } catch (SQLException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return v;
    }
    
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            int idV = rs.getInt("idVozilo");
            String model = rs.getString("model");
            double cena = rs.getDouble("cenaDana");
            String karoserija = rs.getString("karoserija");
            String konjaza = rs.getString("konjaza");
            String kubikaza = rs.getString("kubikaza");
            String boja = rs.getString("boja");

            Vozilo v = new Vozilo(idV, model, cena, karoserija, konjaza, kubikaza, boja);

            lista.add(v);
        }
        rs.close();
        return lista;
    }
    
    
    
}
