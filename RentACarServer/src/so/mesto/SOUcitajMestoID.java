/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.mesto;

import baza.DBBrokerNew;
import java.util.ArrayList;
import java.util.List;
import klase.AbstractDomainObject;
import klase.Mesto;
import so.AbstractSO;

/**
 *
 * @author Miloš
 */
public class SOUcitajMestoID extends AbstractSO{
    private ArrayList<Mesto> mesta = new ArrayList<>();
    private Mesto m = new Mesto();

    public Mesto getM() {
        return m;
    }
    
    

    public ArrayList<Mesto> getMesta() {
        return mesta;
    }
    
    
    @Override
    protected void validate(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        if (ado==null || !(ado instanceof Mesto)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Mesto!");
        }
        
    }
    

    @Override
    protected void execute(Object obj) throws Exception {
        
        AbstractDomainObject ado=(AbstractDomainObject) obj;
        
        AbstractDomainObject object;
        object=DBBrokerNew.getInstance().selectObject(ado);
        m=(Mesto) object;
        
        
//        AbstractDomainObject ado=(AbstractDomainObject) obj;
//        List<AbstractDomainObject> lista=DBBrokerNew.getInstance().selectList(ado);
//        mesta=(ArrayList<Mesto>)(ArrayList<?>)lista;
    }
}
