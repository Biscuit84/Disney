import { Component, ElementRef, OnInit, ViewChild, NgZone } from '@angular/core';
import { pionPlayer, Square } from './plateau-canvas-util';
@Component({
  selector: 'ecran-de-jeu, [ecran-de-jeu]',
  templateUrl: './ecran-de-jeu.component.html',
  styleUrls: ['./ecran-de-jeu.component.css']
})
export class EcranDeJeuComponent implements OnInit {

  declare $: any;
  //img1: any = new Image();


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

  // nombre de case du plateau
  public nbcasePlateau: number = 61; // à recuperer en base

  nbcaseDemande():[ number, number]{
  if (this.nbcasePlateau <= 9)        {   this.nBcaseW = 3;    this.nBcaseH = 3   }
  else if (this.nbcasePlateau <= 16)  {   this.nBcaseW = 4;    this.nBcaseH = 4;  }
  else if (this.nbcasePlateau <= 25)  {   this.nBcaseW = 5;    this.nBcaseH = 5;  }
  else if (this.nbcasePlateau <= 36)  {   this.nBcaseW = 6;    this.nBcaseH = 6;  }
  else if (this.nbcasePlateau <= 49)  {   this.nBcaseW = 7;    this.nBcaseH = 7;  }
  else if (this.nbcasePlateau <= 64)  {   this.nBcaseW = 8;    this.nBcaseH = 8;  }
  else if (this.nbcasePlateau <= 91)  {   this.nBcaseW = 9;    this.nBcaseH = 9;  }
  else if (this.nbcasePlateau <= 100) {   this.nBcaseW = 10;   this.nBcaseH = 10; }

  var format:[ number, number]= [ this.nBcaseW, this.nBcaseH];
 return format;
}
   public format=this.nbcaseDemande();
  public nBcaseW:number = this.format[0];
  public nBcaseH:number = this.format[1];

  public nombreDe: number = 2;
  public valeurDesDes: number = 0;

  public listeCases = [];


  //public taille1 = document.getElementById("canvasesdiv");
  //public h:number = document.getElementById("canvasesdiv");

  // taille du canvas
  //w:number=800;
  //h:number=800;

  //wMax=$("#canvasesdiv").width(); // on prepare le responsive !
  //hMax=$("#canvasesdiv").height();

  //w = this.wMax - (this.wMax%this.nBcaseW); // pour couper les cases proprement
  //h= this.hMax - (this.hMax%this.nBcaseH);

  public w: number;// = 800;
  public h: number;// = 800;

  // taille des cases
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

  ////////////////////////// CONSTRUCTEUR  //////////////////////////
  constructor(private ngZone: NgZone) {  }


  ////////////////////////// ngOnInit  //////////////////////////

  // note : ça sert à initialiser des données et est appelé qu'une fois ! 

