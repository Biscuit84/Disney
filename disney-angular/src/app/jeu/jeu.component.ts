import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Compte, Partie, Personnage, Plateau } from 'src/model';
import { CasesPlateauHttpService } from '../cases-plateau-http.service';
import { PageConnexionService } from '../page-connexion/page-connexion.service';
import { PartieHttpService } from '../partie-http.service';
import { PersoObtenuHttpService } from '../perso-obtenu-http.service';
import { PersonnageHttpService } from '../personnage-http.service';
import { PlateauHttpService } from '../plateau-http.service';

@Component({
  selector: 'app-jeu',
  templateUrl: './jeu.component.html',
  styleUrls: ['./jeu.component.css', './livre.css']
})
export class JeuComponent implements OnInit {

  // en fonction du joueur
  idJoueur: number;
  joueur: Compte;
  partie: Partie;
  personnage: Personnage = new Personnage;
  plateau: Plateau;

  idPerso: number = null;
  idPlateau: number = null;

  partiePossible: boolean;
  life: number;

  constructor(private router: Router,
    public compteService: PageConnexionService,
    private persoObtenuService: PersoObtenuHttpService,
    private personnageService: PersonnageHttpService,
    private plateauService: PlateauHttpService,
    public partieService: PartieHttpService,
    public casesPlateauService: CasesPlateauHttpService) {

    this.joueur = this.compteService.compte;
    this.idJoueur = this.joueur.id;
    this.life = this.compteService.compte.life;
    if (this.life > 0) {
      this.partiePossible = true;
    }
    else { this.partiePossible = false }
    this.listPersonnageDispo();
  }

  ngOnInit(): void {
  }

  listPersonnageDispo(): Array<Personnage> {
    return this.persoObtenuService.findAllPersonnageDuJoueur();
  }

  personnageSelectionne() {
    this.personnageService.findById(this.idPerso).subscribe(response => {
      this.personnage = response;
      console.log("ici")
    }, err => console.log(err));
  }


  listPlateauxDispo(): Array<Plateau> {
    return this.plateauService.findAll();
  }

  CreerLapartie() {
    this.partieService.launchGame(this.idJoueur, this.idPerso, this.idPlateau).subscribe((resp: Partie) => {
      this.compteService.compte.life--;
      this.partieService.LaPartie = resp;
      this.partie = resp;
      console.log(this.compteService.compte)
      if (this.compteService.compte.life > 0) {
        // c'est ok la partie se créé
        //this.partiePossible=true;
        console.log(resp)
        this.casesPlateauService.findAllCasesByPlateau(this.partie.plateau.id).subscribe(resp => {
          this.casesPlateauService.lesCasesPlateau = resp;
          this.router.navigate(['jeu/ecrandejeu']);
        });
      }
      else {
        // ça veut dire qu'il y a plus assez de vie
        console.log("tu n'as pas de vie")
        //this.partiePossible=false;
      }


    }, error => {
      console.log(error);
    }, () => {
      console.log("complete");
    });
  }
}
