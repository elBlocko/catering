package logic;

import java.util.ArrayList;
import java.util.List;

public class TEssensListeBasis extends ArrayList<TEssen>{

	private static final long serialVersionUID = -123455559012345678L;
	List<TEssen> FEssensListe;

	public TEssensListeBasis(List<TEssen> AEssensListe) {
		this.FEssensListe = AEssensListe;
	}
}
