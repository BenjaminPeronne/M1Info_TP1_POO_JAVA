package tp_1;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class GUI {
    JFrame fenetre;
    Container contenu;
    JButton btnok;

    public GUI() {
        fenetre = new JFrame("GUI_Client");
        contenu = fenetre.getContentPane();
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenetre.setLocationRelativeTo(null);
        fenetre.setSize(1080, 720);
        fenetre.setVisible(true);
        contenu.setLayout(new BoxLayout(contenu, BoxLayout.Y_AXIS));
        btnok = new JButton("OK");
        contenu.add(btnok);
    }

    public void inputLabel(String type, String nomChamp, JPanel panel) {

        JLabel label = new JLabel(type + " " + nomChamp + " :");

        JComponent champ = null;
        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);

        switch (type) {
            case "int":
                champ = new JFormattedTextField(formatter);
                break;
            case "double":
                champ = new JFormattedTextField(formatter);
                break;

            case "float":
                champ = new JFormattedTextField(formatter);
                break;

            case "long":
                champ = new JFormattedTextField(formatter);
                break;

            case "short":
                champ = new JFormattedTextField(formatter);
                break;

            case "byte":
                champ = new JFormattedTextField(formatter);
                break;

            case "boolean":
                champ = new JCheckBox();
                break;

            case "char":
                champ = new JTextField();
                break;

            case "String":
                champ = new JTextField();
                break;

            default:
                champ = new JTextField();
                break;
        }

        label.setLabelFor(champ);
        panel.add(label);
        champ.setPreferredSize(new Dimension(100, 20));
        panel.add(champ);
        contenu.add(panel);
    }

    // public static void main(String[] args) {
    //     GUI gui = new GUI();
    // }

}
