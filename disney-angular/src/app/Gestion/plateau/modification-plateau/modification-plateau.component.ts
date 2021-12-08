import { Component, OnInit } from '@angular/core';
import { CasesPlateauHttpService } from 'src/app/cases-plateau-http.service';
import { PartieHttpService } from 'src/app/partie-http.service';
import { PlateauHttpService } from 'src/app/plateau-http.service';
import { Cases, CasesPlateau, Partie, Plateau } from 'src/model';

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
  plateauAlreadyUse: boolean;

  parties: Array<Partie> = new Array<Partie>();

  constructor(private plateauService: PlateauHttpService, private casesPlateauService: CasesPlateauHttpService, private partieService: PartieHttpService) {
    console.log("DANS LE C DE MODIF");
    // this.plateauService.findAllPlateau().subscribe(plateau => {
    //   this.listPlateauDejaCree = plateau;
    //   for (let plat of this.listPlateauDejaCree){
    //     this.plateauDejaUtilise(plat);
    //     console.log("plateau deja utilise CONSTRUCTEUR"+plat.dejaUtilise);
    //   }
    // });

  }

  ngOnInit(): void {
    console.log("DANS LE NGONINIT DE MODIF");
    this.plateauService.findAllPlateau().subscribe(plateau => {
      this.listPlateauDejaCree = plateau;

        this.plateauDejaUtilise();
    });
  }

  plateauDetail(plateau: Plateau) {
    this.id = plateau.id;

  }

  recuperePlateau(plateau: Plateau) {
    this.plateau = plateau;
  }

  delete(plateau: Plateau) {
    this.plateauService.deleteById(plateau.id).subscribe(() => {
      console.log("fini delete");
      this.plateauService.findAllPlateau().subscribe(plateau => {
        this.listPlateauDejaCree = plateau;
        this.plateauDejaUtilise();
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

  plateauDejaUtilise() {
    this.partieService.findAllPartiesWithPlateaux().subscribe(part => {
      this.parties = part;
      // console.log("parties{}", this.parties);
      for(var plateau of this.listPlateauDejaCree){
        for (let p of this.parties) {
          if (p.plateau.id == plateau.id) {
            // console.log("Partie+plateau {}",p.plateau);
            // console.log("plateau+id {}",plateau);
            plateau.dejaUtilise = true;
            // this.plateauAlreadyUse= this.plateau.dejaUtilise;
            console.log("plateauAlreadyUse" + this.plateau.dejaUtilise);
          }
        }
      }
    }, error => console.log(error));
  }

}