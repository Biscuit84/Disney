import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Compte } from 'src/model';
import { AppConfigService } from '../app-config.service';
import { PageConnexionService } from '../page-connexion/page-connexion.service';

@Injectable({
  providedIn: 'root'
})
export class ModificationProfilJoueurService {
  gestionUrl:string
  comptes:Array<Compte> = new Array<Compte>();
  constructor(private http: HttpClient, private appConfig: AppConfigService, public compteService:PageConnexionService) {
    this.gestionUrl = this.appConfig.backEndUrl + "compte/"
    this.load();
   }
   findById(id: number): Observable<Compte> {
    return this.http.get<Compte>(this.gestionUrl + id);
  }

  create(joueur: Compte) {
    this.http.post<Compte>(this.gestionUrl, joueur).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(joueur: Compte) {
    this.http.put<Compte>(this.gestionUrl + joueur.id, joueur).subscribe(resp => {
      this.compteService.compte = resp;
      this.load();
    }, error => console.log(error));
  }

  deleteById(id: number) {
    this.http.delete<void>(this.gestionUrl + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  load() {
    this.http.get<Array<Compte>>(this.gestionUrl).subscribe(response => {
      this.comptes = response;
    }, error => console.log(error));
  }
}
