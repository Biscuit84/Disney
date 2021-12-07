


















export class Square {
  private color = 'red';
  private x = 0;
  private y = 0;
  private z = 30;

  constructor(private ctx: CanvasRenderingContext2D) { }

  moveRight() {
    this.x++;
    this.draw();
  }

  private draw() {
    this.ctx.fillStyle = this.color;
    this.ctx.fillRect(this.z * this.x, this.z * this.y, this.z, this.z);
  }
}




export class pionPlayer {
  numeroPassage: number;
  image: string;
  positionYPlayer: number;
  positionIndexCasePlayer: number;
  playerIsMoving: boolean;
  playedTurn: number;
  positionXPlayer: number;
  positionIndexCasePlayerSuivante: number;
  positionIndexCasePlayerPrecedente: number;
  futurePositionIndexCasePlayer: number;
  finished: boolean;
  constructor(numeroPassage?: number, image?: string, positionXPlayer?: number, positionYPlayer?: number, positionIndexCasePlayerSuivante?: number, positionIndexCasePlayerPrecedente?: number, positionIndexCasePlayer?: number, futurePositionIndexCasePlayer?: number, playerIsMoving?: boolean, playedTurn?: number, finished?: boolean) {
    this.numeroPassage = numeroPassage; //ordre de passage
    this.image = image; //image du pion
    this.positionXPlayer = positionXPlayer;
    this.positionYPlayer = positionYPlayer;
    this.positionIndexCasePlayerSuivante = positionIndexCasePlayerSuivante;
    this.positionIndexCasePlayerPrecedente = positionIndexCasePlayerPrecedente;
    this.positionIndexCasePlayer = positionIndexCasePlayer;
    this.futurePositionIndexCasePlayer = futurePositionIndexCasePlayer;
    this.playerIsMoving = playerIsMoving;
    this.playedTurn = playedTurn;
    this.finished = finished;
  }
}


export class CaseVirtuelle {
  ordre: number;
  nom: string;
  parameter: number;
  typeCase: string;
  color:string;
  positionCaseX: number;
  positionCaseY:number;
  parametre:number;

  constructor(
    ordre?: number, 
    nom?: string, 
    parameter?: number, 
    typeCase?: string, 
    color?: string,
    positionCaseX?: number,
    positionCaseY?:number,
    parametre?:number

) {
    this.ordre = ordre
    this.nom = nom
    this.parameter = parameter
    this.typeCase = typeCase
    this.color = color
    this.positionCaseX=positionCaseX
    this.positionCaseY=positionCaseY
    this.parametre=parametre
  }

}


