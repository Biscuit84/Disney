import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionDuCompteComponent } from './gestion-du-compte.component';

describe('GestionDuCompteComponent', () => {
  let component: GestionDuCompteComponent;
  let fixture: ComponentFixture<GestionDuCompteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionDuCompteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionDuCompteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
