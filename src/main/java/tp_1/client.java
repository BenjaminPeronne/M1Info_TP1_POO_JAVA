/**
 * @author Benjamin Peronne
 * @email contact@benjaminperonne.fr
 * @create date 2022-10-13 16:45:18
 * @modify date 2022-10-13 16:45:18
 * @desc [TP1 - Client]
 */

package tp_1;

import java.io.OutputStream;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        client client1 = new client("Client1", "192.168.1.9", 8003, "HTTP");
        client1.afficher();
        client1.envoyer();
    }

    // ==================== Attributs ====================
    private String nom;
    private String adresseIP;
    private int port;
    private String protocole;

    // ==================== Constructeur ====================
    public client(String nom, String adresseIP, int port, String protocole) {
        this.nom = nom;
        this.adresseIP = adresseIP;
        this.port = port;
        this.protocole = protocole;
    }

    // ==================== GETTERS & SETTERS ====================
    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseIP() {
        return this.adresseIP;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getProtocole() {
        return this.protocole;
    }

    public void setProtocole(String protocole) {
        this.protocole = protocole;
    }

    // ==================== Méthodes ====================
    public void afficher() { // Affiche les informations du client
        System.out.println("Nom : " + this.nom);
        System.out.println("Adresse IP : " + this.adresseIP);
        System.out.println("Port : " + this.port);
        System.out.println("Protocole : " + this.protocole);
    }

    public void envoyer() { // Envoie un message au serveur
        try {
            Socket socket = new Socket(this.adresseIP, this.port);
            OutputStream os = socket.getOutputStream();
            os.write("Hello".getBytes());
            os.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // public void envoyer() {
    // Socket s = null;
    // try {
    // // Création d'un socket client
    // s = new Socket(this.adresseIP, this.port);

    // // Création d'un flux de sortie
    // InputStream is = s.getInputStream();
    // OutputStream os = s.getOutputStream();

    // ObjectInputStream ois = new ObjectInputStream(is);
    // ObjectOutputStream oos = new ObjectOutputStream(os);

    // // Envoi d'un message
    // oos.writeObject("Hello");

    // // Fermeture des flux et du socket
    // ois.close();
    // oos.close();
    // s.close();

    // } catch (Exception e) {
    // System.out.println(e);
    // }
    // }
}
