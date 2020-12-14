import { Moment } from 'moment';
import { IProductOrdered } from 'app/shared/model/product-ordered.model';
import { Status } from 'app/shared/model/enumerations/status.model';

export interface IOrder {
  id?: number;
  status?: Status;
  orderDate?: Moment;
  products?: IProductOrdered[];
  customerId?: number;
}

export class Order implements IOrder {
  constructor(
    public id?: number,
    public status?: Status,
    public orderDate?: Moment,
    public products?: IProductOrdered[],
    public customerId?: number
  ) {}
}
