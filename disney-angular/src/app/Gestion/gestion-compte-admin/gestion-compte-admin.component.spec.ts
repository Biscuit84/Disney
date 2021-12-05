import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionCompteAdminComponent } from './gestion-compte-admin.component';

describe('GestionCompteAdminComponent', () => {
  let component: GestionCompteAdminComponent;
  let fixture: ComponentFixture<GestionCompteAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionCompteAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionCompteAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
