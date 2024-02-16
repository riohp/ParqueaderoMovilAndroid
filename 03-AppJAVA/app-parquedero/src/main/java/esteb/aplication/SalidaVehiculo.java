/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package esteb.aplication;

import com.google.gson.*;
import consumoApi.ConsumoApi;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author EstebanVfx
 */
public class SalidaVehiculo extends javax.swing.JPanel {
    String precio;
    ConsumoApi consumoApi = new ConsumoApi();
    String idparqueadero = "";
    String placa = "";
    int idticket = 0;
    /**
     * Creates new form IngresoVehiculo
     */
    public SalidaVehiculo(String idparqueadero) {
        initComponents();
        this.idparqueadero = idparqueadero;
        init();
        System.out.println("IngresoVehiculo llamado");
    }

    private void init(){
        labelTime1.setText(null);
        inputMarca.setText(null);
        inputColor.setText(null);
        labelInfoPrecio.setText("$0");
        mostrarHora();
        Timer timer = new Timer(1000, e -> {
            mostrarHora();
        });
        timer.start();
        //FUNCION PARA QUE EL TEXTO ESTE CONTINUAMENTE EN MAYUSCULAS
        letrasPlaca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                letrasPlaca.setText(letrasPlaca.getText().toUpperCase());
            }
        });

        numerosletrasPlaca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                numerosletrasPlaca.setText(numerosletrasPlaca.getText().toUpperCase());
            }
        });

        //limitador de caracteres en placa
        PlainDocument doc = new PlainDocument(){
            @Override
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                if(getLength() + str.length() <= 3){
                    super.insertString(offs, str, a);

                    if (getLength() == 3 && Character.isUpperCase(str.charAt(0))) {
                        numerosletrasPlaca.requestFocus();
                    }
                }else{
                    System.out.println("SE LLEGO AL LIMITES y salta al numero");
                }
            }
        };
        letrasPlaca.setDocument(doc);

        PlainDocument docv2 = new PlainDocument(){
          @Override
          public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
              if(getLength() + str.length() <= 3){
                  super.insertString(offs, str, a);
                  System.out.println("SE LLEGO AL LIMITES en letra y numero");
              }
          }
        };
        numerosletrasPlaca.setDocument(docv2);


        //FUNCION OBTENER HORA ACTUAL

    }


    private void mostrarHora(){
        LocalTime hora = LocalTime.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss");
        String horaFormateada = hora.format(formato);

        labelTime.setText(horaFormateada);
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
        panelPlaca = new javax.swing.JPanel();
        numerosletrasPlaca = new javax.swing.JTextField();
        letrasPlaca = new javax.swing.JTextField();
        guionPlaca = new javax.swing.JLabel();
        separador = new javax.swing.JSeparator();
        labelHoraSalida = new javax.swing.JLabel();
        panelTiempo = new esteb.swing.PanelUI();
        labelTime = new javax.swing.JLabel();
        labelMarca = new javax.swing.JLabel();
        labelColor = new javax.swing.JLabel();
        inputColor = new javax.swing.JTextField();
        inputMarca = new javax.swing.JTextField();
        labelPrecio = new javax.swing.JLabel();
        panelPriceHora = new esteb.swing.PanelUI();
        labelInfoPrecio = new javax.swing.JLabel();
        btnComfirmar = new javax.swing.JButton();
        panelTiempo1 = new esteb.swing.PanelUI();
        labelTime1 = new javax.swing.JLabel();
        labelHoraIngreso = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        bgBorder.setBackground(new java.awt.Color(236, 236, 236));
        bgBorder.setRoundbottomLeft(50);
        bgBorder.setRoundtopLeft(50);

        panelPlaca.setBackground(new java.awt.Color(254, 179, 11));
        panelPlaca.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 4, 4, 4, new java.awt.Color(0, 0, 0)));

        numerosletrasPlaca.setBackground(new java.awt.Color(254, 179, 11));
        numerosletrasPlaca.setColumns(3);
        numerosletrasPlaca.setFont(new java.awt.Font("Ubuntu", 1, 80)); // NOI18N
        numerosletrasPlaca.setForeground(new java.awt.Color(0, 0, 0));
        numerosletrasPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numerosletrasPlaca.setText("06G");
        numerosletrasPlaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numerosletrasPlacaKeyReleased(evt);
            }
        });

        letrasPlaca.setBackground(new java.awt.Color(254, 179, 11));
        letrasPlaca.setColumns(3);
        letrasPlaca.setFont(new java.awt.Font("Ubuntu", 1, 80)); // NOI18N
        letrasPlaca.setForeground(new java.awt.Color(0, 0, 0));
        letrasPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        letrasPlaca.setText("MMM");
        letrasPlaca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                letrasPlacaActionPerformed(evt);
            }
        });

        guionPlaca.setBackground(new java.awt.Color(0, 0, 0));
        guionPlaca.setFont(new java.awt.Font("Ubuntu", 1, 80)); // NOI18N
        guionPlaca.setForeground(new java.awt.Color(0, 0, 0));
        guionPlaca.setText("-");

        javax.swing.GroupLayout panelPlacaLayout = new javax.swing.GroupLayout(panelPlaca);
        panelPlaca.setLayout(panelPlacaLayout);
        panelPlacaLayout.setHorizontalGroup(
            panelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPlacaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(letrasPlaca, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(guionPlaca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numerosletrasPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelPlacaLayout.setVerticalGroup(
            panelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelPlacaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(panelPlacaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(letrasPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guionPlaca)
                    .addComponent(numerosletrasPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        labelHoraSalida.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        labelHoraSalida.setForeground(new java.awt.Color(51, 51, 51));
        labelHoraSalida.setText("HORA ACTUAL DE SALIDA:");

        panelTiempo.setRoundbottomLeft(10);
        panelTiempo.setRoundbottomRight(10);
        panelTiempo.setRoundtopLeft(10);
        panelTiempo.setRoundtopRight(10);

        labelTime.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        labelTime.setForeground(new java.awt.Color(255, 255, 255));
        labelTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTime.setText("13:22");

        javax.swing.GroupLayout panelTiempoLayout = new javax.swing.GroupLayout(panelTiempo);
        panelTiempo.setLayout(panelTiempoLayout);
        panelTiempoLayout.setHorizontalGroup(
            panelTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTiempoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTime, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTiempoLayout.setVerticalGroup(
            panelTiempoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTime, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        labelMarca.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        labelMarca.setForeground(new java.awt.Color(51, 51, 51));
        labelMarca.setText("MARCA VEHICULO:");

        labelColor.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        labelColor.setForeground(new java.awt.Color(51, 51, 51));
        labelColor.setText("COLOR VEHICULO:");

        inputColor.setBackground(new java.awt.Color(255, 255, 255));
        inputColor.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        inputColor.setForeground(new java.awt.Color(0, 0, 0));
        inputColor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputColor.setText("Verder");

        inputMarca.setBackground(new java.awt.Color(255, 255, 255));
        inputMarca.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        inputMarca.setForeground(new java.awt.Color(0, 0, 0));
        inputMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputMarca.setText("JEEP");

        labelPrecio.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        labelPrecio.setForeground(new java.awt.Color(51, 51, 51));
        labelPrecio.setText("Tarifa x hora:");

        panelPriceHora.setRoundbottomLeft(10);
        panelPriceHora.setRoundbottomRight(10);
        panelPriceHora.setRoundtopLeft(10);
        panelPriceHora.setRoundtopRight(10);

        labelInfoPrecio.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        labelInfoPrecio.setForeground(new java.awt.Color(255, 255, 255));
        labelInfoPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelInfoPrecio.setText("7.000");

        javax.swing.GroupLayout panelPriceHoraLayout = new javax.swing.GroupLayout(panelPriceHora);
        panelPriceHora.setLayout(panelPriceHoraLayout);
        panelPriceHoraLayout.setHorizontalGroup(
            panelPriceHoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPriceHoraLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(labelInfoPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
        );
        panelPriceHoraLayout.setVerticalGroup(
            panelPriceHoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelPriceHoraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInfoPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnComfirmar.setBackground(new java.awt.Color(100, 205, 51));
        btnComfirmar.setFont(new java.awt.Font("Ubuntu", 1, 48)); // NOI18N
        btnComfirmar.setForeground(new java.awt.Color(255, 255, 255));
        btnComfirmar.setText("COMFIRMAR");
        btnComfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnComfirmarActionPerformed(evt);
            }
        });

        panelTiempo1.setRoundbottomLeft(10);
        panelTiempo1.setRoundbottomRight(10);
        panelTiempo1.setRoundtopLeft(10);
        panelTiempo1.setRoundtopRight(10);

        labelTime1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        labelTime1.setForeground(new java.awt.Color(255, 255, 255));
        labelTime1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTime1.setText("13:22");

        javax.swing.GroupLayout panelTiempo1Layout = new javax.swing.GroupLayout(panelTiempo1);
        panelTiempo1.setLayout(panelTiempo1Layout);
        panelTiempo1Layout.setHorizontalGroup(
            panelTiempo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTiempo1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTime1, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelTiempo1Layout.setVerticalGroup(
            panelTiempo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelTime1, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
        );

        labelHoraIngreso.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        labelHoraIngreso.setForeground(new java.awt.Color(51, 51, 51));
        labelHoraIngreso.setText("HORA  DE INGRESO:");

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("SALIDA VEHICULO");

        javax.swing.GroupLayout bgBorderLayout = new javax.swing.GroupLayout(bgBorder);
        bgBorder.setLayout(bgBorderLayout);
        bgBorderLayout.setHorizontalGroup(
            bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgBorderLayout.createSequentialGroup()
                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(bgBorderLayout.createSequentialGroup()
                        .addGap(0, 23, Short.MAX_VALUE)
                        .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, bgBorderLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgBorderLayout.createSequentialGroup()
                                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(inputMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelMarca))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelColor)
                                    .addComponent(inputColor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)))
                            .addGroup(bgBorderLayout.createSequentialGroup()
                                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelHoraIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(panelTiempo1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelTiempo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgBorderLayout.createSequentialGroup()
                                        .addComponent(labelHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(177, 177, 177)))))))
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(bgBorderLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bgBorderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnComfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bgBorderLayout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPrecio)
                    .addComponent(panelPriceHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        bgBorderLayout.setVerticalGroup(
            bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(1, 1, 1)
                .addComponent(panelPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(separador, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgBorderLayout.createSequentialGroup()
                        .addComponent(labelHoraSalida)
                        .addGap(0, 0, 0)
                        .addComponent(panelTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgBorderLayout.createSequentialGroup()
                        .addComponent(labelHoraIngreso)
                        .addGap(0, 0, 0)
                        .addComponent(panelTiempo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgBorderLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(labelColor)
                        .addGap(0, 0, 0)
                        .addComponent(inputColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgBorderLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelMarca)
                        .addGap(0, 0, 0)
                        .addComponent(inputMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(labelPrecio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPriceHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnComfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bgBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(bgBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void letrasPlacaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_letrasPlacaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_letrasPlacaActionPerformed

    private void numerosletrasPlacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numerosletrasPlacaKeyReleased
        Gson gson = new Gson();

        if(numerosletrasPlaca.getText().length()>=2){
            GsonBuilder contructJson = new GsonBuilder();
            contructJson.setPrettyPrinting();

            Map<String, String> getData = new HashMap<>();
            placa = letrasPlaca.getText()+numerosletrasPlaca.getText();
            getData.put("placa", placa);
            System.out.println(consumoApi.consumoGETV1("http://localhost/APIenPHP/getAll/salirVehiculo.php",getData));
            JsonObject jsonObj = JsonParser.parseString(consumoApi.consumoGETV1("http://localhost/APIenPHP/getAll/salirVehiculo.php",getData)).getAsJsonObject();
            JsonArray data = jsonObj.getAsJsonArray("data");

            if (data.size() != 0) {
                JsonObject dataObject = data.get(0).getAsJsonObject();
                String horaIngreso = dataObject.get("horaentrada").getAsString();
                String marca = dataObject.get("marca").getAsString();
                String color = dataObject.get("color").getAsString();
                String tipovehiculo = dataObject.get("tipovehiculo").getAsString();

                Map<String, String> getTipoPrecio = new HashMap<>();
                getTipoPrecio.put("idparqueadero", idparqueadero);
                JsonObject jsonObjPrecio = JsonParser.parseString(consumoApi.consumoGETV1("http://localhost/APIenPHP/getAll/adivinaElPrecio.php",getTipoPrecio)).getAsJsonObject();
                System.out.println(jsonObjPrecio);
                System.out.println(tipovehiculo);
                if (tipovehiculo.equalsIgnoreCase("moto")) {
                    System.out.println("es moto");
                    precio = jsonObjPrecio.get("costotarifamoto").getAsString();
                }else if (tipovehiculo.equalsIgnoreCase("carro")) {
                    precio = jsonObjPrecio.get("costotarifacarro").getAsString();
                }else if (tipovehiculo.equalsIgnoreCase("camioneta")) {
                    System.out.println("es camioneta");
                    precio = jsonObjPrecio.get("costotarifacamioneta").getAsString();
                }else if (tipovehiculo.equalsIgnoreCase("camion")) {
                    System.out.println("es camion");
                    precio = jsonObjPrecio.get("costotarifacamion").getAsString();
                }else{
                    precio = "0";
                }
                labelInfoPrecio.setText("$"+precio);
                labelTime1.setText(horaIngreso);
                inputMarca.setText(marca);
                inputColor.setText(color);
                idticket = dataObject.get("idtickets").getAsInt();
                System.out.println("idticket: "+idticket);
                if(idticket != 0) {
                    btnComfirmar.setEnabled(true);
                }else{
                    btnComfirmar.setEnabled(false);
                }
            }else{
                idticket = 0;
                System.out.println("No hay datos");
                labelTime1.setText(null);
                inputMarca.setText(null);
                inputColor.setText(null);
            }
        }
    }//GEN-LAST:event_numerosletrasPlacaKeyReleased

    private void btnComfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnComfirmarActionPerformed
        if(idticket != 0){
            GsonBuilder contructJson = new GsonBuilder();
            contructJson.setPrettyPrinting();;

            Map<String, String> getPost = new HashMap<>();
            getPost.put("id_ticket", String.valueOf(idticket));
            getPost.put("precio_tipo_vehiculo", precio);
            JsonObject jsonObj = JsonParser.parseString(consumoApi.consumoPOST("http://localhost/APIenPHP/getAll/updateSalida.php",getPost)).getAsJsonObject();

            boolean status = jsonObj.get("status").getAsBoolean();
            String message = jsonObj.get("message").getAsString();
            System.out.println(status);
            if (status){
                idticket = 0;
                JOptionPane.showMessageDialog(null, message);
                labelTime1.setText(null);
                inputMarca.setText(null);
                inputColor.setText(null);
                letrasPlaca.setText(null);
                numerosletrasPlaca.setText(null);
            }else {
                JOptionPane.showMessageDialog(null, message);
            }
        }else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado esta placa");
        }



    }//GEN-LAST:event_btnComfirmarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private esteb.swing.PanelUI bgBorder;
    private javax.swing.JButton btnComfirmar;
    private javax.swing.JLabel guionPlaca;
    private javax.swing.JTextField inputColor;
    private javax.swing.JTextField inputMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelColor;
    private javax.swing.JLabel labelHoraIngreso;
    private javax.swing.JLabel labelHoraSalida;
    private javax.swing.JLabel labelInfoPrecio;
    private javax.swing.JLabel labelMarca;
    private javax.swing.JLabel labelPrecio;
    private javax.swing.JLabel labelTime;
    private javax.swing.JLabel labelTime1;
    private javax.swing.JTextField letrasPlaca;
    private javax.swing.JTextField numerosletrasPlaca;
    private javax.swing.JPanel panelPlaca;
    private esteb.swing.PanelUI panelPriceHora;
    private esteb.swing.PanelUI panelTiempo;
    private esteb.swing.PanelUI panelTiempo1;
    private javax.swing.JSeparator separador;
    // End of variables declaration//GEN-END:variables
}
