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
        val car = carRepository.findByModel(model)
        return car
    }

    @GetMapping("/brand/{brand}")
    fun getCarsByBrand(@PathVariable(value = "brand") brand: String): List<Car> {
        val cars = carRepository.findByBrand(brand)
        return cars
    }

    @GetMapping("/year/{year}")
    fun getCarsByYear(@PathVariable(value = "year") year: Int): List<Car> {
        val cars = carRepository.findByYear(year)
        return cars
    }

    @GetMapping("/price/model/{model}")
    fun getPriceByModel(@PathVariable(value = "model") model: String): Int {
        val price = carRepository.findByModel(model).price
        return price
    }

    @GetMapping("/sold/model/{model}")
    fun getNumberOfSoldCarsByModel(@PathVariable(value = "model") model: String): Int {
        val sold = carRepository.findByModel(model).sold
        return sold
    }

    @GetMapping("/price/brand/{brand}")
    fun getAveragePriceByBrand(@PathVariable(value = "brand") brand: String): Double {
        val averagePrice = carRepository.findAverageByBrand(brand)
        return averagePrice
    }

    @GetMapping("/sold/brand/{brand}")
    fun getAverageNumberOfSoldCarsByBrand(@PathVariable(value = "brand") brand: String): Double {
        val average = carRepository.findAverageNumberOfSoldCarsByBrand(brand)
        return average
    }

    @PostMapping("/new")
    fun createNewCar(@RequestBody createCarRequest: CreateCarRequest){
        val car = Car(
                brand = createCarRequest.brand,
                model = createCarRequest.model,
                year = createCarRequest.year,
                startingPrice = createCarRequest.price)
        carRepository.save(car)
    }

    @DeleteMapping("/{model}")
    fun deleteCar(@PathVariable(value = "model") model: String) {
        carRepository.delete(carRepository.findByModel(model))
    }

    @PutMapping("/{model}")
    fun updateCar(@PathVariable(value = "model") model: String, @RequestBody updateCarRequest: UpdateCarRequest) {
        var car = carRepository.findByModel(model)

        if (updateCarRequest.year != 0) {
            car.year = updateCarRequest.year
        }

        if (updateCarRequest.price != 0) {
            car.price = updateCarRequest.price
        }

        if (updateCarRequest.sold != 0) {
            car.sold = updateCarRequest.sold
        }

        carRepository.save(car)
    }

    @PutMapping("/sold/{model}")
    fun updateOneSoldCar(@PathVariable(value = "model") model: String) {
        var car = carRepository.findByModel(model)
        car.increaseNumberOfSoldCars()

        carRepository.save(car)
    }

}