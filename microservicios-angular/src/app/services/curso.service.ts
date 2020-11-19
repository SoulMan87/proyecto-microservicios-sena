import {Injectable} from '@angular/core';
import {CommonService} from './common.service';
import {Curso} from '../models/curso';

@Injectable({
  providedIn: 'root'
})
export class CursoService extends CommonService<Curso> {

  protected baseEndpoint = '/api/cursos';

}

