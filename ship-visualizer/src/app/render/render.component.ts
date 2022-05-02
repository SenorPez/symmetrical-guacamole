import {AfterViewInit, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import * as THREE from "three";
import {MeshBasicMaterial, SphereGeometry} from "three";

@Component({
  selector: 'app-render',
  templateUrl: './render.component.html',
  styleUrls: ['./render.component.css']
})
export class RenderComponent implements OnInit, AfterViewInit {
  @ViewChild('shiprender')
  private renderRef!: ElementRef;

  private scene!: THREE.Scene;
  private camera!: THREE.Camera;
  private renderer!: THREE.WebGLRenderer;

  private get canvas(): HTMLCanvasElement {
    return this.renderRef.nativeElement;
  }

  private createScene() {
    this.scene = new THREE.Scene();
    this.scene.background = new THREE.Color(0x000000);

    let geometry: SphereGeometry = new THREE.SphereGeometry();
    let material: MeshBasicMaterial = new THREE.MeshBasicMaterial();
    let sphere: THREE.Mesh = new THREE.Mesh(geometry, material);
    this.scene.add(sphere);

    this.camera = new THREE.PerspectiveCamera(50, this.getAspectRatio());
    this.camera.position.z = 50;
  }

  private getAspectRatio() {
    return this.canvas.clientWidth / this.canvas.clientHeight;
  }

  private startRenderingLoop() {
    this.renderer = new THREE.WebGLRenderer({canvas: this.canvas});
    this.renderer.setPixelRatio(devicePixelRatio);
    this.renderer.setSize(this.canvas.clientWidth, this.canvas.clientHeight);

    let component: RenderComponent = this;
    (function render() {
      requestAnimationFrame(render);
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
