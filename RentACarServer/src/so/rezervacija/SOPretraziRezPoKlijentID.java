/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.rezervacija;

import baza.DBBrokerNew;
import java.util.ArrayList;
import java.util.List;
import klase.AbstractDomainObject;
import klase.Rezervacija;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOPretraziRezPoKlijentID extends AbstractSO{
    private ArrayList<Rezervacija> reeze;

    public ArrayList<Rezervacija> getReeze() {
        return reeze;
    }

    
    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof Rezervacija)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Rez!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
        reeze=(ArrayList<Rezervacija>)(ArrayList<?>)lista;
    }
}
