import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfigService } from './app-config.service';
import { Plateau } from './model';

@Injectable({
  providedIn: 'root'
})
export class PlateauHttpService {

  plateaux: Array<Plateau> = new Array<Plateau>();
  plateauUrl:string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.plateauUrl = this.appConfig.backEndUrl + "plateau/"
    this.load();
   }

   load() {
    this.http.get<Array<Plateau>>(this.plateauUrl).subscribe(response => {

      this.plateaux = response;
    }, error => console.log(error));
  }

  findAll(): Array<Plateau> {
    console.log(this.plateaux);
    return this.plateaux;
  }

  findById(id: number): Observable<Plateau> {
    return this.http.get<Plateau>(this.plateauUrl + id);
  }

  deleteById(id: number) {
    this.http.delete<void>(this.plateauUrl + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  create(plateau: Plateau) {
    this.http.post<Plateau>(this.plateauUrl, plateau).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(plateau: Plateau) {
    this.http.put<Plateau>(this.plateauUrl + plateau.id, plateau).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

}
