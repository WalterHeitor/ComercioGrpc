package com.softwalter.comercio

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.softwalter.comercio")
		.start()
}

