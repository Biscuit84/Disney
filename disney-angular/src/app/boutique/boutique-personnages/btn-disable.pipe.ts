import { Pipe, PipeTransform } from '@angular/core';
import { Personnage, PersonnageDto } from 'src/model';

@Pipe({
  name: 'btnDisable'
})
export class BtnDisablePipe implements PipeTransform {

  transform(personnage: Personnage, arg?:boolean): unknown {
  //   if(personnage.id =  )
    
    return null;
  }

  


}
