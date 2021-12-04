import { Component, OnInit } from '@angular/core';
import { Compte, Joueur } from 'src/model';
import { PageConnexionComponent } from '../page-connexion/page-connexion.component';
import { PageConnexionService } from '../page-connexion/page-connexion.service';
import { GestionDuCompteService } from './gestion-du-compte.service';


@Component({
  selector: 'gestion-du-compte',
  templateUrl: './gestion-du-compte.component.html',
  styleUrls: ['./gestion-du-compte.component.css']
})
export class GestionDuCompteComponent implements OnInit {
joueur:Compte
  constructor(public compteService:PageConnexionService, private gestionDuCompteService:GestionDuCompteService) {
    this.joueur=this.compteService.compte
    
   }

  ngOnInit(): void {
  }

  showHistorique(){
 return this.gestionDuCompteService.Historique()
  }
}
