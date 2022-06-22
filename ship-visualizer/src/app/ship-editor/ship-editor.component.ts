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

  hullSpacesSlider: FormControl = new FormControl(25);
  hullSpacesInput: FormControl = new FormControl(25, [
    Validators.required,
    Validators.min(25),
    Validators.max(500)
  ]);

  driveGenerationSlider: FormControl = new FormControl(3.4);
  driveGenerationInput: FormControl = new FormControl(3.4, [
    Validators.required,
    Validators.min(1),
    Validators.max(9)
  ]);

  thrustSlider: FormControl = new FormControl(6);
  thrustInput: FormControl = new FormControl(6, [
    Validators.required,
    Validators.min(0.5),
    Validators.max(16.0)
  ]);

  shipEditorForm: FormGroup = new FormGroup({
    hullSpacesSlider: this.hullSpacesSlider,
    hullSpacesInput: this.hullSpacesInput,
    driveGenerationSlider: this.driveGenerationSlider,
    driveGenerationInput: this.driveGenerationInput,
    thrustSlider: this.thrustSlider,
    thrustInput: this.thrustInput
  });

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.hullSpacesSlider.valueChanges.subscribe(value => this.hullSpacesInput.setValue(value, {emitEvent: false}));
    this.hullSpacesInput.valueChanges.subscribe(value => this.hullSpacesSlider.setValue(value, {emitEvent: false}));

    this.driveGenerationSlider.valueChanges.subscribe(value => this.driveGenerationInput.setValue(value, {emitEvent: false}));
    this.driveGenerationInput.valueChanges.subscribe(value => this.driveGenerationSlider.setValue(value, {emitEvent: false}));

    this.thrustSlider.valueChanges.subscribe(value => this.thrustInput.setValue(value, {emitEvent: false}));
    this.thrustInput.valueChanges.subscribe(value => this.thrustSlider.setValue(value, {emitEvent: false}));

    this.shipEditorForm.valueChanges.subscribe(value => {
      if (this.shipEditorForm.valid) {
        let hullSpaces = undefined;
        let driveGeneration = undefined;
        let thrust = undefined;

        if (value.hullSpacesInput !== null && value.hullSpacesInput === value.hullSpacesSlider) {
          hullSpaces = value.hullSpacesInput;
        }
        if (value.driveGenerationInput !== null && value.driveGenerationInput === value.driveGenerationSlider) {
          driveGeneration = value.driveGenerationInput;
        }
        if (value.thrustInput !== null && value.thrustInput === value.thrustSlider) {
          thrust = value.thrustInput;
        }

        if (hullSpaces && driveGeneration && thrust) {
          this.shipPatchEvent.emit(this.apiService.putShipData(hullSpaces, driveGeneration, thrust));
        }
      }
    });
  }
}
