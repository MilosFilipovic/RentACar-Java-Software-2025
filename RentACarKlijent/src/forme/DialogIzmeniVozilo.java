/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import javax.swing.JOptionPane;
import klase.Vozilo;
import komunikacija.Komunikacija;
import konstante.Operacije;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Miloš
 */
public class DialogIzmeniVozilo extends javax.swing.JDialog {

    /**
     * Creates new form DialogIzmeniVozilo
     */
    public DialogIzmeniVozilo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblIDVozila = new javax.swing.JLabel();
        txtIzmenjenoVoziloModel = new javax.swing.JTextField();
        ftxtIzmenjenoVoziloCena = new javax.swing.JFormattedTextField();
        txtIzmenjenoVoziloType = new javax.swing.JTextField();
        txtIzmenjenoVoziloKonji = new javax.swing.JTextField();
        txtIzmenjenoVoziloCM = new javax.swing.JTextField();
        txtIzmenjenoVoziloBoja = new javax.swing.JTextField();
        btnSacuvajIzmene = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("ID:");

        jLabel2.setText("Model:");

        jLabel3.setText("Cost per day:");

        jLabel4.setText("Type:");

        jLabel5.setText("Horse power:");

        jLabel6.setText("Cm3:");

        jLabel7.setText("Color:");

        ftxtIzmenjenoVoziloCena.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.00"))));

        btnSacuvajIzmene.setText("Save");
        btnSacuvajIzmene.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSacuvajIzmeneActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSacuvajIzmene)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIzmenjenoVoziloModel)
                                    .addComponent(ftxtIzmenjenoVoziloCena)
                                    .addComponent(txtIzmenjenoVoziloType)
                                    .addComponent(txtIzmenjenoVoziloKonji)
                                    .addComponent(txtIzmenjenoVoziloCM)
                                    .addComponent(txtIzmenjenoVoziloBoja, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIDVozila)))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblIDVozila))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIzmenjenoVoziloModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(ftxtIzmenjenoVoziloCena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtIzmenjenoVoziloType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIzmenjenoVoziloKonji, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtIzmenjenoVoziloCM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtIzmenjenoVoziloBoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSacuvajIzmene)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSacuvajIzmeneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSacuvajIzmeneActionPerformed
        
        if (txtIzmenjenoVoziloBoja.getText().isEmpty() || txtIzmenjenoVoziloCM.getText().isEmpty()
                || txtIzmenjenoVoziloKonji.getText().isEmpty() || txtIzmenjenoVoziloModel.getText().isEmpty() || txtIzmenjenoVoziloType.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }
        
        int id = Integer.valueOf(lblIDVozila.getText());
        String model = txtIzmenjenoVoziloModel.getText();
        double cena = Double.parseDouble(ftxtIzmenjenoVoziloCena.getText());
        String karoserija = txtIzmenjenoVoziloType.getText();
        String konjaza = txtIzmenjenoVoziloKonji.getText();
        String cm = txtIzmenjenoVoziloCM.getText();
        String boja = txtIzmenjenoVoziloBoja.getText();

        Vozilo vozilo = new Vozilo(id, model, cena, karoserija, konjaza, cm, boja);
        
        

        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.IZMENI_VOZILO);
        kz.setParametar(vozilo);

        Komunikacija.getInstance().posaljiZahtev(kz);
        ServerskiOdgovor so = Komunikacija.getInstance().primiOdgovor();

        boolean izmenjen = (boolean) so.getOdgovor();

        if (izmenjen) {
            JOptionPane.showMessageDialog(this, "Vehicle is edited!");
            
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, "Error");
        }
    }//GEN-LAST:event_btnSacuvajIzmeneActionPerformed

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
            java.util.logging.Logger.getLogger(DialogIzmeniVozilo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialogIzmeniVozilo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialogIzmeniVozilo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogIzmeniVozilo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DialogIzmeniVozilo dialog = new DialogIzmeniVozilo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSacuvajIzmene;
    private javax.swing.JFormattedTextField ftxtIzmenjenoVoziloCena;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblIDVozila;
    private javax.swing.JTextField txtIzmenjenoVoziloBoja;
    private javax.swing.JTextField txtIzmenjenoVoziloCM;
    private javax.swing.JTextField txtIzmenjenoVoziloKonji;
    private javax.swing.JTextField txtIzmenjenoVoziloModel;
    private javax.swing.JTextField txtIzmenjenoVoziloType;
    // End of variables declaration//GEN-END:variables

    void postaviId(int id) {
        lblIDVozila.setText(String.valueOf(id));
    }

    void postaviModel(String model) {
        txtIzmenjenoVoziloModel.setText(model);
    }

    void postaviCenu(double cena) {
        ftxtIzmenjenoVoziloCena.setValue(cena);
    }

    void postaviKaroseriju(String karoserija) {
        txtIzmenjenoVoziloType.setText(karoserija);
    }

    void postaviKonjazu(String konjaza) {
        txtIzmenjenoVoziloKonji.setText(konjaza);
    }

    void postaviKubikazu(String kubikaza) {
        txtIzmenjenoVoziloCM.setText(kubikaza);
    }

    void postaviBoju(String boja) {
        txtIzmenjenoVoziloBoja.setText(boja);
    }
}
