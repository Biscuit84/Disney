import { Component, ElementRef, OnInit, ViewChild, NgZone } from '@angular/core';
import { pionPlayer, Square } from './plateau-canvas-util';
import * as $ from 'jquery';
import { Router } from '@angular/router';
import { PageConnexionService } from 'src/app/page-connexion/page-connexion.service';
import { AppConfigService } from 'src/app/app-config.service';
import { PartieHttpService } from 'src/app/partie-http.service';
import { Cases, CasesPlateau, Compte, Joueur, Partie, Personnage, Plateau, TourDeJeuDto } from 'src/model';
import { PlateauHttpService } from 'src/app/plateau-http.service';
import { JoueurHttpService } from 'src/app/joueur-http.service';
import { CaseHttpService } from 'src/app/case-http.service';

@Component({
  selector: 'ecran-de-jeu, [ecran-de-jeu]',
  templateUrl: './ecran-de-jeu.component.html',
  styleUrls: ['./ecran-de-jeu.component.css']
})
export class EcranDeJeuComponent implements OnInit {


  ////////////////// On cherche en base les données du plateau //////////////////
  // en fonction du joueur
  idJoueur: number;
  joueur: Compte;
  idPartie: number;
  partie: Partie = new Partie;
  personnage:Personnage;

  plateau: Plateau;
  public tourEnCours: TourDeJeuDto;
  public casesPlateau:Array<CasesPlateau>;
  public cases:Array<Cases>;


  //moi: Joueur = new Joueur;
  ////////////////////////// CONSTRUCTEUR  //////////////////////////
  constructor(public compteService: PageConnexionService, public partieService: PartieHttpService, private plateauService: PlateauHttpService, private joueurService: JoueurHttpService, private casesService: CaseHttpService,) {
    this.joueur = this.compteService.compte;
    this.idJoueur = this.joueur.id;
    this.partie = this.partieService.LaPartie;
    console.log(this.partie);
    this.personnage=this.partieService.LaPartie.personnages[0];
    this.plateau = this.partieService.LaPartie.plateau; // on recupere le plateau, son id et son nb de case seulement
    //this.casesPlateau=this.partieService.LaPartie.plateau.casesPlateau;
    //console.log(this.casesPlateau);
  }

  ////////////////////////// DECLARATION DES VARIABLES //////////////////////////

  // variables : 
  public velocityH: number = 1; // nb de pixel d'avancement du pion par raffraichissement (10ms)
  public velocityW: number = 1;
  public tauxRaffraichissement: number = 10; // vitesse du setinterval
  public niveauDeVitesseDuPion: number = 1;
  // vitesse
  public valeurdelavitesse: number = 1;
  public slider = document.getElementById("vitesseRange");
  public output = document.getElementById("vitesse");

  // lancement partie var
  public partieEnCours: boolean = true;
  public Tour: number = 0;
  public joueurActuel: number = 0;
  public tourActuel = document.getElementById("NbTour");
  public tourJoueur = document.getElementById("JoueurTour");

  // les des
  public nombreDe: number = 2;
  public valeurDesDes: number = 0;

  // plateau et les cases
  public nbcasePlateau: number;
  public format: [number, number];
  public w: number;// = 800;
  public h: number;// = 800;
  public nBcaseW: number;
  public nBcaseH: number;
  public listeCases = [];


  // // taille des cases
  public taillecaseW: number;// = this.w / this.nBcaseW;
  public taillecaseH: number;// = this.h / this.nBcaseH;

  //initialisation des canvas : 
  ctxPlateau: CanvasRenderingContext2D;
  ctxJoueur: CanvasRenderingContext2D;
  ctxPlayerIA1: CanvasRenderingContext2D;
  ctxPlayerIA2: CanvasRenderingContext2D;
  ctxPlayerIA3: CanvasRenderingContext2D;

