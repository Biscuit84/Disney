import { Component, OnInit } from '@angular/core';
import { PageConnexionService } from '../page-connexion/page-connexion.service';

@Component({
  selector: 'app-barre-de-navigation, [barre-de-navigation]',
  templateUrl: './barre-de-navigation.component.html',
  styleUrls: ['./barre-de-navigation.component.css']
})
export class BarreDeNavigationComponent implements OnInit {
  cheminLogo:any = "src\assets\images\logoPrincessA.png";
  

  constructor(public pageConnexionService: PageConnexionService) { 
    
  }

  ngOnInit(): void {
  }
  deconnexion() {
    this.pageConnexionService.deconnexion();
  }
}