import { IOrder } from 'app/shared/model/order.model';

export interface ICustomer {
  id?: number;
  name?: string;
  address?: string;
  phoneNumber?: number;
  email?: string;
  orders?: IOrder[];
}

export class Customer implements ICustomer {
  constructor(
    public id?: number,
    public name?: string,
    public address?: string,
    public phoneNumber?: number,
    public email?: string,
    public orders?: IOrder[]
  ) {}
}