  @ViewChild('canvasPlateau', { static: true })
  canvasPlateau: ElementRef<HTMLCanvasElement>;
  @ViewChild('canvasJoueur', { static: true })
  canvasJoueur: ElementRef<HTMLCanvasElement>;
  @ViewChild('canvasIA1', { static: true })
  canvasIA1: ElementRef<HTMLCanvasElement>;
  @ViewChild('canvasIA1', { static: true })
  canvasIA2: ElementRef<HTMLCanvasElement>;
  @ViewChild('canvasIA1', { static: true })
  canvasIA3: ElementRef<HTMLCanvasElement>;

  // création des objets images
  backgroundImage = new Image();
  imagePlayer = new Image();
  imagePlayerIA1 = new Image();
  imagePlayerIA2 = new Image();
  imagePlayerIA3 = new Image();

  // création des objets pion
  public pionJoueur: pionPlayer = new pionPlayer(0, '../../../assets/images/jeu/player.png', 0, 0, 0, 0, 0, 0, false, 0, false);
  public pionIA1: pionPlayer = new pionPlayer(1, '../../../assets/images/jeu/ennemi.jpg', 0, 0, 0, 0, 0, 0, false, 0, false);
  public pionIA2: pionPlayer = new pionPlayer(2, '../../../assets/images/jeu/dingo.png', 0, 0, 0, 0, 0, 0, false, 0, false);
  public pionIA3: pionPlayer = new pionPlayer(3, '../../../assets/images/jeu/donald.png', 0, 0, 0, 0, 0, 0, false, 0, false);
  public listePion: pionPlayer[] = [this.pionJoueur, this.pionIA1, this.pionIA2, this.pionIA3];

  // condition des boutons
  public commandeDispo = true; // on peut appuyer sur les boutons
  public dispoBoutonJouer = true;
  public dispoBoutonFin = true;
  public dispoBoutonPouvoir = false; // pas encore implémenté
  public volumeSon:number = 100;
  ////////////////////////// ngOnInit  //////////////////////////
  // note : ça sert à initialiser des données et est appelé qu'une fois ! 

