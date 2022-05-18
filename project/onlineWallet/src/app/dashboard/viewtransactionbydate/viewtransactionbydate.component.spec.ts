import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewtransactionbydateComponent } from './viewtransactionbydate.component';

describe('ViewtransactionbydateComponent', () => {
  let component: ViewtransactionbydateComponent;
  let fixture: ComponentFixture<ViewtransactionbydateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewtransactionbydateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewtransactionbydateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
