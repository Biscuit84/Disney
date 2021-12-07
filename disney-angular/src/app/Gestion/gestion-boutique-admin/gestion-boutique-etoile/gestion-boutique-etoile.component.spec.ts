import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionBoutiqueEtoileComponent } from './gestion-boutique-etoile.component';

describe('GestionBoutiqueEtoileComponent', () => {
  let component: GestionBoutiqueEtoileComponent;
  let fixture: ComponentFixture<GestionBoutiqueEtoileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionBoutiqueEtoileComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionBoutiqueEtoileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
