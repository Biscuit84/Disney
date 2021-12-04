import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Joueur } from 'src/model';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class JoueurHttpService {

  
   
  joueurs: Array<Joueur> = new Array<Joueur>();
  joueurUrl: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.joueurUrl = this.appConfig.backEndUrl + "joueur/"
    this.load();
  }

  load() {
    this.http.get<Array<Joueur>>(this.joueurUrl).subscribe(response => {
      this.joueurs = response;
    }, error => console.log(error));
  }
  findAll(): Array<Joueur> {
    return this.joueurs;
  }

  findById(id: number): Observable<Joueur> {
    return this.http.get<Joueur>(this.joueurUrl + id);
  }
}
