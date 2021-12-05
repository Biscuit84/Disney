import { Component, Input, OnInit } from '@angular/core';
import { PlateauHttpService } from 'src/app/plateau-http.service';
import { Cases, CasesPlateau, Plateau } from 'src/model';
import { CdkDragDrop, copyArrayItem, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { CaseHttpService } from 'src/app/case-http.service';

@Component({
  selector: 'app-gestion-plateau',
  templateUrl: './gestion-plateau.component.html',
  styleUrls: ['./gestion-plateau.component.css']
})
export class GestionPlateauComponent implements OnInit {

  @Input()
  listeCases: Array<Cases> = new Array<Cases>();
  @Input()
  listCasesPlateau: Array<Cases> = new Array<Cases>();
  listePlateaux: Array<Plateau> = new Array<Plateau>();
  plateau: Plateau = new Plateau();
  nombreCases: number;
  // listCases = ['Get to work', 'Pick up groceries', 'Go home', 'Fall asleep'];

  // listCasesPlateau = ['Get up', 'Brush teeth', 'Take a shower', 'Check e-mail', 'Walk dog'];

  nomPlateau: string = "";

  titre: string = "Creation du plateau"

  constructor(private plateauService: PlateauHttpService, private caseService: CaseHttpService) {
    this.caseService.findAll2().subscribe(resp => {
      this.listeCases = resp;
    }
    )
  }

  ngOnInit(): void {
  }

  // findListePlateaux(): Array<Plateau> {
  //   this.listePlateaux = this.plateauService.findAll();
  //   return this.listePlateaux;
  // }



  drop(event: CdkDragDrop<Cases[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      if (event.container.id === 'trashCase') {
        this.listCasesPlateau.splice(event.previousIndex, 1);
      } else {
        copyArrayItem(
          event.previousContainer.data,
          event.container.data,
          event.previousIndex,
          event.currentIndex,
        );
      }

    }
  }


  savePlateau() {
    this.plateau = new Plateau();
    this.plateau.nom = this.nomPlateau;
    this.plateau.nbCases = this.listCasesPlateau.length;

    var listCp = new Array<CasesPlateau>();
    var position = 1;
    for (var c of this.listCasesPlateau) {
      var cp = new CasesPlateau();
      cp.uneCase = c;
      // cp.plateau = this.plateau;
      cp.ordreCase = position;
      position++;
      listCp.push(cp);
    }

    this.plateau.cases = listCp;
    this.plateauService.createPlateau(this.plateau).subscribe(resp => {
      this.plateau = resp;
      console.log(this.plateau);
    }, error => (console.log(error)))
  }



}
