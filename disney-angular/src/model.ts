
export class Compte {
  id: number;
  version: number;
  login: string;
  password: string;
  nom: string;
  prenom: string;
  mail: string;
  role: string;
  avatar: string;
  pseudo: string;
  level: string;
  life: number;
  nbEtoiles: number;
  nbVictoire: number;
  nbDefaite: number;
  partie: Partie;

  constructor(
    id?: number,
    version?: number,
    login?: string,
    password?: string,
    nom?: string,
    prenom?: string,
    mail?: string,
    role?: string,
    avatar?: string,
    pseudo?: string,
    level?: string,
    life?: number,
    nbEtoiles?: number,
    nbVictoire?: number,
    nbDefaite?: number,
    partie?: Partie
  ) {
    this.id = id
    this.version = version
    this.login = login
    this.password = password
    this.nom = nom
    this.prenom = prenom
    this.mail = mail
    this.role = role
    this.avatar = avatar
    this.pseudo = pseudo
    this.level = level
    this.life = life
    this.nbEtoiles = nbEtoiles
    this.nbVictoire = nbVictoire
    this.nbDefaite = nbDefaite
    this.partie = partie
  }




}


export class Admin extends Compte {


  constructor(id?: number, version?: number, login?: string, password?: string, nom?: string, prenom?: string, mail?: string) {
    super(id, version, login, password, nom, prenom, mail);
  }
}

export class Joueur extends Compte {

  historiques: Array<Historique>;
  persos: Array<PersoObtenu>;

  constructor(
    id?: number,
    version?: number,
    login?: string,
    password?: string,
    nom?: string,
    prenom?: string,
    mail?: string,
    pseudo?: string,
    level?: string,
    life?: number,
    nbEtoiles?: number,
    historiques?: Array<Historique>,
    persos?: Array<PersoObtenu>
  ) {
    super(id, version, login, password, nom, prenom, mail);
    this.pseudo = pseudo
    this.level = level
    this.life = life
    this.nbEtoiles = nbEtoiles
    this.historiques = historiques
    this.persos = persos
  }





}



export class Boutique {
  id: number;
  version: number;
  personnages: Array<Personnage>;
  listVies: Array<Vie>;
  listEtoiles: Array<Etoile>;

  constructor(
    id?: number,
    version?: number,
  ) {
    this.id = id
    this.version = version
  }


}

export class Vie {
  id: number;
  version: number;
  nombre: number;
  prix: number;
  image: string;

  constructor(
    id?: number,
    version?: number,
    nombre?: number,
    prix?: number
  ) {
    this.id = id
    this.version = version
    this.nombre = nombre
    this.prix = prix
  }

}

export class Etoile {
  id: number;
  version: number;
  nombre: number;
  prix: number;
  image: string;

  constructor(
    id?: number,
    version?: number,
    nombre?: number,
    prix?: number
  ) {
    this.id = id
    this.version = version
    this.nombre = nombre
    this.prix = prix
  }

}

export class Personnage {
  id: number;
  version: number;
  nom: string;
  prince: string;
  mechant: string;
  pouvoir: string;
  position: CasesPlateau;
  prixAchatPerso: number;
  avatar: string;


  constructor(
    id?: number,
    version?: number,
    nom?: string,
    prince?: string,
    mechant?: string,
    pouvoir?: string,
    prixAchatPerso?: number,
    avatar?: string
  ) {
    this.id = id
    this.version = version
    this.nom = nom
    this.prince = prince
    this.mechant = mechant
    this.pouvoir = pouvoir
    this.prixAchatPerso = prixAchatPerso
    this.avatar = avatar
  }


}

export class PersonnageDto {
  personnage: Personnage;
  persoDejaEnPossession: boolean;
}


export class BoutiqueDto {
  personnages: Array<PersonnageDto>;
  listVies: Array<Vie>;
  listEtoiles: Array<Etoile>;

  constructor(
    personnages?: Array<PersonnageDto>,
    listVies?: Array<Vie>,
    listEtoiles?: Array<Etoile>
  ) {
    this.personnages = personnages
    this.listVies = listVies
    this.listEtoiles = listEtoiles
  }
}

export class BoutiqueEtoileDto {
  idEtoile: Array<number>;

  constructor(idEtoile?: Array<number>) {
    this.idEtoile = idEtoile
  }

}

export class BoutiquePersoAndLifeDto {
  idPersonnages: Array<number>;
  idVies: Array<number>;

  constructor(idPersonnages?: Array<number>, idVies?: Array<number>) {
    this.idPersonnages = idPersonnages
    this.idVies = idVies
  }



}



