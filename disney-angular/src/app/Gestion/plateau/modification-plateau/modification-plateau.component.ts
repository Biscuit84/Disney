import { Component, OnInit } from '@angular/core';
import { CasesPlateauHttpService } from 'src/app/cases-plateau-http.service';
import { PlateauHttpService } from 'src/app/plateau-http.service';
import { Cases, CasesPlateau, Plateau } from 'src/model';

@Component({
  selector: 'app-modification-plateau',
  templateUrl: './modification-plateau.component.html',
  styleUrls: ['./modification-plateau.component.css']
})
export class ModificationPlateauComponent implements OnInit {

  listPlateauDejaCree: Array<Plateau> = new Array<Plateau>();
  listCasesPlateau: Array<CasesPlateau> = new Array<CasesPlateau>();
  listCases: Array<Cases> = new Array<Cases>();
  plateau: Plateau = new Plateau();
  nomPlateau: string = "";
  id: number = -1;
  clicked: boolean = false;

  constructor(private plateauService: PlateauHttpService, private casesPlateauService: CasesPlateauHttpService) {
    console.log("DANS LE C DE MODIF");

  }

  ngOnInit(): void {
    console.log("DANS LE NGONINIT DE MODIF");
    this.plateauService.findAllPlateau().subscribe(plateau => {
      this.listPlateauDejaCree = plateau;
    });
  }

  plateauDetail(plateau: Plateau) {
    this.id = plateau.id;

  }

  assignPlateau(plateau: Plateau) {
    this.plateau = plateau;
  }

  delete(plateau: Plateau) {
    this.plateauService.deleteById(plateau.id).subscribe(() => {
      console.log("fini delete");
      this.plateauService.findAllPlateau().subscribe(plateau => {
        this.listPlateauDejaCree = plateau;
      });
    })

    // this.casesPlateauService.findAllCasesByPlateau(plateau.id).subscribe(resp => {
    //   this.listCasesPlateau = resp;
    //   for (let lp of this.listCasesPlateau) {
    //     this.casesPlateauService.deleteById(lp.id).subscribe()
    //   }
    // })

    // this.plateauService.deleteById(plateau.id).subscribe(() => {
    //   this.plateauService.findAllPlateau().subscribe((plats: Array<Plateau>) => {
    //     this.listPlateauDejaCree = plats;
    //   })
    // })
  }

}