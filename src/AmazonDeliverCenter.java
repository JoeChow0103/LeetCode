public class AmazonDeliverCenter {
    /*
    BEHAVIOR
    Carrier:
    1. pickUP the package
    2. send information of the package
    2.1 update information
    3. record the package info
    4. drive the package to customer
    Customer (may not need, positively receive the delivery)
    1. receive the message of product
    2. receive the message of delivery

    OBJECT:
    Carrier: id, name
    Order: id, receipt, address, item_id, e-mail, carrier_id, status: (shipping,delivering, delivered)
    Truck: id, name
    CarrierTrack: carrier_id, truck_id, startDate, endDate, location, arrive_at
     */
}
