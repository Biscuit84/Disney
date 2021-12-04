import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Compte, Partie, Personnage, Plateau } from 'src/model';
import { PageConnexionService } from '../page-connexion/page-connexion.service';
import { PartieHttpService } from '../partie-http.service';
import { PersoObtenuHttpService } from '../perso-obtenu-http.service';
import { PlateauHttpService } from '../plateau-http.service';

@Component({
  selector: 'app-jeu',
  templateUrl: './jeu.component.html',
  styleUrls: ['./jeu.component.css']
})
export class JeuComponent implements OnInit {

  // en fonction du joueur
  idJoueur: number;
  joueur: Compte;
  partie: Partie;

  idPerso: number = null;
  idPlateau: number = null;

  constructor(private router: Router,
    public compteService: PageConnexionService,
    private persoObtenuService: PersoObtenuHttpService,
    private plateauService: PlateauHttpService,
    public partieService: PartieHttpService) {

    this.joueur = this.compteService.compte;
    this.idJoueur = this.joueur.id;
  }

  ngOnInit(): void {
  }

  listPersonnageDispo(): Array<Personnage> {
    return this.persoObtenuService.findAllPersonnageDuJoueur();
  }

  listPlateauxDispo(): Array<Plateau> {
    return this.plateauService.findAll();
  }

  CreerLapartie() {
    this.partieService.launchGame(this.idJoueur, this.idPerso, this.idPlateau).subscribe((resp: Partie) => {
      this.partieService.LaPartie = resp;
      this.partie = resp;
      //console.log(this.partie);
      this.router.navigate(['jeu/ecrandejeu']);
    }, error => {
      console.log(error);
    }, () => {
      console.log("complete");
    });
  }
}
