
// utilitaires pour le jeu  (full front)



export class PionPlayer  {
    numeroPassage: number;
    image: string;
    positionXPlayer: number;
    positionIndexCasePlayer: number;
    positionIndexCasePlayerPrecedente: number;
    futurePositionIndexCasePlayer: number;
    playerIsMoving: boolean;
    playedTurn: number;
    finished: boolean;

     
        constructor(numeroPassage?:number, image?:string, positionXPlayer?:number, positionYPlayer?:number, positionIndexCasePlayerSuivante?:number, positionIndexCasePlayerPrecedente?:number, positionIndexCasePlayer?:number, futurePositionIndexCasePlayer?:number, playerIsMoving?:boolean, playedTurn?:number, finished?:boolean){
          this.numeroPassage= numeroPassage; //ordre de passage
          this.image=image; //image du pion
          this.positionXPlayer=positionXPlayer;
          this.positionXPlayer=positionYPlayer;
          this.positionIndexCasePlayerPrecedente=positionIndexCasePlayerSuivante;
          this.positionIndexCasePlayerPrecedente=positionIndexCasePlayerPrecedente;
          this.positionIndexCasePlayer=positionIndexCasePlayer;
          this.futurePositionIndexCasePlayer=futurePositionIndexCasePlayer;
          this.playerIsMoving= playerIsMoving;
          this.playedTurn=playedTurn;
          this.finished=finished;
        }
      


}