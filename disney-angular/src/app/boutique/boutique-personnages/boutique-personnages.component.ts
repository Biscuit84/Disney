import { Component, OnInit } from '@angular/core';
import { Personnage } from 'src/model';
import { BoutiquePersoHttpService } from './boutique-perso-http.service';

@Component({
  selector: 'app-boutique-personnages',
  templateUrl: './boutique-personnages.component.html',
  styleUrls: ['./boutique-personnages.component.css']
})
export class BoutiquePersonnagesComponent implements OnInit {

  personnageForm: Personnage;

  constructor(private boutiquePersoService: BoutiquePersoHttpService) { }

  ngOnInit(): void {
  }

  list(): Array<Personnage> {
    return this.boutiquePersoService.findAll();
  }

  add() {
    this.personnageForm = new Personnage();
  }

  edit(id: number) {
    this.boutiquePersoService.findById(id).subscribe(response => {
      this.personnageForm = response;
    }, err => console.log(err));
  }

  save() {
    if(this.personnageForm.id) {
      this.boutiquePersoService.modify(this.personnageForm);
    } else {
      this.boutiquePersoService.create(this.personnageForm);
    }

    this.cancel();
  }

  cancel() {
    this.personnageForm = null;
  }

  remove(id: number) {
    this.boutiquePersoService.deleteById(id);
  }
}