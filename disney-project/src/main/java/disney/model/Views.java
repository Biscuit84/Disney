package disney.model;



public interface Views {
	
	public static interface ViewsCommon {
	}
	public static interface ViewsAdmin extends ViewsCommon{
	}
	public static interface ViewsBoutique extends ViewsCommon{
	}
	public static interface ViewsBoutiqueDetail extends ViewsCommon{
	}
	public static interface ViewsCarte extends ViewsCommon{
	}
	public static interface ViewsCasesPlateau extends ViewsCommon{
	}
	public static interface CasesPlateauDetail extends ViewsCasesPlateau{
	}
	
	public static interface ViewsCases extends ViewsCommon{
	}
	public static interface ViewsCompte extends ViewsCommon{
	}
	public static interface ViewsHistorique extends ViewsCommon{
	}
	public static interface ViewsHistoriqueDetail extends ViewsCommon{
	}
	public static interface ViewsJoueur extends ViewsCommon{
	}
	public static interface ViewsJoueurHistoriques extends ViewsJoueur{
	}
	public static interface ViewsJoueurPersos extends ViewsJoueur{
	}
	public static interface ViewsJoueurParties extends ViewsJoueur{
	}
	public static interface ViewsPartie extends ViewsCommon{
	}
	public static interface ViewsPartieDetail extends ViewsPartie{
	}
	public static interface ViewsPartieDetailPersos extends ViewsPartieDetail{
	}
	public static interface ViewsPartieDetailJoueurs extends ViewsPartie{
	}

	public static interface ViewsPersonnage extends ViewsCommon{
	}
	public static interface ViewsPersonnageDetail extends ViewsPersonnage{
	}
	
	public static interface ViewsPersoObtenu extends ViewsCommon{
	}
	public static interface ViewsPersoObtenuDetailJoueur extends ViewsCommon{
	}
	public static interface ViewsPersoObtenuDetailPerso extends ViewsCommon{
	}
	
	public static interface ViewsJoueurAndPersos extends ViewsPersoObtenuDetailPerso, ViewsJoueurPersos{
		
	}
	public static interface ViewsPlateau extends ViewsCommon{
	}
	public static interface ViewsPlateauDetail extends ViewsPlateau{
	}
	
	public static interface ViewsAvatar extends ViewsCommon{
	}
	
	public static interface ViewsVie extends ViewsCommon{
	}
	
	public static interface ViewsEtoile extends ViewsCommon{
	}
}
