import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cases } from 'src/model';
import { AppConfigService } from './app-config.service';

@Injectable({
  providedIn: 'root'
})
export class CaseHttpService {

    
   
  cases: Array<Cases> = new Array<Cases>();
  caseUrl: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.caseUrl = this.appConfig.backEndUrl + "cases/"
    this.load();
  }

  load() {
    this.http.get<Array<Cases>>(this.caseUrl).subscribe(response => {
      this.cases = response;
    }, error => console.log(error));
  }

  findAll(): Array<Cases> {
    return this.cases;
  }

  findAll2(): Observable<Array<Cases>>{
   return this.http.get<Array<Cases>>(this.caseUrl);
  }

  findById(id: number): Observable<Cases> {
    return this.http.get<Cases>(this.caseUrl + id);
  }

}
