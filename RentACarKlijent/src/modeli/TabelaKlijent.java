/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import klase.Klijent;

/**
 *
 * @author Miloš
 */
public class TabelaKlijent extends AbstractTableModel{

    ArrayList<Klijent> listaKlijent;
    String[] kolone = {"ID", "Name", "Surname", "Phone", "PlaceID"};
    
    public TabelaKlijent(){
        listaKlijent=new ArrayList<>();
        
    }
    
    
    @Override
    public int getRowCount() {
        return listaKlijent.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = listaKlijent.get(rowIndex);

                
        switch (columnIndex) {
            case 0:
                
                return k.getIdKlijent();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getTelefon();
            case 4:
                return k.getIdMesta();
            
                

            default:
                return "return!";
        }
    }

    public void setZaposleni(ArrayList<Klijent> klijent) {
        this.listaKlijent = klijent;
        fireTableDataChanged(); // Obaveštava JTable da osveži prikaz
    }

    public void obrisiKlijenta(int row) {
        listaKlijent.remove(row);
        fireTableDataChanged();
    }

    public void setPretragaKlijent(ArrayList<Klijent> pretraziK) {
        this.listaKlijent = pretraziK;
        fireTableDataChanged();
    }

    
    
    
    
}
