package esteb.main;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import esteb.login.Login;
import javax.swing.*;
import java.awt.*;

public class Aplication extends JFrame {

    boolean loginSucess = Login.isLoginSucess();


    public Aplication(){
        init();
    }

    private void init(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200,700));
        setContentPane(new Login());
        setTitle("Spot Master");
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("esteb.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        FlatMacDarkLaf.setup();
        EventQueue.invokeLater(() -> new Aplication().setVisible(true));


    }
}
