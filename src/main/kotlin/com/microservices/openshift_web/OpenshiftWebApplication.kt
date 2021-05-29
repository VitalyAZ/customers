package com.microservices.openshift_web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class OpenshiftWebApplication

fun main(args: Array<String>) {
	runApplication<OpenshiftWebApplication>(*args)

	println()
	println("\thttp://localhost:8081/customers")
	println()
}

class Customer(val id: Int, val name: String)

@RestController
class CustomerController {
	companion object {
		private val initialCustomers = arrayOf(Customer(1, "Kotlin"),
			Customer(2, "Spring"),
			Customer(3, "Microservice"))
		val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy(Customer::id))
	}

	@GetMapping("/customers")
	fun getCustomers() = customers.values.toList()

	@GetMapping("/customer/{id}")
	fun getCustomer(@PathVariable id: Int) = customers[id]
}