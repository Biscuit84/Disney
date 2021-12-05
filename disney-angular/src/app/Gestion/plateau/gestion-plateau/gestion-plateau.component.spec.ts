import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionPlateauComponent } from './gestion-plateau.component';

describe('GestionPlateauComponent', () => {
  let component: GestionPlateauComponent;
  let fixture: ComponentFixture<GestionPlateauComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionPlateauComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionPlateauComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
