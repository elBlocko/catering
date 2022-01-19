package logic;

import java.util.ArrayList;
import java.util.List;

public class TKundenListeBasis extends ArrayList<TKunde> {
	private static final long serialVersionUID = -123456787772345678L;
	List<TKunde> FKundenListe;

	public TKundenListeBasis(List<TKunde> AKundenListe) {
		this.FKundenListe = AKundenListe;
	}
}
