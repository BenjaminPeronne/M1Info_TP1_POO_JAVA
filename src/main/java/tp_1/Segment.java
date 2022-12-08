package tp_1;

import java.io.Serializable;

public class Segment implements Serializable {
	private static final long serialVersionUID = 1L;
	public String nom;
	public Point P1;
	public Point P2;

	// Constructeur
	public Segment(String nom, Point P1, Point P2) {
		this.nom = nom;
		this.P1 = P1;
		this.P2 = P2;
	}

	public Segment(Point P1, Point P2) {
		this.nom = "";
		this.P1 = P1;
		this.P2 = P2;
	}

	public Segment() {
		this.nom = "";
		this.P1 = new Point();
		this.P2 = new Point();

	}

	// Methodes

	public String toString() {
		return nom + "[" + P1.toString() + "," + P2.toString() + "]";
	}

}
