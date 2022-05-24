import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Ship} from "./ship";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: HttpClient) { }

  baseUrl = 'assets/ship.json';

  getShip() {
    return this.http.get<Ship>(this.baseUrl);
  }
}
