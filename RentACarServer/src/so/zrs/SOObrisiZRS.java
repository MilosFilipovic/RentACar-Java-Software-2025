/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.zrs;

import baza.DBBrokerNew;
import klase.AbstractDomainObject;
import klase.ZRS;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOObrisiZRS extends AbstractSO{
    int affectedRows;

    public int getAffectedRows() {
        return affectedRows;
    }
    
    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        if (!(ado instanceof ZRS)) {
            throw new Exception("Prosledjeni objekat nije instanca klase zrs!");
        }
    }

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        affectedRows=DBBrokerNew.getInstance().delete(ado);
        
    }
}
