import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';


import { AppRoutingModule } from './app-routing.module';
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
import { FormsModule } from '@angular/forms';
import { BoutiquePersonnagesComponent } from './boutique/boutique-personnages/boutique-personnages.component';
import { GestionComponent } from './Gestion/gestion.component';
import { PlateauComponent } from './Gestion/plateau/plateau.component';
import { PageConnexionService } from './page-connexion/page-connexion.service';



@NgModule({
  declarations: [
    AppComponent,
    BarreDeNavigationComponent,
    JeuComponent,
    EcranDeJeuComponent,
    FinDePartieComponent,
    HomeComponent,
    BoutiqueComponent,
    ActualitesComponent,
    NousComponent,
    GestionDuCompteComponent,
    PageConnexionComponent,
    PageNotFoundComponent,
    BoutiquePersonnagesComponent,
    GestionComponent,
    PlateauComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
   
  ],
  providers: [PageConnexionService],
  bootstrap: [AppComponent]
})
export class AppModule { }
