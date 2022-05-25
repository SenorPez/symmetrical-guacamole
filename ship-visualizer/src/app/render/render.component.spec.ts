import {ComponentFixture, TestBed} from '@angular/core/testing';
import {RenderComponent} from './render.component';
import createSpyObj = jasmine.createSpyObj;
import {ApiService} from "../api.service";
import SpyObj = jasmine.SpyObj;
import {Ship} from "../ship";
import { of } from 'rxjs';

function getRandomInteger(min: number, max: number): number {
  return Math.floor(Math.random() * (max - min)) + min;
}

describe('RenderComponent', () => {
  let component: RenderComponent;
  let fixture: ComponentFixture<RenderComponent>;
  let apiServiceSpy: SpyObj<ApiService>;

  beforeEach(async () => {
    const spy = createSpyObj('ApiService', ['getShip']);

    await TestBed.configureTestingModule({
      declarations: [
        RenderComponent
      ],
      providers: [
        {provide: ApiService, useValue: spy}
      ]
    }).compileComponents();

    apiServiceSpy = TestBed.inject(ApiService) as SpyObj<ApiService>;
    apiServiceSpy.getShip.and.returnValue(of(new Ship(10, 10, 10, 10, 10, 10, 10)));
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a 500x500 canvas', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('canvas')?.width).toEqual(500);
    expect(compiled.querySelector('canvas')?.height).toEqual(500);
  });

  it('should update the ship on a patch', async () => {
    const expectedValue: Ship = new Ship(
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101),
      getRandomInteger(1, 101)
    );

    await component.updateShip(of(expectedValue));
    expect(component.ship).toEqual(expectedValue);
  });
});