  ngOnInit() {

    //initialisation des canvas : 
    this.ctxPlateau = this.canvasPlateau.nativeElement.getContext('2d');
    this.ctxJoueur = this.canvasJoueur.nativeElement.getContext('2d');
    this.ctxPlayerIA1 = this.canvasIA1.nativeElement.getContext('2d');
    this.ctxPlayerIA2 = this.canvasIA2.nativeElement.getContext('2d');
    this.ctxPlayerIA3 = this.canvasIA3.nativeElement.getContext('2d');

    // quel plateau 



    this.drawPlateau(this, this.plateau);

    // joueur
    this.drawPlayer(this, this.pionJoueur);
    this.drawPlayer(this, this.pionIA1);
    this.drawPlayer(this, this.pionIA2);
    this.drawPlayer(this, this.pionIA3);

    // actualisation (en setinterval pour les deplacements)
    var actualisationJoueur1 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionJoueur);
    var actualisationJoueur2 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionIA1);
    var actualisationJoueur3 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionIA2);
    var actualisationJoueur4 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionIA3);

  } //ngOnInit fin



  ngOnDestroy() {
    // clearInterval(this.actualisationJoueur1);
    //cancelAnimationFrame(this.requestId);
  }


  ////////////////////////// Methode de la classe liées aux boutons  //////////////////////////

  // bouton joueur
  Play() {
    this.Jouer(this, this.pionJoueur);

  }

  Pouvoir(){
    console.log("pouvoir")
  }

  // bouton fin de tour
  EndTurn() {
    this.FinDeTour(this, this.pionJoueur);
  }







  ////////////////////////// Methode de la classe liées au jeu  //////////////////////////


  nbcaseDemande(): [number, number] {
    if (this.nbcasePlateau <= 9) { this.nBcaseW = 3; this.nBcaseH = 3 }
    else if (this.nbcasePlateau <= 16) { this.nBcaseW = 4; this.nBcaseH = 4; }
    else if (this.nbcasePlateau <= 25) { this.nBcaseW = 5; this.nBcaseH = 5; }
    else if (this.nbcasePlateau <= 36) { this.nBcaseW = 6; this.nBcaseH = 6; }
    else if (this.nbcasePlateau <= 49) { this.nBcaseW = 7; this.nBcaseH = 7; }
    else if (this.nbcasePlateau <= 64) { this.nBcaseW = 8; this.nBcaseH = 8; }
    else if (this.nbcasePlateau <= 91) { this.nBcaseW = 9; this.nBcaseH = 9; }
    else if (this.nbcasePlateau <= 100) { this.nBcaseW = 10; this.nBcaseH = 10; }

    var format: [number, number] = [this.nBcaseW, this.nBcaseH];
    return format;
  }

  // dessine le plateau
  drawPlateau(self, plateau) {
    /*plateau : 
      id: number;
      version: number;
      nom: string;
      nbCases: number;
      cases: Array<CasesPlateau>;
    */

      



    //this.nbcasePlateau = 61; 
    this.nbcasePlateau = this.plateau.nbCases;

    // adaptation du de la grille du plateau
    this.format = this.nbcaseDemande();
    this.nBcaseW = this.format[0];
    this.nBcaseH = this.format[1];

    // taille du plateau 
    let wMax: number = self.canvasPlateau.nativeElement.width;
    let hMax: number = self.canvasPlateau.nativeElement.height;
    this.w = wMax - (wMax % this.nBcaseW); // pour couper les cases proprement
    this.h = hMax - (hMax % this.nBcaseH);

    // taille des cases
    this.taillecaseW = this.w / this.nBcaseW;
    this.taillecaseH = this.h / this.nBcaseH;

    // création des cases
    let numeroCase = 0;

    // sens de la ligne
    for (let y = 0; y < this.nBcaseW; y++) {

      for (let x = 0; x < this.nBcaseH; x++) {
        // on créé la case
        var CaseVirtuelle = {
          numero: 0,
          positionCaseX: 0,
          positionCaseY: 0
        }

        // incrémentation
        numeroCase++;

        // si on devrait dépasser le tableau
        if (numeroCase > this.nbcasePlateau) { break; } // on depasse le nombre de case

        // sens
        if (isEven(y)) { // ligne paire (on va dans le bon sens)
          var pX = this.taillecaseW * x;
        }
        else { // ligne impaire  (on va dans le sens inverse)
          var pX = this.taillecaseW * (this.nBcaseW - x - 1);
        }
        var pY = this.taillecaseH * y;

        // on memorise la case :
        CaseVirtuelle.numero = numeroCase;
        CaseVirtuelle.positionCaseX = pX;
        CaseVirtuelle.positionCaseY = pY;
        this.listeCases.push(CaseVirtuelle);

        // on dessine la case :
        this.ctxPlateau.beginPath();
        this.ctxPlateau.rect(pX, pY, this.taillecaseW, this.taillecaseH);  //ctx.rect(x, y, width, height);

        // pour le test :
        if (isEven(numeroCase)) {
          this.ctxPlateau.fillStyle = "lightpink"; // la couleur de la case
          this.ctxPlateau.globalAlpha = 0.5; // c'est l'opacité
        }
        else {
          this.ctxPlateau.fillStyle = "lightblue";
          this.ctxPlateau.globalAlpha = 0.5;
        }

        //ctx.strokeStyle = "black";
        //ctx.stroke();
        this.ctxPlateau.fill(); // ça remplit la forme (case ici)
        this.ctxPlateau.font = '100px';
        this.ctxPlateau.textBaseline = 'hanging';
        this.ctxPlateau.textAlign = "center";
        var affichecase = numeroCase - 1;
        if (numeroCase == this.nbcasePlateau) {
          var str = "arrivée";
        }
        else if (numeroCase == 1) {
          var str = "départ";
        }
        else {
          var str = "case " + affichecase;
        }
        this.ctxPlateau.strokeText(str, pX + this.taillecaseW / 2, pY + this.taillecaseH / 2);  //ctx.strokeText(texte, x, y [, largeurMax]);
        this.ctxPlateau.closePath();
      }
    }
  }


  // dessine un pion
  drawPlayer = function (self, pion) {

    // vitesse en fonciton du range
    let n: number = self.niveauDeVitesseDuPion;

    if (n == 1) { self.velocityH = 1; self.velocityW = 1; }               // tres lent
    else if (n == 2) { self.velocityH = self.taillecaseH / 32; self.velocityW = self.taillecaseW / 32; }
    else if (n == 3) { self.velocityH = self.taillecaseH / 16; self.velocityW = self.taillecaseW / 16; }
    else if (n == 4) { self.velocityH = self.taillecaseH / 8; self.velocityW = self.taillecaseW / 8; }
    else if (n == 5) { self.velocityH = self.taillecaseH / 4; self.velocityW = self.taillecaseW / 4; }
    else if (n == 6) { self.velocityH = self.taillecaseH / 2; self.velocityW = self.taillecaseW / 2; }
    else if (n == 7) { self.velocityH = self.taillecaseH; self.velocityW = self.taillecaseW; }     // tres rapide


    // trouver A quel index de case se trouve le joueur
    for (let i = 0; i < self.nbcasePlateau; i++) {
      if (self.listeCases[i].positionCaseX === pion.positionXPlayer && self.listeCases[i].positionCaseY === pion.positionYPlayer) { //=== : egalité stricte => le joueur est sur la case i
        pion.positionIndexCasePlayer = i;
      }
    }

    //trouver les index suivants et précédents
    pion.positionIndexCasePlayerSuivante = pion.positionIndexCasePlayer;
    pion.positionIndexCasePlayerSuivante++;
    pion.positionIndexCasePlayerPrecedente = pion.positionIndexCasePlayer;
    pion.positionIndexCasePlayerPrecedente--;


    let diff: number = pion.positionIndexCasePlayer - pion.futurePositionIndexCasePlayer;


    // rentre dans la boucle seulement si l'index <= à la longueur de la liste des cases du plateau
    if (pion.positionIndexCasePlayerSuivante <= self.nbcasePlateau - 1 || pion.positionIndexCasePlayerPrecedente >= 0) {

      if (diff < 0) {
        pion.playerIsMoving = true;
        self.dispoBoutonJouer = false;
        self.dispoBoutonFin = false;
        //console.log("diff:" + diff + ", position Y du pion : " + pion.positionYPlayer);
        //console.log("on avance de" + diff);

        if (self.listeCases[pion.positionIndexCasePlayer].positionCaseY < self.listeCases[pion.positionIndexCasePlayerSuivante].positionCaseY) {      // avancer case par case
          pion.positionYPlayer += self.velocityH;
          //console.log("on avance en Y")
        }

        else if (self.listeCases[pion.positionIndexCasePlayer].positionCaseY > self.listeCases[pion.positionIndexCasePlayerSuivante].positionCaseY) {        //reculer case par case
          pion.positionYPlayer -= self.velocityH;
          //console.log("on recule en Y")
        }

        if (self.listeCases[pion.positionIndexCasePlayer].positionCaseX < self.listeCases[pion.positionIndexCasePlayerSuivante].positionCaseX) {      // avancer case par case
          pion.positionXPlayer += self.velocityW;
        }
        else if (self.listeCases[pion.positionIndexCasePlayer].positionCaseX > self.listeCases[pion.positionIndexCasePlayerSuivante].positionCaseX) {      //reculer case par case
          pion.positionXPlayer -= self.velocityW;
        }
      }
      else if (diff > 0) {
        self.dispoBoutonJouer = false;
        self.dispoBoutonFin = false;

        console.log("on recule de" + diff);
        pion.playerIsMoving = true;
        if (self.listeCases[pion.positionIndexCasePlayer].positionCaseY < self.listeCases[pion.positionIndexCasePlayerPrecedente].positionCaseY) {      // avancer case par case
          pion.positionYPlayer += self.velocityH;
        }
        else if (self.listeCases[pion.positionIndexCasePlayer].positionCaseY > self.listeCases[pion.positionIndexCasePlayerPrecedente].positionCaseY) {        //reculer case par case
          pion.positionYPlayer -= self.velocityH;
        }
        if (self.listeCases[pion.positionIndexCasePlayer].positionCaseX < self.listeCases[pion.positionIndexCasePlayerPrecedente].positionCaseX) {      // avancer case par case
          pion.positionXPlayer += self.velocityW;
        }
        else if (self.listeCases[pion.positionIndexCasePlayer].positionCaseX > self.listeCases[pion.positionIndexCasePlayerPrecedente].positionCaseX) {      //reculer case par case
          pion.positionXPlayer -= self.velocityW;
        }

      }
      else if (diff == 0) {
        //  console.log("le pion"+ pion.numeroPassage+ "est à l'arret");
        pion.playerIsMoving = false;
        if (self.partieEnCours == false && pion.finished == true) {
          //setTimeout(finDePartie, 2000, pion);
        }

        if (pion.numeroPassage == 0 && self.joueurActuel == 0) {
          // on est a l'arret donc on peut réaffichier le bouton fin de tour
          self.dispoBoutonJouer = true;
          self.dispoBoutonFin = true;
        }
      }
    }


    // redessiner le player pour le refresh
    if (pion.numeroPassage == 0) {
      let image = new Image();
      image.width = self.taillecaseW / 2;
      image.height = self.taillecaseH / 2;
      image.src = pion.image;
      image.onload = function () {
        //console.log("dans le log image");
        self.ctxJoueur.clearRect(0, 0, self.canvasJoueur.nativeElement.width, self.canvasJoueur.nativeElement.height); //clear pour refresh
        self.ctxJoueur.drawImage(image, pion.positionXPlayer, pion.positionYPlayer, image.width, image.height);
      }
    }
    else if (pion.numeroPassage == 1) {
      let positionx = pion.positionXPlayer + self.taillecaseW / 2;
      let positiony = pion.positionYPlayer;
      let image = new Image();
      image.width = self.taillecaseW / 2;
      image.height = self.taillecaseH / 2;
      image.src = pion.image;
      image.onload = function () {
        self.ctxPlayerIA1.clearRect(0, 0, self.canvasIA1.nativeElement.width, self.canvasIA1.nativeElement.height);
        self.ctxPlayerIA1.drawImage(image, positionx, positiony, image.width, image.height);
      }
    }
    else if (pion.numeroPassage == 2) {
      let positionx = pion.positionXPlayer;
      let positiony = pion.positionYPlayer + self.taillecaseH / 2;
      let image = new Image();
      image.width = self.taillecaseW / 2;
      image.height = self.taillecaseH / 2;
      image.src = pion.image;
      image.onload = function () {
        self.ctxPlayerIA2.clearRect(0, 0, self.canvasIA2.nativeElement.width, self.canvasIA2.nativeElement.height);
        self.ctxPlayerIA2.drawImage(image, positionx, positiony, image.width, image.height);
      }
    }

    else if (pion.numeroPassage == 3) {
      let positionx = pion.positionXPlayer + self.taillecaseW / 2;
      let positiony = pion.positionYPlayer + self.taillecaseH / 2;
      let image = new Image();
      image.width = self.taillecaseW / 2;
      image.height = self.taillecaseH / 2;
      image.src = pion.image;
      image.onload = function () {
        self.ctxPlayerIA3.clearRect(0, 0, self.canvasIA3.nativeElement.width, self.canvasIA3.nativeElement.height);
        self.ctxPlayerIA3.drawImage(image, positionx, positiony, image.width, image.height);
      }
    }

  }




  // permet de jouer
  Jouer(self, pion) {

    self.partieService.GameTour(self.idJoueur, self.partie).subscribe(res => {
      self.tourEnCours = res;
      console.log("TOUR DE JEUX : {}", this.tourEnCours);


      console.log("///////")
      console.log(self.tourEnCours)

      // animation des dés / sons
      playSoundDice();
      self.roulementDe(); // pour l'affichage seulement

      // lancement du tour 
      setTimeout(self.TourJoueur, 2200, self, pion, self.tourEnCours);  //setTimeout(TourJoueur(pionJoueur), 2200); //marche pas  //setTimeout(function() {TourJoueur(pionJoueur);}, 2200); //autre ecriture
      self.joueurActuel = pion.numeroPassage;

      // LE JOUEUR PEUT FINIR SON TOUR 
      if (self.joueurActuel == 0) {
        self.dispoBoutonJouer = false;
        self.dispoBoutonFin = true;
        //$("#boutonJouer").prop('disabled', true);
        //$("#boutonFinDeTour").prop('disabled', false);
      }

      // LES PIONS UN ET DEUX FINISSENT LEURS TOURS AUTO
      else if (self.joueurActuel == 1 || self.joueurActuel == 2) { // si ce n'est pas le joueur qui joue on "click" auto sur la suite
        let pionSuivant = self.listePion[self.joueurActuel + 1];
        setTimeout(self.FinDeTour, 5000, self, pionSuivant, this.tourEnCours); // passe le tour auto
      }

      // LE DERNEIR PION A FINI, CEST AU TOUR DU JOUEUR
      else if (self.joueurActuel == 3) {
        self.joueurActuel = self.listePion[0].numeroPassage; // on revient au joueur
        //$("#boutonJouer").prop('disabled', false);
        //$("#boutonFinDeTour").prop('disabled', false);
        self.dispoBoutonJouer = true;
        self.dispoBoutonFin = true;
      }

    });
  }


  // permet de finir son tour et donc de faire jouer les IA
  FinDeTour(self, pion) {
    // si la partie n'est pas finie
    if (self.partieEnCours == true) {

      // PION 1, 2 et 3 ONT FINI et ça passe au suivnat
      if (self.joueurActuel != 3) { // n'est pas le dernier de la liste (donc pion 4)
        self.joueurActuel++; // console.log("on incremente le numero du joueur")
        let pionSuivant = self.listePion[self.joueurActuel];
        setTimeout(self.Jouer, 3000, self, pionSuivant);

        //$("#boutonJouer").prop('disabled', true);
        //$("#boutonFinDeTour").prop('disabled', true);
        self.dispoBoutonJouer = false;
        self.dispoBoutonFin = false;
      }

      //PION 4 a finit et ça repasse au joueur
      else {
        self.joueurActuel = 0;
        self.Tour++;// incrémente le nombre de tour

        //$("#boutonJouer").prop('disabled', false);
        //$("#boutonFinDeTour").prop('disabled', false);
        self.dispoBoutonJouer = true;
        self.dispoBoutonFin = true;
      }
    }

    // si la partie est finie ! 
    else if (self.partieEnCours == false) {
      //console.log("findetour, if partieencours false")
      self.commandeDispo = false;
      setTimeout(self.finDePartie, 20000, self, pion,)
      //self.finDePartie(self, pion);
    }
  }


  // lances les dés et donne la valeur que doit atteindre le pion
  TourJoueur(self, pion, tour) {
    /*TourDeJeuDto
    valueDice1: number;
    valueDice2: number;
    tourDeJeuEnCours: number;
    positionFutureJoueur: number;
    finPartie: boolean = false;
    effetAActiver: boolean = false;
    */

    let totalDe = 0;

    // si on decide de n'avoir qu'un ou deux des
    self.AfficheDe(tour.valueDice1, "imageDe");
    if (self.nombreDe == 1) {
      totalDe = tour.valueDice1;
    }
    else if (self.nombreDe == 2) {
      self.AfficheDe(tour.valueDice2, "imageDe2");
      totalDe = tour.valueDice1 + tour.valueDice2;
    }

    self.valeurDesDes = totalDe;

    // avant avec que le front : 
    // //calcule le nouvel index:
    // var nouvelIndex = Number(pion.positionIndexCasePlayer) + Number(totalDe); // pour les conditions du front
    // //console.log(tour.positionFutureJoueur - nouvelIndex);
    // // gere le cas où on dépasse la case arrivee
    // if (nouvelIndex >= self.nbcasePlateau - 1) {
    //   nouvelIndex = self.nbcasePlateau - 1;
    //   // la partie est terminée / le pion a gagné
    //   self.partieEnCours = false;
    //   pion.finished = true; // c'est CE pion qui a gagné
    //   console.log("la partie est terminée")
    //   // si c'est le joeur qui gagne : 
    //   if (pion.numeroPassage == 0) {
    //     // on passera pas dans fin de tour sauf si on clique donc
    //     setTimeout(self.finDePartie, 15000, self, pion,)
    //   }
    // }
    // //détermine la future position X et Y du player
    // pion.futurePositionIndexCasePlayer = nouvelIndex;
    //calcule le nouvel index:
    //var nouvelIndex = Number(pion.positionIndexCasePlayer) + Number(totalDe); // pour les conditions du front
    //console.log(tour.positionFutureJoueur - nouvelIndex);


    // gere le cas où on dépasse la case arrivee
    if (tour.positionFutureJoueur >= self.nbcasePlateau - 1) {
      // la partie est terminée / le pion a gagné
      self.partieEnCours = false;
      pion.finished = true; // c'est CE pion qui a gagné
      console.log("la partie est terminée")

      if (tour.findepartie == true) {//la partie est finie et ça a été vérifié avec le back.
        console.log("on rentre ici")
        setTimeout(self.finDePartie, 15000, self, pion);
        clearInterval(self.actualisationJoueur1);
        clearInterval(self.actualisationJoueur2);
        clearInterval(self.actualisationJoueur3);
        clearInterval(self.actualisationJoueur4);
      }
    }

    //la position du pion est celle du back.
    pion.futurePositionIndexCasePlayer = tour.positionFutureJoueur;
    //// TODO: faire les actions/pouvoirs des joueurs
  }



  // lance un dé et l'affiche
  DiceValue(idDe) {
    var lanceDe = Math.floor(Math.random() * 6) + 1;
    var imageachanger = document.getElementById(idDe);
    switch (lanceDe) {
      case 1:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de1.png");
        break;
      case 2:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de2.png");
        break;
      case 3:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de3.png");
        break;
      case 4:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de4.png");
        break;
      case 5:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de5.png");
        break;
      case 6:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de6.png");
        break;
      default: imageachanger.setAttribute("src", "../../assets/images/jeu/de0.png");
    }
    return lanceDe;
  }

  AfficheDe(lanceDe, idDe) {
    var imageachanger = document.getElementById(idDe);
    switch (lanceDe) {
      case 1:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de1.png");
        break;
      case 2:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de2.png");
        break;
      case 3:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de3.png");
        break;
      case 4:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de4.png");
        break;
      case 5:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de5.png");
        break;
      case 6:
        imageachanger.setAttribute("src", "../../assets/images/jeu/de6.png");
        break;
      default: imageachanger.setAttribute("src", "../../assets/images/jeu/de0.png");
    }
  }


  // affiche des dés qui roule en 2D
  roulementDe() {
    var idDe = "imageDe";
    setTimeout(this.DiceValue, 400, idDe);
    setTimeout(this.DiceValue, 600, idDe);
    setTimeout(this.DiceValue, 700, idDe);
    setTimeout(this.DiceValue, 750, idDe);
    setTimeout(this.DiceValue, 900, idDe);
    setTimeout(this.DiceValue, 1200, idDe);
    setTimeout(this.DiceValue, 1700, idDe);
    var idDe2 = "imageDe2";
    setTimeout(this.DiceValue, 400, idDe2);
    setTimeout(this.DiceValue, 500, idDe2);
    setTimeout(this.DiceValue, 650, idDe2);
    setTimeout(this.DiceValue, 800, idDe2);
    setTimeout(this.DiceValue, 950, idDe2);
    setTimeout(this.DiceValue, 1300, idDe2);
    setTimeout(this.DiceValue, 1800, idDe2);
  }


  /*
  
  
    Render(): void {
      this.drawAll();
      this.ctxPlateau.globalCompositeOperation = "source-over"; // pour afficher le pion SUR le fond
      this.ctxJoueur.globalCompositeOperation = "source-over"; // pour afficher le pion SUR le fond
      //setInterval()
      // slider.oninput = function () {
      //   output.innerHTML = this.value;
      //   niveauDeVitesseDuPion = this.value;
      // }
      // niveauDeVitesseDuPion = slider.value;
  
  
    }
  
  */

  /*
    drawPlayers() {
      // if (pionIA1.playerIsMoving || pionIA2.playerIsMoving || pionIA3.playerIsMoving || pionJoueur.playerIsMoving) {
      //   slider.setAttribute('disabled', true);
      //   //console.log("un pion bouge")
      // }
      // else {
      //   slider.removeAttribute('disabled');
      //   //console.log("tous les pions sont à l'arret")
  
      // }
    }
  */



  finDePartie(self, pion) {


    console.log("on est arrivé / fin de partie ")
    // on clear les set intervals pour pas qu'ils tournent dans le vide 


    if (pion.numeroPassage == 0) {
      console.log("on est arrivé gagné")
      // le joueur à gagner
      self.router.navigate(['jeu/findepartie']);

    } else {
      // le joueur à perdu
      console.log("on est arrivé perdu")
      self.router.navigate(['jeu/findepartie']);
    }
  }
}






