export class Product {
  id: number;
  name: string;
  category: string;
  price: number;
}

export interface PageProduct {
  content: Product[];
  totalElements: number;
}
