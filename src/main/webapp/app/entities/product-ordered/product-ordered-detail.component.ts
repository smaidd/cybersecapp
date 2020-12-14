import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProductOrdered } from 'app/shared/model/product-ordered.model';

@Component({
  selector: 'jhi-product-ordered-detail',
  templateUrl: './product-ordered-detail.component.html',
})
export class ProductOrderedDetailComponent implements OnInit {
  productOrdered: IProductOrdered | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productOrdered }) => (this.productOrdered = productOrdered));
  }

  previousState(): void {
    window.history.back();
  }
}
