import { Routes } from '@angular/router';
import { ClientListComponent } from './pages/clients/client-list/client-list.component';
import { ClientFormComponent } from './pages/clients/client-form/client-form.component';
import { ClientDetailsComponent } from './pages/clients/client-details/client-details.component';
import { PieceListComponent } from './pages/pieces/piece-list/piece-list.component';
import { PieceDetailsComponent } from './pages/pieces/piece-details/piece-details.component';
import { PieceFormComponent } from './pages/pieces/piece-form/piece-form.component';
import { CategorieFormComponent } from './pages/categories/categorie-form/categorie-form.component';
import { CategorieListComponent } from './pages/categories/categorie-list/categorie-list.component';
import { HomeComponent } from './pages/home/home.component';
import { PieceHierarchyComponent } from './pages/piece-hierarchy/piece-hierarchy.component';
import { UploadComponent } from './pages/upload/upload.component';


export const routes: Routes = [
 
  {
    path: 'clients/list',
    component: ClientListComponent
  },
  {
    path: 'clients/new',
    component: ClientFormComponent
  },
  {
    path: 'clients/edit/:id',
    component: ClientFormComponent
  },
  {
    path: 'clients/:id',
    component: ClientDetailsComponent
  },
  {
    path: 'pieces/list',
    component: PieceListComponent
  },
  {
    path: 'pieces/new',
    component: PieceFormComponent
  },
  {
    path: 'pieces/edit/:id',
    component: PieceFormComponent
  },
  {
    path: 'pieces/:id',
    component: PieceDetailsComponent
  },
  {
    path: 'categories/list',
    component: CategorieListComponent
  },
  {
    path: 'categories/new',
    component: CategorieFormComponent
  },
  {
    path: 'categories/edit/:id',
    component: CategorieFormComponent
  },
   { path: '', component: HomeComponent },
  
{
  path: 'hierarchie',
  component: PieceHierarchyComponent
},
  { path: 'upload',
     component: UploadComponent },

];
