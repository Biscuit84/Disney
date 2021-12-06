import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { CasesPlateau, Joueur, Partie, TourDeJeuDto } from 'src/model';
import { AppConfigService } from './app-config.service';
import { JoueurHttpService } from './joueur-http.service';

@Injectable({
  providedIn: 'root'
})
export class PartieHttpService {
  parties: Array<Partie> = new Array<Partie>();

  partieUrl: string;
  LaPartie: Partie = new Partie;
  LesCasesPlateau: CasesPlateau;

  victoire: boolean = false;

  constructor(private http: HttpClient, private appConfig: AppConfigService, private joueurService: JoueurHttpService) {
    this.partieUrl = this.appConfig.backEndUrl + "partie/"
  }


  // pour transiter l'info de la victoire de ecran de jeu a fin de partie
  private messageSource = new BehaviorSubject(true);
  currentMessage = this.messageSource.asObservable();

  changeMessage(message: boolean) {
    this.messageSource.next(message)
  }



  // créer une partie avec les données : JOUEUR / PERSO / PLATEAU
  launchGame(idJoueur: number, idPerso: number, idPlateau: number): Observable<Partie> {  // la fonction marche en soit
    return this.http.post<Partie>(this.partieUrl + "debuterLaPartie/" + idJoueur + "/" + idPerso + "/" + idPlateau, null);
  }

  // put du tour de la partie 
  tourdejeu: TourDeJeuDto;
  GameTour(idJouer: number, LaPartie: Partie): Observable<TourDeJeuDto> {
    return this.http.put<TourDeJeuDto>(this.partieUrl + "tour/" + idJouer + "/" + LaPartie.id, null);
  }

  // on efface la partie
  ExitGame() {
    this.LaPartie = null;
    console.log(this.LaPartie)
  }

  findById(id: number): Observable<Partie> {
    return this.http.get<Partie>(this.partieUrl + id);
  }

   setVictoire(v:boolean){
     this.victoire=v;
     console.log(this.victoire);
   }
    

  /*
    // CRUD SIMPLE
    findAll(): Array<Partie> {
      return this.parties;
    }
  
    load() {
      this.http.get<Array<Partie>>(this.partieUrl).subscribe(response => {
        this.parties = response;
      }, error => console.log(error));
    }
  
    create(partie: Partie) {
      this.http.post<Partie>(this.partieUrl, partie).subscribe(resp => {
        this.load();
      }, error => console.log(error));
    }
  
    modify(partie: Partie) {
      this.http.put<Partie>(this.partieUrl + partie.id, partie).subscribe(resp => {
        this.load();
      }, error => console.log(error));
    }
  
    deleteById(id: number) {
      this.http.delete<void>(this.partieUrl + id).subscribe(resp => {
        this.load();
      }, error => console.log(error));
    }
  
    */


}
