import {Component, OnInit} from '@angular/core';
import {CommonFormComponent} from '../common-form.component';
import {Examen} from '../../models/examen';
import {ExamenService} from '../../services/examen.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Asignatura} from '../../models/asignatura';

@Component({
  selector: 'app-examen-form',
  templateUrl: './examen-form.component.html',
  styleUrls: ['./examen-form.component.css']
})
export class ExamenFormComponent extends CommonFormComponent<Examen, ExamenService> implements OnInit {

  asignarutasPadre: Asignatura[] = [];
  asignarutasHija: Asignatura[] = [];

  constructor(service: ExamenService,
              router: Router,
              route: ActivatedRoute) {
    super(service, router, route);
    this.titulo = 'Crear Examen';
    this.model = new Examen();
    this.nombreModel = Examen.name;
    this.redirect = '/examenes';
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id: number = +params.get('id');
      if (id) {
        this.service.ver(id).subscribe(m => {
          this.model = m;
          this.titulo = 'Editar ' + this.nombreModel;
        });
      }
    });
    this.service.findAllAsignaturas()
      .subscribe(asignaturas =>
        this.asignarutasPadre = asignaturas.filter(a => !a.padre));
  }


}
