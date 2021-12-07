import { Component, OnInit } from '@angular/core';
import { Personnage } from 'src/model';
import { GestionBoutiquePersonnageService } from './gestion-boutique-personnage.service';
import { AppConfigService } from 'src/app/app-config.service';

@Component({
  selector: 'app-gestion-boutique-personnage',
  templateUrl: './gestion-boutique-personnage.component.html',
  styleUrls: ['./gestion-boutique-personnage.component.css']
})
export class GestionBoutiquePersonnageComponent implements OnInit {

  personnageForm : Personnage;
urlDefautAvatar: string = "../../assets/images/persoBoutique/elsa.jpg";

  constructor(private appConfig: AppConfigService, private personnageService : GestionBoutiquePersonnageService) { 

  }

  ngOnInit(): void {
  }

  list(): Array<Personnage> {
    return this.personnageService.findAll();
  }

  add() {
    this.personnageForm = new Personnage();
    this.personnageForm.avatar = this.urlDefautAvatar;
  }

  edit(id: number) {
    this.personnageService.findById(id).subscribe(response => {
      this.personnageForm = response;
    
    }, err => console.log(err));
    
  }

  save() {
    if(this.personnageForm.id) {
      this.personnageService.modify(this.personnageForm);
    } else {
      this.personnageService.create(this.personnageForm);
    }

    this.cancel();
  }

  cancel() {
    this.personnageForm = null;
  }

  remove(id: number) {
    this.personnageService.deleteById(id);
  }


}
