import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProductOrdered } from 'app/shared/model/product-ordered.model';
import { ProductOrderedService } from './product-ordered.service';
import { ProductOrderedDeleteDialogComponent } from './product-ordered-delete-dialog.component';

@Component({
  selector: 'jhi-product-ordered',
  templateUrl: './product-ordered.component.html',
})
export class ProductOrderedComponent implements OnInit, OnDestroy {
  productOrdereds?: IProductOrdered[];
  eventSubscriber?: Subscription;

  constructor(
    protected productOrderedService: ProductOrderedService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.productOrderedService.query().subscribe((res: HttpResponse<IProductOrdered[]>) => (this.productOrdereds = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProductOrdereds();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProductOrdered): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProductOrdereds(): void {
    this.eventSubscriber = this.eventManager.subscribe('productOrderedListModification', () => this.loadAll());
  }

  delete(productOrdered: IProductOrdered): void {
    const modalRef = this.modalService.open(ProductOrderedDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.productOrdered = productOrdered;
  }
}
