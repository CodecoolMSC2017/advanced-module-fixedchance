import { TestBed, async, inject } from '@angular/core/testing';

import { TeacherContentGuard } from './teacher-content.guard';

describe('TeacherContentGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TeacherContentGuard]
    });
  });

  it('should ...', inject([TeacherContentGuard], (guard: TeacherContentGuard) => {
    expect(guard).toBeTruthy();
  }));
});
