import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfigService } from 'src/app/app-config.service';
import { PageConnexionService } from 'src/app/page-connexion/page-connexion.service';
import { Compte } from 'src/model';

@Injectable({
  providedIn: 'root'
})
export class GestionProfilAdminService {
  gestionUrl:string
  admins:Array<Compte> = new Array<Compte>();
  constructor(private http: HttpClient, private appConfig: AppConfigService, public compteService:PageConnexionService) {
    this.gestionUrl = this.appConfig.backEndUrl + "compte/"
    this.load();
   }
   findById(id: number): Observable<Compte> {
    return this.http.get<Compte>(this.gestionUrl + id);
  }

  create(admin: Compte) {
    this.http.post<Compte>(this.gestionUrl, admin).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(admin: Compte) {
    this.http.put<Compte>(this.gestionUrl + admin.id, admin).subscribe(resp => {
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
      this.admins = response;
    }, error => console.log(error));
  }
}
