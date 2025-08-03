/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.vozilo;

import baza.DBBrokerNew;
import klase.AbstractDomainObject;
import klase.Vozilo;
import klase.Zaposleni;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOIzmeniVozilo extends AbstractSO{
    private int affectedRows;

    public int getAffectedRows() {
        return affectedRows;
    }
    
   
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (!(ado instanceof Vozilo)) {
            throw new Exception("Prosledjeni objekat nije instanca klase vozilo!");
        }
        

        
    }

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        affectedRows=DBBrokerNew.getInstance().update(ado);
        
        
    }
}
