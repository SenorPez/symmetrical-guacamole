import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ApiService} from "../api.service";
import {Observable} from "rxjs";
import {Ship} from "../ship";

@Component({
  selector: 'app-ship-editor',
  templateUrl: './ship-editor.component.html',
  styleUrls: ['./ship-editor.component.css']
})
export class ShipEditorComponent implements OnInit {
  @Output() shipPatchEvent = new EventEmitter<Observable<Ship>>();

  hullSpacesInput: FormControl = new FormControl(25, [
    Validators.required,
    Validators.min(25),
    Validators.max(500)
  ]);

  hullSpacesSlider: FormControl = new FormControl(25);

  hullSpacesForm: FormGroup = new FormGroup({
    hullSpacesSlider: this.hullSpacesSlider,
    hullSpacesInput: this.hullSpacesInput
  });

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.hullSpacesInput.valueChanges.subscribe(value => this.hullSpacesSlider.setValue(value, {emitEvent: false}));
    this.hullSpacesSlider.valueChanges.subscribe(value => this.hullSpacesInput.setValue(value, {emitEvent: false}));
    this.hullSpacesForm.valueChanges.subscribe(value => {
      if (value.hullSpacesInput !== null) {
        this.shipPatchEvent.emit(this.apiService.putShipData(value.hullSpacesInput, 1));
      }
    });
  }
}
