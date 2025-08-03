/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.radnasmena;

import baza.DBBrokerNew;
import java.util.ArrayList;
import klase.AbstractDomainObject;
import klase.RadnaSmena;
import klase.Vozilo;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOPretraziRSID extends AbstractSO{
    private ArrayList<RadnaSmena> rslista = new ArrayList<>();
    private RadnaSmena rs = new RadnaSmena();

    public ArrayList<RadnaSmena> getRslista() {
        return rslista;
    }

    public RadnaSmena getRs() {
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
        
        AbstractDomainObject object;
        object=DBBrokerNew.getInstance().selectObject(ado);
        rs=(RadnaSmena) object;
        
        
//        AbstractDomainObject ado=(AbstractDomainObject) obj;
//        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
//        mesta=(ArrayList<Mesto>)(ArrayList<?>)lista;
    }
}
