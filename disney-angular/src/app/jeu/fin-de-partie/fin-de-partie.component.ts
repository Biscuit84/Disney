import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'fin-de-partie',
  templateUrl: './fin-de-partie.component.html',
  styleUrls: ['./fin-de-partie.component.css']
})
export class FinDePartieComponent implements OnInit {

  // recupérer si on a gagné ou perdu
  gagne = false;


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
        break;
      }
    }
  }
}

