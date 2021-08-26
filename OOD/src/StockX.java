public class StockX {
    /*
    Tree structure for ShoeNode
    user flow
    BEHAVIOR:
    Buyer:
    1. Login
    2. ListMostPopularBrands
    3. SearchShoes
    4. ViewShoesDetails
    5. BidShoe
    6. BuyShoe
    7. ViewBuyHistory
    8. CancelBid
    9. CancelOrder
    Seller:
    1. Login
    2. ListMostPopularBrands
    3. SearchShoes
    4. UploadShoesInfo
    5. AddOfferUnderShoes(shoes for bid is a offer): Shoes id
    6. RequestAddBrand(if want to add new brand, verify)


    RELATION:
    ABSTRACT: not suit the abstract class, behavior not the same
    User: id, name, payment, address; List<Order>, List<Offer>
    Shoe: id, name, Brand; color, List<Offer>
    Brand: id, name, description
    Order: id, created_at, status; User buyer, (User seller,) Offer
    Offer: id, ask_price, create_at; size, condition: new, damage_box, used, User Own, Shoe, status

    TB, buyer and seller separate object

    Name of shoes may not the identical. AJ 1 != Aj 1
    Solution: buyer and seller need to search the shoes.
    ShoeNode: has offer1, offer2, ..., bid1, bid2, ...

    most popular size: 7-9. size add to offer
    color: different color to show

    if want to know offer is in which order, then put order in offer
     */
}
