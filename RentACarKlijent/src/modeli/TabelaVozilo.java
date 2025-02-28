/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import klase.Vozilo;

/**
 *
 * @author Miloš
 */
public class TabelaVozilo extends AbstractTableModel{

    ArrayList<Vozilo> listaVozila;
    String[] kolone = {"ID", "Model", "Cost per day", "Type", "Horse power", "Cm3", "Color"};
    
    @Override
    public int getRowCount() {
        return listaVozila.size();
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
        Vozilo v = listaVozila.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return v.getIdVozilo();
            case 1:
                return v.getModel();
            case 2:
                return v.getCenaDana();
            case 3:
                return v.getKaroserija();
            case 4:
                return v.getKonjaza();
            case 5:
                return v.getKubikaza();
            case 6:
                return v.getBoja();
                

            default:
                return "return!";
        }
    }

    public void setVozilo(ArrayList<Vozilo> vozilo) {
        this.listaVozila = vozilo;
        fireTableDataChanged();
    }

    public void obrisiVozilo(int row) {
        listaVozila.remove(row);
        fireTableDataChanged();
    }

    public void setPretragaVozila(ArrayList<Vozilo> pretraziV) {
        this.listaVozila = pretraziV;
        fireTableDataChanged();
    }
    
}
