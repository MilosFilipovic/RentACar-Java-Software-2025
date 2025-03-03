/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import klase.Klijent;
import komunikacija.Komunikacija;
import konstante.Operacije;
import modeli.TabelaKlijent;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Miloš
 */
public class FormaKlijent extends javax.swing.JFrame {

    /**
     * Creates new form FormaKlijent
     */
    public FormaKlijent() {
        initComponents();
        setLocationRelativeTo(null);
        tblKlijent.setModel(new TabelaKlijent());
        
        popuniTabeluKlijenti();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblKlijent = new javax.swing.JTable();
        btnNazad = new javax.swing.JButton();
        btnObrisiKlijenta = new javax.swing.JButton();
        btnOsveziTabeluKlijenata = new javax.swing.JButton();
        btnIzmeniKlijenta = new javax.swing.JButton();
        btnKreirajKlijenta = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtPretraziKlijenta = new javax.swing.JTextField();
        btnPretraziKlijenta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblKlijent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblKlijent);

        btnNazad.setText("Back");
        btnNazad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNazadActionPerformed(evt);
            }
        });

        btnObrisiKlijenta.setText("Delete");
        btnObrisiKlijenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnObrisiKlijentaActionPerformed(evt);
            }
        });

        btnOsveziTabeluKlijenata.setText("Refresh");
        btnOsveziTabeluKlijenata.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOsveziTabeluKlijenataActionPerformed(evt);
            }
        });

        btnIzmeniKlijenta.setText("Change");
        btnIzmeniKlijenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIzmeniKlijentaActionPerformed(evt);
            }
        });

        btnKreirajKlijenta.setText("Create new");
        btnKreirajKlijenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKreirajKlijentaActionPerformed(evt);
            }
        });

        jLabel1.setText("Search by name:");

        btnPretraziKlijenta.setText("Search");
        btnPretraziKlijenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPretraziKlijentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnOsveziTabeluKlijenata)
                                .addGap(96, 96, 96)
                                .addComponent(btnKreirajKlijenta))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(txtPretraziKlijenta)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnIzmeniKlijenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnObrisiKlijenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnNazad))
                            .addComponent(btnPretraziKlijenta))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnObrisiKlijenta)
                    .addComponent(btnOsveziTabeluKlijenata)
                    .addComponent(btnIzmeniKlijenta)
                    .addComponent(btnKreirajKlijenta)
                    .addComponent(btnNazad))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtPretraziKlijenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPretraziKlijenta))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNazadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNazadActionPerformed
        GlavnaForma gf = new GlavnaForma();
        this.dispose();
    }//GEN-LAST:event_btnNazadActionPerformed

    private void btnObrisiKlijentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnObrisiKlijentaActionPerformed
        int row = tblKlijent.getSelectedRow();

        if (row != -1) {
            TabelaKlijent tk = (TabelaKlijent) tblKlijent.getModel();
            Object id = tblKlijent.getValueAt(row, 0);
            tk.obrisiKlijenta(row);

            

            KlijentskiZahtev kz = new KlijentskiZahtev();
            kz.setOperacija(Operacije.OBRISI_KLIJENTA);
            kz.setParametar(id);

            Komunikacija.getInstance().posaljiZahtev(kz);
            ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();

            tk.fireTableDataChanged();

            boolean obrisan = (boolean) so.getOdgovor();

            if (obrisan) {
                JOptionPane.showMessageDialog(this, "Client deleted!");

                

            } else {
                JOptionPane.showMessageDialog(this, "Error");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Element not selected!");
        }
    }//GEN-LAST:event_btnObrisiKlijentaActionPerformed

    private void btnOsveziTabeluKlijenataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOsveziTabeluKlijenataActionPerformed
        popuniTabeluKlijenti();
    }//GEN-LAST:event_btnOsveziTabeluKlijenataActionPerformed

    private void btnKreirajKlijentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKreirajKlijentaActionPerformed
        DialogKreirajKlijenta dkk = new DialogKreirajKlijenta(this, true);
        dkk.setVisible(true);
    }//GEN-LAST:event_btnKreirajKlijentaActionPerformed

    private void btnIzmeniKlijentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIzmeniKlijentaActionPerformed
        int row = tblKlijent.getSelectedRow();
        if (row != -1) {
            
            int id = (int) tblKlijent.getValueAt(row, 0);
            String ime = (String) tblKlijent.getValueAt(row, 1);              // Ime je u koloni 1
            String prezime = (String) tblKlijent.getValueAt(row, 2);          // Prezime je u koloni 2
            String broj = (String) tblKlijent.getValueAt(row, 3);               // broj je u koloni 3
            int mesto = (int) tblKlijent.getValueAt(row, 4);
            
            DialogIzmeniKlijenta dik = new DialogIzmeniKlijenta(this, true);
            
            dik.postaviId(id);
            dik.postaviIme(ime);
            dik.postaviPrezime(prezime);
            dik.postaviTelefon(broj);
            dik.postaviMesto(mesto);
            
            
            dik.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(null, "Element not selected!");
        }
    }//GEN-LAST:event_btnIzmeniKlijentaActionPerformed

    private void btnPretraziKlijentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPretraziKlijentaActionPerformed
        if(txtPretraziKlijenta.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter client name!");
            return;
        }
        
        String ime = txtPretraziKlijenta.getText();
        
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.PRETRAZI_KLIJENTA);
        kz.setParametar(ime);

        Komunikacija.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();

        ArrayList<Klijent> pretraziK = (ArrayList<Klijent>) so.getOdgovor();

        tblKlijent.removeAll();

         
        TabelaKlijent tk = (TabelaKlijent) tblKlijent.getModel();
        tk.setPretragaKlijent(pretraziK);
    }//GEN-LAST:event_btnPretraziKlijentaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormaKlijent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormaKlijent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormaKlijent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormaKlijent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormaKlijent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIzmeniKlijenta;
    private javax.swing.JButton btnKreirajKlijenta;
    private javax.swing.JButton btnNazad;
    private javax.swing.JButton btnObrisiKlijenta;
    private javax.swing.JButton btnOsveziTabeluKlijenata;
    private javax.swing.JButton btnPretraziKlijenta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKlijent;
    private javax.swing.JTextField txtPretraziKlijenta;
    // End of variables declaration//GEN-END:variables

    private void popuniTabeluKlijenti() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_KLIJENTE);

        Komunikacija.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();

        ArrayList<Klijent> klijent = (ArrayList<Klijent>) so.getOdgovor();

        tblKlijent.removeAll();

         
        TabelaKlijent tk = (TabelaKlijent) tblKlijent.getModel();
        tk.setZaposleni(klijent);
    }
}
