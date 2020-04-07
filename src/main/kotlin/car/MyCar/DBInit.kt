package car.MyCar

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class DBInit(val carRepository: CarRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        carRepository.deleteAll()

        val model3 = Car("Tesla", "Model 3", 2017, 35000)
        repeat(5) {
            model3.increaseNumberOfSoldCars()
        }

        val modelS = Car("Tesla", "Model S", 2012, 80000)
        repeat(2) {
            modelS.increaseNumberOfSoldCars()
        }

        val cybertruck = Car("Tesla", "Cybertruck", 2021, 50000)
        val modelY = Car("Tesla", "Model Y", 2020, 40000)

        val cars = mutableListOf<Car>()
        cars.add(model3)
        cars.add(modelS)
        cars.add(cybertruck)
        cars.add(modelY)

        cars.forEach { car ->
            println("${car.model} ${car.year}")
            carRepository.save(car)
        }

        println("Database started")
    }
}