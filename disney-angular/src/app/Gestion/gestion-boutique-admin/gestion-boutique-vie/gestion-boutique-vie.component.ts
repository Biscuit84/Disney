import { Component, OnInit } from '@angular/core';
import { Vie } from 'src/model';
import { GestionBoutiqueVieService } from './gestion-boutique-vie.service';
import { AppConfigService } from 'src/app/app-config.service';

@Component({
  selector: 'app-gestion-boutique-vie',
  templateUrl: './gestion-boutique-vie.component.html',
  styleUrls: ['./gestion-boutique-vie.component.css']
})
export class GestionBoutiqueVieComponent implements OnInit {

  vieForm : Vie;
  urlDefautImage : string = "../../../assets/images/boutique/potion-1.png";
  constructor(private appConfig: AppConfigService, private vieService : GestionBoutiqueVieService) { 

  }

  ngOnInit(): void {
  }

  list(): Array<Vie> {
    return this.vieService.findAll();
  }

  add() {
    this.vieForm = new Vie();
    this.vieForm.image = this.urlDefautImage;
  }

  edit(id: number) {
    this.vieService.findById(id).subscribe(response => {
      this.vieForm = response;
    
    }, err => console.log(err));
    
  }

  save() {
    if(this.vieForm.id) {
      this.vieService.modify(this.vieForm);
    } else {
      this.vieService.create(this.vieForm);
    }

    this.cancel();
  }

  cancel() {
    this.vieForm = null;
  }

  remove(id: number) {
    this.vieService.deleteById(id);
  }

}
