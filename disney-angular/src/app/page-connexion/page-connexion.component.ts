import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Compte, ConnexionDTO } from 'src/model';
import { PageConnexionService } from './page-connexion.service';

@Component({
  selector: 'page-connexion',
  templateUrl: './page-connexion.component.html',
  styleUrls: ['./page-connexion.component.css']
})
export class PageConnexionComponent implements OnInit {

  connexionForm: ConnexionDTO = new ConnexionDTO();
  errorLogin: String;
  

  constructor(private connexionService: PageConnexionService, private router:Router) { }

  ngOnInit(): void {
  }

  login() {
    this.connexionService.connexion(this.connexionForm).subscribe(resp => {
      this.connexionService.compte = resp;
      console.log("#### this.connexionService.compte : {}", this.connexionService.compte);
      
      this.errorLogin = null;
      if(this.connexionService.compte.role == 'joueur'){
        this.router.navigate(['accueilJoueur'])
      }
      else if(this.connexionService.compte.role == 'admin'){
        this.router.navigate(['gestionAdmin'])
      }
        


    }, error => {
      console.log(error);
      if(error.status == 404) {
        this.errorLogin = "Le mail ou le mot de passe est incorrect !"
      }
    });

    
  }
  
}
