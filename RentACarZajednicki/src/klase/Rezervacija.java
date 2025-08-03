/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miloš
 */
public class Rezervacija extends AbstractDomainObject {

    private int idRezervacija;
    private LocalDate datumPreuzimanja;
    private LocalDate datumVracanja;
    private double iznosRezervacije;
    private int idKlijent;
    private int idZaposleni;
    private Vozilo vozilo;

    public Rezervacija() {
    }

    public Rezervacija(int idRezervacija, LocalDate datumPreuzimanja, LocalDate datumVracanja, double iznosRezervacije, int idKlijent, int idZaposleni, Vozilo vozilo) {
        this.idRezervacija = idRezervacija;
        this.datumPreuzimanja = datumPreuzimanja;
        this.datumVracanja = datumVracanja;
        this.iznosRezervacije = iznosRezervacije;
        this.idKlijent = idKlijent;
        this.idZaposleni = idZaposleni;
        this.vozilo = vozilo;
    }

    

    public double getIznosRezervacije() {
        return iznosRezervacije;
    }

    public void setIznosRezervacije(double iznosRezervacije) {
        this.iznosRezervacije = iznosRezervacije;
    }

    public int getIdRezervacija() {
        return idRezervacija;
    }

    public void setIdRezervacija(int idRezervacija) {
        this.idRezervacija = idRezervacija;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public int getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public LocalDate getDatumPreuzimanja() {
        return datumPreuzimanja;
    }

    public void setDatumPreuzimanja(LocalDate datumPreuzimanja) {
        this.datumPreuzimanja = datumPreuzimanja;
    }

    public LocalDate getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(LocalDate datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    

    

    @Override
    public String tableName() {
        return "rezervacija";
    }

    @Override
    public String alies() {
        return "";
    }

    @Override
    public String textJoin() {
        if(vozilo!=null){
            return "JOIN stavkarezervacije sr ON rezervacija.idRezervacije = sr.idRezervacije\n"
                + "JOIN vozilo v ON sr.idVozilo = v.idVozilo";
        }
                
        return "";
    }

    @Override
    public String insertColumns() {
        return "(datumPreuzimanja, datumVracanja, iznosRezervacije, idZaposleni, idKlijent)";
    }

    @Override
    public String insertValues() {
        return "'" + datumPreuzimanja + "', '" + datumVracanja+"', " + iznosRezervacije +", " + idZaposleni+", " + idKlijent;
    }

    @Override
    public String updateValues() {
        return "datumPreuzimanja = '" + datumPreuzimanja + "', datumVracanja = '" + datumVracanja+"', iznosRezervacije = " + iznosRezervacije +", idZaposleni = " + idZaposleni+", idKlijent = " + idKlijent;
    }

    @Override
    public String requiredCondition() {
        return "idRezervacije=" + idRezervacija;
    }

    @Override
    public String conditionForSelect() {
        
        
        if (datumPreuzimanja == null && datumVracanja==null && iznosRezervacije==0 && idKlijent == -1 && idZaposleni != -1) {
            
            return " WHERE idZaposleni = " + idZaposleni;
        }else if(idKlijent != -1 && idKlijent != 0){
            return " WHERE idKlijent = " + idKlijent;
        }else if(datumPreuzimanja == null && datumVracanja==null && iznosRezervacije==0 && idKlijent == -1 && idZaposleni == -1){
            return "WHERE v.model LIKE '" + vozilo.getModel() +"'";
        }
        
        return "";
    }

    @Override
    public String getIdCondition() {
        return " WHERE idRezervacije = " + idRezervacija;
    }

    @Override
    public AbstractDomainObject getAdo(ResultSet rs) {
        Rezervacija r = new Rezervacija();
        try {
            
            
            r = new Rezervacija(rs.getInt("idRezervacije"), rs.getObject("datumPreuzimanja", LocalDate.class), rs.getObject("datumVracanja", LocalDate.class),
                    rs.getDouble("iznosRezervacije"), rs.getInt("idKlijent"), rs.getInt("idZaposleni"), null);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return r;
    }
    
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            LocalDate uzimanje = rs.getObject("datumPreuzimanja", LocalDate.class);
            LocalDate vracanje = rs.getObject("datumVracanja", LocalDate.class);


            Rezervacija r = new Rezervacija(rs.getInt("idRezervacije"), uzimanje, vracanje, rs.getDouble("iznosRezervacije"),
                    rs.getInt("idKlijent"), rs.getInt("idZaposleni"), null);
            lista.add(r);
        }
        rs.close();
        return lista;
    }

}
