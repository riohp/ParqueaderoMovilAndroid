/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package esteb.aplication;
import com.google.gson.*;
import consumoApi.ConsumoApi;
import esteb.aplication.IngresoVehiculoP;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author INSTRUCTOR
 */
public class dashboardAdm extends javax.swing.JPanel {
    DefaultTableModel tableModel;
    ConsumoApi consumoApi = new ConsumoApi();
    String idpark;
    /**
     * Creates new form dashboard
     */
    public dashboardAdm(String idpark) {
        this.idpark = idpark;
        initComponents();
        init();
    }

    private void init(){
        // Ganancias
        int mesActual = Calendar.getInstance().get(Calendar.MONTH);
        double gananciasMes = 0.0;

        jTable1.setEnabled(false);
        GsonBuilder jsonBuilder = new GsonBuilder();

        tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setNumRows(0);

        Map<String, String> data = new HashMap<>();
        JsonObject jsonObject = JsonParser.parseString(consumoApi.consumoGETV1("http://localhost/APIenPHP/getAll/getadmin.php", data)).getAsJsonObject();

        String vehiculoayer = jsonObject.get("vehiculos_ayer").getAsString();
        JsonArray jsonArray = jsonObject.getAsJsonArray("data");

        for (int i = jsonArray.size() - 1; i >= 0; i--) {
            JsonObject registroJson = jsonArray.get(i).getAsJsonObject();

            // Verificar si el campo 'NombreParqueadero' está presente y no es nulo
            if (registroJson.has("NombreParqueadero") && !registroJson.get("NombreParqueadero").isJsonNull()) {
                JsonElement nombreElement = registroJson.get("NombreParqueadero");
                String nombre = nombreElement.getAsString();

                JsonElement placaElement = registroJson.get("Placa");
                String placa = (placaElement != null && !placaElement.isJsonNull()) ? placaElement.getAsString() : "N/A";

                JsonElement tipoVehiculoElement = registroJson.get("TipoVehiculo");
                String tipoVehiculo = (tipoVehiculoElement != null && !tipoVehiculoElement.isJsonNull()) ? tipoVehiculoElement.getAsString() : "N/A";

                JsonElement horaEntradaElement = registroJson.get("HoraEntrada");
                String horaEntrada = (horaEntradaElement != null && !horaEntradaElement.isJsonNull()) ? horaEntradaElement.getAsString() : "N/A";

                JsonElement estadoElement = registroJson.get("Estado");
                String estado = (estadoElement != null && !estadoElement.isJsonNull()) ? estadoElement.getAsString() : "N/A";

                JsonElement horaSalidaElement = registroJson.get("HoraSalida");
                String horaSalida = (horaSalidaElement != null && !horaSalidaElement.isJsonNull()) ? horaSalidaElement.getAsString() : "N/A";

                JsonElement costoTotalElement = registroJson.get("TotalPago");
                String costoTotal = (costoTotalElement != null && !costoTotalElement.isJsonNull()) ? costoTotalElement.getAsString() : "N/A";

                Object[] temp = {nombre, placa, tipoVehiculo, horaEntrada, estado, horaSalida, costoTotal};
                tableModel.addRow(temp);
            }
        }


        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String horaSalida = (String) tableModel.getValueAt(i, 5); // Asumiendo que la hora de salida está en la quinta columna

            if (!"N/A".equals(horaSalida)) {
                // Hora de salida
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try {
                    Date fechaSalida = dateFormat.parse(horaSalida);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fechaSalida);
                    int mesSalida = cal.get(Calendar.MONTH);

                    // Comprobar si la fecha de salida es del mes actual
                    if (mesSalida == mesActual) {
                        String costoTotal = (String) tableModel.getValueAt(i, 6); // Asumiendo que el costo total está en la sexta columna
                        double costo = Double.parseDouble(costoTotal);
                        gananciasMes += costo;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

        // Mostrar las ganancias estimadas en la etiqueta
        jLabel2.setText("$" + gananciasMes);

        int numVehiculos = jsonArray.size();
        int ayervehiculo = Integer.parseInt(vehiculoayer);
        jLabel5.setText(String.valueOf(numVehiculos));
        jLabel6.setText("+" + (numVehiculos - ayervehiculo) + " Que el dia de ayer");

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelUI1 = new esteb.swing.PanelUI();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        panelUI2 = new esteb.swing.PanelUI();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelUI3 = new esteb.swing.PanelUI();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        panelUI1.setBackground(new java.awt.Color(236, 236, 236));
        panelUI1.setRoundbottomLeft(50);
        panelUI1.setRoundtopLeft(50);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Parqueadero","PLACA", "TIPO VEHICULO", "HORA INGRESO", "ESTADO", "HORA SALIDA", "TOTAL PAGO"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        panelUI2.setBackground(new java.awt.Color(255, 255, 255));
        panelUI2.setRoundbottomLeft(20);
        panelUI2.setRoundbottomRight(20);
        panelUI2.setRoundtopLeft(20);
        panelUI2.setRoundtopRight(20);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(56, 56, 56));
        jLabel1.setText("GANANCIAS ESTIMADAS MES");

        jLabel2.setBackground(new java.awt.Color(55, 0, 255));
        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(56, 56, 56));
        jLabel2.setText("$32510");

        javax.swing.GroupLayout panelUI2Layout = new javax.swing.GroupLayout(panelUI2);
        panelUI2.setLayout(panelUI2Layout);
        panelUI2Layout.setHorizontalGroup(
            panelUI2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUI2Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(panelUI2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        panelUI2Layout.setVerticalGroup(
            panelUI2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUI2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelUI3.setBackground(new java.awt.Color(255, 255, 255));
        panelUI3.setRoundbottomLeft(20);
        panelUI3.setRoundbottomRight(20);
        panelUI3.setRoundtopLeft(20);
        panelUI3.setRoundtopRight(20);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(56, 56, 56));
        jLabel4.setText("VEHICULOS INGRESADOS/DIA");

        jLabel5.setBackground(new java.awt.Color(55, 0, 255));
        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(56, 56, 56));
        jLabel5.setText("25");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(56, 56, 56));
        jLabel6.setText("+7 Que el dia de ayer ");

        javax.swing.GroupLayout panelUI3Layout = new javax.swing.GroupLayout(panelUI3);
        panelUI3.setLayout(panelUI3Layout);
        panelUI3Layout.setHorizontalGroup(
            panelUI3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUI3Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(panelUI3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUI3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addComponent(jLabel4))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        panelUI3Layout.setVerticalGroup(
            panelUI3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUI3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(panelUI3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(0, 38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelUI1Layout = new javax.swing.GroupLayout(panelUI1);
        panelUI1.setLayout(panelUI1Layout);
        panelUI1Layout.setHorizontalGroup(
            panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUI1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelUI1Layout.createSequentialGroup()
                        .addComponent(panelUI2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(panelUI3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        panelUI1Layout.setVerticalGroup(
            panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUI1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelUI2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelUI3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private esteb.swing.PanelUI panelUI1;
    private esteb.swing.PanelUI panelUI2;
    private esteb.swing.PanelUI panelUI3;
    // End of variables declaration//GEN-END:variables
}
