import { Component, OnInit } from '@angular/core';
import { ConnexionDTO } from 'src/model';
import { PageConnexionService } from './page-connexion.service';

@Component({
  selector: 'page-connexion',
  templateUrl: './page-connexion.component.html',
  styleUrls: ['./page-connexion.component.css']
})
export class PageConnexionComponent implements OnInit {

  connexionForm: ConnexionDTO = new ConnexionDTO();
  errorLogin: String;

  constructor(private connexionService: PageConnexionService) { }

  ngOnInit(): void {
  }

  login() {
    this.connexionService.connexion(this.connexionForm).subscribe(resp => {
      this.connexionService.compte = resp;
      this.errorLogin = null;
    }, error => {
      console.log(error);
      if(error.status == 404) {
        this.errorLogin = "Le mail ou le mot de passe est incorrect !"
      }
    });
  }
}
