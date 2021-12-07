import { Component, OnInit } from '@angular/core';
import { AppConfigService } from 'src/app/app-config.service';
import { Etoile } from 'src/model';
import { GestionBoutiqueEtoileService } from './gestion-boutique-etoile.service';


@Component({
  selector: 'app-gestion-boutique-etoile',
  templateUrl: './gestion-boutique-etoile.component.html',
  styleUrls: ['./gestion-boutique-etoile.component.css']
})
export class GestionBoutiqueEtoileComponent implements OnInit {

etoileForm :Etoile;


  constructor(private appConfig: AppConfigService, private etoileService : GestionBoutiqueEtoileService) { }

  ngOnInit(): void {
  }

  list(): Array<Etoile> {
    return this.etoileService.findAll();
  }

  add() {
    this.etoileForm = new Etoile();
  }

  edit(id: number) {
    this.etoileService.findById(id).subscribe(response => {
      this.etoileForm = response;
    
    }, err => console.log(err));
    
  }

  save() {
    if(this.etoileForm.id) {
      this.etoileService.modify(this.etoileForm);
    } else {
      this.etoileService.create(this.etoileForm);
    }

    this.cancel();
  }

  cancel() {
    this.etoileForm = null;
  }

  remove(id: number) {
    this.etoileService.deleteById(id);
  }

}
