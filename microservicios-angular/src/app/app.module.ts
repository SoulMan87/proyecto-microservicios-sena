import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {AlumnosComponent} from './components/alumnos/alumnos.component';
import {CursosComponent} from './components/cursos/cursos.component';
import {ExamenesComponent} from './components/examenes/examenes.component';
import {LayaoutModule} from './layaout/layaout.module';
import {HttpClientModule} from '@angular/common/http';
import { AlumnosFormComponent } from './components/alumnos/alumnos-form.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    AlumnosComponent,
    CursosComponent,
    ExamenesComponent,
    AlumnosFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    LayaoutModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
