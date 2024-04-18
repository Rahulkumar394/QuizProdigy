import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetExamDateComponent } from './set-exam-date.component';

describe('SetExamDateComponent', () => {
  let component: SetExamDateComponent;
  let fixture: ComponentFixture<SetExamDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SetExamDateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SetExamDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
