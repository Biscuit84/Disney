import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificationPlateauComponent } from './modification-plateau.component';

describe('ModificationPlateauComponent', () => {
  let component: ModificationPlateauComponent;
  let fixture: ComponentFixture<ModificationPlateauComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModificationPlateauComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificationPlateauComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
