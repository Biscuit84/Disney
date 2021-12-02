import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinDePartieComponent } from './fin-de-partie.component';

describe('FinDePartieComponent', () => {
  let component: FinDePartieComponent;
  let fixture: ComponentFixture<FinDePartieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FinDePartieComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FinDePartieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
