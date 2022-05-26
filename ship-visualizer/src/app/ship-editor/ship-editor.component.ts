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

  driveGenerationSlider: FormControl = new FormControl(1);
  driveGenerationInput: FormControl = new FormControl(1, [
    Validators.required,
    Validators.min(1),
    Validators.max(9)
  ]);

  shipEditorForm: FormGroup = new FormGroup({
    hullSpacesSlider: this.hullSpacesSlider,
    hullSpacesInput: this.hullSpacesInput,
    driveGenerationSlider: this.driveGenerationSlider,
    driveGenerationInput: this.driveGenerationInput
  });

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.hullSpacesSlider.valueChanges.subscribe(value => this.hullSpacesInput.setValue(value, {emitEvent: false}));
    this.hullSpacesInput.valueChanges.subscribe(value => this.hullSpacesSlider.setValue(value, {emitEvent: false}));

    this.driveGenerationSlider.valueChanges.subscribe(value => this.driveGenerationInput.setValue(value, {emitEvent: false}));
    this.driveGenerationInput.valueChanges.subscribe(value => this.driveGenerationSlider.setValue(value, {emitEvent: false}));

    this.shipEditorForm.valueChanges.subscribe(value => {
      if (this.shipEditorForm.valid) {
        let hullSpaces = undefined;
        let driveGeneration = undefined;

        if (value.hullSpacesInput !== null && value.hullSpacesInput === value.hullSpacesSlider) {
          hullSpaces = value.hullSpacesInput;
        }
        if (value.driveGenerationInput !== null && value.driveGenerationInput === value.driveGenerationSlider) {
          driveGeneration = value.driveGenerationInput;
        }

        if (hullSpaces && this.driveGenerationInput) {
          this.shipPatchEvent.emit(this.apiService.putShipData(hullSpaces, driveGeneration, 0.5));
        }
      }
    });
  }
}
