import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoutiqueEtoilesComponent } from './boutique-etoiles.component';

describe('BoutiqueEtoilesComponent', () => {
  let component: BoutiqueEtoilesComponent;
  let fixture: ComponentFixture<BoutiqueEtoilesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoutiqueEtoilesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BoutiqueEtoilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
