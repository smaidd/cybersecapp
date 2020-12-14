import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'customer',
        loadChildren: () => import('./customer/customer.module').then(m => m.CybersecurityAppCustomerModule),
      },
      {
        path: 'order',
        loadChildren: () => import('./order/order.module').then(m => m.CybersecurityAppOrderModule),
      },
      {
        path: 'product-ordered',
        loadChildren: () => import('./product-ordered/product-ordered.module').then(m => m.CybersecurityAppProductOrderedModule),
      },
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.CybersecurityAppProductModule),
      },
      {
        path: 'warehouse',
        loadChildren: () => import('./warehouse/warehouse.module').then(m => m.CybersecurityAppWarehouseModule),
      },
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.CybersecurityAppLocationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class CybersecurityAppEntityModule {}
