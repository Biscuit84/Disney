import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfigService } from './app-config.service';
import { Cases, Plateau } from '../model';

@Injectable({
  providedIn: 'root'
})
export class PlateauHttpService {

  plateaux: Array<Plateau> = new Array<Plateau>();
  plateauxUniquementDisponibles: Array<Plateau> = new Array<Plateau>();
  plateauUrl: string;
  casesUrl: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.plateauUrl = this.appConfig.backEndUrl + "plateau/"
    this.casesUrl = this.appConfig.backEndUrl + "cases/"
    this.load();
    this.loadPlateauDispo();
  }

  load() {
    this.http.get<Array<Plateau>>(this.plateauUrl).subscribe(response => {

      this.plateaux = response;
    }, error => console.log(error));
  }

  loadPlateauDispo() {
    this.http.get<Array<Plateau>>(this.plateauUrl +"/withPlateauDispo").subscribe(response => {

      this.plateauxUniquementDisponibles = response;
    }, error => console.log(error));
  }

  findAll(): Array<Plateau> {
    //console.log(this.plateaux);
    return this.plateaux;
  }

  findAllPlateauDisponibles( ): Array<Plateau> {
    return this.plateauxUniquementDisponibles;
  }

  findAllPlateau(): Observable<Array<Plateau>> {
    //console.log(this.plateaux);
    return this.http.get<Array<Plateau>>(this.plateauUrl);
  }

  findById(id: number): Observable<Plateau> {
    return this.http.get<Plateau>(this.plateauUrl + id);
  }


  findByPartie(idPartie: number): Observable<Plateau> {
    return this.http.get<Plateau>(this.plateauUrl + idPartie);
  }

  findByIdWithCasesDetails(id: number): Observable<Plateau> {
    return this.http.get<Plateau>(this.plateauUrl + id + "/detailCase");
  }


  findAllCasesByIdPlateau(id: number): Observable<Array<Cases>> {
    return this.http.get<Array<Cases>>(this.casesUrl + "listCases/" + id);
  }





  deleteById(id: number) {
   return this.http.delete<void>(this.plateauUrl + id+ "/deleteWithCasesPlateau");
  }

  //save plateau avec les cases Plateau!!!
  createPlateau(plateau: Plateau): Observable<Plateau> {
    return this.http.post<Plateau>(this.plateauUrl + "createPlateauAvecCasesPlateau", plateau);
  }


  updatePlateau(plateau: Plateau): Observable<Plateau> {
    return this.http.put<Plateau>(this.plateauUrl + "updatePlateauAvecCasesPlateau", plateau);
  }


  create(listeCases: Array<Cases>, plateauNom: string): Observable<Plateau> {
    return this.http.post<Plateau>(this.plateauUrl + plateauNom, listeCases);
  }

  // modify(plateau: Plateau) {
  //   this.http.put<Plateau>(this.plateauUrl + plateau.id, plateau).subscribe(resp => {
  //     this.load();
  //   }, error => console.log(error));
  // }



}
