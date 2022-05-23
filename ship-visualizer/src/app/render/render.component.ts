import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {
  AxesHelper,
  Camera,
  Color,
  CylinderGeometry,
  Mesh,
  MeshBasicMaterial,
  PerspectiveCamera,
  Scene,
  SphereGeometry,
  WebGLRenderer
} from "three";
import {OrbitControls} from "three/examples/jsm/controls/OrbitControls";
import {Lantern} from "../lantern";
import {Shield} from "../shield";

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
    let hull: Mesh<SphereGeometry, MeshBasicMaterial> = RenderComponent.createHull(hullDiameter);
    let mast: Mesh<CylinderGeometry, MeshBasicMaterial> = RenderComponent.createMast(mastDiameter, mastLength);
    let shield: Shield = new Shield(shieldDiameter * 1.25, shieldDiameter, shieldLength);
    // let shield: Mesh<CylinderGeometry, MeshBasicMaterial> = RenderComponent.createShield(shieldDiameter, shieldLength);
    let lantern: Lantern = new Lantern(lanternDiameter);
    // let lantern: Line<WireframeGeometry<SphereGeometry>, LineBasicMaterial> = RenderComponent.createLantern(lanternDiameter);

    shield.attachLantern(lantern);
    mast = RenderComponent.attachShield(mast, shield.shieldMesh);
    hull = RenderComponent.attachMast(hull, mast);
    this.scene.add(hull);
  }

  static attachMast(
    hull: Mesh<SphereGeometry, MeshBasicMaterial>,
    mast: Mesh<CylinderGeometry, MeshBasicMaterial>
  ): Mesh<SphereGeometry, MeshBasicMaterial> {
    mast.position.set(0, -1 * (hull.geometry.parameters.radius + mast.geometry.parameters.height / 2), 0);
    return hull.add(mast);
  }

  static attachShield(
    mast: Mesh<CylinderGeometry, MeshBasicMaterial>,
    shield: Mesh<CylinderGeometry, MeshBasicMaterial>
  ): Mesh<CylinderGeometry, MeshBasicMaterial> {
    shield.position.set(0, -0.5 * (mast.geometry.parameters.height + shield.geometry.parameters.height), 0);
    return mast.add(shield);
  }

  // static attachLantern(
  //   shield: Mesh<CylinderGeometry, MeshBasicMaterial>,
  //   lantern: Lantern
  // ): Mesh<CylinderGeometry, MeshBasicMaterial> {
  //   lantern.lanternMesh.position.set(0, -1 * (lantern.lanternMesh.geometry.parameters.geometry.parameters.radius + shield.geometry.parameters.height / 2), 0);
  //   return shield.add(lantern.lanternMesh);
  // }

  static createHull(hullDiameter: number): Mesh<SphereGeometry, MeshBasicMaterial> {
    const hullGeometry: SphereGeometry = new SphereGeometry(hullDiameter / 2);
    const hullMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'red'});
    return new Mesh(hullGeometry, hullMaterial);
  }

  static createMast(mastDiameter: number, mastLength: number): Mesh<CylinderGeometry, MeshBasicMaterial> {
    const mastGeometry: CylinderGeometry = new CylinderGeometry(mastDiameter / 2, mastDiameter / 2, mastLength);
    const mastMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'yellow'});
    return new Mesh(mastGeometry, mastMaterial);
  }

  static createShield(shieldDiameter: number, shieldLength: number): Mesh<CylinderGeometry, MeshBasicMaterial> {
    const shieldGeometry: CylinderGeometry = new CylinderGeometry(shieldDiameter / 2, shieldDiameter / 2, shieldLength);
    const shieldMaterial: MeshBasicMaterial = new MeshBasicMaterial({color: 'lime'});
    return new Mesh(shieldGeometry, shieldMaterial);
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
