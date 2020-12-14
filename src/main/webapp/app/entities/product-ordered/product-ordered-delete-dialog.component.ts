import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProductOrdered } from 'app/shared/model/product-ordered.model';
import { ProductOrderedService } from './product-ordered.service';

@Component({
  templateUrl: './product-ordered-delete-dialog.component.html',
})
export class ProductOrderedDeleteDialogComponent {
  productOrdered?: IProductOrdered;

  constructor(
    protected productOrderedService: ProductOrderedService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.productOrderedService.delete(id).subscribe(() => {
      this.eventManager.broadcast('productOrderedListModification');
      this.activeModal.close();
    });
  }
}
