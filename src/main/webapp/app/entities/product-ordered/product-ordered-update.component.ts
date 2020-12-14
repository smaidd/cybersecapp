import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IProductOrdered, ProductOrdered } from 'app/shared/model/product-ordered.model';
import { ProductOrderedService } from './product-ordered.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';
import { IWarehouse } from 'app/shared/model/warehouse.model';
import { WarehouseService } from 'app/entities/warehouse/warehouse.service';
import { IOrder } from 'app/shared/model/order.model';
import { OrderService } from 'app/entities/order/order.service';

type SelectableEntity = IProduct | IWarehouse | IOrder;

@Component({
  selector: 'jhi-product-ordered-update',
  templateUrl: './product-ordered-update.component.html',
})
export class ProductOrderedUpdateComponent implements OnInit {
  isSaving = false;
  products: IProduct[] = [];
  sellingcompanies: IWarehouse[] = [];
  orders: IOrder[] = [];

  editForm = this.fb.group({
    id: [],
    quantity: [],
    shippingPrice: [],
    productId: [],
    sellingCompanyId: [],
    orderId: [],
  });

  constructor(
    protected productOrderedService: ProductOrderedService,
    protected productService: ProductService,
    protected warehouseService: WarehouseService,
    protected orderService: OrderService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ productOrdered }) => {
      this.updateForm(productOrdered);

      this.productService
        .query({ filter: 'productordered-is-null' })
        .pipe(
          map((res: HttpResponse<IProduct[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IProduct[]) => {
          if (!productOrdered.productId) {
            this.products = resBody;
          } else {
            this.productService
              .find(productOrdered.productId)
              .pipe(
                map((subRes: HttpResponse<IProduct>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IProduct[]) => (this.products = concatRes));
          }
        });

      this.warehouseService
        .query({ filter: 'productordered-is-null' })
        .pipe(
          map((res: HttpResponse<IWarehouse[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IWarehouse[]) => {
          if (!productOrdered.sellingCompanyId) {
            this.sellingcompanies = resBody;
          } else {
            this.warehouseService
              .find(productOrdered.sellingCompanyId)
              .pipe(
                map((subRes: HttpResponse<IWarehouse>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IWarehouse[]) => (this.sellingcompanies = concatRes));
          }
        });

      this.orderService.query().subscribe((res: HttpResponse<IOrder[]>) => (this.orders = res.body || []));
    });
  }

  updateForm(productOrdered: IProductOrdered): void {
    this.editForm.patchValue({
      id: productOrdered.id,
      quantity: productOrdered.quantity,
      shippingPrice: productOrdered.shippingPrice,
      productId: productOrdered.productId,
      sellingCompanyId: productOrdered.sellingCompanyId,
      orderId: productOrdered.orderId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const productOrdered = this.createFromForm();
    if (productOrdered.id !== undefined) {
      this.subscribeToSaveResponse(this.productOrderedService.update(productOrdered));
    } else {
      this.subscribeToSaveResponse(this.productOrderedService.create(productOrdered));
    }
  }

  private createFromForm(): IProductOrdered {
    return {
      ...new ProductOrdered(),
      id: this.editForm.get(['id'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      shippingPrice: this.editForm.get(['shippingPrice'])!.value,
      productId: this.editForm.get(['productId'])!.value,
      sellingCompanyId: this.editForm.get(['sellingCompanyId'])!.value,
      orderId: this.editForm.get(['orderId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProductOrdered>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
