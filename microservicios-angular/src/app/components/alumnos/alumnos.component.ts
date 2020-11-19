import {Component, OnInit, ViewChild} from '@angular/core';
import Swal from 'sweetalert2';
import {AlumnoService} from '../../services/alumno.service';
import {Alumno} from '../../models/alumno';
import {MatPaginator, PageEvent} from '@angular/material/paginator';


@Component({
  selector: 'app-alumnos',
  templateUrl: './alumnos.component.html',
  styleUrls: ['./alumnos.component.css']
})
export class AlumnosComponent implements OnInit {

  titulo = 'Listado de Alumnos';
  alumnos: Alumno[];
  totalRegistros = 0;
  paginaActual = 0;
  totalPorPagina = 4;
  pageSizeOptions: number[] = [3, 5, 10, 25, 100];
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private  service: AlumnoService) {
  }

  ngOnInit(): void {
    this.calcularRangos();
  }

  // tslint:disable-next-line:typedef
  private calcularRangos() {
    this.service.listarPagina(this.paginaActual.toString(), this.totalPorPagina.toString())
      .subscribe(pagina => {
        this.alumnos = pagina.content as Alumno[];
        this.totalRegistros = pagina.totalElements as number;
        this.paginator._intl.itemsPerPageLabel = 'Resgistros por Pagina';
      });
  }

  public paginar(event: PageEvent): void {
    this.paginaActual = event.pageIndex;
    this.totalPorPagina = event.pageSize;
    this.calcularRangos();
  }

  public eliminar(alumno: Alumno): void {
    Swal.fire({
      title: 'Cuidado',
      text: `¿Está seguro de eliminar a ${alumno.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if (result.value) {
        this.service.eliminar(alumno.id).subscribe(() => {
          // this.alumnos = this.alumnos.filter(alumnoFilter => alumnoFilter !== alumno);
          this.calcularRangos();
          Swal.fire('Eliminado: ', `Alumno ${alumno.nombre} eliminado exitosamente`, 'success')
            .then(r => window.location.reload());
        });
      }
    });
  }

}
