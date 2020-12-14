import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProductOrdered } from 'app/shared/model/product-ordered.model';

type EntityResponseType = HttpResponse<IProductOrdered>;
type EntityArrayResponseType = HttpResponse<IProductOrdered[]>;

@Injectable({ providedIn: 'root' })
export class ProductOrderedService {
  public resourceUrl = SERVER_API_URL + 'api/product-ordereds';

  constructor(protected http: HttpClient) {}

  create(productOrdered: IProductOrdered): Observable<EntityResponseType> {
    return this.http.post<IProductOrdered>(this.resourceUrl, productOrdered, { observe: 'response' });
  }

  update(productOrdered: IProductOrdered): Observable<EntityResponseType> {
    return this.http.put<IProductOrdered>(this.resourceUrl, productOrdered, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProductOrdered>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProductOrdered[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
