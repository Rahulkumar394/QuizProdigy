import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetQuestionPaperComponent } from './set-question-paper.component';

describe('SetQuestionPaperComponent', () => {
  let component: SetQuestionPaperComponent;
  let fixture: ComponentFixture<SetQuestionPaperComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SetQuestionPaperComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SetQuestionPaperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
