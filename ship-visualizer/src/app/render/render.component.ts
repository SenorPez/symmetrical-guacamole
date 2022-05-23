import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AxesHelper, Camera, Color, PerspectiveCamera, Scene, WebGLRenderer} from "three";
import {OrbitControls} from "three/examples/jsm/controls/OrbitControls";
import {Lantern} from "../lantern";
import {Shield} from "../shield";
import {Hull} from "../hull";
import {Mast} from "../mast";

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
  private controls!: OrbitControls;
  private renderer!: WebGLRenderer;

  private get canvas(): HTMLCanvasElement {
    return this.renderRef.nativeElement;
  }

  private createShip(
    hullDiameter: number = 14.94895,
    mastLength: number = 28.20816,
    mastDiameter: number = mastLength / 50,
    shieldLength: number = 2.64379,
    shieldDiameter: number = 2.02292,
    lanternDiameter: number = 10.95445
  ) {
    let hull: Hull = new Hull(hullDiameter);
    let mast: Mast = new Mast(mastDiameter, mastLength);
    let shield: Shield = new Shield(shieldDiameter * 1.25, shieldDiameter, shieldLength);
    let lantern: Lantern = new Lantern(lanternDiameter);

    hull.attachMast(mast).attachShield(shield).attachLantern(lantern);
    this.scene.add(hull.hullMesh);
  }

  private createScene() {
    this.scene = new Scene();
    this.scene.background = new Color(0x000000);

    const axesHelper = new AxesHelper(5);
    this.scene.add(axesHelper);

    this.createShip();

    let hullDiameter: number = 14.94895;
    let mastLength: number = 28.20816;
    let shieldLength: number = 2.64379;
    let lanternDiameter: number = 10.95445;

    let totalLength: number = hullDiameter + mastLength + shieldLength + lanternDiameter / 2;

    this.camera = new PerspectiveCamera(50, this.getAspectRatio());
    this.camera.position.set(totalLength, totalLength, totalLength + totalLength / 2);
  }

  private getAspectRatio() {
    return this.canvas.clientWidth / this.canvas.clientHeight;
  }

  private startRenderingLoop() {
    this.renderer = new WebGLRenderer({canvas: this.canvas});
    this.renderer.setPixelRatio(devicePixelRatio);
    this.renderer.setSize(this.canvas.clientWidth, this.canvas.clientHeight);

    this.controls = new OrbitControls(this.camera, this.renderer.domElement);
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
