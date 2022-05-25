import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ShipEditorComponent} from './ship-editor.component';
import {HarnessLoader} from "@angular/cdk/testing";
import {MatSliderModule} from "@angular/material/slider";
import {TestbedHarnessEnvironment} from "@angular/cdk/testing/testbed";
import {MatSliderHarness} from "@angular/material/slider/testing";
import {MatInputHarness} from "@angular/material/input/testing";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {ReactiveFormsModule} from "@angular/forms";
import {NoopAnimationsModule} from "@angular/platform-browser/animations";
import {MatFormFieldHarness} from "@angular/material/form-field/testing";
import SpyObj = jasmine.SpyObj;
import {ApiService} from "../api.service";
import createSpyObj = jasmine.createSpyObj;
import {Ship} from "../ship";
import {of} from "rxjs";

function getRandomInteger(min: number, max: number): number {
  return Math.floor(Math.random() * (max - min)) + min;
}

describe('ShipEditorComponent', () => {
  let component: ShipEditorComponent;
  let fixture: ComponentFixture<ShipEditorComponent>;
  let loader: HarnessLoader;
  let apiServiceSpy: SpyObj<ApiService>;

  beforeEach(async () => {
    const spy = createSpyObj('ApiService', ['patchShip']);

    await TestBed.configureTestingModule({
      imports: [MatFormFieldModule, MatInputModule, MatSliderModule, ReactiveFormsModule, NoopAnimationsModule],
      declarations: [ ShipEditorComponent ],
      providers: [
        {provide: ApiService, useValue: spy}
      ]
    })
    .compileComponents();

    apiServiceSpy = TestBed.inject(ApiService) as SpyObj<ApiService>;
    apiServiceSpy.patchShip.and.returnValue(of(new Ship(10, 10, 10, 10, 10, 10, 10)));

    fixture = TestBed.createComponent(ShipEditorComponent);
    fixture.detectChanges();
    component = fixture.componentInstance;
    loader = TestbedHarnessEnvironment.loader(fixture);
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should load all slider harnesses', async () => {
    const sliders = await loader.getAllHarnesses(MatSliderHarness);
    expect(sliders.length).toBe(1);
  });

  it('should load all form field harnesses', async () => {
    const inputs = await loader.getAllHarnesses(MatFormFieldHarness);
    expect(inputs.length).toBe(1);
  });

  describe('Hull Spaces slider', async () => {
    let slider: MatSliderHarness;

    beforeEach(async () => {
      slider = await loader.getHarness(MatSliderHarness);
    })

    it('should have a default value of 25', async () => {
      expect(await slider.getValue()).toBe(25);
    });

    it('should get min value of slider', async () => {
      expect(await slider.getMinValue()).toBe(25);
    });

    it('should get max value of slider', async () => {
      expect(await slider.getMaxValue()).toBe(500);
    });

    it('should have a thumb label', async () => {
      expect(await slider.getDisplayValue()).not.toBeNull();
    });

    it('should be able to set the value', async () => {
      const expectedValue = getRandomInteger(25, 501);

      // Need to make the slider wider so that harness has a single pixel to 'click' on.
      const appShipEditor: HTMLElement = fixture.nativeElement;
      const sliderElement = appShipEditor.querySelector('#hullSpacesSlider');
      sliderElement?.setAttribute('style', 'width: 1000px');

      await slider.setValue(expectedValue);
      expect(await slider.getValue()).toBe(expectedValue);
    });

    it('should update the input and form group', async () => {
      const randomHullSize = getRandomInteger(25, 501);

      const appShipEditor: HTMLElement = fixture.nativeElement;
      const sliderElement = appShipEditor.querySelector('#hullSpacesSlider');
      sliderElement?.setAttribute('style', 'width: 1000px');

      await slider.setValue(randomHullSize);
      const expectedValue = {
        hullSpacesSlider: randomHullSize,
        hullSpacesInput: randomHullSize
      }
      expect(component.hullSpacesForm.value).toEqual(expectedValue);
    });

    it('should call the API with a patch', async () => {
      const randomHullSize = getRandomInteger(25, 501);

      const appShipEditor: HTMLElement = fixture.nativeElement;
      const sliderElement = appShipEditor.querySelector('#hullSpacesSlider');
      sliderElement?.setAttribute('style', 'width: 1000px');

      await slider.setValue(randomHullSize);
      expect(apiServiceSpy.patchShip).toHaveBeenCalledOnceWith(randomHullSize);
    });
  });

  describe('Hull Spaces form field', () => {
    let formField: MatFormFieldHarness;
    let input: MatInputHarness;

    beforeEach(async () => {
      formField = await loader.getHarness(MatFormFieldHarness);
      input = await formField.getControl() as MatInputHarness;
    });

    it('should display a required error if blank', async () => {
      await input.setValue('');
      await input.blur();
      expect(await formField.getTextErrors()).toEqual(['Hull Spaces is required']);
    });

    it('should display a min value error if too low', async () => {
      await input.setValue('10');
      await input.blur();
      expect(await formField.getTextErrors()).toEqual(['Hull Spaces must be at least 25']);
    });

    it('should display a min value error if too low', async () => {
      await input.setValue('600');
      await input.blur();
      expect(await formField.getTextErrors()).toEqual(['Hull Spaces must be no more than 500']);
    });

    describe('Hull Spaces input', () => {
      it('should have a default value of 25', async () => {
        expect(await input.getValue()).toBe('25');
      });

      it('should have a number type of input', async () => {
        expect(await input.getType()).toBe('number');
      });

      it('should be able to set the value', async () => {
        const expectedValue = String(getRandomInteger(25, 501));
        await input.setValue(expectedValue);
        expect(await input.getValue()).toBe(expectedValue)
      });

      it('should be required', async () => {
        expect(await input.isRequired()).toBeTrue();
      });

      it('should update the slider and form group', async () => {
        const randomHullSize = getRandomInteger(25, 501);
        await input.setValue(String(randomHullSize));
        const expectedValue = {
          hullSpacesSlider: randomHullSize,
          hullSpacesInput: randomHullSize
        }
        expect(component.hullSpacesForm.value).toEqual(expectedValue);
      });

      it('should call the API with a patch', async () => {
        const randomHullSize = getRandomInteger(25, 501);
        await input.setValue(String(randomHullSize));

        // Create step-through as it simulates keyboard input.
        const s = String(randomHullSize).split("");
        const inputs: number[] = [];
        for (let i = 1; i <= s.length; i++) {
          inputs.push(Number(s.slice(0, i).join('')));
        }

        expect(apiServiceSpy.patchShip).toHaveBeenCalledTimes(inputs.length);
        inputs.forEach(value => {
          expect(apiServiceSpy.patchShip).toHaveBeenCalledWith(value);
        });
      });
    });
  });
});
