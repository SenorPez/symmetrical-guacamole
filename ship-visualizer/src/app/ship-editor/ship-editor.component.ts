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

  engineGenerationSlider: FormControl = new FormControl(1);
  engineGenerationInput: FormControl = new FormControl(1, [
    Validators.required,
    Validators.min(1),
    Validators.max(9)
  ]);

  shipEditorForm: FormGroup = new FormGroup({
    hullSpacesSlider: this.hullSpacesSlider,
    hullSpacesInput: this.hullSpacesInput,
    engineGenerationSlider: this.engineGenerationSlider,
    engineGenerationInput: this.engineGenerationInput
  });

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.hullSpacesSlider.valueChanges.subscribe(value => this.hullSpacesInput.setValue(value, {emitEvent: false}));
    this.hullSpacesInput.valueChanges.subscribe(value => this.hullSpacesSlider.setValue(value, {emitEvent: false}));

    this.engineGenerationSlider.valueChanges.subscribe(value => this.engineGenerationInput.setValue(value, {emitEvent: false}));
    this.engineGenerationInput.valueChanges.subscribe(value => this.engineGenerationSlider.setValue(value, {emitEvent: false}));

    this.shipEditorForm.valueChanges.subscribe(value => {
      if (this.shipEditorForm.valid) {
        let hullSpaces = undefined;
        let engineGeneration = undefined;

        if (value.hullSpacesInput !== null && value.hullSpacesInput === value.hullSpacesSlider) {
          hullSpaces = value.hullSpacesInput;
        }
        if (value.engineGenerationInput !== null && value.engineGenerationInput === value.engineGenerationSlider) {
          engineGeneration = value.engineGenerationInput;
        }

        if (hullSpaces && this.engineGenerationInput) {
          this.shipPatchEvent.emit(this.apiService.putShipData(hullSpaces, engineGeneration));
        }
      }
    });
  }
}
