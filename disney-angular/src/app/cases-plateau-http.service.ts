import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CasesPlateau } from 'src/model';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class CasesPlateauHttpService {

  casesPlateauX: Array<CasesPlateau> = new Array<CasesPlateau>();
  casesPlateauUrl:string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.casesPlateauUrl = this.appConfig.backEndUrl + "casesPlateau/"
    this.load();
   }

   load() {
    this.http.get<Array<CasesPlateau>>(this.casesPlateauUrl).subscribe(response => {

      this.casesPlateauX = response;
    }, error => console.log(error));
  }

  //CRUD
  findAll(): Array<CasesPlateau> {
    return this.casesPlateauX;
  }

  findById(id: number): Observable<CasesPlateau> {
    return this.http.get<CasesPlateau>(this.casesPlateauUrl + id);
  }

  deleteById(id: number) {
    this.http.delete<void>(this.casesPlateauUrl + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  create(casesPlateau: CasesPlateau) {
    this.http.post<CasesPlateau>(this.casesPlateauUrl, casesPlateau).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(casesPlateau: CasesPlateau) {
    this.http.put<CasesPlateau>(this.casesPlateauUrl + casesPlateau.id, casesPlateau).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  //AUTRES



}
