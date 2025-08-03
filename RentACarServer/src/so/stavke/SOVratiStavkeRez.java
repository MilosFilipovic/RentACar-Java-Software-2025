/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.stavke;

import baza.DBBrokerNew;
import java.util.ArrayList;
import java.util.List;
import klase.AbstractDomainObject;
import klase.StavkaRezervacije;
import klase.Vozilo;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOVratiStavkeRez extends AbstractSO{
    private ArrayList<StavkaRezervacije> stavke;

    public ArrayList<StavkaRezervacije> getStavke() {
        return stavke;
    }

    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof StavkaRezervacije)) {
            throw new Exception("Prosledjeni objekat nije instanca klase stavke!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
        stavke=(ArrayList<StavkaRezervacije>)(ArrayList<?>)lista;
    }
}
