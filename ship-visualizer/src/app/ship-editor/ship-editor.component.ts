import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-ship-editor',
  templateUrl: './ship-editor.component.html',
  styleUrls: ['./ship-editor.component.css']
})
export class ShipEditorComponent implements OnInit {
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

  constructor() { }

  ngOnInit(): void {
    this.hullSpacesInput.valueChanges.subscribe(value => this.hullSpacesSlider.setValue(value, {emitEvent: false}));
    this.hullSpacesSlider.valueChanges.subscribe(value => this.hullSpacesInput.setValue(value, {emitEvent: false}));
  }
}
