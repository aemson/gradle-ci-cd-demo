package com.lambda

import arrow.core.Either
import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.LambdaLogger
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.service.CalculatorInputs
import com.service.add
import com.service.calculate
import com.service.sub
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.nio.charset.Charset

class Calculator : RequestStreamHandler {
    private var gson: Gson = GsonBuilder().setPrettyPrinting().create()

    override fun handleRequest(input: InputStream?, output: OutputStream?, context: Context?) {
        val logger: LambdaLogger? = context?.logger
        Either.catch {
            val reader = BufferedReader(InputStreamReader(input!!, Charset.forName("US-ASCII")))
            val writer = PrintWriter(BufferedWriter(OutputStreamWriter(output!!, Charset.forName("US-ASCII"))))
            val calculatorInputs = gson.fromJson(reader, CalculatorInputs::class.java)
            logger?.log("inputs = $calculatorInputs")
            val result = doCalculations(calculatorInputs)
            writer.write(gson.toJson(result))
            if (writer.checkError()) logger?.log("WARNING: Writer encountered an error.")
        }.mapLeft { logger?.log(it.message) }
    }
}

fun doCalculations(calculatorInputs: CalculatorInputs) = calculate(calculatorInputs.num1, calculatorInputs.num2, sub)
    /*when (calculatorInputs.operation) {
        "plus" -> calculate(calculatorInputs.num1, calculatorInputs.num2, add)
        "minus" -> calculate(calculatorInputs.num1, calculatorInputs.num2, sub)
        else -> "Invalid operation"
    }*/
