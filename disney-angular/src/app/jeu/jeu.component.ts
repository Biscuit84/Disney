import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Compte, Partie, Personnage, Plateau } from 'src/model';
import { PageConnexionService } from '../page-connexion/page-connexion.service';
import { PartieHttpService } from '../partie-http.service';
import { PersoObtenuHttpService } from '../perso-obtenu-http.service';
import { PersonnageHttpService } from '../personnage-http.service';
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
  personnage: Personnage = new Personnage;

  idPerso: number = null;
  idPlateau: number = null;


  constructor(private router: Router,
    public compteService: PageConnexionService,
    private persoObtenuService: PersoObtenuHttpService,
    private personnageService: PersonnageHttpService,
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
<<<<<<< refs/remotes/origin/master
   this.plateauService.findById(this.idPlateau).subscribe( resp => {
    this.partieForm.plateau = resp;
   }, error => console.log(error))
   this.personnageService.findById(this.idPerso).subscribe( resp => {
    this.partieForm.monPersonnage = resp;
   }, error => console.log(error))
    this.partieService.launchGame(this.idJoueur, this.idPerso, this.idPlateau, this.partieForm).subscribe(resp => {
=======
    this.partieService.launchGame(this.idJoueur, this.idPerso, this.idPlateau).subscribe((resp: Partie) => {
>>>>>>> liaison back / front pour le plateau
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
