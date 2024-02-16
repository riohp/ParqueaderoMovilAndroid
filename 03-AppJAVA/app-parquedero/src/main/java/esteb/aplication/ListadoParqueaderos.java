/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package esteb.aplication;
import com.google.gson.*;
import consumoApi.ConsumoApi;
import esteb.aplication.IngresoVehiculoP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author INSTRUCTOR
 */
public class ListadoParqueaderos extends javax.swing.JPanel {
    DefaultTableModel tableModel;
    ConsumoApi consumoApi = new ConsumoApi();
    /**
     * Creates new form dashboard
     */
    public ListadoParqueaderos() {
        initComponents();
        init();
    }

    private void init(){
        tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setNumRows(0);
        Map<String, String> data = new HashMap<>();
        JsonObject jsonObject = JsonParser.parseString(consumoApi.consumoGETV1("http://localhost/APIenPHP/Parqueaderos/Obtener_P.php", data)).getAsJsonObject();
        System.out.println(jsonObject);

        JsonArray jsonArray = jsonObject.getAsJsonArray("registros");

        for (int i = jsonArray.size() - 1; i >= 0; i--) {
            JsonObject registroJson = jsonArray.get(i).getAsJsonObject();
            String nombre = registroJson.get("nombreparqueadero").getAsString();
            String direccion = registroJson.get("direccion").getAsString();
            String tarifaCarro = registroJson.get("costotarifacarro").getAsString();
            String tarifaMoto = registroJson.get("costotarifamoto").getAsString();
            String tarifaCamion = registroJson.get("costotarifacamion").getAsString();
            String tarifaCamioneta = registroJson.get("costotarifacamioneta").getAsString();


            Object[] temp = {nombre, direccion, tarifaCarro, tarifaMoto, tarifaCamion, tarifaCamioneta};
            tableModel.addRow(temp);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnRegistrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnAsignar = new javax.swing.JButton();

        setBackground(new java.awt.Color(17, 17, 17));

        bgBorder.setBackground(new java.awt.Color(236, 236, 236));
        bgBorder.setRoundbottomLeft(50);
        bgBorder.setRoundtopLeft(50);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "NOMBRE", "DIRECCION", "TARIFA CARRO", "TARIFA MOTO", "TARIFA CAMION", "TARIFA CAMIONETA"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        btnRegistrar.setBackground(new java.awt.Color(56, 56, 56));
        btnRegistrar.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("REGISTRAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(56, 56, 56));
        btnEditar.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("EDITAR ");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("LISTADO PARQUEADEROS");

        btnAsignar.setBackground(new java.awt.Color(56, 56, 56));
        btnAsignar.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        btnAsignar.setForeground(new java.awt.Color(255, 255, 255));
        btnAsignar.setText("ASIGNAR");
        btnAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bgBorderLayout = new javax.swing.GroupLayout(bgBorder);
        bgBorder.setLayout(bgBorderLayout);
        bgBorderLayout.setHorizontalGroup(
            bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgBorderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgBorderLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgBorderLayout.createSequentialGroup()
                        .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(bgBorderLayout.createSequentialGroup()
                                .addComponent(btnEditar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegistrar)
                                .addGap(18, 18, 18)
                                .addComponent(btnAsignar))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 831, Short.MAX_VALUE))
                        .addGap(26, 26, 26))))
        );
        bgBorderLayout.setVerticalGroup(
            bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRegistrar)
                    .addComponent(btnAsignar)
                    .addComponent(btnEditar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addGap(24, 24, 24))
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
            .addComponent(bgBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        EditarParqueadero editarParqueadero = new EditarParqueadero();
        Container container = this.getParent();

        if (container != null) {
            container.removeAll();
            editarParqueadero.setSize(container.getSize());
            editarParqueadero.setLocation(0, 0);
            container.add(editarParqueadero);
            container.repaint();
            container.revalidate();
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        EditarParqueadero editarParqueadero = new EditarParqueadero();
        Container container = this.getParent();

        if (container != null) {
            container.removeAll();
            editarParqueadero.setSize(container.getSize());
            editarParqueadero.setLocation(0, 0);
            container.add(editarParqueadero);
            container.repaint();
            container.revalidate();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarActionPerformed
        AsignarParqueadero asignarParqueadero = new AsignarParqueadero();
        Container container = this.getParent();

        if (container != null) {
            container.removeAll();
            asignarParqueadero.setSize(container.getSize());
            asignarParqueadero.setLocation(0, 0);
            // Agregar el panel de ingreso de vehículos al contenedor
            container.add(asignarParqueadero);
            container.repaint();
            container.revalidate();
        }
    }//GEN-LAST:event_btnAsignarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private esteb.swing.PanelUI bgBorder;
    private javax.swing.JButton btnAsignar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
