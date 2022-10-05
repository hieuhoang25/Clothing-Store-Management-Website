import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForwardpassComponent } from './forwardpass.component';

describe('ForwardpassComponent', () => {
  let component: ForwardpassComponent;
  let fixture: ComponentFixture<ForwardpassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ForwardpassComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForwardpassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
