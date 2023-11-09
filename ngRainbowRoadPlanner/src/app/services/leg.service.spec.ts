/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { LegService } from './leg.service';

describe('Service: Leg', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LegService]
    });
  });

  it('should ...', inject([LegService], (service: LegService) => {
    expect(service).toBeTruthy();
  }));
});
