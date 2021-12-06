import { APP_BOOTSTRAP_LISTENER, Component, OnInit } from '@angular/core';
import { PageConnexionService } from 'src/app/page-connexion/page-connexion.service';
import { Compte } from 'src/model';
import { GestionProfilAdminService } from './gestion-profil-admin.service';
import * as $ from 'jquery';
import 'bootstrap';
@Component({
  selector: 'app-gestion-profil-admin',
  templateUrl: './gestion-profil-admin.component.html',
  styleUrls: ['./gestion-profil-admin.component.css']
})
export class GestionProfilAdminComponent implements OnInit {
admin:Compte
adminForm:Compte
  constructor(public compteService:PageConnexionService, private gestionAdminService:GestionProfilAdminService) {
    this.adminForm=this.compteService.compte;
   }

  ngOnInit(): void {
  }
  edit() {
    this.gestionAdminService.findById(this.compteService.compte.id).subscribe(response => {
      this.adminForm = response;
    }, err => console.log(err));
  }

  save() {
    if(this.adminForm.id) {
      this.gestionAdminService.modify(this.adminForm);
    } else {
      this.gestionAdminService.create(this.adminForm);
    }

    this.test();
    this.cancel();
  }

  cancel() {
    
    this.adminForm = null;
  }

  test() {
    // console.log($(".collapse"));

    // let test = document.getElementById("flush-collapseOne");
    // let bs = new APP_BOOTSTRAP_LISTENER.
    // $(".collapse").collapse('toggle');
  }
}
