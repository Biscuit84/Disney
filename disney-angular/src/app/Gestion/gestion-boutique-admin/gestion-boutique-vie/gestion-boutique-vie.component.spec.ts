import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionBoutiqueVieComponent } from './gestion-boutique-vie.component';

describe('GestionBoutiqueVieComponent', () => {
  let component: GestionBoutiqueVieComponent;
  let fixture: ComponentFixture<GestionBoutiqueVieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionBoutiqueVieComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionBoutiqueVieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
