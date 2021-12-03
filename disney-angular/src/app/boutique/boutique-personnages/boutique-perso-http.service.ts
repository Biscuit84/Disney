import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfigService } from 'src/app/app-config.service';
import { Personnage } from 'src/model';

@Injectable({
  providedIn: 'root'
})
export class BoutiquePersoHttpService {

  persos: Array<Personnage> = new Array<Personnage>();
  boutiquePersoUrl: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.boutiquePersoUrl = this.appConfig.backEndUrl + "boutique/personnages/"
    this.load();
  }

  findAll(): Array<Personnage> {
    return this.persos;
  }

  findById(id: number): Observable<Personnage> {
    return this.http.get<Personnage>(this.boutiquePersoUrl + id);
  }

  create(personnage: Personnage) {
    this.http.post<Personnage>(this.boutiquePersoUrl, personnage).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(personnage: Personnage) {
    this.http.put<Personnage>(this.boutiquePersoUrl + personnage.id, personnage).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  deleteById(id: number) {
    this.http.delete<void>(this.boutiquePersoUrl + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  load() {
    this.http.get<Array<Personnage>>(this.boutiquePersoUrl).subscribe(response => {
      this.persos = response;
    }, error => console.log(error));
  }
}
