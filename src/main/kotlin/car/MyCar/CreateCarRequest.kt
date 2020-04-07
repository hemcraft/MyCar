package car.MyCar

class CreateCarRequest {
    var brand: String
    var model: String
    var year: Int
    var price: Int

    constructor(
            brand: String,
            model: String,
            year: Int,
            price: Int
    ) {
        this.brand = brand
        this.model = model
        this.year = year
        this.price = price
    }
}