/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import klase.ZRS;

/**
 *
 * @author Miloš
 */
public class TabelaZRS extends AbstractTableModel{
    ArrayList<ZRS> listaZRS;
    String[] kolone = {"Date", "EmployeeID", "ShiftID"};
    
    
    

    public TabelaZRS() {
        listaZRS = new ArrayList<>();
        
    }

    @Override
    public int getRowCount() {
        return listaZRS.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ZRS rs = listaZRS.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return rs.getDatumRada();
            case 1:
                return rs.getIdZaposleni();
            case 2:
                return rs.getIdSmena();
            
                

            default:
                return "return!";
        }
    }
    
    public void setZRSmena(ArrayList<ZRS> zrs) {
        this.listaZRS = zrs;
        fireTableDataChanged(); // Obaveštava JTable da osveži prikaz
    }

    public void obrisiRaspored(int row) {
        listaZRS.remove(row);
        fireTableDataChanged();
    }
}
