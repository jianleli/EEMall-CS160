import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { LaptopService } from './laptop.service';



describe('LaptopService', () => {
  let service: LaptopService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientTestingModule,
        RouterTestingModule
      ]
    });
    service = TestBed.inject(LaptopService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
