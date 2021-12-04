import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Compte, Personnage, PersoObtenu } from 'src/model';
import { AppConfigService } from './app-config.service';
import { PageConnexionService } from './page-connexion/page-connexion.service';

@Injectable({
  providedIn: 'root'
})
export class PersoObtenuHttpService {

  // en fonction du joueur
  idJoueur: number ;
  joueur:Compte;

  
  persoObtenus: Array<PersoObtenu> = new Array<PersoObtenu>();
  personnagesDuJoueur: Array<Personnage> = new Array<Personnage>();
  persoObtenuUrl: string;

  constructor(public compteService: PageConnexionService, private http: HttpClient, private appConfig: AppConfigService) {
    this.persoObtenuUrl = this.appConfig.backEndUrl + "persoObtenu/"
    this.joueur= this.compteService.compte;
    this.idJoueur= this.joueur.id
    this.load(this.idJoueur);
    this.loadPersonnage(this.idJoueur)
  }

  // si on veut trouver tous les perso obtenu ever (ça sert à quedalle)
  findAllExisting(): Array<PersoObtenu> {
    this.http.get<Array<PersoObtenu>>(this.persoObtenuUrl).subscribe(response => {
      this.persoObtenus = response;
    }, error => console.log(error));
    return this.persoObtenus;
  }

  findById(id: number): Observable<PersoObtenu> {
    return this.http.get<PersoObtenu>(this.persoObtenuUrl + id);
  }


  /*

  	@GetMapping("/detail/{id}")
	@JsonView(Views.ViewsPersoObtenuDetailJoueur.class)
	public List<Personnage> findAllPersoObtenuByIdJoueur(@PathVariable Long id) {
		List<PersoObtenu> persosObtenu = persoObtenuRepo.findAllByJoueur(id);
		List<Personnage> listPerso = new ArrayList<Personnage>();
		for(PersoObtenu po : persosObtenu) {
			listPerso.add(po.getPerso());
		}
		return listPerso;
	}
  */





  // PersoObtenu en fonction du joueur
  load(idJoueur: number) {
    this.http.get<Array<PersoObtenu>>(this.persoObtenuUrl + idJoueur + "/joueur").subscribe(response => {
      this.persoObtenus = response;
      //console.log(this.persoObtenus)
    }, error => console.log(error));
  }

  findAll(): Array<PersoObtenu>{
    return this.persoObtenus; // la liste des perso obtenus d'un joueur
  }

    // PersoObtenu en fonction du joueur
    loadPersonnage(idJoueur: number) {
      this.http.get<Array<Personnage>>(this.persoObtenuUrl + "detail/" + idJoueur).subscribe(response => {
        this.personnagesDuJoueur= response;
      //  console.log(this.personnagesDuJoueur);
      }, error => console.log(error));
    }

    findAllPersonnageDuJoueur(){
      return this.personnagesDuJoueur;
    }




  // create(personnage: PersoObtenu) {
  //   this.http.post<PersoObtenu>(this.persoObtenuUrl, personnage).subscribe(resp => {
  //     this.load();
  //   }, error => console.log(error));
  // }

  // modify(personnage: PersoObtenu) {
  //   this.http.put<PersoObtenu>(this.persoObtenuUrl + personnage.id, personnage).subscribe(resp => {
  //     this.load(idJoueur);
  //   }, error => console.log(error));
  // }

  // deleteById(id: number) {
  //   this.http.delete<void>(this.persoObtenuUrl + id).subscribe(resp => {
  //     this.load(idJoueur);
  //   }, error => console.log(error));
  // }




}
