export class Product {
  id: number;
  name: string;
  category: Category;
  price: number;
}

export interface PageProduct {
  content: Product[];
  totalElements: number;
}

export enum Category {
  perishable = 'perishable',
  non_perishable = 'non perishable'
}