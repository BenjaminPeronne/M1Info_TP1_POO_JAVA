/**
 * @author Benjamin Peronne
 * @email contact@benjaminperonne.fr
 * @create date 2022-10-13 16:45:18
 * @modify date 2022-10-13 16:45:18
 * @desc [TP1 - Client]
 */

package tp_1;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

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

        ArrayList<String> listeTypeObjet = new ArrayList<String>(
                Arrays.asList("int", "double", "float", "long", "short", "byte", "boolean", "char"));

        for (Field f : obj.getClass().getDeclaredFields()) {
            // f.setAccessible(true);
            System.out.println(f.getType().getSimpleName() + " : " + f.getName() + "\n");
            if (listeTypeObjet.contains(f.getType().getSimpleName())) {
                System.out.println("Veuillez saisir un " + f.getType().getSimpleName());
                boolean isValide = false;

                switch (f.getType().getSimpleName()) {
                    case "int":
                        while (!isValide) {
                            try {
                                f.set(obj, sc.nextInt());
                                isValide = true;
                            } catch (Exception e) {
                                System.out.println("Veuillez saisir un entier");
                                sc.next();
                            }
                        }
                        break;
                    case "double":
                        while (!isValide) {
                            try {
                                f.set(obj, sc.nextDouble());
                                isValide = true;
                            } catch (Exception e) {
                                System.out.println("Veuillez saisir un double");
                                sc.next();
                            }
                        }
                        break;
                    case "float":
                        while (!isValide) {
                            try {
                                f.set(obj, sc.nextFloat());
                                isValide = true;
                            } catch (Exception e) {
                                System.out.println("Veuillez saisir un float");
                                sc.next();
                            }
                        }
                        break;
                    // case "long":
                    //     while (!isValide) {
                    //         try {
                    //             f.set(obj, sc.nextLong());
                    //             isValide = true;
                    //         } catch (Exception e) {
                    //             System.out.println("Veuillez saisir un long");
                    //             sc.next();
                    //         }
                    //     }
                    //     break;
                    case "short":
                        while (!isValide) {
                            try {
                                f.set(obj, sc.nextShort());
                                isValide = true;
                            } catch (Exception e) {
                                System.out.println("Veuillez saisir un short");
                                sc.next();
                            }
                        }
                        break;
                    case "byte":
                        while (!isValide) {
                            try {
                                f.set(obj, sc.nextByte());
                                isValide = true;
                            } catch (Exception e) {
                                System.out.println("Veuillez saisir un byte");
                                sc.next();
                            }
                        }
                        break;
                    case "boolean":
                        while (!isValide) {
                            try {
                                f.set(obj, sc.nextBoolean());
                                isValide = true;
                            } catch (Exception e) {
                                System.out.println("Veuillez saisir un boolean");
                                sc.next();
                            }
                        }
                        break;
                    case "char":
                        while (!isValide) {
                            try {
                                f.set(obj, sc.next().charAt(0));
                                isValide = true;
                            } catch (Exception e) {
                                System.out.println("Veuillez saisir un char");
                                sc.next();
                            }
                        }
                        break;
                    default:
                        break;
                }
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

            // Ecriture d'un objet
            System.out.println("Ecriture d'un objet");
            input(obj);
            oos.writeObject(obj);

            // Fermeture des flux et du socket
            socketClient.close();
            oos.close();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}