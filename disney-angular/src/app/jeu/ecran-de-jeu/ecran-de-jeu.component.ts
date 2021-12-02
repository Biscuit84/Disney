import { Component, ElementRef, OnInit, ViewChild, NgZone } from '@angular/core';
import { PionPlayer } from './jeu';
import { pionPlayer, Square } from './plateau-canvas-util';



@Component({
  selector: 'ecran-de-jeu, [ecran-de-jeu]',
  templateUrl: './ecran-de-jeu.component.html',
  styleUrls: ['./ecran-de-jeu.component.css']
})
export class EcranDeJeuComponent implements OnInit {

  declare $: any;
  img1: any = new Image();
  

  // variables : 
  velocityH: number = 1; // nb de pixel d'avancement du pion par raffraichissement (10ms)
  velocityW: number = 1;
  tauxRaffraichissement: number = 10; // vitesse du setinterval
  niveauDeVitesseDuPion: number = 1;


  // lancement partie var
  partieEnCours: boolean = true;
  //var Tour = 0;
  //var joueurActuel = 0;
  //var tourActuel = document.getElementById("NbTour");
  //var tourJoueur = document.getElementById("JoueurTour");

  // nombre de case du plateau

  //var format=nbcaseDemande();
  //format:Array<number> = [64,8,8];
  //nbcasePlateau:number = format[0];
  //nBcaseW:number = format[1];
  //vnBcaseH:number = format[2];
  nbcasePlateau: number = 61;
  nBcaseW: number = 8;
  nBcaseH: number = 8;


  listeCases = [];

  // taille du canvas
  //w:number=800;
  //h:number=800;

  //wMax=$("#canvasesdiv").width(); // on prepare le responsive !
  //hMax=$("#canvasesdiv").height();


  //w = this.wMax - (this.wMax%this.nBcaseW); // pour couper les cases proprement
  //h= this.hMax - (this.hMax%this.nBcaseH);

  w: number = 800;
  h: number = 800;

  // taille des cases
  taillecaseW: number = this.w / this.nBcaseW;
  taillecaseH: number = this.h / this.nBcaseH;

  //initialisation des canvas : 
  private ctxPlateau: CanvasRenderingContext2D;
  private ctxJoueur: CanvasRenderingContext2D;
  private ctxPlayerIA1: CanvasRenderingContext2D;
  private ctxPlayerIA2: CanvasRenderingContext2D;
  private ctxPlayerIA3: CanvasRenderingContext2D;

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


  constructor(private ngZone: NgZone) { }

  ngOnInit() {
    //initialisation des canvas : 
    this.ctxPlateau = this.canvasPlateau.nativeElement.getContext('2d');
    this.ctxJoueur = this.canvasJoueur.nativeElement.getContext('2d');
    this.ctxPlayerIA1 = this.canvasIA1.nativeElement.getContext('2d');
    this.ctxPlayerIA2 = this.canvasIA2.nativeElement.getContext('2d');
    this.ctxPlayerIA3 = this.canvasIA3.nativeElement.getContext('2d');


    this.drawPlateau();


    
 
    this.img1.src = '../../src/assets/images/player.png';

    // création des objets pion
    var pionJoueur: pionPlayer = new pionPlayer(0, this.img1.src, 0, 0, 0, 0, 0, 0, false, 0, false);
    var pionIA1: pionPlayer = new pionPlayer(1, "../img/ennemi.jpg", 0, 0, 0, 0, 0, 0, false, 0, false);
    var pionIA2: pionPlayer = new pionPlayer(2, "../img/donald.png", 0, 0, 0, 0, 0, 0, false, 0, false);
    var pionIA3: pionPlayer = new pionPlayer(3, "../img/dingo.png", 0, 0, 0, 0, 0, 0, false, 0, false);

    var listePion = [];
    listePion.push(pionJoueur);
    listePion.push(pionIA1);
    listePion.push(pionIA2);
    listePion.push(pionIA3);
    //console.log(listePion);



    this.drawPlayer(pionJoueur);










    // exemple  : 
    /*
    //création du canvas : 
    this.ctxPlateau.fillStyle = 'red';

    // animation : 
    this.ngZone.runOutsideAngular(() => this.tick());
    setInterval(() => {
      this.tick();
    }, 200);

    // lance le tout !*:
    this.play();
    */
  }


