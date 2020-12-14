import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CybersecurityAppSharedModule } from 'app/shared/shared.module';
import { ProductOrderedComponent } from './product-ordered.component';
import { ProductOrderedDetailComponent } from './product-ordered-detail.component';
import { ProductOrderedUpdateComponent } from './product-ordered-update.component';
import { ProductOrderedDeleteDialogComponent } from './product-ordered-delete-dialog.component';
import { productOrderedRoute } from './product-ordered.route';

@NgModule({
  imports: [CybersecurityAppSharedModule, RouterModule.forChild(productOrderedRoute)],
  declarations: [
    ProductOrderedComponent,
    ProductOrderedDetailComponent,
    ProductOrderedUpdateComponent,
    ProductOrderedDeleteDialogComponent,
  ],
  entryComponents: [ProductOrderedDeleteDialogComponent],
})
export class CybersecurityAppProductOrderedModule {}
