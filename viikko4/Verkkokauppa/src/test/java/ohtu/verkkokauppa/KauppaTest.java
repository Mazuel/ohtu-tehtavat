package ohtu.verkkokauppa;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class KauppaTest {
	
	@Mock
	private Kauppa kauppa;
	
	@Mock
	private Varasto varasto;
	
	private Viitegeneraattori viite = new Viitegeneraattori();
	
	@Mock
	private Pankki pankki;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		when(varasto.saldo(1)).thenReturn(10);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "kaurahiutaleet", 1));
		when(varasto.saldo(2)).thenReturn(10);
		when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "teepussit", 4));
		when(varasto.saldo(3)).thenReturn(0);
		when(varasto.haeTuote(3)).thenReturn(new Tuote(2, "vadelma-pepsi", 3));
		kauppa = new Kauppa(varasto, pankki, viite);
	}

	@Test
	public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaanOikeillaParametreilla() {
		kauppa.aloitaAsiointi();
		kauppa.lisaaKoriin(1);
		kauppa.tilimaksu("pekka", "12345");

		verify(pankki).tilisiirto("pekka", 1, "12345", "33333-44455", 1);
	}
	
	
	@Test
	public void pankinTilisiirtoaKutsutaanOikeinUseammallaTuotteella() {
		kauppa.aloitaAsiointi();
		kauppa.lisaaKoriin(1);
		kauppa.lisaaKoriin(2);
		kauppa.tilimaksu("Kari", "666");
		
		verify(pankki).tilisiirto("Kari", 1, "666", "33333-44455", 5);
	}
	
	@Test
	public void pankinTilisiirtoaKutsutaanOikeinUseammallaSamallaTuotteella() {
		
		kauppa.aloitaAsiointi();
		kauppa.lisaaKoriin(1);
		kauppa.lisaaKoriin(1);
		kauppa.tilimaksu("Kari", "666");
		
		verify(pankki).tilisiirto("Kari", 1, "666", "33333-44455", 2);
	}
	
	@Test
	public void pankinTilisiirtoaKutsutaanOikeinUseammallaJoistaYksiLoppunutTuotteella() {
		kauppa.aloitaAsiointi();
		kauppa.lisaaKoriin(1);
		kauppa.lisaaKoriin(3);
		kauppa.tilimaksu("Kari", "666");
		
		verify(pankki).tilisiirto("Kari", 1, "666", "33333-44455", 1);
	}
	
	@Test
	public void uusiAsiointiNollaaAiemmatOstokset() {
		kauppa.aloitaAsiointi();
		kauppa.lisaaKoriin(1);
		kauppa.lisaaKoriin(3);
		kauppa.tilimaksu("Kari", "666");
		
		//Aloitetaan uusi asiointi
		kauppa.aloitaAsiointi();
		kauppa.lisaaKoriin(2);
		kauppa.lisaaKoriin(2);
		kauppa.tilimaksu("Kari", "666");
		verify(pankki).tilisiirto("Kari", 2, "666", "33333-44455", 8);
	}
	
	@Test
	public void uudellaMaksutapahtumallaOnUusiViitenumero() {
		kauppa.aloitaAsiointi();
		kauppa.lisaaKoriin(1);
		kauppa.lisaaKoriin(3);
		kauppa.tilimaksu("Kari", "666");
		verify(pankki).tilisiirto("Kari", 1, "666", "33333-44455", 1);
		
		kauppa.aloitaAsiointi();
		kauppa.lisaaKoriin(1);
		kauppa.lisaaKoriin(3);
		kauppa.tilimaksu("Kari", "123");
		verify(pankki).tilisiirto("Kari", 2, "123", "33333-44455", 1);
	}
	
	@Test
	public void tuotteenPoistaminenKoristaLuoOikeanMaksutapahtuman() {
		kauppa.aloitaAsiointi();
		kauppa.lisaaKoriin(1);
		kauppa.lisaaKoriin(2);
		kauppa.poistaKorista(2);
		kauppa.tilimaksu("Kari", "666");
		verify(pankki).tilisiirto("Kari", 1, "666", "33333-44455", 1);
	}
	
}
