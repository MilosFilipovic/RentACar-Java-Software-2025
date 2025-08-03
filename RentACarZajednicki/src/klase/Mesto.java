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
public class Mesto extends AbstractDomainObject {

    private int idMesta;
    private String nazivMesta;
    private String adresa;

    public Mesto() {
    }

    public Mesto(int idMesta, String nazivMesta, String adresa) {
        this.idMesta = idMesta;
        this.nazivMesta = nazivMesta;
        this.adresa = adresa;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNazivMesta() {
        return nazivMesta;
    }

    public void setNazivMesta(String nazivMesta) {
        this.nazivMesta = nazivMesta;
    }

    public int getIdMesta() {
        return idMesta;
    }

    public void setIdMesta(int idMesta) {
        this.idMesta = idMesta;
    }

    @Override
    public String toString() {
        return nazivMesta + ", " + adresa;
    }

    @Override
    public String tableName() {
        return "mesto";
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
        return "(nazivMesta, adresa)";
    }

    @Override
    public String insertValues() {
        return "'" + nazivMesta + "', '" + adresa+"'";
    }

    @Override
    public String updateValues() {
        return " nazivMesta= '" + nazivMesta + "', adresa = '" + adresa + "'";
    }

    @Override
    public String requiredCondition() {
        return "idMesta=" + idMesta;
    }

    @Override
    public String conditionForSelect() {
        if (nazivMesta == null && adresa == null) {
            return "";
        }
        return " WHERE nazivMesta= " + "'" + nazivMesta + "'";
    }

    @Override
    public String getIdCondition() {
        return " WHERE idMesta= " + idMesta;
    }

    @Override
    public AbstractDomainObject getAdo(ResultSet rs) {
        Mesto m = new Mesto();
        try {
            m = new Mesto(rs.getInt("idMesta"), rs.getString("nazivMesta"), rs.getString("adresa"));
        } catch (SQLException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return m;
    }
    
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            int idMesta = rs.getInt("idMesta");
            String nazivMesta = rs.getString("nazivMesta");
            String adresa = rs.getString("adresa");

            Mesto m = new Mesto(idMesta, nazivMesta, adresa);

            lista.add(m);
        }
        rs.close();
        return lista;
    }

}
