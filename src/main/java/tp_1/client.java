/**
 * @author Benjamin Peronne
 * @email contact@benjaminperonne.fr
 * @create date 2022-10-13 16:45:18
 * @modify date 2022-10-13 16:45:18
 * @desc [TP1 - Client]
 */

package tp_1;

import java.awt.Component;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.*;

public class client {

    // ==================== Attributs ====================
    private static int serverPort = 8010;
    // private String nom;
    // private String adresseIP;
    // private String protocole;

    // ==================== Constructeur ====================
    // public client(String nom, String adresseIP, int serverPort, String protocole)
    // {
    // this.nom = nom;
    // this.adresseIP = adresseIP;
    // this.serverPort = serverPort;
    // this.protocole = protocole;
    // }

    // ==================== Méthodes ====================
    // public void afficher() { // Affiche les informations du client
    // System.out.println("Nom : " + this.nom);
    // System.out.println("Adresse IP : " + this.adresseIP);
    // System.out.println("Port : " + this.serverPort);
    // System.out.println("Protocole : " + this.protocole);
    // }

    public static void input(Object obj) {
        Scanner sc = new Scanner(System.in);

        ArrayList<String> listeTypeObjet = new ArrayList<String>( // Liste des types
                Arrays.asList("String", "char", "int", "Integer", "Double", "double", "Boolean", "boolean", "float",
                        "Float", "Short", "short", "Long", "long", "Byte", "byte"));
        Object valObject = null; // Valeur de l'objet à saisir

        for (Field f : obj.getClass().getFields()) { // Parcours des attributs de la classe
            System.out.println(f.getType().getSimpleName() + " : " + f.getName() + "\n");
            if (listeTypeObjet.contains(f.getType().getSimpleName())) { // Si le type de l'attribut est dans la liste des types
                // System.out.println("Veuillez saisir un " + f.getType().getSimpleName());
                System.out.println("Veuillez saisir un " + f.getName() + "\n");
                boolean isValide = false; // Permet de savoir si la saisie est valide

                switch (f.getType().getSimpleName()) { // Selon le type de l'attribut
                    case "String" -> {
                        isValide = false;
                        do { // Tant que la saisie n'est pas valide
                            try { 
                                // f.set(obj, sc.nextLine());
                                valObject = sc.nextLine();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un String");
                            }
                        } while (!isValide);
                    }
                    case "char" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextLine().charAt(0));
                                valObject = sc.nextLine().charAt(0);
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un char");
                            }
                        } while (!isValide);
                    }

                    case "int" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextInt());
                                valObject = sc.nextInt();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un int");
                            }
                        } while (!isValide);
                    }

                    case "Integer" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextInt());
                                valObject = sc.nextInt();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un Integer");
                            }
                        } while (!isValide);
                    }

                    case "Double" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextDouble());
                                valObject = sc.nextDouble();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un Double");
                            }
                        } while (!isValide);
                    }

                    case "double" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextDouble());
                                valObject = sc.nextDouble();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un double");
                            }
                        } while (!isValide);
                    }

                    case "Boolean" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextBoolean());
                                valObject = sc.nextBoolean();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un Boolean");
                            }
                        } while (!isValide);
                    }

                    case "boolean" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextBoolean());
                                valObject = sc.nextBoolean();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un boolean");
                            }
                        } while (!isValide);
                    }

                    case "float" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextFloat());
                                valObject = sc.nextFloat();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un float");
                            }
                        } while (!isValide);
                    }

                    case "Float" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextFloat());
                                valObject = sc.nextFloat();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un Float");
                            }
                        } while (!isValide);
                    }

                    case "Short" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextShort());
                                valObject = sc.nextShort();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un Short");
                            }
                        } while (!isValide);
                    }

                    case "short" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextShort());
                                valObject = sc.nextShort();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un short");
                            }
                        } while (!isValide);
                    }

                    case "Long" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextLong());
                                valObject = sc.nextLong();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un Long");
                            }
                        } while (!isValide);
                    }

                    case "long" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextLong());
                                valObject = sc.nextLong();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un long");
                            }
                        } while (!isValide);
                    }

                    case "Byte" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextByte());
                                valObject = sc.nextByte();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un Byte");
                            }
                        } while (!isValide);
                    }

                    case "byte" -> {
                        isValide = false;
                        do {
                            try {
                                // f.set(obj, sc.nextByte());
                                valObject = sc.nextByte();
                            } catch (InputMismatchException e) {
                                isValide = true;
                                sc.nextLine();
                                System.out.println("Veuillez saisir un byte");
                            }
                        } while (!isValide);
                    }

                    default -> {
                        System.out.println("Type non pris en charge");
                        valObject = null;
                    }
                }
            }
        }
    }

    public static void graphiqueInput(Object obj, GUI gui) { // Permet de créer un formulaire graphique
        ArrayList<String> listeObjet = new ArrayList<String>(
                Arrays.asList("String", "char", "Character", "int", "Integer", "Double", "double", "Boolean", "boolean",
                        "float", "Float", "Short", "short", "Long", "long", "Byte", "byte"));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // panel.setLayout(new FlowLayout());

        for (Field f : obj.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            if (listeObjet.contains(f.getType().getSimpleName())) {
                JLabel label = new JLabel(f.getName());
                JTextField textField = new JTextField(10);
                panel.add(label);
                panel.add(textField);
            }
        }
    }

    // ==================== Main ====================
    public static void main(String[] args) {
        Socket socketClient = null;

        try {
            // Création du client
            socketClient = new Socket("localhost", serverPort);
            System.out.println("Connexion établie");

            // Création des flux d'entrée et de sortie
            OutputStream os = socketClient.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            InputStream is = socketClient.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            // Lecture d'un objet
            System.out.println("Lecture d'un objet");
            Object obj = (Object) ois.readObject();
            System.out.println(obj);

            if (obj instanceof String && obj.equals("fin")) {
                System.out.println("Fin de la connexion");
                socketClient.close();
            } else {
                // Ecriture d'un objet
                System.out.println("Ecriture d'un objet");
                input(obj);
                oos.writeObject(obj);
                
                // Envoi d'un objet
                System.out.println("Envoi d'un objet");
                oos.writeObject(obj);

                // GUI gui = new GUI();
                // gui.fenetre.setTitle(obj.getClass().getSimpleName());
                // graphiqueInput(obj, gui);
                // gui.contenu.add(gui.btnok);
                // gui.fenetre.pack();
                // ArrayList<JPanel> jpanel = new ArrayList<JPanel>();
                // for (Component c : gui.contenu.getComponents()) {
                //     if (c.getClass().getSimpleName().equals("JPanel")) {
                //         jpanel.add((JPanel) c);
                //     }
                // }
                // for (JPanel p : jpanel) {
                //     for (Component c : p.getComponents()) {
                //         if (c.getClass().getSimpleName().equals("JTextField")) {
                //             JTextField tf = (JTextField) c;
                //             System.out.println(tf.getText());
                //         }
                //     }
                // }
            }

            // Fermeture des flux et du socket
            socketClient.close();
            oos.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}