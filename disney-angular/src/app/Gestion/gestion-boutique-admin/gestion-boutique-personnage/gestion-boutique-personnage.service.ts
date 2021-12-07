import { Injectable } from '@angular/core';
import { Personnage } from 'src/model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppConfigService } from 'src/app/app-config.service';



@Injectable({
  providedIn: 'root'
})
export class GestionBoutiquePersonnageService {

  personnages : Array<Personnage> = new Array<Personnage>();
  personnageURL:string;



  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.personnageURL = this.appConfig.backEndUrl + "personnage/"
    this.load();

   }


   findAll(): Array<Personnage> {
    return this.personnages;
  }

  findById(id: number): Observable<Personnage> {
    return this.http.get<Personnage>(this.personnageURL + id);
  }

  create(personnage: Personnage) {
    this.http.post<Personnage>(this.personnageURL, personnage).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(personnage: Personnage) {
    this.http.put<Personnage>(this.personnageURL + personnage.id, personnage).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  deleteById(id: number) {
    this.http.delete<void>(this.personnageURL + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }
  
  load() {
    this.http.get<Array<Personnage>>(this.personnageURL).subscribe(response => {
      this.personnages = response;
    }, error => console.log(error));
  }


}
