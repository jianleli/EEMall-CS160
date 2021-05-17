import { ShopCartItem } from "./shop-cart-item";

export class OrderItem {
    imageUrl: string;
    unitPrice: number;
    quantity: number;
    productId: string;

    constructor(shopCartItem: ShopCartItem) {
        this.imageUrl = shopCartItem.imageUrl;
        this.quantity = shopCartItem.quantity;
        this.unitPrice = shopCartItem.unitPrice;
        this.productId = shopCartItem.id;
    }
}
