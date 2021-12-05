import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cases, CasesPlateau } from 'src/model';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class CaseHttpService {

    
   
  cases: Array<Cases> = new Array<Cases>();
  casesUrl: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.casesUrl = this.appConfig.backEndUrl + "cases/"
    this.load();
  }

  //CRUD
  load() {
    this.http.get<Array<Cases>>(this.casesUrl).subscribe(response => {
      this.cases = response;
    }, error => console.log(error));
  }

  findAll(): Array<Cases> {
    return this.cases;
  }

  findAll2(): Observable<Array<Cases>>{
   return this.http.get<Array<Cases>>(this.casesUrl);
  }

  findById(id: number): Observable<Cases> {
    return this.http.get<Cases>(this.casesUrl + id);
  }

  deleteById(id: number) {
    this.http.delete<void>(this.casesUrl + id).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  create(casesPlateau: Cases) {
    this.http.post<Cases>(this.casesUrl, casesPlateau).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  modify(casesPlateau: Cases) {
    this.http.put<Cases>(this.casesUrl + casesPlateau.id, casesPlateau).subscribe(resp => {
      this.load();
    }, error => console.log(error));
  }

  //AUTRES



}
