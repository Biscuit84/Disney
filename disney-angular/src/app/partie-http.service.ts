import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Partie,  PartieDTO,  TourDeJeuDto } from 'src/model';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class PartieHttpService {
  parties: Array<Partie> = new Array<Partie>();

  partieUrl: string;
  LaPartie: Partie = new Partie;
  

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.partieUrl = this.appConfig.backEndUrl + "partie/"
    //this.load();
  }


  // FONCTIONS SPECIALES (HEHE)
  // créer une partie avec les données : JOUEUR / PERSO / PLATEAU
  launchGame(idJoueur: number, idPerso: number, idPlateau: number, LaPartie: PartieDTO): Observable<Partie> {  // la fonction marche en soit
    return this.http.post<Partie>(`${this.partieUrl}debuterLaPartie/${idJoueur}/${idPerso}/${idPlateau}`, LaPartie);
  }

  // put du tour de la partie 
  /*
 @PutMapping("/tour/{idJoueur}/{idPartie}")
 public TourDeJeuDto jouerUnTour(@PathVariable Long idJoueur, @PathVariable Long idPartie) {
  */
  tourdejeu: TourDeJeuDto;

  GameTour(idJouer: number, LaPartie: Partie): TourDeJeuDto {
    this.http.put<TourDeJeuDto>(this.partieUrl + "tour/" + idJouer + "/" + LaPartie.id, LaPartie).subscribe(resp => {
      this.tourdejeu = resp;

    }, error => console.log(error));
    return this.tourdejeu;
  }


  ExitGame() {
    this.LaPartie = null;
  }




  /*
    // CRUD SIMPLE
    findAll(): Array<Partie> {
      return this.parties;
    }
  
    findById(id: number): Observable<Partie> {
      return this.http.get<Partie>(this.partieUrl + id);
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
