import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfigService } from 'src/app/app-config.service';
import { Etoile } from 'src/model';

@Injectable({
  providedIn: 'root'
})
export class GestionBoutiqueEtoileService {

  etoiles : Array<Etoile> = new Array<Etoile>();
  etoileURL:string;



  constructor(private http: HttpClient, private appConfig: AppConfigService) { 
    this.etoileURL = this.appConfig.backEndUrl + "etoile/"
    this.load();

  }
  findAll(): Array<Etoile> {
    return this.etoiles;
  }

  findById(id: number): Observable<Etoile> {
    return this.http.get<Etoile>(this.etoileURL + id);
  }

  create(etoile: Etoile) {
    this.http.post<Etoile>(this.etoileURL, etoile).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(etoile: Etoile) {
    this.http.put<Etoile>(this.etoileURL + etoile.id, etoile).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  deleteById(id: number) {
    this.http.delete<void>(this.etoileURL + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }
  
  load() {
    this.http.get<Array<Etoile>>(this.etoileURL).subscribe(response => {
      this.etoiles = response;
    }, error => console.log(error));
  }


}
