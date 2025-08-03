/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miloš
 */
public class StavkaRezervacije extends AbstractDomainObject {


    private int idrezervacije;
    private int rb;
    private String nazivStavke;
    private String napomena;
    private double cenaVozila;
    private int brojDana;
    private double iznosStavke;
    private int idVozilo;

    public StavkaRezervacije() {
    }

    public StavkaRezervacije(int idrezervacije, int rb, String nazivStavke, String napomena, double cenaVozila, int brojDana, double iznosStavke, int idVozilo) {
        this.idrezervacije = idrezervacije;
        this.rb = rb;
        this.nazivStavke = nazivStavke;
        this.napomena = napomena;
        this.cenaVozila = cenaVozila;
        this.brojDana = brojDana;
        this.iznosStavke = iznosStavke;
        this.idVozilo = idVozilo;
        
    }

    public double getIznosStavke() {
        return iznosStavke;
    }

    public void setIznosStavke(double iznosStavke) {
        this.iznosStavke = iznosStavke;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public String getNazivStavke() {
        return nazivStavke;
    }

    public void setNazivStavke(String nazivStavke) {
        this.nazivStavke = nazivStavke;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public double getCenaVozila() {
        return cenaVozila;
    }

    public void setCenaVozila(double cenaVozila) {
        this.cenaVozila = cenaVozila;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public int getIdVozilo() {
        return idVozilo;
    }

    public void setIdVozilo(int idVozilo) {
        this.idVozilo = idVozilo;
    }

    

    public int getIdrezervacije() {
        return idrezervacije;
    }

    public void setIdrezervacije(int idrezervacije) {
        this.idrezervacije = idrezervacije;
    }

    @Override
    public String tableName() {
        return "stavkarezervacije";
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
        return "(nazivStavke, napomena, cenaVozila, brojDana, iznosStavke, idVozilo, idRezervacije)";
    }

    @Override
    public String insertValues() {
        return "'" + nazivStavke + "', '" + napomena + "', " + cenaVozila +", " + brojDana + ", " + iznosStavke + ", " + idVozilo + ", " + idrezervacije;
    }

    @Override
    public String updateValues() {
        return "nazivStavke = '" + nazivStavke + "', napomena = '" + napomena + "', cenaVozila = " + cenaVozila + 
                ", brojDana = " + brojDana + ", iznosStavke = " + iznosStavke + ", idVozilo = " + idVozilo + ", idRezervacije = " + idrezervacije;
    }

    @Override
    public String requiredCondition() {
        return "rb = " + rb;
    }

    @Override
    public String conditionForSelect() {
        if (nazivStavke == null) {
            return "";
        }
        
        return " WHERE idRezervacije = " + idrezervacije;
    }

    @Override
    public String getIdCondition() {
        return " WHERE rb = " + rb;
    }

    @Override
    public AbstractDomainObject getAdo(ResultSet rs) {
        StavkaRezervacije sr = new StavkaRezervacije();
        try {
            
            
            sr = new StavkaRezervacije(rs.getInt("idRezervacije"), rs.getInt("rb"), rs.getString("nazivStavke"), rs.getString("napomena"),
                    rs.getDouble("cenaVozila"), rs.getInt("brojDana"), rs.getDouble("iznosStavke"), rs.getInt("idVozilo"));
        
        
        
            
        } catch (SQLException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sr;
    }
    
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            StavkaRezervacije sr = new StavkaRezervacije(rs.getInt("idRezervacije"), rs.getInt("rb"), rs.getString("nazivStavke"), rs.getString("napomena"),
                    rs.getDouble("cenaVozila"), rs.getInt("brojDana"), rs.getDouble("iznosStavke"), rs.getInt("idVozilo"));


            
            lista.add(sr);
        }
        rs.close();
        return lista;
    }

}
