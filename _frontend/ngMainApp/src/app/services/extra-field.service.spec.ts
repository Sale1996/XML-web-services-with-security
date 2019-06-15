import { TestBed } from '@angular/core/testing';

import { ExtraFieldService } from './extra-field.service';

describe('ExtraFieldService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ExtraFieldService = TestBed.get(ExtraFieldService);
    expect(service).toBeTruthy();
  });
});
