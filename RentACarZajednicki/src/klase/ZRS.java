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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Miloš
 */
public class ZRS extends AbstractDomainObject{

    
    private LocalDate datumRada;
    private int idZaposleni;
    private int idSmena;
    

    public ZRS() {
    }

    

    public ZRS(LocalDate datumRada, int idZaposleni, int idSmena) {
        
        this.datumRada = datumRada;
        this.idZaposleni = idZaposleni;
        this.idSmena = idSmena;
    }

    

    

    public int getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(int idSmena) {
        this.idSmena = idSmena;
    }

    

    public int getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
    }

    public LocalDate getDatumRada() {
        return datumRada;
    }

    public void setDatumRada(LocalDate datumRada) {
        this.datumRada = datumRada;
    }

    @Override
    public String tableName() {
        return "zrs";
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
        return "(datumRada,idZaposlen,idSmena)";
    }

    @Override
    public String insertValues() {
        return "'" + datumRada.toString() + "', " + idZaposleni + ", " + idSmena;

    }

    @Override
    public String updateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String requiredCondition() {
        return "datumRada = '" + datumRada + "' AND idZaposlen = " + idZaposleni + " AND idSmena = " + idSmena;
    }

    @Override
    public String conditionForSelect() {
        return "";
    }

    @Override
    public String getIdCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public AbstractDomainObject getAdo(ResultSet rs) {
        return null;
    }
    
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            
            int idS = rs.getInt("idSmena");
            LocalDate datum = rs.getObject("datumRada", LocalDate.class);
            int idZ = rs.getInt("idZaposlen");

            ZRS smena= new ZRS(datum, idZ, idS);


            lista.add(smena);
        }
        rs.close();
        return lista;
    }

    

    
    
}
