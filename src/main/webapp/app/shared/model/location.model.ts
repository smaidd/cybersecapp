import { IWarehouse } from 'app/shared/model/warehouse.model';

export interface ILocation {
  id?: number;
  address?: string;
  city?: string;
  county?: string;
  warehouses?: IWarehouse[];
}

export class Location implements ILocation {
  constructor(
    public id?: number,
    public address?: string,
    public city?: string,
    public county?: string,
    public warehouses?: IWarehouse[]
  ) {}
}
