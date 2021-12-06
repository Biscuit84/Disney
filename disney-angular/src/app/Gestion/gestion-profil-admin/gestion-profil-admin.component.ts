import { Component, OnInit } from '@angular/core';
import { PageConnexionService } from 'src/app/page-connexion/page-connexion.service';
import { Compte } from 'src/model';

@Component({
  selector: 'app-gestion-profil-admin',
  templateUrl: './gestion-profil-admin.component.html',
  styleUrls: ['./gestion-profil-admin.component.css']
})
export class GestionProfilAdminComponent implements OnInit {
admin:Compte
  constructor(public compteService:PageConnexionService) {
    this.admin=this.compteService.compte
   }

  ngOnInit(): void {
  }

}
