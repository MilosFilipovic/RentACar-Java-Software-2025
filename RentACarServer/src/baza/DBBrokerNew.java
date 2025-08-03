/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import klase.AbstractDomainObject;
import klase.Mesto;
import klase.Zaposleni;

/**
 *
 * @author Miloš
 */
public class DBBrokerNew {
    private static DBBrokerNew instance;

    private DBBrokerNew() {
    }

    public static DBBrokerNew getInstance() {

        if (instance == null) {
            instance = new DBBrokerNew();

        }

        return instance;
    }
    
    public Zaposleni login(String username, String password) {
        
        String upit = "SELECT * FROM zaposleni";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Zaposleni zaposleni = new Zaposleni(rs.getInt("idZaposleni"),
                        rs.getString("ime"), rs.getString("prezime"), rs.getString("lozinka"),
                        rs.getString("korisnickoIme"));

                if (zaposleni.getKorisnickoIme().equals(username)
                        && zaposleni.getLozinka().equals(password)) {
                    return zaposleni;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public List<AbstractDomainObject> selectList(AbstractDomainObject ado) throws Exception {

        String upit = "SELECT * FROM " + ado.tableName() + " " + ado.textJoin() + " " + ado.conditionForSelect();
        System.out.println(upit);
        Statement s = Konekcija.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(upit);
        return ado.getList(rs);

    }
    
    public int insert(AbstractDomainObject ado) throws Exception {

        int id = -1;

        String upit = "INSERT INTO " + ado.tableName() + " "
                + ado.insertColumns() + " VALUES(" + ado.insertValues() + ")";

        System.out.println(upit);
        Statement s = Konekcija.getInstance().getConnection().createStatement();
        s.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = s.getGeneratedKeys();

        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        s.close();

        return id;
    }

    
    public int delete(AbstractDomainObject ado) throws Exception {

        String upit = "DELETE FROM " + ado.tableName() + " WHERE " + ado.requiredCondition();
        System.out.println(upit);
        Statement s = Konekcija.getInstance().getConnection().createStatement();
        int affectedRows = s.executeUpdate(upit);
        return affectedRows;

    }

    public int update(AbstractDomainObject ado) throws SQLException {
        String upit = "UPDATE " + ado.tableName() + " SET "
                + ado.updateValues() + " WHERE " + ado.requiredCondition();
        System.out.println(upit);
        Statement s = Konekcija.getInstance().getConnection().createStatement();
        int affectedRows = s.executeUpdate(upit);
        return affectedRows;
    }

    public AbstractDomainObject selectObject(AbstractDomainObject ado) throws SQLException {
        String upit = " SELECT * FROM " + ado.tableName() + " " + ado.alies() + " "
                + ado.textJoin() + " " + " " + ado.getIdCondition();
        System.out.println(upit);
        Statement s = Konekcija.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(upit);
        if (rs.next()) {
            
            return ado.getAdo(rs);
        }
        return null;
    }

    
    
}
