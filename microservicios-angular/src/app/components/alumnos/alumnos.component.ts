import {Component, OnInit} from '@angular/core';
import {AlumnoService} from '../../services/alumno.service';
import {Alumno} from '../../models/alumno';

@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent implements OnInit {

  titulo = 'Listado de Alumnos';
  alumnos: Alumno[];

  constructor(private  service: AlumnoService) {
  }

  ngOnInit(): void {
    this.service.listar().subscribe(alumnos => this.alumnos = alumnos);
  }

  public eliminar(alumno: Alumno): void {
    if (confirm(`¿Está seguro de eliminar a ${alumno.nombre} ?`)) {
      this.service.eliminar(alumno.id).subscribe(() => {
        this.alumnos = this.alumnos.filter(alumnoFilter => alumnoFilter !== alumno);
        alert(`Alumno ${alumno.nombre} eliminado exitosamente`);
      });
    }
  }
}
