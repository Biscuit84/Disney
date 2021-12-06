import { Component, OnInit } from '@angular/core';
import { Compte } from 'src/model';
import { PageConnexionService } from '../page-connexion/page-connexion.service';
import { ModificationProfilJoueurService } from './modification-profil-joueur.service';

@Component({
  selector: 'app-modification-profil-joueur',
  templateUrl: './modification-profil-joueur.component.html',
  styleUrls: ['./modification-profil-joueur.component.css']
})
export class ModificationProfilJoueurComponent implements OnInit {
joueurForm : Compte
  constructor(public compteService:PageConnexionService, private gestionJoueurService:ModificationProfilJoueurService) {
    this.joueurForm=this.compteService.compte;
   }

  ngOnInit(): void {
  }
  edit() {
    this.gestionJoueurService.findById(this.compteService.compte.id).subscribe(response => {
      this.joueurForm = response;
    }, err => console.log(err));
  }

  save() {
    if(this.joueurForm.id) {
      this.gestionJoueurService.modify(this.joueurForm);
    } else {
      this.gestionJoueurService.create(this.joueurForm);
    }

    
    this.cancel();
  }

  cancel() {
    
    this.joueurForm = null;
  }

}
