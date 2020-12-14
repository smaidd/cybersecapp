import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProductOrdered, ProductOrdered } from 'app/shared/model/product-ordered.model';
import { ProductOrderedService } from './product-ordered.service';
import { ProductOrderedComponent } from './product-ordered.component';
import { ProductOrderedDetailComponent } from './product-ordered-detail.component';
import { ProductOrderedUpdateComponent } from './product-ordered-update.component';

@Injectable({ providedIn: 'root' })
export class ProductOrderedResolve implements Resolve<IProductOrdered> {
  constructor(private service: ProductOrderedService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProductOrdered> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((productOrdered: HttpResponse<ProductOrdered>) => {
          if (productOrdered.body) {
            return of(productOrdered.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProductOrdered());
  }
}

export const productOrderedRoute: Routes = [
  {
    path: '',
    component: ProductOrderedComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProductOrdereds',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProductOrderedDetailComponent,
    resolve: {
      productOrdered: ProductOrderedResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProductOrdereds',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProductOrderedUpdateComponent,
    resolve: {
      productOrdered: ProductOrderedResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProductOrdereds',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProductOrderedUpdateComponent,
    resolve: {
      productOrdered: ProductOrderedResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'ProductOrdereds',
    },
    canActivate: [UserRouteAccessService],
  },
];
