import { IWarehouse } from 'app/shared/model/warehouse.model';

export interface IProduct {
  id?: number;
  productName?: string;
  description?: string;
  price?: number;
  warehouses?: IWarehouse[];
  productOrderedId?: number;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public productName?: string,
    public description?: string,
    public price?: number,
    public warehouses?: IWarehouse[],
    public productOrderedId?: number
  ) {}
}
