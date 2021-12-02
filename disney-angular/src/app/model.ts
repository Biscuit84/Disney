import { HistoriqueHttpService } from "./historique-http.service";

export class Compte {
    id: number;
    version: number;
    login:string;
    password: string;
    nom: string;
    prenom:string;
    mail:string;

    constructor(id?: number, version?: number, login?:string, password?: string, nom?: string,prenom?:string, mail?:string) {
        this.id = id;
        this.version = version;
        this.login=login;
        this.password=password;
        this.nom=nom;
        this.prenom=prenom;
        this.mail=mail;

    }
}


export class Admin extends Compte {
    //rien

    constructor(id?: number, version?: number, login?:string, password?: string, nom?: string,prenom?:string, mail?:string) {
        super(id, version, login, password, nom, prenom, mail);
    }
}

export class Joueur extends Compte {
    pseudo:string;
    level:string;
    life:number;
    nbEtoile:number;
    historiques:  Array<Historique>;
    persos: Array<PersoObtenu>;
    //constructor à completer
    constructor(id?: number, version?: number, login?:string, password?: string, nom?: string,prenom?:string, mail?:string) {
        super(id, version, login, password, nom, prenom, mail);
    }
}



export class Boutique {
    id: number;
    version: number;
    personnages: Array<Personnage>;
    vies:number;
    nbEtoile:number;


//constructor à completer
    constructor(id?: number, version?: number) {
        this.id = id;
        this.version = version;
    }
}

export class Partie {
    id: number;
    version: number;
    plateau:Plateau;
    personnages: Array<Personnage>;
    joueursPartie: Array<Joueur>;

//constructor à completer
    constructor(id?: number, version?: number) {
        this.id = id;
        this.version = version;
    }
}

export class Historique {
    id: number;
    version: number;
    dateHeureDebutPartie:string;
    dateHeureFinPartie:string;
    positionArrivee:number;
    nbEtoilesGagnees:number;
    parties:Array<Partie>;

//constructor à completer
    constructor(id?: number, version?: number) {
        this.id = id;
        this.version = version;
    }
}


export class Plateau {
    id: number;
    version: number;
    nom:string;
    nbCases:number;
    cases:Array<CasesPlateau>;

//constructor à completer
    constructor(id?: number, version?: number) {
        this.id = id;
        this.version = version;
    }
}



export class Cases {
    id: number;
    version: number;
    nom:string;
    parameter:number;
    typeCase:string;



//constructor à completer
    constructor(id?: number, version?: number) {
        this.id = id;
        this.version = version;
    }
}

export class CasesPlateau {
    id: number;
    version: number;
    plateau:Plateau;
    uneCase:Cases;
    joueurs: Array<Joueur>;
    ordreCase:number;



//constructor à completer
    constructor(id?: number, version?: number) {
        this.id = id;
        this.version = version;
    }
}

export class Personnage {
    id: number;
    version: number;
    prince:string;
    mechant:string;
    pouvoir:string;
    position:CasesPlateau;
    prixAchatPerso:number;



  constructor(
    id?: number, 
    version?: number, 
    prince?: string, 
    mechant?: string, 
    pouvoir?: string, 
    position?: CasesPlateau, 
    prixAchatPerso?: number
) {
    this.id = id
    this.version = version
    this.prince = prince
    this.mechant = mechant
    this.pouvoir = pouvoir
    this.position = position
    this.prixAchatPerso = prixAchatPerso
  }


}

export class PersoObtenu {
    id: number;
    version: number;
    perso:Personnage;
    joueur:Joueur;

//constructor à completer
    constructor(id?: number, version?: number) {
        this.id = id;
        this.version = version;
    }
}

