import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { BarreDeNavigationComponent } from './barre-de-navigation/barre-de-navigation.component';
import { JeuComponent } from './jeu/jeu.component';
import { EcranDeJeuComponent } from './jeu/ecran-de-jeu/ecran-de-jeu.component';
import { FinDePartieComponent } from './jeu/fin-de-partie/fin-de-partie.component';
import { HomeComponent } from './Home/home.component';
import { BoutiqueComponent } from './boutique/boutique.component';
import { ActualitesComponent } from './actualites/actualites.component';
import { NousComponent } from './nous/nous.component';

import { PageConnexionComponent } from './page-connexion/page-connexion.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { BoutiquePersonnagesComponent } from './boutique/boutique-personnages/boutique-personnages.component';
import { PlateauComponent } from './Gestion/plateau/plateau.component';
import { GestionComponent } from './Gestion/gestion.component';
import { CreationCompteComponent } from './creation-compte/creation-compte.component';
import { ModificationProfilJoueurComponent } from './modification-profil-joueur/modification-profil-joueur.component';
import { BoutiqueEtoilesComponent } from './boutique/boutique-etoiles/boutique-etoiles.component';
import { GestionProfilAdminComponent } from './Gestion/gestion-profil-admin/gestion-profil-admin.component';
import { GestionCompteAdminComponent } from './Gestion/gestion-compte-admin/gestion-compte-admin.component';
import { GestionBoutiqueAdminComponent } from './Gestion/gestion-boutique-admin/gestion-boutique-admin.component';
import { GestionDuCompteComponent } from './gestion-du-compte/gestion-du-compte.component';
import { GestionPlateauComponent } from './Gestion/plateau/gestion-plateau/gestion-plateau.component';
import { ModificationPlateauComponent } from './Gestion/plateau/modification-plateau/modification-plateau.component';
import { GestionBoutiquePersonnageComponent } from './Gestion/gestion-boutique-admin/gestion-boutique-personnage/gestion-boutique-personnage.component';


const routes : Routes= [

  // pour le joueur :
  {path : "home", component: HomeComponent},
  {path : "nous", component: NousComponent},
  {path : "actualites", component: ActualitesComponent},
  {path : "jeu", component: JeuComponent},
  {path : "jeu/ecrandejeu", component: EcranDeJeuComponent},
  {path : "jeu/findepartie", component: FinDePartieComponent},
  {path : "boutique", component: BoutiqueComponent},
  {path : "boutique/personnages", component: BoutiquePersonnagesComponent},
  {path : "boutique/etoiles", component: BoutiqueEtoilesComponent},
  {path : "accueilJoueur", component: GestionDuCompteComponent},
  {path : "connexion", component: PageConnexionComponent},
  {path : "newjoueur", component: CreationCompteComponent},
  {path : "modifProfil", component: ModificationProfilJoueurComponent},
  


  // pour l'admin :
  {path : "gestionAdmin", component: GestionComponent},
  {path : "gestionAdmin/plateau", component: PlateauComponent},
  {path : "gestionAdmin/profil", component: GestionProfilAdminComponent},
  {path : "gestionAdmin/compte", component: GestionCompteAdminComponent},
  {path : "gestionAdmin/boutique", component: GestionBoutiqueAdminComponent},
  {path : "gestionAdmin/createPlateau", component: GestionPlateauComponent},
  {path : "gestionAdmin/updatePlateau", component: ModificationPlateauComponent},
  {path : "gestionAdmin/boutique/personnage", component: GestionBoutiquePersonnageComponent},

  // autres
  {path : "", redirectTo:"home", pathMatch: "full"},
  {path: '**', component: PageNotFoundComponent }

]

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
