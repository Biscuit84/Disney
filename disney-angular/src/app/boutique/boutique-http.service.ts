import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Boutique, BoutiqueDto, Compte, Joueur, Personnage, PersonnageDto, PersoObtenu } from 'src/model';
import { AppConfigService } from '../app-config.service';
import { PageConnexionService } from '../page-connexion/page-connexion.service';

@Injectable({
  providedIn: 'root'
})
export class BoutiqueHttpService {

  boutiqueDto: BoutiqueDto = new BoutiqueDto();
  boutique: Boutique = new Boutique();
  // boutiques: Array<BoutiqueDto> = new Array <BoutiqueDto>();
  boutiqueUrl: string;
  idJoueur: number ;
  joueur:Compte;

  constructor(public compteService: PageConnexionService, private http: HttpClient, private appConfig: AppConfigService) {
    this.boutiqueUrl = this.appConfig.backEndUrl + "boutique/"
  this.joueur= this.compteService.compte;
   this.idJoueur= this.joueur.id
    this.load(this.idJoueur);

  }

  findBoutique(): BoutiqueDto {
    return this.boutiqueDto;
  }

  // findBoutiqueBase(id: number): Boutique {
  //   return this.http.get<Boutique>(this.boutiqueUrl + id);
  // }

  // create(personnage: Personnage) {
  //   this.http.post<Personnage>(this.persoUrl, personnage).subscribe(resp => {
  //     this.load();
  //   }, error => console.log(error));
  // }

  // modify(personnage: PersonnageDto) {
  //   this.http.put<PersoObtenu>("http://localhost:8080/persoObtenu/" + personnage.personnage.id, this.personnage ).subscribe(resp => {
  //   }, error => console.log(error));
  // }

  // deleteById(id: number) {
  //   this.http.delete<void>(this.persoUrl + id).subscribe(resp => {
  //     this.load();
  //   }, error => console.log(error));
  // }

  load(id: number) {
    this.http.get<BoutiqueDto>(this.boutiqueUrl + id).subscribe(response => {
      this.boutiqueDto = response;
    }, error => console.log(error));
  }
}
