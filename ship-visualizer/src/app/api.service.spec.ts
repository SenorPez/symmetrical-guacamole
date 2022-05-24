import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {TestBed} from '@angular/core/testing';
import {HttpClient} from "@angular/common/http";

import {ApiService} from './api.service';
import {Ship} from "./ship";

describe('ApiService', () => {
  let httpClient: HttpClient;
  let httpTestingController: HttpTestingController;
  let service: ApiService;

  function getRandomInteger(min: number, max: number): number {
    return Math.floor(Math.random() * (max - min)) + min;
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ApiService]
    });

    httpClient = TestBed.inject(HttpClient);
    httpTestingController = TestBed.inject(HttpTestingController);
    service = TestBed.inject(ApiService);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  describe('getShip', () => {
    let expectedValue: Ship = {
      hullDiameter: getRandomInteger(1, 101),
      lanternDiameter: getRandomInteger(1, 101),
      mastDiameter: getRandomInteger(1, 101),
      mastLength: getRandomInteger(1, 101),
      shieldDepth: getRandomInteger(1, 101),
      shieldMaxDiameter: getRandomInteger(1, 101),
      shieldMinDiameter: getRandomInteger(1, 101)
    };

    it('should return ship as expected', () => {
      service.getShip().subscribe({
        next: ship => expect(ship).toEqual(expectedValue),
        error: fail
      });

      const req = httpTestingController.expectOne(service.baseUrl);
      expect(req.request.method).toEqual('GET');

      req.flush(expectedValue);
    });
  });
});
