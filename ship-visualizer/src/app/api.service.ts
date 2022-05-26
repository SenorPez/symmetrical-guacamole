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

  mapToShip = map((value: ApiShip) => new Ship(
    value.hullDiameter,
    value.mastLength,
    value.mastDiameter,
    value.shieldThickness,
    value.shieldMaxDiameter,
    value.shieldMinDiameter,
    value.lanternDiameter)
  );

  private getApiShip(): Observable<ApiShip> {
    return this.http.get<ApiShip>(this.baseUrl);
  }

  getShip(): Observable<Ship> {
    return this.mapToShip(this.getApiShip());
  }

  putShipData(hullSpaces: number, engineGeneration: number): Observable<Ship> {
    const response: Observable<ApiShip> = this.http.put<ApiShip>(this.baseUrl + "shipData/", {
      hullSpaces: hullSpaces,
      engineGeneration: engineGeneration
    });
    return this.mapToShip(response);
  }
}

export interface ApiShip {
  "hullDiameter": number,
  "mastLength": number,
  "mastDiameter": number,
  "shieldMaxDiameter": number,
  "shieldMinDiameter": number,
  "shieldThickness": number,
  "lanternDiameter": number
}
