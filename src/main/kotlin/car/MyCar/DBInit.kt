package car.MyCar

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DBInit(val carRepository: CarRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        carRepository.deleteAll()

        val model3 = Car(brand = "Tesla", model = "Model 3", year = 2017, startingPrice = 35000)
        repeat(5) {
            model3.increaseNumberOfSoldCars()
        }

        val modelS = Car(brand = "Tesla", model = "Model S", year = 2012, startingPrice = 80000)
        repeat(2) {
            modelS.increaseNumberOfSoldCars()
        }

        val cybertruck = Car(brand = "Tesla", model = "Cybertruck", year = 2021, startingPrice = 50000)
        val modelY = Car(brand = "Tesla", model = "Model Y", year = 2020, startingPrice = 40000)

        val cars = listOf(model3, modelS, cybertruck, modelY)

        cars.forEach { car ->
            println("${car.model} ${car.year}")
            carRepository.save(car)
        }

        println("Database started")
    }
}