export class Partie {
  id: number;
  version: number;
  plateau: Plateau;
  personnages: Array<Personnage>;
  joueursPartie: Array<Joueur>;
  nbTourDeJeu:number;

  constructor(
    id?: number,
    version?: number,
    plateau?: Plateau,
    personnages?: Array<Personnage>,
    joueursPartie?: Array<Joueur>,
    nbTourDeJeu?:number
  ) {
    this.id = id
    this.version = version
    this.plateau = plateau
    this.personnages = personnages
    this.joueursPartie = joueursPartie
    this.nbTourDeJeu = nbTourDeJeu
  }

}
/*
export class PartieDTO {
  id: number;
  version: number;
  plateau: Plateau;
  monPersonnage: Personnage;
  nbTourDeJeu:number;
  joueur: Joueur;

  constructor(
    id?: number,
    version?: number,
    plateau?: Plateau,
    monPersonnage?: Personnage,
    nbTourDeJeu?:number,
    joueur?:Joueur,
  ) {
    this.id = id
    this.version = version
    this.plateau = plateau
    this.monPersonnage = monPersonnage
    this.nbTourDeJeu = nbTourDeJeu
    this.joueur= joueur
  }

}
*/

export class TourDeJeuDto {
  valueDice1: number;
  valueDice2: number;
  tourDeJeuEnCours: number;
  positionFutureJoueur: number;
  finPartie: boolean = false;
  effetAActiver: boolean = false;
  


  constructor(
    valueDice1?: number,
    valueDice2?: number,
    tourDeJeuEnCours?: number,
    positionFutureJoueur?: number,
    finPartie?: boolean,
    effetAActiver?: boolean

  ) {
    this.valueDice1 = valueDice1
    this.valueDice2 = valueDice2
    this.tourDeJeuEnCours = tourDeJeuEnCours
    this.positionFutureJoueur = positionFutureJoueur
    this.finPartie = finPartie
    this.effetAActiver = effetAActiver
   
  }

}

export class Historique {
  id: number;
  version: number;
  dateHeureDebutPartie: string;
  dateHeureFinPartie: string;
  positionArrivee: number;
  nbEtoilesGagnees: number;
  victoire: boolean
  partie: Partie;
  personnage: Personnage;


  constructor(
    id?: number,
    version?: number,
    dateHeureDebutPartie?: string,
    dateHeureFinPartie?: string,
    positionArrivee?: number,
    nbEtoilesGagnees?: number,
    victoire?: boolean,
    partie?: Partie,
    personnage?: Personnage
  ) {
    this.id = id
    this.version = version
    this.dateHeureDebutPartie = dateHeureDebutPartie
    this.dateHeureFinPartie = dateHeureFinPartie
    this.positionArrivee = positionArrivee
    this.nbEtoilesGagnees = nbEtoilesGagnees
    this.victoire = victoire
    this.partie = partie
    this.personnage=personnage
  }

}


export class Plateau {
  id: number;
  version: number;
  nom: string;
  nbCases: number;
  cases: Array<CasesPlateau>;


  constructor(
    id?: number,
    version?: number,
    nom?: string,
    nbCases?: number,
    cases?: Array<CasesPlateau>
  ) {
    this.id = id
    this.version = version
    this.nom = nom
    this.nbCases = nbCases
    this.cases = cases
  }

}



export class Cases {
  id: number;
  version: number;
  nom: string;
  parameter: number;
  typeCase: string;

  constructor(
    id?: number,
    version?: number,
    nom?: string,
    parameter?: number,
    typeCase?: string
  ) {
    this.id = id
    this.version = version
    this.nom = nom
    this.parameter = parameter
    this.typeCase = typeCase
  }
}

export class CasesPlateau {
  id: number;
  version: number;
  plateau: Plateau;
  uneCase: Cases;
  joueurs: Array<Joueur>;
  ordreCase: number;


  constructor(
    id?: number,
    version?: number,
    ordreCase?: number,
    plateau?: Plateau,
    uneCase?: Cases,
    joueurs?: Array<Joueur>

  ) {
    this.id = id
    this.version = version
    this.ordreCase = ordreCase
    this.plateau = plateau
    this.uneCase = uneCase
    this.joueurs = joueurs
  }



}



export class PersoObtenu {
  id: number;
  version: number;
  perso: Personnage;
  joueur: Joueur;

  constructor(
    id?: number,
    version?: number,
    perso?: Personnage,
    joueur?: Joueur

  ) {
    this.id = id
    this.version = version
    this.perso = perso
    this.joueur = joueur
  }

}
export class ConnexionDTO {
  mail: string;
  password: string;

  constructor(
    mail?: string,
    password?: string
  ) {
    this.mail = mail
    this.password = password
  }


}

