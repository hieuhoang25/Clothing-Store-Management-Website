import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotfoundComponent } from '../notfound/notfound.component';
import { CategoryComponent } from './category/category.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DiscountComponent } from './discount/discount.component';
import { HomeComponent } from './home/home.component';
import { OrderComponent } from './order/order.component';
import { ProductComponent } from './product/product.component';
import { UserComponent } from './user/user.component';

import { WorkspaceComponent } from './workspace.component';

const routes: Routes = [
{ path: '', component: HomeComponent },
{ path: 'dashboard', component: DashboardComponent },
{ path: 'discount-voucher', component: DiscountComponent },
{ path: 'category', component: CategoryComponent },
{ path: 'product', component: ProductComponent },
{ path: 'user', component: UserComponent },
{ path: 'order', component: OrderComponent },
{ path: '**', redirectTo:'workspace', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WorkspaceRoutingModule { }
