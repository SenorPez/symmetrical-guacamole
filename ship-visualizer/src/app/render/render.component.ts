import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AxesHelper, Camera, Color, Mesh, PerspectiveCamera, Scene, WebGLRenderer} from "three";
import {ArcballControls} from "three/examples/jsm/controls/ArcballControls";
import {Ship} from "../ship";
import {ApiService} from "../api.service";
import {Observable} from "rxjs";

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

  ship!: Ship;
  private shipMesh!: Mesh;

  private get canvas(): HTMLCanvasElement {
    return this.renderRef.nativeElement;
  }

  private createScene() {
    this.scene = new Scene();
    this.scene.background = new Color('black');

    const axesHelper = new AxesHelper(100);
    // this.scene.add(axesHelper);
    this.shipMesh = this.ship.shipMesh;
    this.shipMesh.rotateX(Math.PI / 2);

    this.scene.add(this.shipMesh);

    const totalLength: number = this.ship.hullDiameter + this.ship.mastLength + this.ship.shieldThickness + this.ship.lanternDiameter / 2;
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
    this.controls.target = this.shipMesh.position;
    this.controls.update();

    let component: RenderComponent = this;
    (function render() {
      requestAnimationFrame(render);
      component.controls.update();
      component.renderer.render(component.scene, component.camera);
    }());
  }

  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.apiService.getShip().subscribe({
      next: (ship: Ship) => {
        this.ship = ship;
        this.createScene();
        this.startRenderingLoop();
      }
    });
  }

  updateShip($event: Observable<Ship>) {
    $event.subscribe(ship => {
      this.ship = ship;
      this.scene.remove(this.shipMesh);
      this.shipMesh = this.ship.shipMesh;
      this.scene.add(this.shipMesh);
    });
  }
}
