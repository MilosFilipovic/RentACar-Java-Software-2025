/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.klijent;

import baza.DBBrokerNew;
import java.util.ArrayList;
import klase.AbstractDomainObject;
import klase.Klijent;
import klase.Mesto;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOPretraziKlijentaID extends AbstractSO{
    private ArrayList<Klijent> klijent = new ArrayList<>();
    private Klijent k = new Klijent();

    public Klijent getK() {
        return k;
    }

    
    

    public ArrayList<Klijent> getKlijente() {
        return klijent;
    }
    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof Klijent)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Klijent!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        AbstractDomainObject object;
        object=DBBrokerNew.getInstance().selectObject(ado);
        k=(Klijent) object;
        
        
//        AbstractDomainObject ado=(AbstractDomainObject) obj;
//        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
//        mesta=(ArrayList<Mesto>)(ArrayList<?>)lista;
    }
}
