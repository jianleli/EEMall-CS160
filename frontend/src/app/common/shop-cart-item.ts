import { Laptop } from "./laptop";

export class ShopCartItem {
    id: string;
    name: string;
    imageUrl: string;
    unitPrice: number;
    quantity: number;

    constructor(laptop: Laptop){
        this.id = laptop.id;
        this.name = laptop.name;
        this.imageUrl = laptop.imageUrl;
        this.unitPrice = laptop.unitPrice;

        this.quantity = 1;
    }
}
