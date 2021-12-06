import { Component, Input, OnInit } from '@angular/core';
import { PlateauHttpService } from 'src/app/plateau-http.service';
import { Cases, CasesPlateau, Plateau } from 'src/model';
import { CdkDragDrop, copyArrayItem, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { CaseHttpService } from 'src/app/case-http.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-gestion-plateau',
  templateUrl: './gestion-plateau.component.html',
  styleUrls: ['./gestion-plateau.component.css']
})
export class GestionPlateauComponent implements OnInit {

  listeCases: Array<Cases> = new Array<Cases>();
  listCasesPlateau: Array<Cases> = new Array<Cases>();
  listePlateaux: Array<Plateau> = new Array<Plateau>();
  plateau: Plateau = new Plateau();
  nombreCases: number;
  // listCases = ['Get to work', 'Pick up groceries', 'Go home', 'Fall asleep'];

  // listCasesPlateau = ['Get up', 'Brush teeth', 'Take a shower', 'Check e-mail', 'Walk dog'];

  nomPlateau: string = "";

  @Input()
  idPlateau: number = -1;

  @Input()
  titre: string = "Creation du plateau"


  constructor(private plateauService: PlateauHttpService, private caseService: CaseHttpService, private router: Router) {
    this.load();
  }

  load() {
    //on récupère la liste des cases
    this.caseService.findAll2().subscribe(resp => {
      this.listeCases = resp;
    });

  }

  ngOnInit(): void {
    if (this.idPlateau != -1) {
      this.plateauService.findByIdWithCasesDetails(this.idPlateau).subscribe(plat => {
        var lcp = new Array<Cases>();
        this.plateauService.findAllCasesByIdPlateau(this.idPlateau).subscribe(resp => {
          var index = 0;
          for (var cp of this.plateau.cases) {
            cp.uneCase = resp[index];
            index++;
            lcp.push(cp.uneCase);
          }
        })
        this.plateau = plat;
        this.nomPlateau = this.plateau.nom;

        this.listCasesPlateau = lcp;
        console.log(this.listCasesPlateau);
      });
    }
  }



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
    //update
    if (this.plateau.id) {
      this.majPlateau();
      this.plateauService.updatePlateau(this.plateau).subscribe(resp => {
        this.plateau = resp;
        this.router.routeReuseStrategy.shouldReuseRoute = () => false;
        this.router.onSameUrlNavigation = 'reload';
        this.router.navigate(['gestionAdmin/updatePlateau']);
        // this.router.navigate(['gestionAdmin/plateau']);
      }, error => (console.log(error)))

    } else {
      //creation
      this.plateau = new Plateau();
      this.majPlateau();
      this.plateauService.createPlateau(this.plateau).subscribe(resp => {
        this.plateau = resp;
        this.load();
        this.listCasesPlateau = new Array<Cases>();
        this.listePlateaux = new Array<Plateau>();
        this.plateau = new Plateau();
        this.nombreCases = 0;
        this.nomPlateau = "";
        this.idPlateau = -1;
        this.titre = "Creation du plateau"
      }, error => (console.log(error)))
    }
  }




  private majPlateau() {
    this.plateau.nom = this.nomPlateau;
    this.plateau.nbCases = this.listCasesPlateau.length;

    var listCp = new Array<CasesPlateau>();
    var position = 0;
    for (var c of this.listCasesPlateau) {
      var cp = new CasesPlateau();
      cp.uneCase = c;
      // cp.plateau = this.plateau;
      cp.ordreCase = position;
      position++;
      listCp.push(cp);
    }

    this.plateau.cases = listCp;
  }

}
