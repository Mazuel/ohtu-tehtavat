
package ohtu.intjoukkosovellus;

public class IntJoukko {

	public final static int KAPASITEETTI = 5, // aloitustalukon koko
			OLETUSKASVATUS = 5; // luotava uusi taulukko on
	// näin paljon isompi kuin vanha
	private int kasvatuskoko = OLETUSKASVATUS; // Uusi taulukko on tämän verran vanhaa suurempi.
	private int[] ljono; // Joukon luvut säilytetään taulukon alkupäässä.
	private int alkioidenLkm = 0; // Tyhjässä joukossa alkioiden_määrä on nolla.

	public IntJoukko() {
		ljono = new int[KAPASITEETTI];
		for (int i = 0; i < ljono.length; i++) {
			ljono[i] = 0;
		}
	}

	public IntJoukko(int kapasiteetti) {
		if (kapasiteetti < 0) {
			throw new IndexOutOfBoundsException("Kapasiteetti ei saa olla pienempi kuin 0");
		}
		ljono = new int[kapasiteetti];
		for (int i = 0; i < ljono.length; i++) {
			ljono[i] = 0;
		}

	}

	public IntJoukko(int kapasiteetti, int kasvatuskoko) {
		if (kapasiteetti < 0) {
			throw new IndexOutOfBoundsException("Kapasiteetti ei saa olla pienempi kuin 0");
		}
		if (kasvatuskoko < 0) {
			throw new IndexOutOfBoundsException("Kasvatuskoko ei saa olla pienempi kuin 0");
		}
		ljono = new int[kapasiteetti];
		for (int i = 0; i < ljono.length; i++) {
			ljono[i] = 0;
		}
		this.kasvatuskoko = kasvatuskoko;

	}

	public boolean lisaa(int luku) {
		if (!kuuluu(luku)) {
			ljono[alkioidenLkm] = luku;
			alkioidenLkm++;
			if (alkioidenLkm % ljono.length == 0) {
				int[] taulukkoNew = new int[ljono.length + kasvatuskoko];
				kopioiTaulukko(ljono, taulukkoNew);
				ljono = taulukkoNew;
			}
			return true;
		}
		return false;
	}

	public boolean kuuluu(int luku) {
		for (int i = 0; i < alkioidenLkm; i++) {
			if (luku == ljono[i]) {
				return true;
			}
		}
		return false;
	}

	public boolean poista(int luku) {
		int kohta = -1;
		for (int i = 0; i < alkioidenLkm; i++) {
			if (luku == ljono[i]) {
				kohta = i;
				ljono[kohta] = 0;
				break;
			}
		}
		if (kohta != -1) {
			for (int j = kohta; j < alkioidenLkm - 1; j++) {
				ljono[j] = ljono[j + 1];
			}
			alkioidenLkm--;
			return true;
		}

		return false;
	}

	private void kopioiTaulukko(int[] vanha, int[] uusi) {
		for (int i = 0; i < vanha.length; i++) {
			uusi[i] = vanha[i];
		}

	}

	public int mahtavuus() {
		return alkioidenLkm;
	}

	@Override
	public String toString() {
		if (alkioidenLkm == 0) {
			return "{}";
		} else if (alkioidenLkm == 1) {
			return "{" + ljono[0] + "}";
		} else {
			String tuotos = "{";
			for (int i = 0; i < alkioidenLkm - 1; i++) {
				tuotos += ljono[i];
				tuotos += ", ";
			}
			tuotos += ljono[alkioidenLkm - 1];
			tuotos += "}";
			return tuotos;
		}
	}

	public int[] toIntArray() {
		int[] taulu = new int[alkioidenLkm];
		for (int i = 0; i < taulu.length; i++) {
			taulu[i] = ljono[i];
		}
		return taulu;
	}

	public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
		IntJoukko x = new IntJoukko();
		int[] aTaulu = a.toIntArray();
		int[] bTaulu = b.toIntArray();
		for (int i = 0; i < aTaulu.length; i++) {
			x.lisaa(aTaulu[i]);
			x.lisaa(bTaulu[i]);
		}
		return x;
	}

	public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
		IntJoukko y = new IntJoukko();
		int[] aTaulu = a.toIntArray();
		for (int i = 0; i < aTaulu.length; i++) {
			if(b.kuuluu(aTaulu[i])) {
				y.lisaa(aTaulu[i]);
			}
		}
		return y;

	}

	public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
		IntJoukko z = new IntJoukko();
		int[] aTaulu = a.toIntArray();
		int[] bTaulu = b.toIntArray();
		for (int i = 0; i < aTaulu.length; i++) {
			z.lisaa(aTaulu[i]);
		}
		for (int i = 0; i < bTaulu.length; i++) {
			z.poista(bTaulu[i]);
		}

		return z;
	}

}
