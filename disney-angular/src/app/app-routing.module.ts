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
import { GestionDuCompteComponent } from './gestion-du-compte/gestion-du-compte.component';
import { PageConnexionComponent } from './page-connexion/page-connexion.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { BoutiquePersonnagesComponent } from './boutique/boutique-personnages/boutique-personnages.component';
import { PlateauComponent } from './Gestion/plateau/plateau.component';
import { GestionComponent } from './Gestion/gestion.component';

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
  {path : "compte", component: GestionDuCompteComponent},
  {path : "connexion", component: PageConnexionComponent},

  // pour l'admin :
  {path : "gestionAdmin", component: GestionComponent},
  {path : "gestionAdmin/plateau", component: PlateauComponent},


  // autres
  {path : "", redirectTo:"home", pathMatch: "full"},
  {path: '**', component: PageNotFoundComponent }

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
