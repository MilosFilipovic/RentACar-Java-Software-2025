/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.zrs;

import baza.DBBrokerNew;
import java.util.ArrayList;
import java.util.List;
import klase.AbstractDomainObject;
import klase.RadnaSmena;
import klase.ZRS;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOGetListZRS extends AbstractSO{
    private ArrayList<ZRS> zrs;

    public ArrayList<ZRS> getZRS() {
        return zrs;
    }
    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof ZRS)) {
            throw new Exception("Prosledjeni objekat nije instanca klase ZRS!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
        zrs=(ArrayList<ZRS>)(ArrayList<?>)lista;
    }

   
}
