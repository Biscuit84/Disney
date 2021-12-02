import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'fin-de-partie',
  templateUrl: './fin-de-partie.component.html',
  styleUrls: ['./fin-de-partie.component.css']
})
export class FinDePartieComponent implements OnInit {

  gagne=true;
  perdu=false;

  constructor() { }

  ngOnInit(): void {
  }

}
