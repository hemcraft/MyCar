package car.MyCar

class UpdateCarRequest {
    var year: Int
    var price: Int
    var sold: Int

    constructor(
            year: Int,
            price: Int,
            sold: Int
    ) {
        this.year = year
        this.price = price
        this.sold = sold
    }
}