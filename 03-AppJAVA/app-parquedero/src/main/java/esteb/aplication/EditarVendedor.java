package esteb.aplication;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import esteb.aplication.IngresoVehiculoP;
import esteb.main.ConsumoAPI;
import javax.swing.*;
import java.awt.*;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class EditarVendedor extends javax.swing.JPanel {
    ConsumoAPI consumo = new ConsumoAPI();
    private String endpoint =  "http://localhost/apienphp/Usuarios/Obtener_Vendedores.php";
    private String idusuario;
    String docIdentificacion;
    public EditarVendedor() {
        initComponents();
    }
    
    public void buscarUsuario (String endpoint){
        if ( endpoint != null ){
            String datos = consumo.consumoPOST(endpoint);
            System.out.println("-> " + datos);
            try {
                JsonReader jsonReader = new JsonReader(new StringReader(datos));
                jsonReader.setLenient(true);
                JsonObject jsonObject = JsonParser.parseReader(jsonReader).getAsJsonObject();
                JsonArray results = jsonObject.getAsJsonArray("registros");
                for (JsonElement result : results){
                    JsonObject registro = result.getAsJsonObject();
                    String identificacion = registro.get("identificacion").getAsString();
                    String doc = getInputDoc();
                    if (doc.equalsIgnoreCase(identificacion)){
                        this.idusuario = (registro.get("idusuario").getAsString());
                        inputNombre.setText(registro.get("nombreusuario").getAsString());
                        inputCorreo.setText(registro.get("correoelectronico").getAsString());
                        inputContrasena.setText(registro.get("contrasena").getAsString());
                        System.out.println("Consumo SELECT ALL:-> DENTRO: " + doc);
                        break;
                    }else{
                        System.out.println("Consumo SELECT ALL:-> FUERA: " + doc);
                        doc = "";
                        inputNombre.setText("");
                        inputCorreo.setText("");
                        inputContrasena.setText("");
                    }
                } 
            } catch (JsonSyntaxException e) {
                System.err.println("Error al parsear el JSON: " + e.getMessage());
            }
        }
    }
    
    public String getInputDoc() {
        return docIdentificacion;
    }
    public void setInputDoc(String doc) {
        this.docIdentificacion = doc;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgBorder = new esteb.swing.PanelUI();
        panelUI1 = new esteb.swing.PanelUI();
        inputIdentificacion = new javax.swing.JTextField();
        inputNombre = new javax.swing.JTextField();
        inputCorreo = new javax.swing.JTextField();
        inputContrasena = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(17, 17, 17));

        bgBorder.setBackground(new java.awt.Color(216, 216, 216));
        bgBorder.setRoundbottomLeft(50);
        bgBorder.setRoundtopLeft(50);

        panelUI1.setBackground(new java.awt.Color(236, 236, 236));
        panelUI1.setRoundbottomLeft(25);
        panelUI1.setRoundbottomRight(25);
        panelUI1.setRoundtopLeft(25);
        panelUI1.setRoundtopRight(25);

        inputIdentificacion.setBackground(new java.awt.Color(255, 255, 255));
        inputIdentificacion.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        inputIdentificacion.setForeground(new java.awt.Color(0, 0, 0));
        inputIdentificacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inputIdentificacion.setToolTipText("");
        inputIdentificacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                inputIdentificacionKeyReleased(evt);
            }
        });

        inputNombre.setBackground(new java.awt.Color(255, 255, 255));
        inputNombre.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        inputNombre.setForeground(new java.awt.Color(0, 0, 0));
        inputNombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        inputCorreo.setBackground(new java.awt.Color(255, 255, 255));
        inputCorreo.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        inputCorreo.setForeground(new java.awt.Color(0, 0, 0));
        inputCorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        inputContrasena.setBackground(new java.awt.Color(255, 255, 255));
        inputContrasena.setFont(new java.awt.Font("Ubuntu", 0, 36)); // NOI18N
        inputContrasena.setForeground(new java.awt.Color(0, 0, 0));
        inputContrasena.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        btnRegistrar.setBackground(new java.awt.Color(100, 205, 51));
        btnRegistrar.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("CONFIRMAR");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 255));
        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("VENDEDOR");

        jComboBox1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "vendedor", "administrador" }));

        jLabel2.setBackground(new java.awt.Color(0, 0, 255));
        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("EDITAR/CREAR");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setLabelFor(inputIdentificacion);
        jLabel3.setText("Identificación");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setLabelFor(inputNombre);
        jLabel4.setText("Nombre");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setLabelFor(inputCorreo);
        jLabel5.setText("Correo");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setLabelFor(inputContrasena);
        jLabel6.setText("Contraseña");

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Rol");

        jComboBox2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBox2.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jComboBox2.setForeground(new java.awt.Color(0, 0, 0));
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "activo", "inactivo" }));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Estado");

        javax.swing.GroupLayout panelUI1Layout = new javax.swing.GroupLayout(panelUI1);
        panelUI1.setLayout(panelUI1Layout);
        panelUI1Layout.setHorizontalGroup(
            panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUI1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUI1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(38, 38, 38)))
                .addGap(117, 117, 117))
            .addGroup(panelUI1Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUI1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(inputCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(inputNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(inputIdentificacion, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE))
                    .addGroup(panelUI1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inputContrasena, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelUI1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 64, Short.MAX_VALUE))
            .addGroup(panelUI1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(btnRegistrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUI1Layout.setVerticalGroup(
            panelUI1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUI1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputIdentificacion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inputContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout bgBorderLayout = new javax.swing.GroupLayout(bgBorder);
        bgBorder.setLayout(bgBorderLayout);
        bgBorderLayout.setHorizontalGroup(
            bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgBorderLayout.createSequentialGroup()
                .addContainerGap(246, Short.MAX_VALUE)
                .addComponent(panelUI1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
        bgBorderLayout.setVerticalGroup(
            bgBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgBorderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
        initAlternComponents2();
    }//GEN-LAST:event_btnRegistrarActionPerformed
    
    public void initAlternComponents2(){
        String identificacion = inputIdentificacion.getText();
        String nombreusuario = inputNombre.getText();
        String correo = inputCorreo.getText();
        String contrasena = inputContrasena.getText();
        String rol = (String) jComboBox1.getSelectedItem();
        String estado = (String) jComboBox2.getSelectedItem();
        
        if (idusuario != null){
            // Actualiza el usuario
            Map<String, String> updateData = new HashMap<>();
            updateData.put("idusuario", idusuario);
            updateData.put("identificacion", identificacion);
            updateData.put("nombreusuario", nombreusuario);
            updateData.put("correoelectronico", correo);
            updateData.put("contrasena", contrasena);
            updateData.put("rol", rol);
            updateData.put("estado", estado);
            String datosEnviados = consumo.consumoPOST("http://localhost/apienphp/Usuarios/Update_U.php", updateData);
            JsonObject json = JsonParser.parseString(datosEnviados).getAsJsonObject();
            boolean status = json.get("status").getAsBoolean();
            if (status) {
                bgBorder.removeAll();
                ListadoVendedores listadoDash = new ListadoVendedores();
                listadoDash.setSize(bgBorder.getSize());
                listadoDash.setLocation(0, 0);
                bgBorder.add(listadoDash);
                repaint();
                revalidate();
                System.out.println("Consumo SELECT ALL:-> ACTUALIZADO ");
            }else{
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error. Debe llenar todos los campos para actualizar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            // Ingresa el usuario
            Map<String, String> insertData = new HashMap<>();
            insertData.put("identificacion", identificacion);
            insertData.put("nombreusuario", nombreusuario);
            insertData.put("correoelectronico", correo);
            insertData.put("contrasena", contrasena);
            insertData.put("rol", rol);
            insertData.put("estado", estado);
            String datosEnviados = consumo.consumoPOST("http://localhost/apienphp/Usuarios/Insert_U.php", insertData);
            JsonObject json = JsonParser.parseString(datosEnviados).getAsJsonObject();
            boolean status = json.get("status").getAsBoolean();
             if (status) {
                bgBorder.removeAll();
                ListadoVendedores listadoDash = new ListadoVendedores();
                listadoDash.setSize(bgBorder.getSize());
                listadoDash.setLocation(0, 0);
                bgBorder.add(listadoDash);
                repaint();
                revalidate();
                System.out.println("Consumo SELECT ALL:-> ACTUALIZADO ");
            }else{
                JOptionPane.showMessageDialog(this, "Ha ocurrido un error. Debe llenar todos los campos para ingresar el usuario.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
            
    public void initAlternComponents(){
        String identificacion = inputIdentificacion.getText();
        setInputDoc(identificacion);
        buscarUsuario (endpoint);
    }
    
    private void inputIdentificacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputIdentificacionKeyReleased
       inputIdentificacion.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                initAlternComponents();
                System.out.println("Texto agregado: " + inputIdentificacion.getText());
            }   
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                initAlternComponents();
                System.out.println("Texto eliminado: " + inputIdentificacion.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // No se utiliza en este ejemplo
            }
        });
    }//GEN-LAST:event_inputIdentificacionKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private esteb.swing.PanelUI bgBorder;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JTextField inputContrasena;
    private javax.swing.JTextField inputCorreo;
    private javax.swing.JTextField inputIdentificacion;
    private javax.swing.JTextField inputNombre;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private esteb.swing.PanelUI panelUI1;
    // End of variables declaration//GEN-END:variables
}
