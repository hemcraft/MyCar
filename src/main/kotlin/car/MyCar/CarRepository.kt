package car.MyCar

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository : CrudRepository<Car, Long>{
    fun findByModel(model: String): Car

    fun findByBrand(brand: String): List<Car>

    fun findByYear(year: Int): List<Car>

    @Query("SELECT AVG(c.price) from Car c where c.brand = ?1 group by c.brand")
    fun findAverageByBrand(brand: String): Double

    @Query("SELECT AVG(c.sold) from Car c where c.brand = ?1 group by c.brand")
    fun findAverageNumberOfSoldCarsByBrand(brand: String): Double
}