  ngOnInit() {
    //initialisation des canvas : 
    this.ctxPlateau = this.canvasPlateau.nativeElement.getContext('2d');
    this.ctxJoueur = this.canvasJoueur.nativeElement.getContext('2d');
    this.ctxPlayerIA1 = this.canvasIA1.nativeElement.getContext('2d');
    this.ctxPlayerIA2 = this.canvasIA2.nativeElement.getContext('2d');
    this.ctxPlayerIA3 = this.canvasIA3.nativeElement.getContext('2d');

    this.drawPlateau(this);
    //console.log(this.taille1)
   // console.log(this.nBcaseW)

    //this.imagePlayer.src = '../../../assets/images/jeu/player.png';
    //this.imagePlayerIA1.src = '../../../assets/images/jeu/ennemi.jpg';
    //this.imagePlayerIA2.src = '../../../assets/images/jeu/dingo.png';
    //this.imagePlayerIA3.src = '../../../assets/images/jeu/donald.png';

    // création des objets pion
    //var pionJoueur: pionPlayer = new pionPlayer(0, this.imagePlayer.src, 0, 0, 0, 0, 0, 0, false, 0, false);
    //var pionIA1: pionPlayer = new pionPlayer(1, this.imagePlayerIA1.src, 0, 0, 0, 0, 0, 0, false, 0, false);
    //var pionIA2: pionPlayer = new pionPlayer(2, this.imagePlayerIA2.src, 0, 0, 0, 0, 0, 0, false, 0, false);
    //var pionIA3: pionPlayer = new pionPlayer(3, this.imagePlayerIA3.src, 0, 0, 0, 0, 0, 0, false, 0, false);

    //var listePion = [];
    //listePion.push(this.pionJoueur);
    //listePion.push(this.pionIA1);
    //listePion.push(this.pionIA2);
    //listePion.push(this.pionIA3);
    //console.log(listePion);


    //console.log(this.ctxJoueur);

    this.drawPlayer(this, this.pionJoueur);
    this.drawPlayer(this, this.pionIA1);
    this.drawPlayer(this, this.pionIA2);
    this.drawPlayer(this, this.pionIA3);

    var actualisationJoueur1 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionJoueur);
    var actualisationJoueur2 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionIA1);
    var actualisationJoueur3 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionIA2);
    var actualisationJoueur4 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionIA3);
    //var actualisationJoueur5 = setInterval(function() {
    //  console.log("boucle")
    //}, this.tauxRaffraichissement);




  } //ngOnInit fin

  //this.slider.oninput = function (): number {

  //this.output.innerHTML = this.value;
  //this.niveauDeVitesseDuPion = this.value;
  //return value;
  //}

  //this.niveauDeVitesseDuPion = this.slider.value;


  ngOnChanges() {
    //let actualisationJoueur1 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionJoueur);
    // let actualisationJoueur2 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionIA1);
    // let actualisationJoueur3 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionIA2);
    // let actualisationJoueur4 = setInterval(this.drawPlayer, this.tauxRaffraichissement, this, this.pionIA3);
    // let actualisationJoueur5 = setInterval(function() {
    //  console.log("boucle")
    //}, this.tauxRaffraichissement);
  }


  ngOnDestroy() {
    //clearInterval(this.interval);
    //cancelAnimationFrame(this.requestId);
  }


  ////////////////////////// Methode de la classe  //////////////////////////

  // bouton joueur
  Play() {
    this.Jouer(this, this.pionJoueur);
  }
  // bouton fin de tour
  EndTurn() {
    this.FinDeTour(this, this.pionJoueur);
  }


  // dessine le plateau
  drawPlateau(self) {
   // this.ctxPlateau.
   // taille du plateau 

   //tailleW = self.canvasPlateau.nativeElement.width;
   //console.log(tailleW);

  //self.canvasPlateau.nativeElement.width
  //self.canvasJoueur.nativeElement.width
  //public taille1 = document.getElementById("canvasesdiv");
  //public h:number = document.getElementById("canvasesdiv");

  // taille du canvas
  //w:number=800;
  //h:number=800;

    let wMax:number = self.canvasPlateau.nativeElement.width;
    let hMax:number = self.canvasPlateau.nativeElement.height;
  
  console.log(wMax);

  //wMax=$("#canvasesdiv").width(); // on prepare le responsive !
  //hMax=$("#canvasesdiv").height();

  this.w = wMax - (wMax%this.nBcaseW); // pour couper les cases proprement
  this.h= hMax - (hMax%this.nBcaseH);

  //public w: number = 800;
  //public h: number = 800;

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
    //console.log("on est dans drawPlayer")
    //console.log(self.ctxJoueur);
    //console.log("yolo");

    // vitesse en fonciton du range
    let n: number = self.niveauDeVitesseDuPion;

    //console.log("////////////////////////////");
    //console.log(this.velocityH);
    //console.log(self.velocityH);
    //console.log("////////////////////////////");

    if (n == 1) { self.velocityH = 1; self.velocityW = 1; }               // tres lent
    else if (n == 2) { self.velocityH = self.taillecaseH / 32; self.velocityW = self.taillecaseW / 32; }
    else if (n == 3) { self.velocityH = self.taillecaseH / 16; self.velocityW = self.taillecaseW / 16; }
    else if (n == 4) { self.velocityH = self.taillecaseH / 8; self.velocityW = self.taillecaseW / 8; }
    else if (n == 5) { self.velocityH = self.taillecaseH / 4; self.velocityW = self.taillecaseW / 4; }
    else if (n == 6) { self.velocityH = self.taillecaseH / 2; self.velocityW = self.taillecaseW / 2; }
    else if (n == 7) { self.velocityH = self.taillecaseH; self.velocityW = self.taillecaseW; }     // tres rapide


    //clear pour refresh
    if (pion.numeroPassage == 0) {
      //console.log(self.canvasJoueur.nativeElement.width);
      //self.ctxJoueur.clearRect(0, 0, self.canvasJoueur.nativeElement.width , self.canvasJoueur.nativeElement.height);
    }
    else if (pion.numeroPassage == 1) {
      //this.ctxPlayerIA1.clearRect(0, 0, this.w, this.h);
    }
    else if (pion.numeroPassage == 2) {
      //this.ctxPlayerIA2.clearRect(0, 0, this.w, this.h);
    }
    else if (pion.numeroPassage == 3) {
      //this.ctxPlayerIA3.clearRect(0, 0, this.w, this.h);
    }

    // trouver A quel index de case se trouve le joueur
    //pion.positionIndexCasePlayer = positionActuelleJoueur(pion); // case
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
    if (pion.numeroPassage == 0) {
      //console.log("on doit bouger de " + diff);
      //console.log("////////////////////////////");
      //console.log(this.nbcasePlateau);
      //console.log("////////////////////////////");
    }



    // rentre dans la boucle seulement si l'index <= à la longueur de la liste des cases du plateau
    if (pion.positionIndexCasePlayerSuivante <= self.nbcasePlateau - 1 || pion.positionIndexCasePlayerPrecedente >= 0) {

      if (diff < 0) {

        pion.playerIsMoving = true;

        //console.log("diff:" + diff + ", position Y du pion : " + pion.positionYPlayer);
        //console.log(pion);
        console.log("on avance de" + diff);

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


      }

    }


    // redessiner le player pour le refresh
    if (pion.numeroPassage == 0) {
      let image = new Image();
      image.width = self.taillecaseW / 2;
      image.height = self.taillecaseH / 2;
      image.src = pion.image;
      image.onload = function () {
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
        self.ctxPlayerIA3.drawImage(image, positionx, positiony, image.width, image.height);
      }

    }
  }




  // permet de jouer
  Jouer(self, pion) {

    playSoundDice();
    self.roulementDe();
    let variable1 = pion.numeroPassage;
    console.log("c'est le pion :" + pion.numeroPassage);


    setTimeout(self.TourJoueur, 2200, self, pion);  //setTimeout(TourJoueur(pionJoueur), 2200); //marche pas  //setTimeout(function() {TourJoueur(pionJoueur);}, 2200); //autre ecriture
    self.joueurActuel = pion.numeroPassage;

    
      if (self.joueurActuel != 0) { // si ce n'est pas le joueur qui joue on "click" auto sur la suite
        let pionSuivant = self.listePion[self.joueurActuel + 1];
        setTimeout(self.FinDeTour, 5000, self, pionSuivant);
      }
      else if (self.joueurActuel == 3) { //dernier pion avant le joueur
        //joueurActuel=listePion[0].numeroPassage; // on revient au joueur
        //console.log("à toi de jouer");
        // on active les boutons :
        document.getElementById("boutonJouer").setAttribute('disabled', 'false');
        document.getElementById("boutonFinDeTour").setAttribute('disabled', 'false');
        //$("#boutonJouer").prop('disabled', false);
        //$("#boutonFinDeTour").prop('disabled', false);
      }
  
      if (self.joueurActuel == 0) {
        document.getElementById("boutonJouer").setAttribute('disabled', 'true');
        document.getElementById("boutonFinDeTour").setAttribute('disabled', 'false');
       // $("#boutonJouer").prop('disabled', true);
       // $("#boutonFinDeTour").prop('disabled', false);
      }
    
  }


  // permet de finir son tour et donc de faire jouer les IA
  FinDeTour(self, pion) {
    
    // on a fini le tour ça incremente notre nombre de tour jouer
    pion.playedTurn =   pion.playedTurn + 1;
    //console.log("on est dans fin de tour");
  
    // si c'est pas le dernier pion :
    // faire jouer le prochain :
    if (self.partieEnCours == true) {
      if (self.joueurActuel != 3) {
  
        self.joueurActuel++;
        // console.log("on incremente le numero du joueur")
        //console.log(joueurActuel);
  
        //console.log(self.listePion);
        let pionSuivant = self.listePion[self.joueurActuel];
        setTimeout(self.Jouer, 3000, self, pionSuivant);
        document.getElementById("boutonJouer").setAttribute('disabled', 'true');
        document.getElementById("boutonFinDeTour").setAttribute('disabled', 'true');
       // $("#boutonJouer").prop('disabled', true);
        //$("#boutonFinDeTour").prop('disabled', true);
      }
      else { // sinon on revient au joueur
        self.joueurActuel = 0;
        self.Tour++;// incrémente le nombre de tour
        //console.log("nouveau tour")
        //console.log("joueur suivant :" + joueurActuel);
        document.getElementById("boutonJouer").setAttribute('disabled', 'false');
        document.getElementById("boutonFinDeTour").setAttribute('disabled', 'false');
        //$("#boutonJouer").prop('disabled', false);
        //$("#boutonFinDeTour").prop('disabled', false);
      }
    }
  
    else if (self.partieEnCours == false) {
      //finDePartie(pion);
    }
    
  }


  // lances les dés et donne la valeur que doit atteindre le pion
  TourJoueur(self, pion) {

    console.log(self.taillecaseH);

    // Affiche la valeur du dé dans le html
    //var monDeAvance = document.getElementById("valeurDeAvance");
    //console.log(monDeAvance);

    let totalDe = 0;
    //random de 1 à 6s
    //var idDe = "imageDe";
    let lanceDe: number = Math.floor(Math.random() * 6) + 1;
    //let lanceDe = self.DiceValue(idDe);
    // let lanceDe = self.DiceValue("imageDe");
    console.log(self.nombreDe + "des")
    if (self.nombreDe == 1) {
      totalDe = lanceDe;
    }
    else if (self.nombreDe == 2) {
      let lanceDe2: number = Math.floor(Math.random() * 6) + 1;
      //var lanceDe2 = self.DiceValue("imageDe2");
      totalDe = lanceDe + lanceDe2;
      console.log("lanceDe2");
      console.log(lanceDe2);
    }
    self.valeurDesDes = totalDe;
    //monDeAvance.innerHTML = toString(thtotalDe);

    console.log("lanceDe");
    console.log(lanceDe);



    console.log("pion.positionIndexCasePlayer");
    console.log(pion.positionIndexCasePlayer);
    console.log("totalDe");
    console.log(totalDe);

    //calcule le nouvel index:
    var nouvelIndex = Number(pion.positionIndexCasePlayer) + Number(totalDe);

    // gere le cas où on dépasse la case arrivee
    if (nouvelIndex >= self.nbcasePlateau - 1) {
      nouvelIndex = self.nbcasePlateau - 1;

      // et donc ça veut dire qu'on a gagné !

      // la partie est terminée
      self.partieEnCours = false;
      pion.finished = true; // c'est CE pion qui a gagné
      console.log("la partie est terminée")
    }

    //détermine la future position X et Y du player
    pion.futurePositionIndexCasePlayer = nouvelIndex;
    console.log("pion.futurePositionIndexCasePlayer");
    console.log(pion.futurePositionIndexCasePlayer);
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
  
    drawAll() {
      //actualisationBackground = setInterval(this.drawBackground, this.tauxRaffraichissement);
      //  drawBackground(); // ne sert à rien
      this.drawPlateau();
      let actualisationJoueur = setInterval(this.drawPlayers, this.tauxRaffraichissement);
  
    }
  */
  /*
    drawPlayers() {
      //this.drawPlayer(this.pionJoueur);
      //this.drawPlayer(pionjoueur);
      //drawPlayer(pionIA1);
      //drawPlayer(pionIA2);
      //drawPlayer(pionIA3);
      //tourActuel.innerHTML = Tour;
      //tourJoueur.innerHTML = joueurActuel;
  
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







}






//////////////////////////////////////////////////////////////////////////////////////////
// son

function playSoundDice() {
  document.getElementById("diceThrowAudio").setAttribute('src', '../../assets/sons/DiceThrow.mp3');
  const myaudio = document.getElementById("diceThrowAudio");
  (myaudio as HTMLAudioElement).play();
}
/*

// CODE POUR LE SON
var e = document.querySelector('.volume-slider-con');
var eInner = document.querySelector('.volume-slider');
var audio = document.querySelector('audio');
var drag = false;
e.addEventListener('mousedown', function (ev) {
  drag = true;
  updateBar(ev.clientX);
});
document.addEventListener('mousemove', function (ev) {
  if (drag) {
    updateBar(ev.clientX);
  }
});
document.addEventListener('mouseup', function (ev) {
  drag = false;
});
var updateBar = function (x, vol) {
  var volume = e;
  var percentage;
  //if only volume have specificed
  //then direct update volume
  if (vol) {
    percentage = vol * 100;
  } else {
    var position = x - volume.offsetLeft;
    percentage = 100 * position / volume.clientWidth;
  }

  if (percentage > 100) {
    percentage = 100;
  }
  if (percentage < 0) {
    percentage = 0;
  }

  //update volume bar and video volume
  eInner.style.width = percentage + '%';
  audio.volume = percentage / 100;
};
*/























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



// dessine un pion
function drawPlayer2(self, pion) {
  //console.log("on est dans drawPlayer")
  //console.log(self.ctxJoueur);
  //console.log("yolo");

  // vitesse en fonciton du range
  let n: number = this.niveauDeVitesseDuPion;

  if (n == 1) { this.velocityH = 1; this.velocityW = 1; }               // tres lent
  else if (n == 2) { this.velocityH = this.taillecaseH / 32; this.velocityW = this.taillecaseW / 32; }
  else if (n == 3) { this.velocityH = this.taillecaseH / 16; this.velocityW = this.taillecaseW / 16; }
  else if (n == 4) { this.velocityH = this.taillecaseH / 8; this.velocityW = this.taillecaseW / 8; }
  else if (n == 5) { this.velocityH = this.taillecaseH / 4; this.velocityW = this.taillecaseW / 4; }
  else if (n == 6) { this.velocityH = this.taillecaseH / 2; this.velocityW = this.taillecaseW / 2; }
  else if (n == 7) { this.velocityH = this.taillecaseH; this.velocityW = this.taillecaseW; }     // tres rapide


  //clear pour refresh
  if (pion.numeroPassage == 0) {
    //this.ctxJoueur.clearRect(0, 0, this.w, this.h);

  }
  else if (pion.numeroPassage == 1) {
    //this.ctxPlayerIA1.clearRect(0, 0, this.w, this.h);
  }
  else if (pion.numeroPassage == 2) {
    //this.ctxPlayerIA2.clearRect(0, 0, this.w, this.h);
  }
  else if (pion.numeroPassage == 3) {
    //this.ctxPlayerIA3.clearRect(0, 0, this.w, this.h);
  }

  // trouver A quel index de case se trouve le joueur
  //pion.positionIndexCasePlayer = positionActuelleJoueur(pion); // case
  for (let i = 0; i < this.nbcasePlateau; i++) {
    if (this.listeCases[i].positionCaseX === pion.positionXPlayer && this.listeCases[i].positionCaseY === pion.positionYPlayer) { //=== : egalité stricte => le joueur est sur la case i
      pion.positionIndexCasePlayer = i;
    }
  }

  //trouver les index suivants et précédents
  pion.positionIndexCasePlayerSuivante = pion.positionIndexCasePlayer;
  pion.positionIndexCasePlayerSuivante++;
  pion.positionIndexCasePlayerPrecedente = pion.positionIndexCasePlayer;
  pion.positionIndexCasePlayerPrecedente--;


  let diff: number = pion.positionIndexCasePlayer - pion.futurePositionIndexCasePlayer;
  if (pion.numeroPassage == 0) {
    console.log("on doit bouger de " + diff);
    console.log("////////////////////////////");
    console.log(this.nbcasePlateau);
    console.log("////////////////////////////");
  }



  // rentre dans la boucle seulement si l'index <= à la longueur de la liste des cases du plateau
  if (pion.positionIndexCasePlayerSuivante <= this.nbcasePlateau - 1 || pion.positionIndexCasePlayerPrecedente >= 0) {

    if (diff < 0) {

      pion.playerIsMoving = true;

      //console.log("diff:" + diff + ", position Y du pion : " + pion.positionYPlayer);
      //console.log(pion);
      console.log("on avance de" + diff);

      if (this.listeCases[pion.positionIndexCasePlayer].positionCaseY < this.listeCases[pion.positionIndexCasePlayerSuivante].positionCaseY) {      // avancer case par case
        pion.positionYPlayer += this.velocityH;
        //console.log("on avance en Y")
      }

      else if (this.listeCases[pion.positionIndexCasePlayer].positionCaseY > this.listeCases[pion.positionIndexCasePlayerSuivante].positionCaseY) {        //reculer case par case
        pion.positionYPlayer -= this.velocityH;
        //console.log("on recule en Y")
      }

      if (this.listeCases[pion.positionIndexCasePlayer].positionCaseX < this.listeCases[pion.positionIndexCasePlayerSuivante].positionCaseX) {      // avancer case par case
        pion.positionXPlayer += this.velocityW;
      }
      else if (this.listeCases[pion.positionIndexCasePlayer].positionCaseX > this.listeCases[pion.positionIndexCasePlayerSuivante].positionCaseX) {      //reculer case par case
        pion.positionXPlayer -= this.velocityW;
      }
    }
    else if (diff > 0) {

      console.log("on recule de" + diff);

      pion.playerIsMoving = true;
      if (this.listeCases[pion.positionIndexCasePlayer].positionCaseY < this.listeCases[pion.positionIndexCasePlayerPrecedente].positionCaseY) {      // avancer case par case
        pion.positionYPlayer += this.velocityH;
      }
      else if (this.listeCases[pion.positionIndexCasePlayer].positionCaseY > this.listeCases[pion.positionIndexCasePlayerPrecedente].positionCaseY) {        //reculer case par case
        pion.positionYPlayer -= this.velocityH;
      }
      if (this.listeCases[pion.positionIndexCasePlayer].positionCaseX < this.listeCases[pion.positionIndexCasePlayerPrecedente].positionCaseX) {      // avancer case par case
        pion.positionXPlayer += this.velocityW;
      }
      else if (this.listeCases[pion.positionIndexCasePlayer].positionCaseX > this.listeCases[pion.positionIndexCasePlayerPrecedente].positionCaseX) {      //reculer case par case
        pion.positionXPlayer -= this.velocityW;
      }

    }
    else if (diff == 0) {

      //  console.log("le pion"+ pion.numeroPassage+ "est à l'arret");
      pion.playerIsMoving = false;
      if (this.partieEnCours == false && pion.finished == true) {
        //setTimeout(finDePartie, 2000, pion);
      }


    }

  }


  // redessiner le player pour le refresh
  if (pion.numeroPassage == 0) {
    let image = new Image();
    image.width = this.taillecaseW / 2;
    image.height = this.taillecaseH / 2;
    image.src = pion.image;
    image.onload = function () {
      self.ctxJoueur.drawImage(image, pion.positionXPlayer, pion.positionYPlayer, image.width, image.height);
    }
  }
  else if (pion.numeroPassage == 1) {
    let positionx = pion.positionXPlayer + this.taillecaseW / 2;
    let positiony = pion.positionYPlayer;
    let image = new Image();
    image.width = this.taillecaseW / 2;
    image.height = this.taillecaseH / 2;
    image.src = pion.image;
    image.onload = function () {
      self.ctxPlayerIA1.drawImage(image, positionx, positiony, image.width, image.height);
    }
  }
  else if (pion.numeroPassage == 2) {
    let positionx = pion.positionXPlayer;
    let positiony = pion.positionYPlayer + this.taillecaseH / 2;
    let image = new Image();
    image.width = this.taillecaseW / 2;
    image.height = this.taillecaseH / 2;
    image.src = pion.image;
    image.onload = function () {
      self.ctxPlayerIA2.drawImage(image, positionx, positiony, image.width, image.height);
    }
  }

  else if (pion.numeroPassage == 3) {
    let positionx = pion.positionXPlayer + this.taillecaseW / 2;
    let positiony = pion.positionYPlayer + this.taillecaseH / 2;
    let image = new Image();
    image.width = this.taillecaseW / 2;
    image.height = this.taillecaseH / 2;
    image.src = pion.image;
    image.onload = function () {
      self.ctxPlayerIA3.drawImage(image, positionx, positiony, image.width, image.height);
    }

  }
}

