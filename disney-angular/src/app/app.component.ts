import { Component } from '@angular/core';

import { PageConnexionService } from './page-connexion/page-connexion.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'AngularDisney';
  
  constructor(public pageConnexionService: PageConnexionService,){
   
  }
 

   deconnexion() {
    this.pageConnexionService.deconnexion();
  }
}
