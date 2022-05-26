import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Ship} from "./ship";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:8080/ship/';

  mapToShip = map((value: ShipModel) => new Ship(
    value.hullDiameter,
    value.mastLength,
    value.mastDiameter,
    value.shieldThickness,
    value.shieldMaxDiameter,
    value.shieldMinDiameter,
    value.lanternDiameter)
  );

  private getApiShip(): Observable<ShipModel> {
    return this.http.get<ShipModel>(this.baseUrl);
  }

  getShip(): Observable<Ship> {
    return this.mapToShip(this.getApiShip());
  }

  putShipData(hullSpaces: number, driveGeneration: number, thrust: number): Observable<Ship> {
    const response: Observable<ShipModel> = this.http.put<ShipModel>(this.baseUrl + "shipData/", {
      hullSpaces: hullSpaces,
      driveGeneration: driveGeneration,
      thrust: thrust
    });
    return this.mapToShip(response);
  }
}

export interface ShipModel {
  "hullDiameter": number,
  "mastLength": number,
  "mastDiameter": number,
  "shieldMaxDiameter": number,
  "shieldMinDiameter": number,
  "shieldThickness": number,
  "lanternDiameter": number
}
