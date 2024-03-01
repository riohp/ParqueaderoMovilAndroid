package esteb.login;

import com.formdev.flatlaf.FlatClientProperties;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import consumoApi.ConsumoApi;
import esteb.main.AplictionAdm;
import esteb.main.AplictionVen;
import esteb.main.selecionarParqueadero;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Login extends JPanel {
    ConsumoApi consumoApi = new ConsumoApi();
    private static boolean loginSucess = false;
    public Login(){
        init();
    }
    private void init(){
        setLayout(new MigLayout("fill, insets 20", "[center]", "[center]"));
        txtUser = new JTextField("admin", 20);
        txtPassword = new JPasswordField("admin",20);
        btnLogin = new JButton("Login");
        JPanel panel = new JPanel(new MigLayout("wrap, fillx, insets 35 45 30 45", "fill, 250:280"));
        panel.putClientProperty(FlatClientProperties.STYLE,""+
                "arc:20;"+
                "[light]background:#121832;"+
                "[dark]background:lighten(@background,3%);");
        txtPassword.putClientProperty(FlatClientProperties.STYLE,""+
                "showRevealButton:true");
        btnLogin.putClientProperty(FlatClientProperties.STYLE,""+
                "borderWidth:0;"+
                "focusWidth:0;"+
                "innerFocusWidth:0;");

        txtUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su usuario");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Ingrese su contraseña");

        JLabel lblTitle = new JLabel("Bienvenido!");
        JLabel description = new JLabel("Ingrese sus credenciales para acceder al sistema");
        lblTitle.putClientProperty(FlatClientProperties.STYLE,""+
                "font:bold +10");
        description.putClientProperty(FlatClientProperties.STYLE,""+
                "[light]foreground:lighten(@foreground,50%);"+
                "[dark]foreground:darken(@foreground,50%);");
        btnLogin.addActionListener(e -> Login());
        panel.add(lblTitle);
        panel.add(description);
        panel.add(new JLabel("Usuario"), "gapy 8");
        panel.add(txtUser);
        panel.add(new JLabel("Contraseña"), "gapy 8");
        panel.add(txtPassword);
        panel.add(btnLogin, "gapy 15, span, split 2, align center");
        add(panel);
    }

    public static boolean isLoginSucess() {
        return loginSucess;
    }

    public void Login(){
        String user = txtUser.getText();
        String password = txtPassword.getText();
        Map<String, String> insertData = new HashMap<>();
        insertData.put("user", user);
        insertData.put("pass", password);
        System.out.println("Consumo de api"+ consumoApi.consumoPOST("http://localhost/APIenPHP/Aplication/login.php", insertData));
        String rjson = consumoApi.consumoPOST("http://localhost/APIenPHP/Aplication/login.php", insertData);
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(rjson, JsonObject.class);
        boolean verif = jsonObject.get("data").getAsBoolean();
        if(verif) {
            String rolv = jsonObject.get("rol").getAsString();
            //String estado = jsonObject.get("estado").getAsString();
            System.out.println(jsonObject);
            System.out.println(rolv);
            if(rolv.equals("vendedor") ){
                Map<String, String> insertDataParkign = new HashMap<>();
                insertDataParkign.put("user", user);
                //System.out.println(consumoApi.consumoGETV1("http://localhost/APIenPHP/getAll/PerfilAPi/getInfoPerfil.php", insertDataParkign));
                String rjsonParking = consumoApi.consumoGETV1("http://localhost/APIenPHP/getAll/PerfilAPi/getInfoPerfil.php", insertDataParkign);
                JsonObject jsonObjectParking = gson.fromJson(rjsonParking, JsonObject.class);
                if (jsonObjectParking.getAsJsonArray("data").size() > 1) {
                    selecionarParqueadero selectParking = new selecionarParqueadero(JsonArray.class.cast(jsonObjectParking.getAsJsonArray("data")), user);
                    selectParking.setVisible(true);
                    SwingUtilities.getWindowAncestor(this).dispose();
                }else{
                    AplictionVen aplicationVen = new AplictionVen(user, null, null, null);
                    aplicationVen.setVisible(true);
                    SwingUtilities.getWindowAncestor(this).dispose();
                }
                System.out.println("Login success in login");

            }else if(rolv.equals("administrador")) {
                Map<String, String> insertDataParking = new HashMap<>();
                insertDataParking.put("user", user);
                String rjsonParking = consumoApi.consumoGETV1("http://localhost/APIenPHP/getAll/PerfilAPi/getInfoPerfil.php", insertDataParking);
                JsonObject jsonObjectParking = gson.fromJson(rjsonParking, JsonObject.class);
                if (jsonObjectParking.getAsJsonArray("data").size() > 1) {
                    selecionarParqueadero selectParking = new selecionarParqueadero(JsonArray.class.cast(jsonObjectParking.getAsJsonArray("data")), user);
                    selectParking.setVisible(true);
                    SwingUtilities.getWindowAncestor(this).dispose();
                }else{
                    AplictionAdm aplicationAdm = new AplictionAdm(user, null, null);
                    aplicationAdm.setVisible(true);
                    SwingUtilities.getWindowAncestor(this).dispose();
                }
                System.out.println("Login success in login ADM");
            }else{
                JOptionPane.showMessageDialog(null, jsonObject.get("mesagge").getAsString());
                loginSucess = false;
            }
        }else{
            JOptionPane.showMessageDialog(null, jsonObject.get("mesagge").getAsString());
            loginSucess = false;
        }
    }
    private JTextField txtUser;
    private JPasswordField txtPassword;
    private JButton btnLogin;
}
