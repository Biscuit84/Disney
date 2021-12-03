import { Component, OnInit } from '@angular/core';
import { PersonnageHttpService } from 'src/app/personnage-http.service';
import { Personnage } from 'src/model';
import { BoutiquePersoHttpService } from './boutique-perso-http.service';

@Component({
  selector: 'app-boutique-personnages',
  templateUrl: './boutique-personnages.component.html',
  styleUrls: ['./boutique-personnages.component.css']
})
export class BoutiquePersonnagesComponent implements OnInit {

  personnageForm: Personnage;
  portfolio:string="";

  constructor(private persoService: PersonnageHttpService) { }

  ngOnInit(): void {
  }

  // list(): Array<Personnage> {
  //   return this.boutiquePersoService.findAll();
  // }

  listAllPerso():Array<Personnage> {
    return this.persoService.findAll();
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