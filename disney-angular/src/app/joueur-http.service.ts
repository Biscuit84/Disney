import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Compte, Partie } from 'src/model';
import { Joueur } from 'src/model';
import { PageConnexionService } from './page-connexion/page-connexion.service';
import { AppConfigService } from './app-config.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JoueurHttpService {


  joueurUrl:string;
  joueurs: Array<Joueur> = new Array<Joueur>();
  joueur: Joueur;

  constructor(public compteService: PageConnexionService,private http: HttpClient, private appConfig: AppConfigService) { 
    this.load();
    this.joueurUrl = this.appConfig.backEndUrl + "joueur/"
  }


  findAll(): Array<Joueur> {
    return this.joueurs;
  }
  findById(id: number): Observable<Joueur> {
    return this.http.get<Joueur>(this.joueurUrl + id);
  }

  load() {
    this.http.get<Array<Joueur>>(this.joueurUrl).subscribe(response => {
      this.joueurs = response;
    }, error => console.log(error));
  }
  
  infosJoueur(): Observable<Compte>{
    var url=this.appConfig.backEndUrl+"compte/"+this.compteService.compte.id;
    return this.http.get<Compte>(url);
  }




}
