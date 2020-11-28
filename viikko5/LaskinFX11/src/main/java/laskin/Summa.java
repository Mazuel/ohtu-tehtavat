package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends Komento {
	
	int edellinenTulos = 0;
	int edellinenSyote = 0;

	public Summa(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo,
			Sovelluslogiikka sovelluslogiikka) {
		super(tuloskentta, syotekentta, nollaa, undo, sovelluslogiikka);
	}

	@Override
	public void suorita() {
		int luku1 = Integer.parseInt(tuloskentta.getText());
		int luku2 = Integer.parseInt(syotekentta.getText());
		int summa = luku1 + luku2;
		System.out.println(summa);
		edellinenTulos = luku1;
		edellinenSyote = luku2;
		tuloskentta.setText(String.valueOf(summa));

		if (summa == 0) {
			nollaa.disableProperty().set(true);
		} else {
			nollaa.disableProperty().set(false);
		}

		undo.disableProperty().set(false);
	}

	@Override
	public void peru() {
		tuloskentta.setText(String.valueOf(edellinenTulos));
		syotekentta.setText(String.valueOf(edellinenSyote));
	}

}
