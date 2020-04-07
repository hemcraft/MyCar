package car.MyCar

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyCarApplication

fun main(args: Array<String>) {
	runApplication<MyCarApplication>(*args)
}
