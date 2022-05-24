import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AxesHelper, Camera, Color, PerspectiveCamera, Scene, WebGLRenderer} from "three";
import {Lantern} from "../lantern";
import {Shield} from "../shield";
import {Hull} from "../hull";
import {Mast} from "../mast";
import {ArcballControls} from "three/examples/jsm/controls/ArcballControls";

@Component({
  selector: 'app-render',
  templateUrl: './render.component.html',
  styleUrls: ['./render.component.css']
})
export class RenderComponent implements OnInit, AfterViewInit {
  @ViewChild('shiprender')
  private renderRef!: ElementRef;

  private scene!: Scene;
  private camera!: Camera;
  private controls!: ArcballControls;
  private renderer!: WebGLRenderer;

  private hull!: Hull;

  private get canvas(): HTMLCanvasElement {
    return this.renderRef.nativeElement;
  }

  static createShip(
    hullDiameter: number,
    mastLength: number,
    mastDiameter: number,
    shieldLength: number,
    shieldMaxDiameter: number,
    shieldMinDiameter: number,
    lanternDiameter: number
  ): Hull {
    let hull: Hull = new Hull(hullDiameter);
    let mast: Mast = new Mast(mastDiameter, mastLength);
    let shield: Shield = new Shield(shieldMaxDiameter, shieldMinDiameter, shieldLength);
    let lantern: Lantern = new Lantern(lanternDiameter);

    hull.attachMast(mast).attachShield(shield).attachLantern(lantern);
    return hull;
  }

  private createScene() {
    this.scene = new Scene();
    this.scene.background = new Color('black');

    const axesHelper = new AxesHelper(5);
    this.scene.add(axesHelper);

    const hullDiameter: number = 14.94895;
    const mastLength: number = 28.20816;
    const mastDiameter: number = mastLength / 50;
    const shieldMaxDiameter: number = 2.02292;
    const shieldMinDiameter: number = shieldMaxDiameter * 0.75;
    const shieldLength: number = 2.64379;
    const lanternDiameter: number = 10.95445;
    const totalLength: number = hullDiameter + mastLength + shieldLength + lanternDiameter / 2;
    this.hull = RenderComponent.createShip(
      hullDiameter,
      mastLength,
      mastDiameter,
      shieldLength,
      shieldMaxDiameter,
      shieldMinDiameter,
      lanternDiameter
    );

    this.scene.add(this.hull.hullMesh);

    this.camera = new PerspectiveCamera(50, this.getAspectRatio());
    this.camera.position.set(totalLength * 2, 0, 0);
  }

  private getAspectRatio() {
    return this.canvas.clientWidth / this.canvas.clientHeight;
  }

  private startRenderingLoop() {
    this.renderer = new WebGLRenderer({canvas: this.canvas});
    this.renderer.setPixelRatio(devicePixelRatio);
    this.renderer.setSize(this.canvas.clientWidth, this.canvas.clientHeight);

    this.controls = new ArcballControls(this.camera, this.renderer.domElement);
    this.controls.update();

    let component: RenderComponent = this;
    (function render() {
      requestAnimationFrame(render);
      component.controls.update();
      component.renderer.render(component.scene, component.camera);
    }());
  }

  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.createScene();
    this.startRenderingLoop();
  }
}
