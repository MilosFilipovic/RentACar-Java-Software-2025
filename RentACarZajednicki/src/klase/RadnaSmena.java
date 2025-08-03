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
public class RadnaSmena extends AbstractDomainObject{

    private int idSmena;
    private LocalTime pocetakSmene;
    private LocalTime krajSmene;
    

    public RadnaSmena() {
    }

    public RadnaSmena(int idSmena, LocalTime pocetakSmene, LocalTime krajSmene) {
        this.idSmena = idSmena;
        this.pocetakSmene = pocetakSmene;
        this.krajSmene = krajSmene;
    }

    public int getIdSmena() {
        return idSmena;
    }

    public void setIdSmena(int idSmena) {
        this.idSmena = idSmena;
    }

    public LocalTime getPocetakSmene() {
        return pocetakSmene;
    }

    public void setPocetakSmene(LocalTime pocetakSmene) {
        this.pocetakSmene = pocetakSmene;
    }

    public LocalTime getKrajSmene() {
        return krajSmene;
    }

    public void setKrajSmene(LocalTime krajSmene) {
        this.krajSmene = krajSmene;
    }

    @Override
    public String toString() {
        return String.valueOf(idSmena);
    }

    @Override
    public String tableName() {
        return "radnasmena";
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
        return "(pocetakSMene, krajSmene)";
    }

    @Override
    public String insertValues() {
        return "'" + pocetakSmene + "', '" + krajSmene +"'";
    }

    @Override
    public String updateValues() {
        return " pocetakSmene= '" + pocetakSmene + "', krajSmene = '" + krajSmene + "'";
    }

    @Override
    public String requiredCondition() {
        return "idSmena=" + idSmena;
    }

    @Override
    public String conditionForSelect() {
        if (pocetakSmene == null && krajSmene == null) {
            return "";
        }
        return " WHERE pocetakSmene= " + "'" + pocetakSmene + "'";
    }

    @Override
    public String getIdCondition() {
        return " WHERE idSmena= " + idSmena;
    }

    @Override
    public AbstractDomainObject getAdo(ResultSet rs) {
        RadnaSmena radnasmena = new RadnaSmena();
        try {
            radnasmena = new RadnaSmena(rs.getInt("idSmena"), rs.getObject("pocetakSmene", LocalTime.class), rs.getObject("krajSmene", LocalTime.class));
        } catch (SQLException ex) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return radnasmena;
    }
    
    @Override
    public ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {

            int idS = rs.getInt("idSmena");
            LocalTime pocetak = rs.getObject("pocetakSmene", LocalTime.class);
            LocalTime kraj = rs.getObject("krajSmene", LocalTime.class);

            
            RadnaSmena smenica = new RadnaSmena(idS, pocetak, kraj);


            lista.add(smenica);
        }
        rs.close();
        return lista;
    }

    

}
