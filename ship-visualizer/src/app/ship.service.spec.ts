import {ShipService} from './ship.service';
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {TestBed} from "@angular/core/testing";
import {Ship} from "./ship";

describe('ShipService', () => {
  let service: ShipService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [ShipService]
    }).compileComponents();

    service = TestBed.inject(ShipService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('/ship should return a ship', () => {
    const expectedValue: Ship = {hullLength: 11.42}

    service.getShip().subscribe(ship => expect(ship).toEqual(expectedValue));
    const req = httpTestingController.expectOne("http://localhost:8080/ship");
    expect(req.request.method).toEqual("GET");
    req.flush(expectedValue);
    httpTestingController.verify();
  })
});
