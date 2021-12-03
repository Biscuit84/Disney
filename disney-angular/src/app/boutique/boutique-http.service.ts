import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Boutique, BoutiqueDto } from 'src/model';
import { AppConfigService } from '../app-config.service';

@Injectable({
  providedIn: 'root'
})
export class BoutiqueHttpService {

  boutiqueDto: BoutiqueDto = new BoutiqueDto();
  boutique: Boutique= new Boutique();
  boutiqueUrl: string;

  constructor(private http: HttpClient, private appConfig: AppConfigService) {
    this.boutiqueUrl = this.appConfig.backEndUrl + "boutique/"
    // this.load();
  }

  // findAll(): Array<Personnage> {
  //   return this.personnages;
  // }

  findByIdWithDetail(id: number): Observable<Boutique> {
    return this.http.get<Boutique>(this.boutiqueUrl + id);
  }

  // create(personnage: Personnage) {
  //   this.http.post<Personnage>(this.persoUrl, personnage).subscribe(resp => {
  //     this.load();
  //   }, error => console.log(error));
  // }

  // modify(personnage: Personnage) {
  //   this.http.put<Personnage>(this.persoUrl + personnage.id, personnage).subscribe(resp => {
  //     this.load();
  //   }, error => console.log(error));
  // }

  // deleteById(id: number) {
  //   this.http.delete<void>(this.persoUrl + id).subscribe(resp => {
  //     this.load();
  //   }, error => console.log(error));
  // }

  load(id : number) {
    this.http.get<BoutiqueDto>(this.boutiqueUrl + id).subscribe(response => {
      this.boutiqueDto = response;
    }, error => console.log(error));
  }
}
