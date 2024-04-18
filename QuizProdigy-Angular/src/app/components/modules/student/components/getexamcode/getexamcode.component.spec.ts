import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetexamcodeComponent } from './getexamcode.component';

describe('GetexamcodeComponent', () => {
  let component: GetexamcodeComponent;
  let fixture: ComponentFixture<GetexamcodeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GetexamcodeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GetexamcodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
