import { Component, OnInit } from '@angular/core';
import { Compte } from 'src/model';
import { GestionCompteAdminService } from './gestion-compte-admin.service';

@Component({
  selector: 'app-gestion-compte-admin',
  templateUrl: './gestion-compte-admin.component.html',
  styleUrls: ['./gestion-compte-admin.component.css']
})
export class GestionCompteAdminComponent implements OnInit {
  admin = "admin"
  joueur= "joueur"
  gestionForm: Compte
  constructor(private gestionCompteAdminService: GestionCompteAdminService) { }

  ngOnInit(): void {
  }
  list(): Array<Compte> {
    return this.gestionCompteAdminService.findAll();
  }

  add() {
    this.gestionForm = new Compte();
  }

  edit(id: number) {
    this.gestionCompteAdminService.findById(id).subscribe(response => {
      this.gestionForm = response;
    }, err => console.log(err));
  }

  save() {
    if(this.gestionForm.id) {
      this.gestionCompteAdminService.modify(this.gestionForm);
    } else {
      this.gestionCompteAdminService.create(this.gestionForm);
    }

    this.cancel();
  }

  cancel() {
    this.gestionForm = null;
  }

  remove(id: number) {
    this.gestionCompteAdminService.deleteById(id);
  }
}

