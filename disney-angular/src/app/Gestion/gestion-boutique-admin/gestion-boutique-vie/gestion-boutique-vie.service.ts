import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfigService } from 'src/app/app-config.service';
import { Vie } from 'src/model';

@Injectable({
  providedIn: 'root'
})
export class GestionBoutiqueVieService {

  vies : Array<Vie> = new Array<Vie>();
  vieURL:string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.vieURL = this.appConfig.backEndUrl + "vie/"
    this.load();

   }


   findAll(): Array<Vie> {
    return this.vies;
  }

  findById(id: number): Observable<Vie> {
    return this.http.get<Vie>(this.vieURL + id);
  }

  create(vie: Vie) {
    this.http.post<Vie>(this.vieURL, vie).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(vie: Vie) {
    this.http.put<Vie>(this.vieURL + vie.id, vie).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  deleteById(id: number) {
    this.http.delete<void>(this.vieURL + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }
  
  load() {
    this.http.get<Array<Vie>>(this.vieURL).subscribe(response => {
      this.vies = response;
    }, error => console.log(error));
  }
}
