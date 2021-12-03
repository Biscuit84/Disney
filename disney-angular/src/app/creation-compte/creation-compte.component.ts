import { Component, OnInit } from '@angular/core';
import { Compte } from 'src/model';
import { AppConfigService } from '../app-config.service';
import { CreationCompteService } from './creation-compte.service';

@Component({
  selector: 'app-creation-compte',
  templateUrl: './creation-compte.component.html',
  styleUrls: ['./creation-compte.component.css']
})
export class CreationCompteComponent implements OnInit {
creatForm: Compte =new Compte();
  constructor( private creationCompteService:CreationCompteService) { }

  ngOnInit(): void {
  }
add(){
  this.creatForm=new Compte();
}
  

  save() {
    this.creatForm.role = 'joueur';
    this.creatForm.nbVictoire= 0;
    this.creatForm.nbEtoiles=500;
    this.creatForm.nbDefaite=0;
    this.creatForm.life=3;
    this.creationCompteService.create(this.creatForm);
    this.creatForm = null;
  }

}