  // exemples :
  /*
   tick() {
     this.ctxPlateau.clearRect(0, 0, this.ctxPlateau.canvas.width, this.ctxPlateau.canvas.height);
     this.squares.forEach((square: Square) => {
       square.moveRight();
     });
     this.requestId = requestAnimationFrame(() => this.tick);
   }
 
   play() {
     const square = new Square(this.ctxPlateau);
     this.squares = this.squares.concat(square);
   }
 
   ngOnDestroy() {
     clearInterval(this.interval);
     cancelAnimationFrame(this.requestId);
   }
 */






  // dessine le plateau
  drawPlateau() {
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
  drawPlayer(pion) {


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
      this.ctxJoueur.clearRect(0, 0, this.w, this.h);
    }
    else if (pion.numeroPassage == 1) {
      this.ctxPlayerIA1.clearRect(0, 0, this.w, this.h);
    }
    else if (pion.numeroPassage == 2) {
      this.ctxPlayerIA2.clearRect(0, 0, this.w, this.h);
    }
    else if (pion.numeroPassage == 3) {
      this.ctxPlayerIA3.clearRect(0, 0, this.w, this.h);
    }

    // trouver A quel index de case se trouve le joueur
    pion.positionIndexCasePlayer = this.positionActuelleJoueur(pion); // case


    //trouver les index suivants et précédents
    pion.positionIndexCasePlayerSuivante = pion.positionIndexCasePlayer;
    pion.positionIndexCasePlayerSuivante++;
    pion.positionIndexCasePlayerPrecedente = pion.positionIndexCasePlayer;
    pion.positionIndexCasePlayerPrecedente--;


    var diff: number = pion.positionIndexCasePlayer - pion.futurePositionIndexCasePlayer;


    // rentre dans la boucle seulement si l'index <= à la longueur de la liste des cases du plateau
    if (pion.positionIndexCasePlayerSuivante <= this.listeCases.length - 1 || pion.positionIndexCasePlayerPrecedente >= 0) {

      if (diff < 0) {

        pion.playerIsMoving = true;

        //console.log("diff:" + diff + ", position Y du pion : " + pion.positionYPlayer);
        //console.log(pion);

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

      //backgroundPlayer.src = pion.image;
      //backgroundPlayer.src = "../img/milkandmocha.jpg";
      //this.ctxJoueur.drawImage(backgroundPlayer, pion.positionXPlayer, pion.positionYPlayer, this.taillecaseW / 2, this.taillecaseH / 2);
      this.ctxJoueur.drawImage(this.img1, pion.positionXPlayer, pion.positionYPlayer, this.taillecaseW / 2, this.taillecaseH / 2);
    }
    else if (pion.numeroPassage == 1) {
      /*
      backgroundPlayerIA1.src = pion.image;
      //console.log(pion.positionYPlayer)
      ctxPlayerIA1.drawImage(backgroundPlayerIA1, pion.positionXPlayer + taillecaseW / 2, pion.positionYPlayer, taillecaseW / 2, taillecaseH / 2);
      */
    }
    else if (pion.numeroPassage == 2) {
      /*
      backgroundPlayerIA2.src = pion.image;
      ctxPlayerIA2.drawImage(backgroundPlayerIA2, pion.positionXPlayer, pion.positionYPlayer + taillecaseH / 2, taillecaseW / 2, taillecaseH / 2);
      */
    }

    else if (pion.numeroPassage == 3) {
      /*
      backgroundPlayerIA3.src = pion.image;
      ctxPlayerIA3.drawImage(backgroundPlayerIA3, pion.positionXPlayer + taillecaseW / 2, pion.positionYPlayer + taillecaseH / 2, taillecaseW / 2, taillecaseH / 2);
      */
    }
  }





  // donne la position actuelle du pion
  positionActuelleJoueur(pion) {
    for (var i in this.listeCases) {
      if (this.listeCases[i].positionCaseX === pion.positionXPlayer && this.listeCases[i].positionCaseY === pion.positionYPlayer) { //=== : egalité stricte => le joueur est sur la case i
        pion.positionIndexCasePlayer = i;
      }
    }
    return pion.positionIndexCasePlayer;
  }


















}


//////////////////////////////////////////////////////////////////////////////////
function isEven(value: number): boolean {
  if (value % 2 == 0)
    return true;

  else
    return false;
}
