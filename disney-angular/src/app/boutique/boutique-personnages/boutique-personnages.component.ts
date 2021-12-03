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
  listePersonnagesPanier: Array<Personnage> = new Array<Personnage>();
  listeViePanier: Array<Vie> = new Array<Vie>();
  boutique: BoutiqueDto;
  prixTotalPanier: number = 0;
  // compte: Compte;
  nombreEtoilesJoueur: number;
  joueur: Compte;


  constructor(private pageConnexionService: PageConnexionService, private persoService: PersonnageHttpService, private boutiqueService: BoutiqueHttpService, private joueurService: JoueurHttpService) {
    // this.compte= pageConnexionService.connexion;
    this.joueur = this.pageConnexionService.compte;
    // this.nombreEtoileJoueur=this.joueur.nbEtoiles
  }

  ngOnInit(): void {
    console.log("ON CHARGE LE TOUT");
    
    this.listAllPersoBoutiqueDto();
  }

  // connecJoueur(){
  //   this.pageConnexionService.connexion().;
  // }

  // list(): Array<Personnage> {
  //   return this.boutiquePersoService.findAll();
  // }

  listAllPerso(): Array<Personnage> {
    return this.persoService.findAll();
  }

  listAllPersoBoutiqueDto(){
    // this.boutique = this.boutiqueService.findBoutique();

    this.boutiqueService.loadBoutique().subscribe(res => {
      this.boutique = res;
      this.boutiquePerso = this.boutique.personnages;
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
    }, error => console.log(error));
    console.log("panierDto::::::::::::::::::::::{}", panierDto);



  }

  afficheRecapCommande() {
    // for (let p of this.listePersonnagesPanier) {
    //   let nom = p.nom;
    //   this.prixTotalPanier = (this.prixTotalPanier + p.prixAchatPerso);
    // }
    this.montantDuPanier();
    return this.listePersonnagesPanier;

  }


  ajouterAuPanier(personnage: Personnage) {
    this.listePersonnagesPanier.push(personnage);
    console.log("ma list dans le panier : {}", this.listePersonnagesPanier);



    // for (let i=0; i<this.listePersonnagesPanier.length; i++ ){
    //     this.isDisabled=true;

    // }



  }





  // somme des achats en etoile: 
  montantDuPanier() {
    this.prixTotalPanier = 0;
    for (let p of this.listePersonnagesPanier) {
      // console.log(p.prix);
      this.prixTotalPanier = (this.prixTotalPanier + p.prixAchatPerso);

    }

    // $(".montantTotalPanier").html(this.prixTotalPanier + '<i class="fas fa-star etoile"></i>');
  }


  afficherListePersoPanier() {
    // let li = "";
    // this.listePersonnagesPanier.forEach(function (pp) {
    //     console.log("pp : ", pp);
    //     li += '<li class="itemAchatPerso" >' + pp.personnage.nom + ': ' + pp.personnage.prixAchatPerso + ' <i class="fas fa-star etoile"></i> <button type="button" class="btn " onclick="removePerso(id)" id="' + pp.personnage.nom + '"> <i class="far fa-trash-alt poubelle"></i> </button> </li>';
    // });

    // document.getElementById("listePersosPanier").innerHTML = li;
  }



  //Afficher le bouton Panier seulement si on achète des choses
  afficherListePanier() {
    // if (this.listePersonnagesPanier.length > 0) {
    //     $("#listePanier").css("visibility", "visible");
    // } else {
    //     $("#listePanier").css("visibility", "hidden");
    // }

  }


  // add() {
  //   this.personnageForm = new Personnage();
  // }

  // edit(id: number) {
  //   this.boutiquePersoService.findById(id).subscribe(response => {
  //     this.personnageForm = response;
  //   }, err => console.log(err));
  // }

  // save() {
  //   if(this.personnageForm.id) {
  //     this.boutiquePersoService.modify(this.personnageForm);
  //   } else {
  //     this.boutiquePersoService.create(this.personnageForm);
  //   }

  //   this.cancel();
  // }

  // cancel() {
  //   this.personnageForm = null;
  // }

  // remove(id: number) {
  //   this.boutiquePersoService.deleteById(id);
  // }

  // loadPortfolio() : string{
  //   this.portfolio= "";
  //   var nb : number=0;

  //  var listePersonnages : Array<Personnage> = new Array<Personnage>();
  //   listePersonnages= this.persoService.findAll();

  // for (let perso of listePersonnages){
  // //si on est modulo 3 et que l'on ne vient pas juste de rajouter un ligne
  // if (nb % 3 == 0 && nb !== 0) {
  //   // je ferme ma balise row
  //   this.portfolio += '</div>';
  //   this.portfolio += '<br/>';
  // }
  // //on met 3 perso par ligne
  // if (nb % 3 == 0) {
  //   this.portfolio += '<div class="row">';
  // }
  // this.portfolio += '<div class="media achat col">';
  // this.portfolio += '<img class="mr-3 portfolio rounded-circle" src="' + perso.avatar + '" alt="' + perso.nom + '" data-bs-toggle="collapse" data-bs-target="#collapse' + perso.id + '" aria-expanded="false" aria-controls="collapseWidthExample">'
  // this.portfolio += '<div class="media-body collapse collapse-horizontal mr-200" style="min-height: 180px;" id="collapse' + perso.id + '" data-bs-parent="#portfolioCollapse">';
  // this.portfolio += '<div class="card card-body detail" style="width: 35rem;">';
  // this.portfolio += '<h2 class="nomPerso">' + perso.nom + '</h2>';
  // // portolio += '<p class="contenuPouvoir" data-bs-toggle="collapse" aria-expanded="false" aria-controls="collapseExample" data-bs-target="#collapseChild' + perso.id + '">' + perso.pouvoirLibelle + '&nbsp; <img class="iconePouvoir" src="' + perso.pouvoirImg + '" /></p>';
  // // portolio += '<div class="collapse" id="collapseChild' + perso.id + '">';
  // // portolio += '<div class="card card-body descriptionPouvoir">'
  // // portolio += perso.pouvoirDescription;
  // // portolio += '</div>';
  // // portolio += '</div>';
  // this.portfolio += '<p class="prix">' + perso.prixAchatPerso + '&nbsp;<i class="fas fa-star etoile"></i></p>';
  // this.portfolio += '<div class="divBouton" id="' + perso.id + perso.prixAchatPerso + '">';
  // this.portfolio += '<button type="button" class="btn btn-info btn-sm bouton" id="' + perso.id + '" onclick="ajouterAuPanier(id)"><i class="bi bi-star-fill" style:color="yellow"></i> Acheter</button>';
  // this.portfolio += '</div>';
  // this.portfolio += '</div>';
  // this.portfolio += '</div>';
  // // fin de la div media achat col
  // this.portfolio += '</div>'
  // nb++;
  // // si je suis sur mon dernier perso, je close ma balise row
  // if (nb == listePersonnages.length) {
  //   // je ferme ma balise row
  //   this.portfolio += '</div>';
  // }
  // };

  // return this.portfolio;
  // }

}