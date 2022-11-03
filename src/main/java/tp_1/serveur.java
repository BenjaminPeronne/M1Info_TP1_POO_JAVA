/**
 * @author Benjamin Peronne
 * @email contact@benjaminperonne.fr
 * @create date 2022-10-13 16:45:36
 * @modify date 2022-10-13 16:45:36
 * @desc [TP1 - Serveur]
 */

package tp_1;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serveur {
    public static void main(String[] args) {
        serveur serveur1 = new serveur("Serveur1", "192.168.1.9", 8003, "HTTP");

        serveur1.afficher();
        serveur1.ecouter();
    }

    // ==================== Attributs ====================
    protected int serverPort = 8003;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread = null;
    private String nom;
    private String adresseIP;
    private int port;
    private String protocole;

    // ==================== Constructeur ====================
    public serveur(String nom, String adresseIP, int port, String protocole) {
        this.nom = nom;
        this.adresseIP = adresseIP;
        this.port = port;
        this.protocole = protocole;
    }

    // ==================== GETTERS & SETTERS ====================
    // public String getNom() {
    // return this.nom;
    // }

    // public void setNom(String nom) {
    // this.nom = nom;
    // }

    // public String getAdresseIP() {
    // return this.adresseIP;
    // }

    // public void setAdresseIP(String adresseIP) {
    // this.adresseIP = adresseIP;
    // }

    // public int getPort() {
    // return this.port;
    // }

    // public void setPort(int port) {
    // this.port = port;
    // }

    // public String getProtocole() {
    // return this.protocole;
    // }

    // public void setProtocole(String protocole) {
    // this.protocole = protocole;
    // }

    // ==================== Méthodes ====================
    public void afficher() { // Affiche les informations du serveur
        System.out.println("Nom : " + this.nom);
        System.out.println("Adresse IP : " + this.adresseIP);
        System.out.println("Port : " + this.port);
        System.out.println("Protocole : " + this.protocole);
    }

    public void ecouter() { // Ecoute le port du serveur
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (Exception e) {
                if (isStopped()) {
                    System.out.println("Serveur arrêté.");
                    return;
                }
                throw new RuntimeException("Erreur lors de l'acceptation de la connexion client", e);
            }
            new Thread(new WorkerRunnable(clientSocket, "Multithreaded Server")).start(); // Création d'un thread pour
                                                                                          // chaque client
        }
        System.out.println("Serveur arrêté.");
    }

    private synchronized boolean isStopped() { // Vérifie si le serveur est arrêté ou non
        return this.isStopped;
    }

    public synchronized void stop() { // Arrête le serveur et ferme le socket
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la fermeture du servicer", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (Exception e) {
            throw new RuntimeException("Impossible d'ouvrir le port 8003", e);
        }
    }

    public class WorkerRunnable implements Runnable { // Classe interne pour gérer les clients
        protected Socket clientSocket = null;
        protected String serverText = null;

        public WorkerRunnable(Socket clientSocket, String serverText) { // Constructeur
            this.clientSocket = clientSocket;
            this.serverText = serverText;
        }

        public void run() {
            try {
                InputStream input = clientSocket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(input);

                // Récupération de l'objet
                // Object objet = ois.readObject();
                // System.out.println("Objet reçu : " + objet);

                String message = (String) ois.readObject();
                System.out.println(
                        "Message reçu : " + message + " de " + clientSocket.getInetAddress().getHostAddress());

                OutputStream output = clientSocket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(output);
            
                // Envoi d'un message de confirmation au client
                oos.writeObject("Envoi effectué avec succès");
                oos.flush();

                // Fermeture des flux
                ois.close();
                oos.close();

                // Fermeture de la connexion
                clientSocket.close();
            } catch (

            Exception e) {
                // report exception somewhere.
                e.printStackTrace();
            }
        }
    }
}
