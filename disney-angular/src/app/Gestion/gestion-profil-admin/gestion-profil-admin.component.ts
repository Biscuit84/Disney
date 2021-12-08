import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PageConnexionService } from 'src/app/page-connexion/page-connexion.service';
import { Compte } from 'src/model';
import { GestionProfilAdminService } from './gestion-profil-admin.service';
@Component({
  selector: 'app-gestion-profil-admin',
  templateUrl: './gestion-profil-admin.component.html',
  styleUrls: ['./gestion-profil-admin.component.css']
})
export class GestionProfilAdminComponent implements OnInit {
  admin: Compte
  adminForm: Compte = new Compte();
  constructor(public compteService: PageConnexionService, private gestionAdminService: GestionProfilAdminService, public router: Router) {
    this.adminForm = this.compteService.compte;
    this.edit();
  }

  ngOnInit(): void {
  }

  edit() {
    this.gestionAdminService.findById(this.compteService.compte.id).subscribe(response => {
      this.adminForm = response;
    }, err => console.log(err));
  }

  save() {
    if (this.adminForm.id) {
      this.gestionAdminService.modify(this.adminForm).subscribe(resp => {
        this.adminForm = resp;
      }, error => console.log(error));
    } else {
      this.gestionAdminService.create(this.adminForm).subscribe(resp => {
        this.adminForm = resp;
      }, error => console.log(error));
    }

    this.cancel();
    this.router.navigate(['gestionAdmin'])
  }

  cancel() {
    this.adminForm = null;
  }
}
