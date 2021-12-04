import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Compte, Partie, Personnage, PersoObtenu, Plateau, PartieDTO } from 'src/model';
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

  partieForm: PartieDTO = new PartieDTO();
  persoObtenuDispos: Array<PersoObtenu> = new Array<PersoObtenu>();
  personnagesDispos: Array<Personnage> = new Array<Personnage>();
  idPerso: number = null;
  idPlateau: number = null;


  constructor(private router: Router,
    public compteService: PageConnexionService,
    private persoObtenuService: PersoObtenuHttpService,
    private plateauService: PlateauHttpService,
    private personnageService: PersonnageHttpService,
    public partieService: PartieHttpService) {

    this.joueur = this.compteService.compte;
    this.idJoueur = this.joueur.id;

  }

  ngOnInit(): void {
  }

  /*
    listPersoObtenu(idJoueur: number): Array<PersoObtenu> {
      // trouver la liste des personnages obtenus par le joueur : 
      return this.persoObtenuDispos = this.persoObtenuService.findAll();
    }
    */

  listPersonnageDispo(): Array<Personnage> {
    return this.persoObtenuService.findAllPersonnageDuJoueur();
  }


  listPlateauxDispo(): Array<Plateau> {
    return this.plateauService.findAll();
  }


  CreerLapartie() {
   this.plateauService.findById(this.idPlateau).subscribe( resp => {
    this.partieForm.plateau = resp;
   }, error => console.log(error))
   this.personnageService.findById(this.idPerso).subscribe( resp => {
    this.partieForm.MonPersonnage = resp;
   }, error => console.log(error))
    this.partieService.launchGame(this.idJoueur, this.idPerso, this.idPlateau, this.partieForm).subscribe(resp => {
      this.partieService.LaPartie = resp;
      console.log("///// dans créer la partie ")
      console.log(this.partieService.LaPartie) // ça ça marche
      console.log("/////")
    }, error => {
      console.log(error);
    });

    this.router.navigate(['jeu/ecrandejeu']);    // redirection : [routerLink]="['/jeu/ecrandejeu']" routerLinkActive="active"

  }



}
