import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Compte, Historique } from 'src/model';
import { AppConfigService } from '../app-config.service';
import { PageConnexionService } from '../page-connexion/page-connexion.service';

@Injectable({
  providedIn: 'root'
})
export class GestionDuCompteService {
  historiqueUrl: string;
  historiques: Array<Historique> = new Array<Historique>();
  joueur: Compte
  // id:number
  constructor(public compteService: PageConnexionService, private http: HttpClient, private appConfig: AppConfigService) {

        // this.id=this.joueur.id

    this.historiqueUrl = this.appConfig.backEndUrl + "historique/";


    this.load()
  }

  Historique(): Array<Historique> {
    return this.historiques;
  }

  load() {
    
    if (this.compteService.compte) {
      this.http.get<Array<Historique>>(this.historiqueUrl + this.compteService.compte.id + "/joueur").subscribe(response => {
        this.historiques = response;
      }, error => console.log(error));
    }
  }
}
