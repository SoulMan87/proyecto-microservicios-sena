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
import {AlumnosFormComponent} from './components/alumnos/alumnos-form.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatPaginatorModule} from '@angular/material/paginator';
import {CursoFormComponent} from './components/cursos/curso-form.component';
import {ExamenFormComponent} from './components/examenes/examen-form.component';
import {MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatButtonModule} from '@angular/material/button';
import {AsignarAlumnosComponent} from './components/cursos/asignar-alumnos.component';
import {MatCardModule} from '@angular/material/card';
import {MatTabsModule} from '@angular/material/tabs';
import {MatAutocomplete} from '@angular/material/autocomplete';

@NgModule({
  declarations: [
    AppComponent,
    AlumnosComponent,
    CursosComponent,
    ExamenesComponent,
    AlumnosFormComponent,
    CursoFormComponent,
    ExamenFormComponent,
    AsignarAlumnosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    LayaoutModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule,
    MatTableModule,
    MatCheckboxModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatCardModule,
    MatTabsModule,
    MatAutocomplete
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
