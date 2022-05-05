import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, of, tap} from "rxjs";
import {MessageService} from "./message.service";

interface Ship {
}

@Injectable({
  providedIn: 'root'
})
export class ShipService {

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  getShip(): Observable<Ship> {
    return this.http.get<Ship>("http://localhost:8080/ship")
      .pipe(
        tap(_ => this.log("GET ship")),
        catchError(this.handleError<Ship>('getShip', []))
      );
  }

  private log(message: string) {
    this.messageService.add(`ShipService: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
