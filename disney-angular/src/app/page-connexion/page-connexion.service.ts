import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {  Compte, ConnexionDTO,  } from 'src/model';
import { AppConfigService } from '../app-config.service';

@Injectable({
  providedIn: 'root'
})
export class PageConnexionService {

  connexionUrl: string;
  compte: Compte;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.connexionUrl = this.appConfig.backEndUrl + "compte/"
  }

  connexion(connexion: ConnexionDTO): Observable<Compte> {
    return this.http.post<Compte>(this.connexionUrl + "connexion", connexion);
  }

  deconnexion() {
    this.compte = null;
  }

  isJoueur(): boolean {
    return this.compte?.role=='joueur';
  }

  isAdmin(): boolean {
    return this.compte?.role=='admin';
  }
}
