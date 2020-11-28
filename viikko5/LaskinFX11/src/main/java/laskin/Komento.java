package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class Komento {
	protected TextField tuloskentta;
	protected TextField syotekentta;
	protected Button nollaa;
	protected Button undo;
	protected Sovelluslogiikka sovelluslogiikka;

	public Komento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovelluslogiikka) {
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		this.nollaa = nollaa;
		this.undo = undo;
		this.sovelluslogiikka = sovelluslogiikka;
	}

	public abstract void suorita();

	public abstract void peru();
}