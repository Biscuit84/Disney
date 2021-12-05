import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GestionBoutiqueAdminComponent } from './gestion-boutique-admin.component';

describe('GestionBoutiqueAdminComponent', () => {
  let component: GestionBoutiqueAdminComponent;
  let fixture: ComponentFixture<GestionBoutiqueAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GestionBoutiqueAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GestionBoutiqueAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
