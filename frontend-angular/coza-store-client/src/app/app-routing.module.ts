import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ForwardpassComponent } from './forwardpass/forwardpass.component';
import { LoginComponent } from './login/login.component';
import { NotfoundComponent } from './notfound/notfound.component';
import { SecurityGuard } from './security.guard';
import { WorkspaceComponent } from './workspace/workspace.component';

const routes: Routes = [
  { path: '', component: LoginComponent, title:'SB Shop - Login' },

  // { path: '', redirectTo: '', pathMatch: 'full' },
  { path: 'login', component: LoginComponent, title:"SB Shop - Login" },
  { path: 'forgot', component: ForwardpassComponent , title: "SB Shop - Forgot Password"},
  {
    path: 'workspace',
    title:"SB Shop - Workspace",
    component: WorkspaceComponent,
    children: [
      {
        path: '',
        loadChildren: () =>
          import('./workspace/workspace.module').then((m) => m.WorkspaceModule),

      },
    ],
    canActivate:[SecurityGuard],
    data:{
      role: 'ROLE_ADMIN'
    }
  },
  // phải để ở cuối cùng
  { path: '**', pathMatch: 'full', 
  component: NotfoundComponent },
  /**
  có / sẽ lấy localhost:4200/ + cọng vào
  ko có / dẽ lấy url hiện tại r cộng vào
   */
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
