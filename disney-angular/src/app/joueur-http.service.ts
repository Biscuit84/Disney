import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Compte } from 'src/model';
import { AppConfigService } from './app-config.service';
import { PageConnexionService } from './page-connexion/page-connexion.service';

@Injectable({
  providedIn: 'root'
})
export class JoueurHttpService {

  constructor(public compteService: PageConnexionService,private http: HttpClient, private appConfig: AppConfigService) { 

  }

  infosJoueur(): Observable<Compte>{
    var url=this.appConfig.backEndUrl+"compte/"+this.compteService.compte.id;
    return this.http.get<Compte>(url);
  }



}
