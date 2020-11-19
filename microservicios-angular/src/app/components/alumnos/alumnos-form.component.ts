import {Component, OnInit} from '@angular/core';
import {Alumno} from '../../models/alumno';
import {AlumnoService} from '../../services/alumno.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-alumnos-form',
  templateUrl: './alumnos-form.component.html',
  styleUrls: ['./alumnos-form.component.css']
})
export class AlumnosFormComponent implements OnInit {

  titulo = 'Crear Alumnos';
  alumno: Alumno = new Alumno();
  error: any;

  constructor(private service: AlumnoService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id');
      if (id) {
        this.service.ver(id).subscribe(alumno => this.alumno = alumno);
      }
    });
  }

  public crear(): void {
    this.service.crear(this.alumno).subscribe(alumno => {
      console.log(alumno);
      alert(`Alumno ${alumno.nombre} creado con exito `);
      const promise = this.router.navigate(['/alumnos']);
    }, err => {
      if (err.status === 400) {
        this.error = err.error;
        console.log('Error: ' + `${this.error}`);
      }
    });
  }

  public editar(): void {
    this.service.editar(this.alumno).subscribe(alumno => {
      console.log(alumno);
      alert(`Alumno ${alumno.nombre} actualizado con exito `);
      const promise = this.router.navigate(['/alumnos']);
    }, err => {
      if (err.status === 400) {
        this.error = err.error;
        console.log('Error: ' + `${this.error}`);
      }
    });
  }
}
