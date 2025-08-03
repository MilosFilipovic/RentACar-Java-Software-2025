/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miloš
 */
public class Klijent extends AbstractDomainObject {

    private int idKlijent;
    private String ime;
    private String prezime;
    private String telefon;
    private int idMesta;

    public Klijent() {
    }

    public Klijent(int idKlijent, String ime, String prezime, String telefon, int idMesta) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.idMesta = idMesta;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
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

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getIdMesta() {
        return idMesta;
    }

    public void setIdMesta(int idMesta) {
        this.idMesta = idMesta;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public String tableName() {
        return "klijent";
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
        return "(ime, prezime, telefon, idMesta)";
    }

    @Override
    public String insertValues() {
        return "'" + ime + "', '" + prezime +"', '" + telefon +"', " + idMesta;
    }

    @Override
    public String updateValues() {
        return " ime= '" + ime + "', prezime= '" + prezime + "'," + " telefon= '" + telefon + "', idMesta= " + idMesta;
    }

    @Override
    public String requiredCondition() {
        return " idKlijent = " + idKlijent;
    }

    @Override
    public String conditionForSelect() {
        if (ime == null && prezime == null && telefon == null && idMesta == 0) {
            return "";
        }
        return " WHERE ime= " + "'" + ime + "'";
    }

    @Override
    public String getIdCondition() {
        return " WHERE idKlijent= "+idKlijent;
    }

    @Override
    public AbstractDomainObject getAdo(ResultSet rs) {
        Klijent k = new Klijent();
        try {
            k = new Klijent(rs.getInt("idKlijent"), rs.getString("ime"), rs.getString("prezime"), rs.getString("telefon"), rs.getInt("idMesta"));
        } catch (SQLException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return k;
    }
    
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            int idKlijent = rs.getInt("idKlijent");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String telefon = rs.getString("telefon");
            int idMesta = rs.getInt("idMesta");

            
            
            Klijent k = new Klijent(idKlijent, ime, prezime, telefon, idMesta);

            lista.add(k);
        }
        rs.close();
        return lista;
    }

}
