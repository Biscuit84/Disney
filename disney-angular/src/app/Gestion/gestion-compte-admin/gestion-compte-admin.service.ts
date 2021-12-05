import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfigService } from 'src/app/app-config.service';
import { Compte } from 'src/model';

@Injectable({
  providedIn: 'root'
})
export class GestionCompteAdminService {

  comptes: Array<Compte> = new Array<Compte>();
  compteUrl: string;
  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.compteUrl = this.appConfig.backEndUrl + "compte/"
    this.load();
   }

   findAll(): Array<Compte> {
    return this.comptes;
  }
  findById(id: number): Observable<Compte> {
    return this.http.get<Compte>(this.compteUrl + id);
  }

  create(evaluation: Compte) {
    this.http.post<Compte>(this.compteUrl, evaluation).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(evaluation: Compte) {
    this.http.put<Compte>(this.compteUrl + evaluation.id, evaluation).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }
  deleteById(id: number) {
    this.http.delete<void>(this.compteUrl + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  load() {
    this.http.get<Array<Compte>>(this.compteUrl).subscribe(response => {
      this.comptes = response;
    }, error => console.log(error));
  }
}
