package car.MyCar

import javax.persistence.*

@Entity
@Table(name = "CAR")
class Car(val brand: String, val model: String, var year: Int, var startingPrice: Int) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var price: Int = startingPrice
    var sold: Int = 0

    fun increaseNumberOfSoldCars(){
        sold = sold + 1
    }
}