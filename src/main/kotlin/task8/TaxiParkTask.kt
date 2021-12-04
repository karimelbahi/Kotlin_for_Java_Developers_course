package main.kotlin.task8

import test.kotlin.taxipark.passenger
import test.kotlin.taxipark.trip
import kotlin.test.asserter

/*
 * Task #1. Find all the drivers who performed no trips.
 */
fun TaxiPark.findFakeDrivers(): Set<Driver> {

    // get all drivers with trips
    val tripsDrivers = this.trips.map { it.driver }
    // get all drivers without any trips
    return this.allDrivers.filter { it !in tripsDrivers }.toSet()

    /*
    * another way
    *     return allDrivers.filter { d->trips.none { it.driver==d } }.toSet()

    * */

    /*

    * another way optimum solution
    *     return allDrivers-trips.map { it.driver }
    * */
}

/*
 * Task #2. Find all the clients who completed at least the given number of trips.
 */
fun TaxiPark.findFaithfulPassengers(minTrips: Int): Set<Passenger> {

    if (minTrips == 0) return this.allPassengers

    return this.trips
        .flatMap { it.passengers }                  //get all passengers
        .groupingBy { it }                          // grouping passengers with their trips
        .eachCount()                                //count trips for each passengers
        .filter { (passName, tripsNum) -> tripsNum >= minTrips }.keys     //return only keys

    /*another sol
    return  trips.flatMap(Trip::passengers)
        .groupBy { passenger -> passenger }
        .filterValues { group -> group.size >= minTrips }
        .keys
           */
    /*another sol
      return allPassengers
        .filter { p ->
            trips.count { p in it.passengers } >= minTrips
        }
        .toSet()
    * */


}


/*
 * Task #3. Find all the passengers, who were taken by a given driver more than once.
 */
fun TaxiPark.findFrequentPassengers(driver: Driver): Set<Passenger> {

    return this.trips
        .filter { trip -> trip.driver == driver }  //filter trips by driver name
        .flatMap { it.passengers }                 // map trip to passenger we did not use map because it return set of passenger
        .groupingBy { it }                         // grouping passengers name together [{karim,karim},{ali,ali}]
        .eachCount()                               //count number of trips for each passenger
        .filter { (_, count) -> count > 1 }.keys


}


/*
 * Task #4. Find the passengers who had a discount for majority of their trips.
 */
fun TaxiPark.findSmartPassengers(): Set<Passenger> {

    // get num of discount for the passenger
    fun discount(p: Passenger): Int {
        return trips
            .filter { it.passengers.contains(p) && it.discount != null && it.discount > 0.0 }
            .count()
    }

    // get num of not discount for the passenger
    fun nonDiscount(p: Passenger): Int {
        return trips
            .filter { it.passengers.contains(p) && it.discount == null || it.discount == 0.0 }
            .count()
    }
    // filter to get passengers with majority of discounts
    return allPassengers.filter { p -> discount(p) > nonDiscount(p) }.toSet()


    /*another sol
    val (tripsWithDiscount, tripsWithoutDiscount) = trips.partition { it.discount != null }
    return allPassengers.filter { passenger ->
    tripsWithDiscount.count { passenger in it.passengers } > tripsWithoutDiscount.count { passenger in it.passengers }
    }.toSet()
    */

    /*another sol
    *
    * return allPassengers.associate { p ->
        p to trips.filter { t -> p in t.passengers }
    }
        .filterValues { group ->
            val (withDiscount, withoutDiscount) = group
                .partition { it.discount != null }
            withDiscount.size > withoutDiscount.size
        }
        .keys
        * */


}

/*
 * Task #5. Find the most frequent trip duration among minute periods 0..9, 10..19, 20..29, and so on.
 * Return any period if many are the most frequent, return `null` if there're no trips.
 */
fun TaxiPark.findTheMostFrequentTripDurationPeriod(): IntRange? {
    val mamRange = this.trips
        .map { it.duration / 10 }
        .groupingBy { it }
        .eachCount()
        .maxByOrNull { it.value }?.key


    return if (mamRange != null) {
        (mamRange * 10)..(mamRange * 10) + 9
    } else {
        null
    }

    /*
    *     return this.trips
        .map { it.duration / 10 }
        .groupingBy { it }
        .eachCount()
        .map { (it.key * 10)..(it.key * 10) + 9 }
//        .max(it)
        .maxBy { it.first}*/


    /*another sol
    return trips
        .groupBy {
            val start = it.duration / 10 * 10
            val end = start + 9
            start..end
        }
        .toList()
        .maxByOrNull { (_, group) -> group.size }
        ?.first

     */


}

/*
 * Task #6.
 * Check whether 20% of the drivers contribute 80% of the income.
 */
fun TaxiPark.checkParetoPrinciple(): Boolean {

    fun findTripByDriver(d: Driver): List<Trip> {
        return trips.filter { trip -> trip.driver == d }
    }

    fun findTwentyPercentDrivers(): Int {
        return (allDrivers.count() * 0.2).toInt()
    }

    fun calculateAllIncome(): Double {
        return trips
            .map { t -> t.cost }
            .sum()
    }

    fun calculateTwentyPercentIncome(): Double {
        return allDrivers
            .map { d ->
                findTripByDriver(d)
                    .map { it.cost }
                    .sum()
            }
            .sortedDescending()
            .take(findTwentyPercentDrivers())  // equal .subList(0, findTwentyPercentDrivers())

            .sum()
    }

    fun calculateRelativeIncome(): Double {
        return calculateTwentyPercentIncome() / calculateAllIncome()
    }

    return (calculateRelativeIncome() >= 0.8)
}