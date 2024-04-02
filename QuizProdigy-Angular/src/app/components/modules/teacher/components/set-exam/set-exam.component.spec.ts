import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetExamComponent } from './set-exam.component';

describe('SetExamComponent', () => {
  let component: SetExamComponent;
  let fixture: ComponentFixture<SetExamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SetExamComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SetExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
