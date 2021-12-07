import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AvatarHttpService } from 'src/app/avatar-http.service';
import { JoueurHttpService } from 'src/app/joueur-http.service';
import { PageConnexionService } from 'src/app/page-connexion/page-connexion.service';
import { PersonnageHttpService } from 'src/app/personnage-http.service';
import { Avatar, BoutiqueDto, BoutiqueEtoileDto, Compte, Etoile, Personnage } from 'src/model';
import { BoutiqueHttpService } from '../boutique-http.service';

@Component({
  selector: 'app-boutique-etoiles',
  templateUrl: './boutique-etoiles.component.html',
  styleUrls: ['./boutique-etoiles.component.css']
})
export class BoutiqueEtoilesComponent implements OnInit {

  personnageForm: Personnage;
  portfolio: string = "";
  boutiqueEtoiles: Array<Etoile> = new Array<Etoile>();
  listeEtoilesPanier: Array<Etoile> = new Array<Etoile>();
  boutique: BoutiqueDto;
  prixTotalPanier: number = 0;
  // compte: Compte;
  nombreEtoilesJoueur: number;
  joueur: Compte;


  constructor(public pageConnexionService: PageConnexionService, private persoService: PersonnageHttpService, private boutiqueService: BoutiqueHttpService, private joueurService: JoueurHttpService,private router: Router, public avatarService: AvatarHttpService) {
    // this.compte= pageConnexionService.connexion;
    this.joueur = this.pageConnexionService.compte;
    // this.nombreEtoileJoueur=this.joueur.nbEtoiles
  }

  ngOnInit(): void {
    console.log("ON CHARGE LE TOUT");

    this.listAllEtoilesBoutiqueDto();
  }
  // listAllEtoiles(): Array<Personnage> {
  //   return this.persoService.findAll();
  // }

  listAllEtoilesBoutiqueDto() {
    // this.boutique = this.boutiqueService.findBoutique();

    this.boutiqueService.loadBoutique().subscribe(res => {
      this.boutique = res;
      this.boutiqueEtoiles = this.boutique.listEtoiles;
    })
  }

  nombreEtoileJoueur(id: number) {

  }

  // /**
  //  * @returns true si l'é est dans le panier, false sinon
  //  */
  // isEtoileDansLePanier(etoile: Etoile) {
  //   return this.listeEtoilesPanier.includes(etoile);
  // }


  viderPanier() {
    this.resetPanier();
  }

  validerPanier() {
    var panierDto: BoutiqueEtoileDto = new BoutiqueEtoileDto();

    panierDto.idEtoile = this.listeEtoilesPanier.map(({ id }) => id);
    this.boutiqueService.achatBoutiqueEtoile(panierDto).subscribe((resp: BoutiqueDto) => {
      this.joueurService.infosJoueur().subscribe(respJoueur => {
        this.joueur = respJoueur;
        this.pageConnexionService.compte = this.joueur;
      }, error => console.log(error));

      this.boutique = resp;
      this.boutiqueEtoiles = this.boutique.listEtoiles;
      this.resetPanier();
    }, error => console.log(error));
  }

  afficheRecapCommande() {
    this.montantDuPanier();
    return this.listeEtoilesPanier;
  }


  ajouterEtoileAuPanier(etoile: Etoile) {
    this.listeEtoilesPanier.push(etoile);
    this.montantDuPanier();
  }


  // somme des achats en euros: 
  montantDuPanier() {
    this.prixTotalPanier = 0;
    for (let e of this.listeEtoilesPanier) {
      this.prixTotalPanier = (this.prixTotalPanier + e.prix);
    }
  }

  private resetPanier() {
    this.listeEtoilesPanier = [];
    this.prixTotalPanier = 0;
  }

  removeEtoile(id: number){

    let idx = this.listeEtoilesPanier.findIndex(etoile => etoile.id == id);
    if (idx != -1) {
      this.listeEtoilesPanier.splice(idx, 1);
    }
  }
  deconnexion() {
    this.pageConnexionService.deconnexion();
    this.router.navigate(['home'])
  }


  findAvatarCompte() {
  }

  changeAvatar(avatar: Avatar) {
    this.pageConnexionService.updateAvatar(avatar);
  }
}
