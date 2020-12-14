import { IProduct } from 'app/shared/model/product.model';

export interface IWarehouse {
  id?: number;
  warehouseName?: string;
  postalCode?: string;
  phoneNumber?: number;
  locationId?: number;
  products?: IProduct[];
}

export class Warehouse implements IWarehouse {
  constructor(
    public id?: number,
    public warehouseName?: string,
    public postalCode?: string,
    public phoneNumber?: number,
    public locationId?: number,
    public products?: IProduct[]
  ) {}
}
