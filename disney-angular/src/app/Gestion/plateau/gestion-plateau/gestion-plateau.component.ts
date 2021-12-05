import { Component, OnInit } from '@angular/core';
import { PlateauHttpService } from 'src/app/plateau-http.service';
import { CasesPlateau, Plateau } from 'src/model';
import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-gestion-plateau',
  templateUrl: './gestion-plateau.component.html',
  styleUrls: ['./gestion-plateau.component.css']
})
export class GestionPlateauComponent implements OnInit {

 
  casePlateau: Array<CasesPlateau>= new Array<CasesPlateau>();
  listePlateaux: Array<Plateau>= new Array<Plateau>();
  plateau: Plateau; 
  listCases = ['Get to work', 'Pick up groceries', 'Go home', 'Fall asleep'];

  listCasesPlateau = ['Get up', 'Brush teeth', 'Take a shower', 'Check e-mail', 'Walk dog'];

  nomPlateau: string = ""; 

  titre:string = "Creation du plateau"

  constructor(private plateauService: PlateauHttpService) { }

  ngOnInit(): void {
  }

  list(): Array<Plateau> {
    this.listePlateaux= this.plateauService.findAll();
    return this.listePlateaux;
  }


  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }

}
