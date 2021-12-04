import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Personnage } from 'src/model';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class PersonnageHttpService {

  personnages: Array<Personnage> = new Array<Personnage>();
  persoUrl: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.persoUrl = this.appConfig.backEndUrl + "personnage/"
    this.load();
  }

  findAll(): Array<Personnage> {
    //console.log(this.personnages)
    return this.personnages;
    
  }

  findById(id: number): Observable<Personnage> {
    return this.http.get<Personnage>(this.persoUrl + id);
  }

  create(personnage: Personnage) {
    this.http.post<Personnage>(this.persoUrl, personnage).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(personnage: Personnage) {
    this.http.put<Personnage>(this.persoUrl + personnage.id, personnage).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  deleteById(id: number) {
    this.http.delete<void>(this.persoUrl + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  load() {
    this.http.get<Array<Personnage>>(this.persoUrl).subscribe(response => {
      this.personnages = response;
     // console.log(this.personnages)
    }, error => console.log(error));
  }
}
