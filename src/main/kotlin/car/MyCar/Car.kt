package car.MyCar

import javax.persistence.*

@Entity
@Table(name = "CAR")
class Car(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,
        val brand: String,
        val model: String,
        var year: Int,
        var startingPrice: Int,
        var sold: Int = 0, 
        var price: Int = startingPrice) {

    fun increaseNumberOfSoldCars(){
        sold++
    }
}