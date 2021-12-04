import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateAgo'
})
export class DateAgoPipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): unknown {
    let dtValue: Date = new Date(value);

    let dtJour: Date = new Date();

    let ago = dtJour.getDay() - dtValue.getDay();

    return ago;
    
  }

}
