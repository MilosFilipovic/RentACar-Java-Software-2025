/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miloš
 */
public class Zaposleni extends AbstractDomainObject{

    private int idZaposleni;
    private String ime;
    private String prezime;
    private String lozinka;
    private String korisnickoIme;

    public Zaposleni() {
    }

    public Zaposleni(int idZaposleni, String ime, String prezime, String lozinka, String korisnickoIme) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.lozinka = lozinka;
        this.korisnickoIme = korisnickoIme;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public int getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return korisnickoIme;
    }

    @Override
    public String tableName() {
        return "zaposleni";
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
        return "(ime, prezime, lozinka, korisnickoIme)";
    }

    @Override
    public String insertValues() {
        return "'" + ime + "', '" + prezime +"', '" + lozinka +"', '" + korisnickoIme+"'";
    }

    @Override
    public String updateValues() {
        return "ime= '" + ime + "', prezime= '" + prezime + "', korisnickoIme= '" + korisnickoIme + "'";

    }

    @Override
    public String requiredCondition() {
        return "idZaposleni=" + idZaposleni;
    }

    @Override
    public String conditionForSelect() {
        if (ime == null && prezime == null && lozinka == null && korisnickoIme == null) {
            return "";
        }
        return " WHERE ime= " + "'" + ime + "'";
    }

    @Override
    public String getIdCondition() {
        return " WHERE idZaposleni = " + idZaposleni;
    }

    @Override
    public AbstractDomainObject getAdo(ResultSet rs) {
        Zaposleni z = new Zaposleni();
        try {
            z = new Zaposleni(rs.getInt("idZaposleni"), rs.getString("ime"), rs.getString("prezime"), rs.getString("lozinka"), rs.getString("korisnickoIme"));
        } catch (SQLException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return z;
    }
    
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            int idZ = rs.getInt("idZaposleni");
            String ime = rs.getString("ime");
            
            String prezime = rs.getString("prezime");
            String lozinka = rs.getString("lozinka");
            String korisnickoIme = rs.getString("korisnickoIme");
            

            Zaposleni z = new Zaposleni(idZ, ime, prezime, lozinka, korisnickoIme);

            lista.add(z);
        }
        rs.close();
        return lista;
    }
    
    
}
