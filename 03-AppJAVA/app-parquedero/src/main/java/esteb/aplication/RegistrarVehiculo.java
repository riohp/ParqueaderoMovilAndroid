/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package esteb.aplication;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import consumoApi.ConsumoApi;
import esteb.aplication.IngresoVehiculoP;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author INSTRUCTOR
 */
public class RegistrarVehiculo extends javax.swing.JPanel {
    ConsumoApi consumoApi = new ConsumoApi();
    Gson gson = new Gson();
    String idUser;
    String placaquita;
    String id_park;
    /**
     * Creates new form dashboard
     */
    public RegistrarVehiculo(String idUser, String id_park, String placaquita) {
        initComponents();
        this.idUser = idUser;
        this.id_park = id_park;
        this.placaquita = placaquita;
        init();
        setPlaca();
    }

    public void init(){
        selecionarVehiculo.removeAllItems();
        selecionarVehiculo.addItem("Seleccione un tipo de vehiculo");
        selecionarVehiculo.addItem("carro");
        selecionarVehiculo.addItem("moto");
        selecionarVehiculo.addItem("camion");
        selecionarVehiculo.addItem("camioneta");
    }

    public void setPlaca(){
        if (placaquita != null){
            inputPlaca.setText(placaquita);
            inputPlaca.setEditable(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgBorder = new esteb.swing.PanelUI();
        panelUI1 = new esteb.swing.PanelUI();
        inputPlaca = new javax.swing.JTextField();
        inputMarca = new javax.swing.JTextField();
        inputModelo = new javax.swing.JTextField();
        inputColor = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        selecionarVehiculo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(17, 17, 17));

        bgBorder.setBackground(new java.awt.Color(216, 216, 216));
        bgBorder.setRoundbottomLeft(50);
        bgBorder.setRoundtopLeft(50);

        panelUI1.setBackground(new java.awt.Color(255, 255, 255));
        panelUI1.setRoundbottomLeft(25);
        panelUI1.setRoundbottomRight(25);
        panelUI1.setRoundtopLeft(25);
        panelUI1.setRoundtopRight(25);

        inputPlaca.setBackground(new java.awt.Color(255, 204, 0));
        inputPlaca.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        inputPlaca.setForeground(new java.awt.Color(0, 0, 0));
        inputPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);


        inputMarca.setBackground(new java.awt.Color(240, 240, 240));
        inputMarca.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        inputMarca.setForeground(new java.awt.Color(0, 0, 0));
        inputMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        inputModelo.setBackground(new java.awt.Color(240, 240, 240));
        inputModelo.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        inputModelo.setForeground(new java.awt.Color(0, 0, 0));
        inputModelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        inputColor.setBackground(new java.awt.Color(240, 240, 240));
        inputColor.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        inputColor.setForeground(new java.awt.Color(0, 0, 0));
        inputColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnRegistrar.setBackground(new java.awt.Color(100, 205, 51));
        btnRegistrar.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 255));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("REGISTRAR VEHICULO");

        selecionarVehiculo.setBackground(new java.awt.Color(240, 240, 240));
        selecionarVehiculo.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        selecionarVehiculo.setForeground(new java.awt.Color(51, 51, 51));
        selecionarVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Placa:");

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Tipo vehiculo:");

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Marca:");

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Modelo:");

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Color:");

        javax.swing.GroupLayout panelUI1Layout = new javax.swing.GroupLayout(panelUI1);
        panelUI1.setLayout(panelUI1Layout);
        panelUI1Layout.setHorizontalGroup(
            panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUI1Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addGroup(panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(selecionarVehiculo, 0, 405, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addComponent(inputPlaca)
                        .addComponent(inputMarca)
                        .addComponent(inputModelo)
                        .addComponent(inputColor)))
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(panelUI1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRegistrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUI1Layout.setVerticalGroup(
            panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUI1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(inputPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(selecionarVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(0, 0, 0)
                .addComponent(inputMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(inputModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(0, 0, 0)
                .addComponent(inputColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout bgBorderLayout = new javax.swing.GroupLayout(bgBorder);
        bgBorder.setLayout(bgBorderLayout);
        bgBorderLayout.setHorizontalGroup(
            bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgBorderLayout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(panelUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(254, Short.MAX_VALUE))
        );
        bgBorderLayout.setVerticalGroup(
            bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgBorderLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(panelUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bgBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bgBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if(!inputPlaca.getText().isEmpty() && !inputMarca.getText().isEmpty() && !inputModelo.getText().isEmpty() && !inputColor.getText().isEmpty() && selecionarVehiculo.getSelectedIndex() != 0){
            System.out.println("Esta lleno ALL");
            Map<String, String> insertData = new HashMap<>();
            insertData.put("placa", inputPlaca.getText());
            insertData.put("tpvehiculo", selecionarVehiculo.getSelectedItem().toString());
            insertData.put("marca", inputMarca.getText());
            insertData.put("modelo", inputModelo.getText());
            insertData.put("color", inputColor.getText());
            insertData.put("idUser", idUser);
            JsonObject response = gson.fromJson(consumoApi.consumoPOST("http://localhost/APIenPHP/getAll/agregarNewVehiculo.php", insertData), JsonObject.class);
            if(response.get("status").getAsBoolean()) {
                JOptionPane.showMessageDialog(null, response.get("message").getAsString());
                Container pane = this.getParent();
                pane.removeAll();
                IngresoVehiculoP ingresoVehiculoP = new IngresoVehiculoP(id_park, inputPlaca.getText(), idUser);
                ingresoVehiculoP.setSize(pane.getSize());
                ingresoVehiculoP.setLocation(0,0);
                pane.add(ingresoVehiculoP);
                pane.revalidate();
                pane.repaint();
            }else{
                JOptionPane.showMessageDialog(null, response.get("message").getAsString());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Por favor llene todos los campos");
        }
        System.out.println("EL ID ES " + idUser);
    }//GEN-LAST:event_btnRegistrarActionPerformed




    // Variables declaration - do not modify//GEN-BEGIN:variables
    private esteb.swing.PanelUI bgBorder;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JTextField inputColor;
    private javax.swing.JTextField inputMarca;
    private javax.swing.JTextField inputModelo;
    private javax.swing.JTextField inputPlaca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private esteb.swing.PanelUI panelUI1;
    private javax.swing.JComboBox<String> selecionarVehiculo;
    // End of variables declaration//GEN-END:variables
}
