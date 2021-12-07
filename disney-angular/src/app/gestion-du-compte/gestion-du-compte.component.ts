import { Component, OnInit } from '@angular/core';
import { PageConnexionService } from '../page-connexion/page-connexion.service';
import { GestionDuCompteService } from './gestion-du-compte.service';


@Component({
  selector: 'gestion-du-compte',
  templateUrl: './gestion-du-compte.component.html',
  styleUrls: ['./gestion-du-compte.component.css']
})
export class GestionDuCompteComponent implements OnInit {
  constructor(public compteService: PageConnexionService, private gestionDuCompteService: GestionDuCompteService) {
    
    this.gestionDuCompteService.load();
    this.compteService.reload();
  }

  ngOnInit(): void {
  }

  showHistorique() {
    return this.gestionDuCompteService.Historique()
  }
}
