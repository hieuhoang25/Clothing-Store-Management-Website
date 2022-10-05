import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { WorkspaceRoutingModule } from './workspace-routing.module';
import { WorkspaceComponent } from './workspace.component';
import { HomeComponent } from './home/home.component';
import { DiscountComponent } from './discount/discount.component';
import { HttpClientModule} from '@angular/common/http';
import { ModalComponent } from './modal/modal.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ProductComponent } from './product/product.component';

@NgModule({
  declarations: [
    WorkspaceComponent,
    HomeComponent,
    DiscountComponent,
    ModalComponent,
    ProductComponent
  ],
  imports: [
    CommonModule,
    WorkspaceRoutingModule,
    HttpClientModule,
    FormsModule
  ]
})
export class WorkspaceModule { }
