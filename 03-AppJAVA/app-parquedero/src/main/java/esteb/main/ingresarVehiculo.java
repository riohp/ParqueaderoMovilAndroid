package esteb.main;

import java.util.HashMap;
import java.util.Map;

public class ingresarVehiculo extends javax.swing.JPanel {


    public ingresarVehiculo() {
        initComponents();
        initAlternComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        etq_titulo = new javax.swing.JLabel();
        campo_placa = new javax.swing.JTextField();
        btn_registrar = new javax.swing.JButton();
        etq_ev = new javax.swing.JLabel();
        etq_ev1 = new javax.swing.JLabel();
        etq_ev2 = new javax.swing.JLabel();
        etq_ev3 = new javax.swing.JLabel();
        etq_ev4 = new javax.swing.JLabel();
        etq_marca = new javax.swing.JLabel();
        etq_color = new javax.swing.JLabel();
        etq_actual = new javax.swing.JLabel();
        etq_tarifa = new javax.swing.JLabel();
        etq_entrada3 = new javax.swing.JLabel();
        btn_confirmar = new javax.swing.JButton();

        etq_titulo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        etq_titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        etq_titulo.setText("ENTRADA DE VEHICULO");

        campo_placa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_placaActionPerformed(evt);
            }
        });
        campo_placa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_placaKeyReleased(evt);
            }
        });

        btn_registrar.setText("REGISTRAR");

        etq_ev.setText("Marca");

        etq_ev1.setText("Color");

        etq_ev2.setText("Hora Actual");

        etq_ev3.setText("Hora Entrada");

        etq_ev4.setText("Tarifa Vehiculo");

        btn_confirmar.setText("CONFIRMAR");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(etq_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(124, 124, 124)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(etq_ev)
                            .addComponent(etq_ev3)
                            .addComponent(etq_entrada3, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                            .addComponent(campo_placa))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(etq_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(etq_ev2)
                    .addComponent(etq_ev1)
                    .addComponent(etq_color, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(143, 143, 143))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_confirmar)
                            .addComponent(etq_tarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(etq_ev4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(etq_titulo)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(etq_titulo)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btn_registrar)
                            .addComponent(campo_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etq_ev)
                            .addComponent(etq_ev1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etq_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etq_color, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(etq_ev2)
                            .addComponent(etq_ev3))
                        .addGap(18, 18, 18)
                        .addComponent(etq_actual, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(etq_entrada3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(etq_ev4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(etq_tarifa, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_confirmar)
                .addGap(44, 44, 44))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void initAlternComponents(){

    }
    private void campo_placaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_placaActionPerformed
       
    }//GEN-LAST:event_campo_placaActionPerformed

    private void campo_placaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_placaKeyReleased
        ConsumoAPI api = new ConsumoAPI();
        Map<String, String> getData = new HashMap<>();
        String placa = campo_placa.getText();
        getData.put("placa", placa);
        String datosRecibidos = api.consumoGET("http://localhost/apienphp/Vehiculos/Obtener_V.php", getData);
        System.out.println("Consumo "+ datosRecibidos);
    }//GEN-LAST:event_campo_placaKeyReleased
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_confirmar;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JTextField campo_placa;
    private javax.swing.JLabel etq_actual;
    private javax.swing.JLabel etq_color;
    private javax.swing.JLabel etq_entrada3;
    private javax.swing.JLabel etq_ev;
    private javax.swing.JLabel etq_ev1;
    private javax.swing.JLabel etq_ev2;
    private javax.swing.JLabel etq_ev3;
    private javax.swing.JLabel etq_ev4;
    private javax.swing.JLabel etq_marca;
    private javax.swing.JLabel etq_tarifa;
    private javax.swing.JLabel etq_titulo;
    // End of variables declaration//GEN-END:variables
}
