/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import klase.StavkaRezervacije;

/**
 *
 * @author Miloš
 */
public class TabelaStavkiRezervacije extends AbstractTableModel{

    ArrayList<StavkaRezervacije> listaStavki;
    String[] kolone = {"RB", "Item", "Note", "Vehicle Price", "Days", "Item price"};
    
    public TabelaStavkiRezervacije(){
        listaStavki = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return listaStavki.size();
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
        StavkaRezervacije sr = listaStavki.get(rowIndex);
        
        switch (columnIndex) {
            case 0:
                return sr.getRb();
            case 1:

                return sr.getNazivStavke();
            case 2:
                return sr.getNapomena();
            case 3:
                return sr.getCenaVozila();
            case 4:
                return sr.getBrojDana();
            case 5:
                return sr.getIznosStavke();

            default:
                return "return!";
        }
    }

    public void setStavkeRez(ArrayList<StavkaRezervacije> stavke) {
        this.listaStavki=stavke;
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        listaStavki.remove(row);
        fireTableDataChanged();
    }
    
}
