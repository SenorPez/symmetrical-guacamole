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
    const expectedValue: Ship = new Ship(
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101)
    );

    it('should return ship as expected', () => {
      service.getShip().subscribe({
        next: ship => {
          expect(ship.hullDiameter).toEqual(expectedValue.hullDiameter);
          expect(ship.mastLength).toEqual(expectedValue.mastLength);
          expect(ship.mastDiameter).toEqual(expectedValue.mastDiameter);
          expect(ship.shieldThickness).toEqual(expectedValue.shieldThickness);
          expect(ship.shieldMaxDiameter).toEqual(expectedValue.shieldMaxDiameter);
          expect(ship.shieldMinDiameter).toEqual(expectedValue.shieldMinDiameter);
          expect(ship.lanternDiameter).toEqual(expectedValue.lanternDiameter);
        },
        error: fail
      });

      const req = httpTestingController.expectOne(service.baseUrl);
      expect(req.request.method).toEqual('GET');

      req.flush(expectedValue);
    });
  });
});
