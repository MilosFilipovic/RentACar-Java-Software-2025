/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.radnasmena;

import baza.DBBrokerNew;
import java.util.ArrayList;
import java.util.List;
import klase.AbstractDomainObject;
import klase.RadnaSmena;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOGetListRS extends AbstractSO{
    private ArrayList<RadnaSmena> rs;

    public ArrayList<RadnaSmena> getRS() {
        return rs;
    }
    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof RadnaSmena)) {
            throw new Exception("Prosledjeni objekat nije instanca klase RS!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
        rs=(ArrayList<RadnaSmena>)(ArrayList<?>)lista;
    }
}