//////////////////////////////////////////////////////////////////////////////////////////
// son

function playSoundDice() {
  document.getElementById("diceThrowAudio").setAttribute('src', '../../assets/sons/DiceThrow.mp3');
  const myaudio = document.getElementById("diceThrowAudio");
  (myaudio as HTMLAudioElement).play();
}








// CODE POUR LE SON
// var e = document.querySelector('.volume-slider-con');
// var eInner = document.querySelector('.volume-slider');
// var audio = document.querySelector('audio');
// var drag = false;
// e.addEventListener('mousedown', function (ev) {
//   drag = true;
//   updateBar(ev.clientX);
// });
// document.addEventListener('mousemove', function (ev) {
//   if (drag) {
//     updateBar(ev.clientX);
//   }
// });
// document.addEventListener('mouseup', function (ev) {
//   drag = false;
// });
// var updateBar = function (x, vol) {
//   var volume = e;
//   var percentage;
//   //if only volume have specificed
//   //then direct update volume
//   if (vol) {
//     percentage = vol * 100;
//   } else {
//     var position = x - volume.offsetLeft;
//     percentage = 100 * position / volume.clientWidth;
//   }

//   if (percentage > 100) {
//     percentage = 100;
//   }
//   if (percentage < 0) {
//     percentage = 0;
//   }

//   //update volume bar and video volume
//   eInner.style.width = percentage + '%';
//   audio.volume = percentage / 100;
// };
























//////////////////////////////////////////////////////////////////////////////////
function isEven(value: number): boolean {
  if (value % 2 == 0)
    return true;

  else
    return false;
}


// // donne la position actuelle du pion
// function positionActuelleJoueur(pion:pionPlayer) :number{
//   for (let i=0; i < listeCases.length; i++) {
//     if (this.listeCases[i].positionCaseX === pion.positionXPlayer && this.listeCases[i].positionCaseY === pion.positionYPlayer) { //=== : egalité stricte => le joueur est sur la case i
//       pion.positionIndexCasePlayer = i;
//     }
//   }
//   return pion.positionIndexCasePlayer;
// }










// test



