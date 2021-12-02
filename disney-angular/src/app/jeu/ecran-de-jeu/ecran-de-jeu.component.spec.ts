import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EcranDeJeuComponent } from './ecran-de-jeu.component';

describe('EcranDeJeuComponent', () => {
  let component: EcranDeJeuComponent;
  let fixture: ComponentFixture<EcranDeJeuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EcranDeJeuComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EcranDeJeuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
