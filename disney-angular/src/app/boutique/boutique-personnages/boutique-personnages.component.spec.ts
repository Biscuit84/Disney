import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoutiquePersonnagesComponent } from './boutique-personnages.component';

describe('BoutiquePersonnagesComponent', () => {
  let component: BoutiquePersonnagesComponent;
  let fixture: ComponentFixture<BoutiquePersonnagesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoutiquePersonnagesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoutiquePersonnagesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
