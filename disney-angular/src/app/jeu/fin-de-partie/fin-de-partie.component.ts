import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';


@Component({
  selector: 'fin-de-partie',
  templateUrl: './fin-de-partie.component.html',
  styleUrls: ['./fin-de-partie.component.css']
})
export class FinDePartieComponent implements OnInit {

  // recupérer si on a gagné ou perdu
  gagne = true;


  ngOnInit(): void {
    this.getImage();
  }




  imageName: String;


  getImage() {
    
    switch (this.gagne) {
      case true: {
        this.imageName = "../../assets/images/jeu/win.jpg";
        console.log(this.imageName);
        break;
      }
      case false: {
        this.imageName = "../../assets/images/jeu/gameover.jpg";
        this.makeItRain();
        break;
      }
    }
  }

  // la pluie si on perd est générée avec du code : 

    makeItRain() {
      $('.rain').empty();
      var increment = 0;
      var drops = "";
      var backDrops = "";

      while (increment <100) {
        //couple random numbers to use for various randomizations
        //random number between 98 and 1
        var randoHundo = (Math.floor(Math.random() * (98 - 1 + 1) + 1));
        //random number between 5 and 2
        var randoFiver = (Math.floor(Math.random() * (5 - 2 + 1) + 2));
        //increment
        increment += randoFiver;
        //add in a new raindrop with various randomizations to certain CSS properties
        drops += '<div class="drop" style="left: ' + increment + '%; bottom: ' + (randoFiver + randoFiver - 1 + 100) + '%; animation-delay: 0.' + randoHundo + 's; animation-duration: 0.5' + randoHundo + 's;"><div class="stem" style="animation-delay: 0.' + randoHundo + 's; animation-duration: 0.5' + randoHundo + 's;"></div><div class="splat" style="animation-delay: 0.' + randoHundo + 's; animation-duration: 0.5' + randoHundo + 's;"></div></div>';
        backDrops += '<div class="drop" style="right: ' + increment + '%; bottom: ' + (randoFiver + randoFiver - 1 + 100) + '%; animation-delay: 0.' + randoHundo + 's; animation-duration: 0.5' + randoHundo + 's;"><div class="stem" style="animation-delay: 0.' + randoHundo + 's; animation-duration: 0.5' + randoHundo + 's;"></div><div class="splat" style="animation-delay: 0.' + randoHundo + 's; animation-duration: 0.5' + randoHundo + 's;"></div></div>';
      }
    
      $('.rain.front-row').append(drops);
      $('.rain.back-row').append(backDrops);

    }


















}

