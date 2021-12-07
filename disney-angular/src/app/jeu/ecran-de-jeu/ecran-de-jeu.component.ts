import { Component, ElementRef, OnInit, Self, ViewChild } from '@angular/core';
import { CaseVirtuelle, pionPlayer } from './plateau-canvas-util';
import * as $ from 'jquery';
import { PageConnexionService } from 'src/app/page-connexion/page-connexion.service';
import { PartieHttpService } from 'src/app/partie-http.service';
import { Cases, CasesPlateau, Compte, Joueur, Partie, Personnage, Plateau, TourDeJeuDto } from 'src/model';
import { CasesPlateauHttpService } from 'src/app/cases-plateau-http.service';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { TemplateLiteralElement } from '@angular/compiler';
import { compileNgModuleDeclarationExpression } from '@angular/compiler/src/render3/r3_module_compiler';

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
  personnage: Personnage;

  plateau: Plateau;
  public tourEnCours: TourDeJeuDto;
  public casesPlateau: Array<CasesPlateau>;
  public joueurs: Array<Joueur>;
  public cases: Array<Cases>;
  srcPionJoueur: string;
  srcPionIA1: string;
  srcPionIA2: string;
  srcPionIA3: string;

  // pour transiter des infos
  message: boolean;
  subscription: Subscription;


  //moi: Joueur = new Joueur;
  ////////////////////////// CONSTRUCTEUR  //////////////////////////
  constructor(public compteService: PageConnexionService, public partieService: PartieHttpService, private casesPlateauService: CasesPlateauHttpService, private router: Router) {
    this.joueur = this.compteService.compte;
    this.idJoueur = this.joueur.id;
    this.partie = this.partieService.LaPartie;
    console.log(this.partie);
    this.personnage = this.partieService.LaPartie.personnages[0];
    this.plateau = this.partieService.LaPartie.plateau; // on recupere le plateau, son id et son nb de case seulement
    this.casesPlateau = this.casesPlateauService.lesCasesPlateau;
    console.log(this.casesPlateau)
    this.srcPionJoueur = '../../../assets/images/jeu/pion/pion_' + this.partie.personnages[0].nom + '.jpg';
    this.srcPionIA1 = '../../../assets/images/jeu/pion/pion_' + this.partie.personnages[1].nom + '.jpg';
    this.srcPionIA2 = '../../../assets/images/jeu/pion/pion_' + this.partie.personnages[2].nom + '.jpg';
    this.srcPionIA3 = '../../../assets/images/jeu/pion/pion_' + this.partie.personnages[3].nom + '.jpg';
    this.partieService.currentMessage.subscribe(message => this.message = message)
    //console.log(this.srcPionJoueur);
    this.joueurs=this.partieService.LaPartie.joueursPartie;

    //console.log(this.casesPlateau)
  }

  ////////////////////////// DECLARATION DES VARIABLES //////////////////////////

  // variables : 
  public velocityH: number = 1; // nb de pixel d'avancement du pion par raffraichissement (10ms)
  public velocityW: number = 1;
  public tauxRaffraichissement: number = 10; // vitesse du setinterval
  public niveauDeVitesseDuPion: number = 2;
  // vitesse
  public valeurdelavitesse: number = 1;
  public slider = document.getElementById("vitesseRange");
  public output = document.getElementById("vitesse");

  // lancement partie var
  public partieEnCours: boolean = true;
  public tourActuel: number = 0;
  public joueurActuel: number = 0;
  public caseEnCours=" Attention où tu marches ! ";
  //public nomDuJoueur:string;
 
  //public tourActuel = document.getElementById("NbTour");
  //ublic tourJoueur = document.getElementById("JoueurTour");

  // les des
  public nombreDe: number = 2;
  public valeurDesDes: number = 0;
  public tempsDes:number = 2500;

  // plateau et les cases
  public nbcasePlateau: number;
  public format: [number, number];
  public w: number;// = 800;
  public h: number;// = 800;
  public nBcaseW: number;
  public nBcaseH: number;
  //public listeCases = [];

  public listeCases: Array<CaseVirtuelle> = new Array<CaseVirtuelle>();

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

  // images du pion en fonction du personnage

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
  public volumeSon: number = 100;
  public actualisation: any;
  //public mouvement:boolean = false;
  ////////////////////////// ngOnInit  ///////////////////////////////////////////////////////////////////////////////
  // note : ça sert à initialiser des données et est appelé qu'une fois ! 

  ngOnInit() {

    //initialisation des canvas : 
    this.ctxPlateau = this.canvasPlateau.nativeElement.getContext('2d');
    this.ctxJoueur = this.canvasJoueur.nativeElement.getContext('2d');
    this.ctxPlayerIA1 = this.canvasIA1.nativeElement.getContext('2d');
    this.ctxPlayerIA2 = this.canvasIA2.nativeElement.getContext('2d');
    this.ctxPlayerIA3 = this.canvasIA3.nativeElement.getContext('2d');
    //console.log(this.casesPlateau)

    // on charge les bonnes images pour les pions
    this.pionJoueur.image = this.srcPionJoueur;
    this.pionIA1.image = this.srcPionIA1;
    this.pionIA2.image = this.srcPionIA2;
    this.pionIA3.image = this.srcPionIA3;

    // quel plateau 
    this.drawPlateau(this, this.plateau);

    // joueur
    this.drawPlayer(this, this.pionJoueur);
    this.drawPlayer(this, this.pionIA1);
    this.drawPlayer(this, this.pionIA2);
    this.drawPlayer(this, this.pionIA3);

    // actualisation (en setinterval pour les deplacements)
    this.actualisation = setInterval(this.drawPlayers, this.tauxRaffraichissement, this);
   

  } //ngOnInit fin


  ngOnDestroy() {
    // clearInterval(this.actualisationJoueur1);
    //cancelAnimationFrame(this.requestId);
  }


  ////////////////////////// Methode de la classe liées aux boutons  //////////////////////////

  // bouton joueur
  Play() {
    //this.dispoBoutonJouer = false;
    this.pionJoueur.playedTurn++;
    this.Jouer(this, this.pionJoueur);
    //this.nomDuJoueur= this.joueurs[this.joueurActuel].pseudo;
    
  }

  Pouvoir() {
    console.log("pouvoir")
  }

  // bouton fin de tour
  EndTurn() {
    this.FinDeTour(this, this.pionJoueur, this.tourActuel);
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

    // listes des cases du front.
    // on veut recuperer 3 infos : l'ordre de la case  / son type et son parameter
    // il faut aussi trier la liste parce que sinon... bah on galere
    // on map les indices de casesPlateau dans un tableau
    let tableau: [number] = [this.nbcasePlateau];

    for (let a = 0; a < this.casesPlateau.length; a++) { // on trie
      tableau[this.casesPlateau[a].ordreCase] = a;
    }
    //console.log(tableau)

    for (let i = 0; i < this.casesPlateau.length; i++) // on parcours la liste 
    {
      let cetteCase: CaseVirtuelle = new CaseVirtuelle;
      cetteCase.ordre = this.casesPlateau[tableau[i]].ordreCase;
      cetteCase.nom = this.casesPlateau[tableau[i]].uneCase.nom;
      cetteCase.typeCase = this.casesPlateau[tableau[i]].uneCase.typeCase;
      cetteCase.parametre = this.casesPlateau[tableau[i]].parametre;
      this.listeCases.push(cetteCase);
      //console.log(this.casesPlateau[tableau[i]].parametre)
      //cette liste est triée en fonction de ordreCase.
    }

    //console.log(this.listeCases)
    //console.log(this.listeCases)

    // DESSIN DU PLATEAU
    let numeroCase = 0;
    // sens de la ligne
    for (let y = 0; y < this.nBcaseW; y++) {

      // sens de la colonne
      for (let x = 0; x < this.nBcaseH; x++) {

        numeroCase++;
        // si on devrait dépasser le tableau
        if (numeroCase > this.nbcasePlateau) { break; } // on depasse le nombre de case

        //position en fonction du sens de cheminement du plateau 
        if (isEven(y)) { // ligne paire (on va dans le bon sens)
          var pX = this.taillecaseW * x;
        }
        else { // ligne impaire  (on va dans le sens inverse)
          var pX = this.taillecaseW * (this.nBcaseW - x - 1);
        }
        var pY = this.taillecaseH * y;

        // on enregistre la positon de cetteCase :
        this.listeCases[numeroCase - 1].positionCaseX = pX;
        this.listeCases[numeroCase - 1].positionCaseY = pY;

        // quelques variables utiles pour le dessin
        let milieudelacaseX: number = pX + this.taillecaseW / 2;
        let milieudelacaseY: number = pY + this.taillecaseH / 2;
        let limiteX1: number = pX + this.taillecaseH * 0.1;
        let limiteX2: number = pX + this.taillecaseH * 0.9;
        let limiteY1: number = pY + this.taillecaseW * 0.1;
        let limiteY2: number = pY + this.taillecaseW * 0.9;
        let limiteX11 = pX + this.taillecaseH * 0.05;
        let limiteX22 = pX + this.taillecaseH * 0.95;
        let limiteY11 = pY + this.taillecaseW * 0.05;
        let limiteY22 = pY + this.taillecaseW * 0.95;
        let outer = [limiteX11, limiteY11, limiteX22, limiteY22];
        let inner = [limiteX1, limiteY1, limiteX2, limiteY2];

        //////////////////////////////// TEST
        // //quadrillage pour le reperage
        // this.ctxPlateau.rect(pX, pY, this.taillecaseW, this.taillecaseH);  //ctx.rect(x, y, width, height);
        // this.ctxPlateau.moveTo(milieudelacaseX, pY);
        // this.ctxPlateau.lineTo(milieudelacaseX, pY + this.taillecaseH);
        // this.ctxPlateau.stroke();
        // this.ctxPlateau.moveTo(pX, milieudelacaseY);
        // this.ctxPlateau.lineTo(pX + this.taillecaseH, milieudelacaseY);
        // this.ctxPlateau.stroke();

        // this.ctxPlateau.moveTo(limiteX1, pY);
        // this.ctxPlateau.lineTo(limiteX1, pY + this.taillecaseH);
        // this.ctxPlateau.stroke();
        // this.ctxPlateau.moveTo(pX, limiteY1);
        // this.ctxPlateau.lineTo(pX + this.taillecaseH, limiteY1);
        // this.ctxPlateau.stroke();

        // this.ctxPlateau.moveTo(limiteX2, pY);
        // this.ctxPlateau.lineTo(limiteX2, pY + this.taillecaseH);
        // this.ctxPlateau.stroke();
        // this.ctxPlateau.moveTo(pX, limiteY2);
        // this.ctxPlateau.lineTo(pX + this.taillecaseH, limiteY2);
        // this.ctxPlateau.stroke()

        // this.ctxPlateau.moveTo(limiteX11, pY);
        // this.ctxPlateau.lineTo(limiteX11, pY + this.taillecaseH);
        // this.ctxPlateau.stroke();
        // this.ctxPlateau.moveTo(pX, limiteY11);
        // this.ctxPlateau.lineTo(pX + this.taillecaseH, limiteY11);
        // this.ctxPlateau.stroke();

        // this.ctxPlateau.moveTo(limiteX22, pY);
        // this.ctxPlateau.lineTo(limiteX22, pY + this.taillecaseH);
        // this.ctxPlateau.stroke();
        // this.ctxPlateau.moveTo(pX, limiteY22);
        // this.ctxPlateau.lineTo(pX + this.taillecaseH, limiteY22);
        // this.ctxPlateau.stroke()

        this.ctxPlateau.beginPath();
        ///////////////////////////////// FIN TEST

        // on dessine la case :
        // en fonction du type de case 
        this.ctxPlateau.globalAlpha = 0.5; // c'est l'opacité
        if (this.listeCases[numeroCase - 1].typeCase == "depart") {
          this.drawSquare(pX, pY, this.taillecaseW, this.taillecaseH, "white");
          this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "plum")
        }
        else if (this.listeCases[numeroCase - 1].typeCase == "arrivee") {
          this.drawSquare(pX, pY, this.taillecaseW, this.taillecaseH, "white");
          this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "lightgoldenrodyellow")
          this.drawStar(milieudelacaseX, milieudelacaseY, 5, (limiteX2 - limiteX1) / 2, (limiteX2 - limiteX1) / 4, "yellow")
        }
        else if (this.listeCases[numeroCase - 1].typeCase == "vide") {
          this.drawSquare(pX, pY, this.taillecaseW, this.taillecaseH, "white");
          this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "lightsteelblue") //[184,255,250]
        }
        else if (this.listeCases[numeroCase - 1].typeCase == "mechant") {
          this.drawSquare(pX, pY, this.taillecaseW, this.taillecaseH, "white");

          this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "darkslateblue")
          this.drawHeart(milieudelacaseX, limiteY1, limiteX2, limiteY2, (limiteX2 - limiteX1), (limiteY2 - limiteY1), "midnightblue");
        }
        else if (this.listeCases[numeroCase - 1].typeCase == "prince") {
          this.drawSquare(pX, pY, this.taillecaseW, this.taillecaseH, "white");
 
          this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "lightpink")
          this.drawHeart(milieudelacaseX, limiteY1, limiteX2, limiteY2, (limiteX2 - limiteX1), (limiteY2 - limiteY1), "crimson");
        }
        else if (this.listeCases[numeroCase - 1].typeCase == "prison") {

          this.drawSquare(pX, pY, this.taillecaseW, this.taillecaseH, "white");
          this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "grey")
          this.drawPrison(limiteX1, limiteY1, limiteX2, limiteY2, "black")
        }
        else if (this.listeCases[numeroCase - 1].typeCase == "deplacement") {
          this.drawSquare(pX, pY, this.taillecaseW, this.taillecaseH, "white");

          let parametre = this.listeCases[numeroCase - 1].parametre;
          if ((parametre >= 0) && isEven(y)) {
            var color = 'green';
            var sens = "right";
            this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "palegreen")
            this.drawArrow(limiteX1, milieudelacaseY, limiteX2, milieudelacaseY, milieudelacaseX, milieudelacaseY, this.taillecaseH, "limegreen", "right");
          }
          else if ((parametre >= 0) && isEven(y) == false) {
            var color = 'green';
            var sens = "left";
            this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "palegreen")
            this.drawArrow(limiteX1, milieudelacaseY, limiteX2, milieudelacaseY, milieudelacaseX, milieudelacaseY, this.taillecaseH, "limegreen", "left");
          }
          else if ((parametre == -1) && isEven(y)) {
            var color = 'red';
            var sens = "left";
            this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "lightcoral")
            this.drawArrow(limiteX1, milieudelacaseY, limiteX2, milieudelacaseY, milieudelacaseX, milieudelacaseY, this.taillecaseH, "red", "left");
          }
          else if ((parametre == -1) && isEven(y) == false) {
            var color = 'red';
            var sens = "right";
            this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "lightcoral")
            this.drawArrow(limiteX1, milieudelacaseY, limiteX2, milieudelacaseY, milieudelacaseX, milieudelacaseY, this.taillecaseH, "red", "right");
          }
        }
        else if (this.listeCases[numeroCase - 1].typeCase == "duel") {
          this.drawSquare(pX, pY, this.taillecaseW, this.taillecaseH, "white");
          this.ctxPlateau.globalAlpha = 0.7; // c'est l'opacité
          this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "sandybrown")

          this.drawStar(limiteX11+this.taillecaseW*0.2, limiteY11+this.taillecaseH*0.3, 8, (limiteX2 - limiteX1) / 5, (limiteX2 - limiteX1) / 10, "tomato")
          this.drawStar(limiteX11+this.taillecaseW*0.6, limiteY11+this.taillecaseH*0.7, 12, (limiteX2 - limiteX1) / 4, (limiteX2 - limiteX1) / 8, "tomato")
          this.drawStar(limiteX11+this.taillecaseW*0.7, limiteY11+this.taillecaseH*0.4, 10, (limiteX2 - limiteX1) / 7, (limiteX2 - limiteX1) / 14, "tomato")
          this.drawStar(limiteX11+this.taillecaseW*0.1, limiteY11+this.taillecaseH*0.7, 7, (limiteX2 - limiteX1) / 8, (limiteX2 - limiteX1) / 16, "tomato")
          this.drawStar(limiteX11+this.taillecaseW*0.5, limiteY11+this.taillecaseH*0.1, 9, (limiteX2 - limiteX1) / 9, (limiteX2 - limiteX1) / 18, "tomato")
          this.drawStar(milieudelacaseX, milieudelacaseY, 10, (limiteX2 - limiteX1) / 3, (limiteX2 - limiteX1) / 6, "tomato")
          //this.drawCross(x, y, size, this.taillecaseH, "indianred") 
        }
        else if (this.listeCases[numeroCase - 1].typeCase == "pioche") {
          this.drawSquare(pX, pY, this.taillecaseW, this.taillecaseH, "white");
          this.ctxPlateau.globalAlpha = 0.7; // c'est l'opacité
          this.roundRect(limiteX11, limiteY11, Math.abs(limiteX11 - limiteX22), Math.abs(limiteY11 - limiteY22), 10, true, false, "lightsalmon")
          this.roundRect(limiteX11+this.taillecaseW*0.2, limiteY11+this.taillecaseW*0.1, Math.abs(limiteX11 - limiteX22)*0.4, Math.abs(limiteY11 - limiteY22)*0.6, 10, true, false, "salmon")
          this.roundRect(limiteX11+this.taillecaseW*0.4, limiteY11+this.taillecaseH*0.2, Math.abs(limiteX11 - limiteX22)*0.4, Math.abs(limiteY11 - limiteY22)*0.6, 10, true, false, "salmon")
          
        }
        //this.ctxPlateau.font = '100px';
        this.ctxPlateau.textBaseline = 'middle';
        this.ctxPlateau.textAlign = "center";
        this.ctxPlateau.font = '15px serif';
        var affichecase = numeroCase - 1;
        if (numeroCase == this.nbcasePlateau) {
          var str = "arrivée";
        }
        else if (numeroCase == 1) {
          var str = "départ";
        }
        else {
          //var str = "case " + affichecase;
          var str = " " + affichecase + " ";
        }
        this.ctxPlateau.closePath();
        this.ctxPlateau.beginPath();
        this.drawCircle(milieudelacaseX,milieudelacaseY, this.taillecaseH/10, "white");
      
        this.ctxPlateau.strokeText(str, pX + this.taillecaseW / 2, pY + this.taillecaseH / 2);  //ctx.strokeText(texte, x, y [, largeurMax]);
        this.ctxPlateau.closePath();
      }
    }
  }

  drawPlayers(self) {
    self.drawPlayer(self, self.pionJoueur);
    self.drawPlayer(self, self.pionIA1);
    self.drawPlayer(self, self.pionIA2);
    self.drawPlayer(self, self.pionIA3);
  }

  // dessine un pion
  drawPlayer(self, pion) {


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
    let test: boolean = false;
    // rentre dans la boucle seulement si l'index <= à la longueur de la liste des cases du plateau
    if ((pion.positionIndexCasePlayerSuivante <= self.nbcasePlateau - 1 || pion.positionIndexCasePlayerPrecedente >= 0) && test == false) {
      if (diff == 0) {
        //  console.log("le pion"+ pion.numeroPassage+ "est à l'arret");
        pion.playerIsMoving = false;
        //self.commandeDispo=false;

        if (self.partieEnCours == false && pion.finished == true) {
          test = false;
          //setTimeout(finDePartie, 2000, pion);
        }

        if (pion.numeroPassage == 0 && self.joueurActuel == 0) {
          // on est a l'arret donc on peut réaffichier le bouton fin de tour
          self.dispoBoutonFin = true;
        }
      }
      else if (diff < 0) {
        pion.playerIsMoving = true;
        //self.commandeDispo=false;

        //self.dispoBoutonFin = false;
        //console.log("diff:" + diff + ", position Y du pion : " + pion.positionYPlayer);
        //console.log("on avance de" + diff);
        //console.log(pion.numeroPassage)
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
        //self.commandeDispo=false;

        //self.dispoBoutonJouer = false;
        //self.dispoBoutonFin = false;

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

    }

    if (pion.numeroPassage == 0) {
      let image = new Image();
      image.width = self.taillecaseW / 2;
      image.height = self.taillecaseH / 2;
      image.src = pion.image;
      image.onload = function () {
        //console.log("dans le log image");
        if (pion.playerIsMoving) {
          self.ctxJoueur.clearRect(0, 0, self.canvasJoueur.nativeElement.width, self.canvasJoueur.nativeElement.height); //clear pour refresh
        }
        //self.roundRect( pion.positionXPlayer, pion.positionYPlayer, image.width, image.height, 10, false, true,'white');
        //self.ctxJoueur.clip();
        let circlePath = new Path2D();
        self.ctxJoueur.save();
        circlePath.arc( pion.positionXPlayer+self.taillecaseH/4,  pion.positionYPlayer+self.taillecaseH/4, self.taillecaseH/4, 0, 2 * Math.PI ); //void ctx.arc(x, y, rayon, angleDépart, angleFin, sensAntiHoraire);
        // Set the clip to the circle
        self.ctxJoueur.clip(circlePath);
        self.ctxJoueur.drawImage(image, pion.positionXPlayer, pion.positionYPlayer, image.width, image.height);
        self.ctxJoueur.restore();
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
        if (pion.playerIsMoving) {
          self.ctxPlayerIA1.clearRect(0, 0, self.canvasIA1.nativeElement.width, self.canvasIA1.nativeElement.height);
        }
        self.ctxPlayerIA1.save();
        //let circlePath = new Path2D();
        //circlePath.arc( pion.positionXPlayer+self.taillecaseH/4,  pion.positionYPlayer+self.taillecaseH/4, self.taillecaseH/4, 0, 2 * Math.PI ); //void ctx.arc(x, y, rayon, angleDépart, angleFin, sensAntiHoraire);
        // Set the clip to the circle
        //self.ctxPlayerIA1.clip(circlePath);
        self.ctxPlayerIA1.drawImage(image, positionx, positiony, image.width, image.height);
        self.ctxPlayerIA1.restore();
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
        if (pion.playerIsMoving) {
          self.ctxPlayerIA2.clearRect(0, 0, self.canvasIA2.nativeElement.width, self.canvasIA2.nativeElement.height);
        }
        self.ctxPlayerIA2.save();
        let circlePath = new Path2D();
        circlePath.arc( pion.positionXPlayer+self.taillecaseH/4,  pion.positionYPlayer+self.taillecaseH/4, self.taillecaseH/4, 0, 2 * Math.PI ); //void ctx.arc(x, y, rayon, angleDépart, angleFin, sensAntiHoraire);
        // Set the clip to the circle
        //self.ctxPlayerIA2.clip(circlePath);
        self.ctxPlayerIA2.drawImage(image, positionx, positiony, image.width, image.height);
        self.ctxPlayerIA2.restore();
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
        if (pion.playerIsMoving) {
          self.ctxPlayerIA3.clearRect(0, 0, self.canvasIA3.nativeElement.width, self.canvasIA3.nativeElement.height);
        }
        self.ctxPlayerIA3.save();
        let circlePath = new Path2D();
        circlePath.arc( pion.positionXPlayer+self.taillecaseH/4,  pion.positionYPlayer+self.taillecaseH/4, self.taillecaseH/4, 0, 2 * Math.PI ); //void ctx.arc(x, y, rayon, angleDépart, angleFin, sensAntiHoraire);
        // Set the clip to the circle
        //self.ctxPlayerIA3.clip(circlePath);
        self.ctxPlayerIA3.drawImage(image, positionx, positiony, image.width, image.height);
        self.ctxPlayerIA3.restore();
      }
    }
  }




  // permet de jouer
  Jouer(self, pion) {

    self.partieService.GameTour(self.idJoueur, self.partie).subscribe(res => {
      self.tourEnCours = res;
      console.log("TOUR DE JEUX : {}", self.tourEnCours);
      self.affichageInformation(self, pion, res);
      //console.log("///////")
      //console.log(self.tourEnCours)

      // animation des dés / sons
      playSoundDice();
      self.roulementDe(); // pour l'affichage seulement

      // lancement du tour 
      setTimeout(self.TourJoueur, self.tempsDes, self, pion, self.tourEnCours);  //setTimeout(TourJoueur(pionJoueur), 2200); //marche pas  //setTimeout(function() {TourJoueur(pionJoueur);}, 2200); //autre ecriture
      self.joueurActuel = pion.numeroPassage;
      //self.nomDuJoueur= self.joueurs[self.joueurActuel].pseudo;

      // LE JOUEUR PEUT FINIR SON TOUR 
      if (self.joueurActuel == 0) {
        //self.dispoBoutonJouer = false;
        //self.dispoBoutonFin = true;
      }

      // LES PIONS UN ET DEUX FINISSENT LEURS TOURS AUTO
      else if (self.joueurActuel == 1 || self.joueurActuel == 2) { // si ce n'est pas le joueur qui joue on "click" auto sur la suite
        //let pionSuivant = self.listePion[self.joueurActuel + 1];
        var waitTime = 2000 + self.tempsDes + self.tauxRaffraichissement * (self.tourEnCours.valueDice1 + self.tourEnCours.valueDice2) * self.taillecaseW / self.velocityW;
        if (self.tourEnCours.effetAActiver == true) {
          if(self.tourEnCours.deplacement !=0){
            waitTime = waitTime + self.tourEnCours.deplacement * self.tauxRaffraichissement * self.taillecaseW / self.velocityW + 1000;
          }
        }

        //console.log("wait time = " + waitTime);
        setTimeout(self.FinDeTour, waitTime, self, pion, self.tourEnCours.positionFutureJoueur); // passe le tour auto
        //self.dispoBoutonFin = false;
      }

      // LE DERNEIR PION A FINI, CEST AU TOUR DU JOUEUR
      else if (self.joueurActuel == 3) {
        //self.dispoBoutonJouer = true;
        var waitTime = 2000 + self.tempsDes + self.tauxRaffraichissement * (self.tourEnCours.valueDice1 + self.tourEnCours.valueDice2) * self.taillecaseW / self.velocityW;
        if (self.tourEnCours.effetAActiver == true) {
          if(self.tourEnCours.deplacement !=0){
            waitTime = waitTime + self.tourEnCours.deplacement * self.tauxRaffraichissement * self.taillecaseW / self.velocityW + 1000;
          }
        }
        console.log("wait time = " + waitTime);
        setTimeout(self.FinDeTour, waitTime, self, pion, self.tourEnCours.positionFutureJoueur); // passe le tour auto
        this.dispoBoutonJouer=true;
        this.dispoBoutonFin=true;
      }
    });
  }


  // permet de finir son tour et donc de faire jouer les IA
  FinDeTour(self, pion, position) {
    // si la partie n'est pas finie
   // self.dispoBoutonFin = false;

   // pour eviter les beugs on force la position du pion à la où il devrait être
    // console.log(position)
    //  console.log(self.listeCases)
    //  let x = self.listeCases[position].positionCaseX;
    //  let y = self.listeCases[position].positionCaseY;
  
    //  self.placerLePion(self, pion, x, y ,position);
     



    if (self.partieEnCours == true) {

      // PION 1, 2 et 3 ONT FINI et ça passe au suivnat
      if (self.joueurActuel != 3) { // n'est pas le dernier de la liste (donc pion 4)
        self.joueurActuel++; // console.log("on incremente le numero du joueur")
        //self.nomDuJoueur= self.joueurs[self.joueurActuel].pseudo;
        let pionSuivant = self.listePion[self.joueurActuel];
        setTimeout(self.Jouer, 2000, self, pionSuivant);
        self.dispoBoutonFin = false;
        self.dispoBoutonJouer = false;
      }

      //PION 4 a finit et ça repasse au joueur
      else {
        self.joueurActuel = 0;
        //self.nomDuJoueur= self.joueurs[self.joueurActuel].pseudo;
        self.tourActuel++;// incrémente le nombre de tour
        self.dispoBoutonFin = true;
        self.dispoBoutonJouer = true;
      }
    }

    // si la partie est finie ! 
    //else if (self.partieEnCours == false) {
      //console.log("findetour, if partieencours false")
      // self.commandeDispo = false;
      //setTimeout(self.finDePartie, 20000, self, pion,)
      //self.finDePartie(self, pion);
    //}
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
    console.log(tour)

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

    

    // gere le cas où on dépasse la case arrivee
    if (pion.positionIndexCasePlayer + totalDe >= self.nbcasePlateau - 1) {
      // la partie est terminée / le pion a gagné
      self.partieEnCours = false;
      pion.finished = true; // c'est CE pion qui a gagné
      console.log("la partie est terminée")

      if (tour.findepartie == true || self.partieEnCours == false) {//la partie est finie et ça a été vérifié avec le back.
        console.log("on rentre ici")

        console.log(pion)
        let waitTime = 1000+self.tempsDes + self.tauxRaffraichissement * (self.tourEnCours.valueDice1 + self.tourEnCours.valueDice2) * self.taillecaseW / self.velocityW;
        setTimeout(self.finDePartie, waitTime, self, pion);
        // setTimeout(clearInterval, waitTime+500, self.actualisation);
        // clearInterval(self.actualisation);
        // la position du pion est celle du back.
        pion.futurePositionIndexCasePlayer = tour.positionFutureJoueur;
      }
    }

    // sinon est ce que l'on arrive sur une case à effet ?
    else if (tour.effetAActiver) // case avec un effet 
    {
      // d'abord on se déplace du nombre de case des dés.
      pion.futurePositionIndexCasePlayer = pion.positionIndexCasePlayer + totalDe;

      // on va devoir se déplacer encore une fois.
      let waitTime = 1000+self.tauxRaffraichissement * (self.tourEnCours.valueDice1 + self.tourEnCours.valueDice2) * self.taillecaseW / self.velocityW;
      setTimeout(self.effetCaseDeplacement, waitTime, self, pion, tour);
    }
    else { // pas de case speciale ET on a pas finit de jouer pour autant
      // la position du pion est celle du back.
      pion.futurePositionIndexCasePlayer = tour.positionFutureJoueur;
    }

    // besoin d'un test quand même si on arrive à la fin


    //// TODO: faire les actions/pouvoirs des joueurs
  }

  effetCaseDeplacement(self, pion, tour) {
    // cette case à un effet de deplacement

       // besoin d'un test quand même si on arrive à la fin
       if ( tour.positionFutureJoueur >= self.nbcasePlateau - 1) {
        // la partie est terminée / le pion a gagné
        self.partieEnCours = false;
        pion.finished = true; // c'est CE pion qui a gagné
        console.log("la partie est terminée grace a ce bonus !")
  
        if (tour.findepartie == true || self.partieEnCours == false) {//la partie est finie et ça a été vérifié avec le back.
  
  
          let waitTime = 1000+self.tempsDes + self.tauxRaffraichissement * (tour.deplacement) * self.taillecaseW / self.velocityW+1000;
          setTimeout(self.finDePartie, waitTime, self, pion);
  
          //pion.futurePositionIndexCasePlayer = tour.positionFutureJoueur;
        }
      }
      // dans tous les cas 
      pion.futurePositionIndexCasePlayer = tour.positionFutureJoueur;
  
  }

  // fin de partie : redirection
  finDePartie(self, pion) {


    console.log("on est arrivé / fin de partie ")
    // on clear les set intervals pour pas qu'ils tournent dans le vide 
    clearInterval(self.actualisation);
    if (pion.numeroPassage == 0) {
      self.partieService.changeMessage(true);
      console.log("on est arrivé gagné")
      // le joueur à gagner
      //this.partieService.victoire=true;
      self.partieService.ExitGame();
      self.router.navigate(['jeu/findepartie']);
    } else {
      // le joueur à perdu
      self.partieService.changeMessage(false);
      console.log("on est arrivé perdu")
      self.router.navigate(['jeu/findepartie']);
    }
  }

  placerLePion(self, pion, x, y, position){
    pion.positionActuelleJoueur=position;
    if (pion.numeroPassage == 0) {
      let image = new Image();
      image.width = self.taillecaseW / 2;
      image.height = self.taillecaseH / 2;
      image.src = pion.image;
      image.onload = function () {
        //console.log("dans le log image");
        if (pion.playerIsMoving) {
          self.ctxJoueur.clearRect(0, 0, self.canvasJoueur.nativeElement.width, self.canvasJoueur.nativeElement.height); //clear pour refresh
        }
        //self.roundRect( pion.positionXPlayer, pion.positionYPlayer, image.width, image.height, 10, false, true,'white');
        //self.ctxJoueur.clip();
        let circlePath = new Path2D();
        self.ctxJoueur.save();
        circlePath.arc( x +self.taillecaseH/4,  y+self.taillecaseH/4, self.taillecaseH/4, 0, 2 * Math.PI ); //void ctx.arc(x, y, rayon, angleDépart, angleFin, sensAntiHoraire);
        // Set the clip to the circle
        self.ctxJoueur.clip(circlePath);
        self.ctxJoueur.drawImage(image, x, y, image.width, image.height);
        self.ctxJoueur.restore();
      }
    }
    else if (pion.numeroPassage == 1) {
      let positionx = x + self.taillecaseW / 2;
      let positiony = y;
      let image = new Image();
      image.width = self.taillecaseW / 2;
      image.height = self.taillecaseH / 2;
      image.src = pion.image;
      image.onload = function () {
        if (pion.playerIsMoving) {
          self.ctxPlayerIA1.clearRect(0, 0, self.canvasIA1.nativeElement.width, self.canvasIA1.nativeElement.height);
        }
        self.ctxPlayerIA1.save();
        //let circlePath = new Path2D();
        //circlePath.arc( pion.positionXPlayer+self.taillecaseH/4,  pion.positionYPlayer+self.taillecaseH/4, self.taillecaseH/4, 0, 2 * Math.PI ); //void ctx.arc(x, y, rayon, angleDépart, angleFin, sensAntiHoraire);
        // Set the clip to the circle
        //self.ctxPlayerIA1.clip(circlePath);
        self.ctxPlayerIA1.drawImage(image, positionx, positiony, image.width, image.height);
        self.ctxPlayerIA1.restore();
      }
    }
    else if (pion.numeroPassage == 2) {
      let positionx = x;
      let positiony = y + self.taillecaseH / 2;
      let image = new Image();
      image.width = self.taillecaseW / 2;
      image.height = self.taillecaseH / 2;
      image.src = pion.image;
      image.onload = function () {
        if (pion.playerIsMoving) {
          self.ctxPlayerIA2.clearRect(0, 0, self.canvasIA2.nativeElement.width, self.canvasIA2.nativeElement.height);
        }
        self.ctxPlayerIA2.save();
        let circlePath = new Path2D();
        circlePath.arc( pion.x+self.taillecaseH/4,  y+self.taillecaseH/4, self.taillecaseH/4, 0, 2 * Math.PI ); //void ctx.arc(x, y, rayon, angleDépart, angleFin, sensAntiHoraire);
        // Set the clip to the circle
        //self.ctxPlayerIA2.clip(circlePath);
        self.ctxPlayerIA2.drawImage(image, positionx, positiony, image.width, image.height);
        self.ctxPlayerIA2.restore();
      }
    }
    else if (pion.numeroPassage == 3) {
      let positionx = x + self.taillecaseW / 2;
      let positiony = y + self.taillecaseH / 2;
      let image = new Image();
      image.width = self.taillecaseW / 2;
      image.height = self.taillecaseH / 2;
      image.src = pion.image;
      image.onload = function () {
        if (pion.playerIsMoving) {
          self.ctxPlayerIA3.clearRect(0, 0, self.canvasIA3.nativeElement.width, self.canvasIA3.nativeElement.height);
        }
        self.ctxPlayerIA3.save();
        let circlePath = new Path2D();
        circlePath.arc( x+self.taillecaseH/4,  y+self.taillecaseH/4, self.taillecaseH/4, 0, 2 * Math.PI ); //void ctx.arc(x, y, rayon, angleDépart, angleFin, sensAntiHoraire);
        // Set the clip to the circle
        //self.ctxPlayerIA3.clip(circlePath);
        self.ctxPlayerIA3.drawImage(image, positionx, positiony, image.width, image.height);
        self.ctxPlayerIA3.restore();
      }
    }
  }


  //////////////////////////////////////////////////////////////////////////////////////////////////////////////////:

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

  affichageInformation(self, pion, tour){
            //affichage du type de case pour le joueur :

            //console.log(self.listeCases)

            //console.log(self.listeCases)
            //console.log(pion.positionIndexCasePlayer)
            
            //console.log(tour.valueDice1+tour.valueDice2)

            let index = pion.positionIndexCasePlayer+tour.valueDice1+tour.valueDice2;
            if (index >= self.nbcasePlateau-1) {index = this.nbcasePlateau-1;} // si ça depasse le plateau


            let position = self.listeCases[index].typeCase;
            

            if (position=="depart" ){
              var effet = "Case Départ, c'est parti !"
            }
            else if (position=="arrivee" ){
              let name= this.joueurs[pion.numeroPassage].pseudo;
              var effet = "Case Arrivée, BRAVO "+name+" ! Dommage pour les autres ! "
            }
            else if (position=="prison" ){
              var effet = "Case Prison, mauvaise ambiance..."
            }
            else if (position=="pioche" ){
              var effet = "Case pioche, tu réfléchis à tes choix de vie..."
            }
            else if (position=="prince" ){
              var effet = "Case prince, tu retrouves un être cher et cela double ton déplacement ! "
            }
            else if (position=="mechant" ){
              var effet = "Case mechant, tu tombes nez à nez à ton ennemi qui te repousse à ton point de départ, dommage... "
            }
            else if (position=="duel" ){
              var effet = "Case duel, ici de nombreux combat ont eu lieu... "
            }
            else if (position=="vide" ){
              var effet = "Case vide, tu mérites bien de te reposer un peu ! "
            }
            else if (position=="deplacement" ){
              if (self.listeCases[pion.positionIndexCasePlayer+tour.valueDice1+tour.valueDice2].parametre==-1) {
                var effet = "Case déplacement, tu suis une route mais c'était la mauvaise !  "
              }
              else  {
                var effet = "Case déplacement, tu as trouvé un raccourcis ! "
              }
            }
        
        self.caseEnCours=effet;
  }






  //////////////////////////////////////////// FONCTIONS DE DESSIN /////////////////////////////////////////////////////////////////////////////
  drawSquare(pX, pY, w, h, color) {
    this.ctxPlateau.save();
    this.ctxPlateau.beginPath();
    this.ctxPlateau.rect(pX, pY, w, h);  //ctx.rect(x, y, width, height);
    this.ctxPlateau.fillStyle = color; // la couleur de la case
    this.ctxPlateau.fill(); // ça remplit la forme (case ici)
    this.ctxPlateau.closePath();
    this.ctxPlateau.fillStyle = color;
    this.ctxPlateau.fill();
    this.ctxPlateau.restore();
  }


  drawCard(inner, outer, color) {
    console.log(inner);
    console.log(outer);
    //outer=[limiteX11, limiteY11, limiteX22, limiteY22];
    //inner=[limiteX1, limiteY1, limiteX2, limiteY2];
    this.ctxPlateau.save();
    this.ctxPlateau.beginPath();
    this.ctxPlateau.arc(inner[0], inner[3], Math.abs(inner[0] - outer[1]), 1 * Math.PI, 0.5 * Math.PI, true); // coin gauche bas
    //this.ctxPlateau.moveTo(inner[0], outer[3]);
    //this.ctxPlateau.lineTo(inner[2], outer[3]);       
    this.ctxPlateau.arc(inner[2], inner[3], Math.abs(inner[0] - outer[1]), -1.5 * Math.PI, 0, true); // coin droite bas
    this.ctxPlateau.moveTo(outer[2], inner[3]);
    this.ctxPlateau.lineTo(outer[2], inner[1]);
    this.ctxPlateau.arc(inner[2], inner[1], Math.abs(inner[0] - outer[1]), 0, -0.5 * Math.PI, true); // coin gauche haut
    this.ctxPlateau.moveTo(inner[2], outer[1]);
    this.ctxPlateau.lineTo(inner[0], outer[1]);
    this.ctxPlateau.arc(inner[0], inner[1], Math.abs(inner[0] - outer[1]), 1.5 * Math.PI, Math.PI, true); // coin droite haut
    this.ctxPlateau.moveTo(outer[0], inner[1]);
    this.ctxPlateau.lineTo(outer[0], inner[3]);
    //this.ctxPlateau.moveTo(outer[0], inner[3]);
    this.ctxPlateau.fillStyle = color;
    this.ctxPlateau.stroke();
    this.ctxPlateau.closePath();
    this.ctxPlateau.restore();
  }

  roundRect(x, y, width, height, radius, fill, stroke, color) {
    if (typeof stroke === 'undefined') {
      stroke = true;
    }
    if (typeof radius === 'undefined') {
      radius = 5;
    }
    if (typeof radius === 'number') {
      radius = { tl: radius, tr: radius, br: radius, bl: radius };
    } else {
      var defaultRadius = { tl: 0, tr: 0, br: 0, bl: 0 };
      for (var side in defaultRadius) {
        radius[side] = radius[side] || defaultRadius[side];
      }
    }
    this.ctxPlateau.beginPath();
    this.ctxPlateau.moveTo(x + radius.tl, y);
    this.ctxPlateau.lineTo(x + width - radius.tr, y);
    this.ctxPlateau.quadraticCurveTo(x + width, y, x + width, y + radius.tr);
    this.ctxPlateau.lineTo(x + width, y + height - radius.br);
    this.ctxPlateau.quadraticCurveTo(x + width, y + height, x + width - radius.br, y + height);
    this.ctxPlateau.lineTo(x + radius.bl, y + height);
    this.ctxPlateau.quadraticCurveTo(x, y + height, x, y + height - radius.bl);
    this.ctxPlateau.lineTo(x, y + radius.tl);
    this.ctxPlateau.quadraticCurveTo(x, y, x + radius.tl, y);
    this.ctxPlateau.closePath();
    if (fill) {
      //this.ctxPlateau.fillStyle = `rgba(color[1], color[2], color[3], 0.5)`;;
      this.ctxPlateau.fillStyle = color;
      this.ctxPlateau.fill();
    }
    if (stroke) {
      this.ctxPlateau.stroke();
    }

  }



  drawHeart(fromx, fromy, tox, toy, lw, hlen, color) {

    let x = fromx;
    let y = fromy;
    let width = lw;
    let height = hlen;

    this.ctxPlateau.save();
    this.ctxPlateau.beginPath();
    var topCurveHeight = height * 0.35;
    this.ctxPlateau.moveTo(x, y + topCurveHeight);
    // top left curve
    this.ctxPlateau.bezierCurveTo(
      x, y,
      x - width / 2, y,
      x - width / 2, y + topCurveHeight
    );
    // bottom left curve
    this.ctxPlateau.bezierCurveTo(
      x - width / 2, y + (height + topCurveHeight) / 2,
      x, y + (height + topCurveHeight) / 2,
      x, y + height
    );
    // bottom right curve
    this.ctxPlateau.bezierCurveTo(
      x, y + (height + topCurveHeight) / 2,
      x + width / 2, y + (height + topCurveHeight) / 2,
      x + width / 2, y + topCurveHeight
    );
    // top right curve
    this.ctxPlateau.bezierCurveTo(
      x + width / 2, y,
      x, y,
      x, y + topCurveHeight
    );
    this.ctxPlateau.closePath();
    this.ctxPlateau.fillStyle = color;
    this.ctxPlateau.fill();
    this.ctxPlateau.restore();
  }




  drawArrow(fromx, fromy, tox, toy, milieuX, milieuY, taillecase, color, direction) {
    let rayon = taillecase / 3.2;
    let y_center = milieuY;

    let angle;
    let x;
    let y;
    this.ctxPlateau.beginPath();
    if (direction == "right") {
      this.ctxPlateau.rect(fromx, (fromy - rayon / 2), (1.5 * rayon), rayon)
      var x_center: number = milieuX + 0.1 * this.taillecaseW;
      angle = Math.atan2(toy - fromy, tox - fromx);

    }
    else if (direction == "left") {
      this.ctxPlateau.rect(tox - 1.5 * rayon, (fromy - rayon / 2), (1.5 * rayon), rayon)
      var x_center: number = milieuX - 0.1 * this.taillecaseW;
      angle = Math.atan2(fromy - toy, fromx - tox);

    }
    x = rayon * Math.cos(angle) + x_center;
    y = rayon * Math.sin(angle) + y_center;

    this.ctxPlateau.moveTo(x, y);

    angle += (1 / 3) * (2 * Math.PI)
    x = rayon * Math.cos(angle) + x_center;
    y = rayon * Math.sin(angle) + y_center;

    this.ctxPlateau.lineTo(x, y);

    angle += (1 / 3) * (2 * Math.PI)
    x = rayon * Math.cos(angle) + x_center;
    y = rayon * Math.sin(angle) + y_center;

    this.ctxPlateau.lineTo(x, y);
    this.ctxPlateau.closePath();
    this.ctxPlateau.fillStyle = color;
    this.ctxPlateau.fill();
  }



  drawStar(cx, cy, spikes, outerRadius, innerRadius, color) {
    var rot = Math.PI / 2 * 3;
    var x = cx;
    var y = cy;
    var step = Math.PI / spikes;

    //this.ctxPlateau.strokeStyle = "#000";
    this.ctxPlateau.beginPath();
    this.ctxPlateau.moveTo(cx, cy - outerRadius)
    for (let i = 0; i < spikes; i++) {
      x = cx + Math.cos(rot) * outerRadius;
      y = cy + Math.sin(rot) * outerRadius;
      this.ctxPlateau.lineTo(x, y)
      rot += step

      x = cx + Math.cos(rot) * innerRadius;
      y = cy + Math.sin(rot) * innerRadius;
      this.ctxPlateau.lineTo(x, y)
      rot += step
    }
    this.ctxPlateau.lineTo(cx, cy - outerRadius)
    this.ctxPlateau.closePath();
    this.ctxPlateau.fillStyle = color;
    this.ctxPlateau.fill();

  }

  drawPrison(fromx, fromy, tox, toy, color) {
    this.ctxPlateau.beginPath();

    let epaisseur = (tox - fromx) / 5;
    this.ctxPlateau.rect(fromx, fromy, epaisseur, toy - fromy)
    this.ctxPlateau.rect(fromx + 2*epaisseur, fromy, epaisseur, toy - fromy)
    this.ctxPlateau.rect(fromx + 4*epaisseur, fromy, epaisseur, toy - fromy)

    this.ctxPlateau.closePath();
    //var r_a = 0.3;
    //this.ctxPlateau.fillStyle = `rgba(color[1], color[2], color[3], ${r_a})`;
    this.ctxPlateau.fillStyle = color;
    this.ctxPlateau.fill();
  }


  drawCircle(milieux, milieuy, rayon, color  ) {
    this.ctxPlateau.beginPath();


    //ctx.arc(x, y, rayon, angleDépart, angleFin, sensAntiHoraire);
    this.ctxPlateau.arc(milieux, milieuy, rayon, 0, 2*Math.PI, true)

    this.ctxPlateau.closePath();
    this.ctxPlateau.fillStyle = color;
    this.ctxPlateau.fill();
  }


 drawCross(x, y, size, width, color) {
    // Start at the top left corner and draw an X
    this.ctxPlateau.beginPath();
    x -= size;
    y -= size;
    this.ctxPlateau.moveTo(x, y);
    x += width / 2;
    y -= width / 2;
    this.ctxPlateau.lineTo(x, y);
    x += size;
    y += size;
    this.ctxPlateau.lineTo(x, y);
    x += size;
    y -= size;
    this.ctxPlateau.lineTo(x, y);
    x += width / 2;
    y += width / 2;
    this.ctxPlateau.lineTo(x, y);
    x -= size;
    y += size;
    this.ctxPlateau.lineTo(x, y);
    x += size;
    y += size;
    this.ctxPlateau.lineTo(x, y);
    x -= width / 2;
    y += width / 2;
    this.ctxPlateau.lineTo(x, y);
    x -= size;
    y -= size;
    this.ctxPlateau.lineTo(x, y);
    x -= size;
    y += size;
    this.ctxPlateau.lineTo(x, y);
    x -= width / 2;
    y -= width / 2;
    this.ctxPlateau.lineTo(x, y);
    x += size;
    y -= size;
    this.ctxPlateau.lineTo(x, y);
    x -= size;
    y -= size;
    this.ctxPlateau.lineTo(x, y);
    //this.ctxPlateau.stroke();
    this.ctxPlateau.closePath();
    this.ctxPlateau.fillStyle = color;
    this.ctxPlateau.fill();
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



////////////////////////////////////////////////////////////////////////////////////////////////