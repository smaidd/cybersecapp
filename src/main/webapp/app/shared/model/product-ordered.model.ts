export interface IProductOrdered {
  id?: number;
  quantity?: number;
  shippingPrice?: number;
  productId?: number;
  sellingCompanyId?: number;
  orderId?: number;
}

export class ProductOrdered implements IProductOrdered {
  constructor(
    public id?: number,
    public quantity?: number,
    public shippingPrice?: number,
    public productId?: number,
    public sellingCompanyId?: number,
    public orderId?: number
  ) {}
}
