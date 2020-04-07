package car.MyCar

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cars")
class CarController(val carRepository: CarRepository) {

    @GetMapping("/all")
    fun getAllCars(): MutableIterable<Car>{
        return carRepository.findAll()
    }

    @GetMapping("/model/{model}")
    fun getCarByModel(@PathVariable(value = "model") model: String): Car {
        return getSingleCarByModel(model = model)
    }

    @GetMapping("/brand/{brand}")
    fun getCarsByBrand(@PathVariable(value = "brand") brand: String): List<Car> {
        return getAllCars().filter { it.brand == brand }
    }

    @GetMapping("/year/{year}")
    fun getCarsByYear(@PathVariable(value = "year") year: Int): List<Car> {
        return getAllCars().filter { it.year == year }
    }

    @GetMapping("/price/model/{model}")
    fun getPriceByModel(@PathVariable(value = "model") model: String): Int {
        return getSingleCarByModel(model = model).price
    }

    @GetMapping("/sold/model/{model}")
    fun getNumberOfSoldCarsByModel(@PathVariable(value = "model") model: String): Int {
        return getSingleCarByModel(model = model).sold
    }

    @GetMapping("/price/brand/{brand}")
    fun getAveragePriceByBrand(@PathVariable(value = "brand") brand: String): Double {
        val cars = getAllCars().filter { it.brand == brand }
        val priceArray = cars.map { it.price.toDouble() }

        return priceArray.average()
    }

    @GetMapping("/sold/brand/{brand}")
    fun getAverageNumberOfSoldCarsByBrand(@PathVariable(value = "brand") brand: String): Double {
        val cars = getAllCars().filter { it.brand == brand }
        val soldArray = cars.map { it.sold.toDouble() }

        return soldArray.average()
    }

    @PostMapping("/new")
    fun createNewCar(@RequestBody createCarRequest: CreateCarRequest){
        carRepository.save(Car(
                brand = createCarRequest.brand,
                model = createCarRequest.model,
                year = createCarRequest.year,
                startingPrice = createCarRequest.price))
    }

    @DeleteMapping("/{model}")
    fun deleteCar(@PathVariable(value = "model") model: String): String {
        carRepository.delete(getSingleCarByModel(model = model))
        return "Car deleted"
    }

    @PutMapping("/{model}")
    fun updateCar(@PathVariable(value = "model") model: String, @RequestBody updateCarRequest: UpdateCarRequest): String {
        val car = getSingleCarByModel(model = model)

        if (updateCarRequest.year != null) {
            if(updateCarRequest.year > 1900 && updateCarRequest.year < 2030)
                car.year = updateCarRequest.year
            else
                return "Error, wrong year(1900 < year < 2030)"
        }

        if (updateCarRequest.price != null) {
            car.price = updateCarRequest.price
        }

        if (updateCarRequest.sold != null) {
            if(updateCarRequest.sold >= 0)
                car.sold = updateCarRequest.sold
            else
                return "Error, sold cars can not be below 0"
        }

        carRepository.save(car)
        return "Update Success"
    }

    @PutMapping("/sold/{model}")
    fun updateOneSoldCar(@PathVariable(value = "model") model: String) {
        val car = getSingleCarByModel(model = model)
        car.increaseNumberOfSoldCars()

        carRepository.save(car)
    }

    fun getSingleCarByModel(model: String): Car{
        return getAllCars().first { it.model == model }
    }

}