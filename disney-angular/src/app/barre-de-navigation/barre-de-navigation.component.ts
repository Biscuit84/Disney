import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Avatar, Historique, Personnage, Vie } from 'src/model';
import { AvatarHttpService } from '../avatar-http.service';
import { GestionDuCompteService } from '../gestion-du-compte/gestion-du-compte.service';
import { PageConnexionService } from '../page-connexion/page-connexion.service';

@Component({
  selector: 'app-barre-de-navigation, [barre-de-navigation]',
  templateUrl: './barre-de-navigation.component.html',
  styleUrls: ['./barre-de-navigation.component.css']
})
export class BarreDeNavigationComponent implements OnInit {
  cheminLogo: any = "src\assets\images\logoPrincessA.png";

  avatarJoueur: Avatar = new Avatar();
  listeAvatars: Array<Avatar> = new Array<Avatar>();
  srcAvatarCompte: string = "";

  constructor(public pageConnexionService: PageConnexionService, private gestionDuCompteService: GestionDuCompteService, private router: Router, public avatarService: AvatarHttpService) {
  }

  ngOnInit(): void {
  }

  deconnexion() {
    this.pageConnexionService.deconnexion();
    this.gestionDuCompteService.historiques = new Array<Historique>();
    this.router.navigate(['home'])
  }


  findAvatarCompte() {
  }

  changeAvatar(avatar: Avatar) {
    this.pageConnexionService.updateAvatar(avatar);
  }
}