// Leer un numero por consola

fun leerNumero(): Int{
    println("Ingrese un numero:")
    return readln().toInt()
}

// Leer arreglo de Ints por consola

fun leerNumeros(): Array<Int>{
    var numbers = arrayOf<Int>()
    println("Ingrese numero. (Vacio para detener)")
    while (true) {
        val siguiente = readlnOrNull().takeUnless {
            it.isNullOrEmpty()
        } ?: break
        numbers += siguiente.toInt()
    }
    return numbers
}

// Leer arreglo de doubles por consola

fun leerArreglo(): Array<Double>{
    var numbers = arrayOf<Double>()
    println("Ingrese numero. (Vacio para detener)")
    while (true) {
        val siguiente = readlnOrNull().takeUnless {
            it.isNullOrEmpty()
        } ?: break
        numbers += siguiente.toDouble()
    }
    return numbers
}

// Leer arreglo de tamano size por consola

fun leerArreglo(size: Int): Array<Double>{
    println("Ingrese numero.")
    var arreglo = arrayOf<Double>()

    var arraySize = size;
    while (arraySize > 0) {
        val siguiente = readlnOrNull().takeUnless {
            it.isNullOrEmpty()
        } ?: break
        arreglo += siguiente.toDouble()
        arraySize--
    }
    return arreglo
}

// Leer arreglo de Strings por consola

fun leerCadenas(): Array<String>{
    var cadenas = arrayOf<String>()
    println("Ingrese texto. (Vacio para detener)")
    while (true) {
        val siguiente = readlnOrNull().takeUnless {
            it.isNullOrEmpty()
        } ?: break
        cadenas += siguiente
    }
    return cadenas
}