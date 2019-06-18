import { TestBed } from '@angular/core/testing';

import { AccommodationUnitService } from './accommodation-unit.service';

describe('AccommodationUnitService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AccommodationUnitService = TestBed.get(AccommodationUnitService);
    expect(service).toBeTruthy();
  });
});
