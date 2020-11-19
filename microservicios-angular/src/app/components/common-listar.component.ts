import {OnInit, ViewChild} from '@angular/core';
import Swal from 'sweetalert2';
import {MatPaginator, PageEvent} from '@angular/material/paginator';
import {Generic} from '../models/generic';
import {CommonService} from '../services/common.service';

export abstract class CommonListarComponent<E extends Generic, S extends CommonService<E>> implements OnInit {

  titulo: string;
  lista: E[];
  protected nombreModel: string;

  totalRegistros = 0;
  paginaActual = 0;
  totalPorPagina = 4;
  pageSizeOptions: number[] = [3, 5, 10, 25, 100];
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(protected  service: S) {
  }

  ngOnInit(): void {
    this.calcularRangos();
  }

  // tslint:disable-next-line:typedef
  private calcularRangos() {
    this.service.listarPagina(this.paginaActual.toString(), this.totalPorPagina.toString())
      .subscribe(pagina => {
        this.lista = pagina.content as E[];
        this.totalRegistros = pagina.totalElements as number;
        this.paginator._intl.itemsPerPageLabel = 'Resgistros por Pagina';
      });
  }

  public paginar(event: PageEvent): void {
    this.paginaActual = event.pageIndex;
    this.totalPorPagina = event.pageSize;
    this.calcularRangos();
  }

  public eliminar(e: E): void {
    Swal.fire({
      title: 'Cuidado',
      text: `¿Está seguro de eliminar a ${e.nombre} ?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, eliminar!'
    }).then((result) => {
      if (result.value) {
        this.service.eliminar(e.id).subscribe(() => {
          this.calcularRangos();
          Swal.fire('Eliminado: ', `${this.nombreModel} ${e.nombre} eliminado exitosamente`, 'success')
            .then(r => window.location.reload());
        });
      }
    });
  }

}
