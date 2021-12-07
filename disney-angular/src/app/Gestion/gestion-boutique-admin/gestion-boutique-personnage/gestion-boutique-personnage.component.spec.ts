import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionBoutiquePersonnageComponent } from './gestion-boutique-personnage.component';

describe('GestionBoutiquePersonnageComponent', () => {
  let component: GestionBoutiquePersonnageComponent;
  let fixture: ComponentFixture<GestionBoutiquePersonnageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionBoutiquePersonnageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionBoutiquePersonnageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
