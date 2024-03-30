import { TestBed } from '@angular/core/testing';

import { SetExamService } from './set-exam.service';

describe('SetExamService', () => {
  let service: SetExamService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SetExamService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
