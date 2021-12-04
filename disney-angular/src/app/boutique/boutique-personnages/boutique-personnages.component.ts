import { Component, OnInit } from '@angular/core';
import { PersonnageHttpService } from 'src/app/personnage-http.service';
import { BoutiqueDto, BoutiquePersoAndLifeDto, Compte, Personnage, PersonnageDto, Vie } from 'src/model';
import { BoutiqueHttpService } from '../boutique-http.service';
import * as $ from 'jquery';
import { PageConnexionService } from 'src/app/page-connexion/page-connexion.service';
import { JoueurHttpService } from 'src/app/joueur-http.service';
// declare var $:JQueryStatic;

@Component({
  selector: 'app-boutique-personnages',
  templateUrl: './boutique-personnages.component.html',
  styleUrls: ['./boutique-personnages.component.css']
})
export class BoutiquePersonnagesComponent implements OnInit {

  personnageForm: Personnage;
  portfolio: string = "";
  boutiquePerso: Array<PersonnageDto> = new Array<PersonnageDto>();
  boutiqueVie: Array<Vie> = new Array<Vie>();
  listePersonnagesPanier: Array<Personnage> = new Array<Personnage>();
  listeViePanier: Array<Vie> = new Array<Vie>();
  boutique: BoutiqueDto;
  prixTotalPanier: number = 0;
  // compte: Compte;
  nombreEtoilesJoueur: number;
  joueur: Compte;


  constructor(private pageConnexionService: PageConnexionService, private persoService: PersonnageHttpService,
    private boutiqueService: BoutiqueHttpService, private joueurService: JoueurHttpService) {
    // this.compte= pageConnexionService.connexion;
    this.joueur = this.pageConnexionService.compte;
    // this.nombreEtoileJoueur=this.joueur.nbEtoiles
  }

  ngOnInit(): void {
    console.log("ON CHARGE LE TOUT");

    this.listAllPersoBoutiqueDto();
  }
  listAllPerso(): Array<Personnage> {
    return this.persoService.findAll();
  }

  listAllPersoBoutiqueDto() {
    // this.boutique = this.boutiqueService.findBoutique();

    this.boutiqueService.loadBoutique().subscribe(res => {
      this.boutique = res;
      this.boutiquePerso = this.boutique.personnages;
      this.boutiqueVie = this.boutique.listVies;
    })
  }

  nombreEtoileJoueur(id: number) {

  }

  /**
   * @returns true si le perso est dans le panier, false sinon
   */
  isPersonnageEstDansLePanier(personnage: Personnage) {
    return this.listePersonnagesPanier.includes(personnage);
  }


  viderPanier() {
    this.resetPanier();
  }

  validerPanier() {
    var panierDto: BoutiquePersoAndLifeDto = new BoutiquePersoAndLifeDto();

    panierDto.idPersonnages = this.listePersonnagesPanier.map(({ id }) => id);
    //TODO mapper avec la liste de vie plus tard
    panierDto.idVies = this.listeViePanier.map(({ id }) => id);
    this.boutiqueService.achatBoutique(panierDto).subscribe((resp: BoutiqueDto) => {
      this.joueurService.infosJoueur().subscribe(respJoueur => {
        this.joueur = respJoueur;
      }, error => console.log(error));

      this.boutique = resp;
      this.boutiquePerso = this.boutique.personnages;
      this.resetPanier();
    }, error => console.log(error));
  }

  afficheRecapCommande() {
    this.montantDuPanier();
    return this.listePersonnagesPanier;
  }


  ajouterAuPanier(personnage: Personnage) {
    this.listePersonnagesPanier.push(personnage);
  }

  ajouterVieAuPanier(vie: Vie) {
    this.listeViePanier.push(vie);
  }

  // somme des achats en etoile: 
  montantDuPanier() {
    this.prixTotalPanier = 0;
    for (let p of this.listePersonnagesPanier) {
      this.prixTotalPanier = (this.prixTotalPanier + p.prixAchatPerso);
    }
    for (let vie of this.listeViePanier) {
      this.prixTotalPanier = (this.prixTotalPanier + vie.prix);
    }
  }



  private resetPanier() {
    this.listePersonnagesPanier = [];
    this.listeViePanier = [];
    this.prixTotalPanier = 0;
  }

  removePerso(nom: string){

    let idx = this.listePersonnagesPanier.findIndex(perso => perso.nom == nom);
    if (idx != -1) {
      this.listePersonnagesPanier.splice(idx, 1);
    }
  }


  removeVie(id: number){

    let idx = this.listeViePanier.findIndex(vie => vie.id == id);
    if (idx != -1) {
      this.listeViePanier.splice(idx, 1);
    }
  }

}