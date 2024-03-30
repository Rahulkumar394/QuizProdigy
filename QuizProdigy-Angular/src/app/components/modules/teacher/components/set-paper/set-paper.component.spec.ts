import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SetPaperComponent } from './set-paper.component';

describe('SetPaperComponent', () => {
  let component: SetPaperComponent;
  let fixture: ComponentFixture<SetPaperComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SetPaperComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SetPaperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
