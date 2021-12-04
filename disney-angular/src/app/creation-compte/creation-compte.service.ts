import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Compte } from 'src/model';
import { AppConfigService } from '../app-config.service';

@Injectable({
  providedIn: 'root'
})
export class CreationCompteService {
creaUrl:string

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.creaUrl=this.appConfig.backEndUrl+"compte"
    
  }
  create(compte: Compte) {
    this.http.post<Compte>(this.creaUrl, compte).subscribe(resp => {
      
    }, error => console.log(error));
  }
}
