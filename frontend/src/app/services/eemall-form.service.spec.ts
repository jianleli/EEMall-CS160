import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { EEmallFormService } from './eemall-form.service';

describe('EEmallFormService', () => {
  let service: EEmallFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientTestingModule,
        RouterTestingModule
      ]
    });
    service = TestBed.inject(EEmallFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
