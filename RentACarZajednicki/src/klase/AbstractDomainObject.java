/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package klase;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Miloš
 */
public abstract class AbstractDomainObject implements Serializable {



    public abstract String tableName();

    public abstract String alies();

    public abstract String textJoin();

    public abstract String insertColumns();

    public abstract String insertValues();

    public abstract String updateValues();

    public abstract String requiredCondition();

    public abstract String conditionForSelect();

    public abstract String getIdCondition();
    
    public abstract AbstractDomainObject getAdo(ResultSet rs);

    public abstract ArrayList<AbstractDomainObject> getList(ResultSet rs) throws SQLException;
}
