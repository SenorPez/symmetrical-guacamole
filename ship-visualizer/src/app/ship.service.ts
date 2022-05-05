import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

interface Ship {
}

@Injectable({
  providedIn: 'root'
})
export class ShipService {

  constructor(
    private http: HttpClient
  ) { }

  getShip(): Ship {
    return this.http.get<Ship>("http://localhost:8080/ship");
  }
}
