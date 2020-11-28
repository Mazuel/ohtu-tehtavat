package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaa extends Komento {
	int edellinenTulos = 0;
	int edellinenSyote = 0;

	public Nollaa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo,
			Sovelluslogiikka sovelluslogiikka) {
		super(tuloskentta, syotekentta, nollaa, undo, sovelluslogiikka);
	}

	@Override
	public void suorita() {
		edellinenTulos = Integer.parseInt(tuloskentta.getText());
		edellinenSyote = Integer.parseInt(syotekentta.getText());
		tuloskentta.setText("0");
		syotekentta.setText("0");
	}

	@Override
	public void peru() {
		tuloskentta.setText(String.valueOf(edellinenTulos));
		syotekentta.setText(String.valueOf(edellinenSyote));
	}

}
