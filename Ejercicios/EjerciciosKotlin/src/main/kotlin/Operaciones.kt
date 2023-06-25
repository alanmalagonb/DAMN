fun main() {
    var option: Int
    do{
        println("- Ejercicios Kotlin -\n" +
            "1. Par o no\n" +
            "2. Tabla de multiplicar\n" +
            "3. Producto mediante sumas\n" +
            "4. Posicion del mayor valor\n" +
            "5. Suma de vectores\n" +
            "6. Media\n" +
            "7. Ordenar numeros\n" +
            "8. Longitud, mayusculas y minusculas\n" +
            "9. Salir\n" +
            "Elija una opcion:")

        option = readln().toInt()

        when(option){
            1 -> esPar()
            2 -> tablaDeMultiplicar()
            3 -> productoMedianteSumas()
            4 -> posicionMayorValor()
            5 -> sumaVectores()
            6 -> media()
            7 -> ordenarNumeros()
            8 -> LongMayusMinus()
            9 -> continue
            else -> {
                print("No existe esa opcion\n")
            }
        }

        println("Presione una tecla para continuar...\n")
        readln();

    } while(option != 9)
}

// Leer un numero y mostrar si dicho numero es par o no es par

fun esPar() {
    val number = leerNumero()

    if(number % 2 == 0)
        println("$number es par.")
    else
        println("$number no es par.")
}

// Leer un numero y mostrar su tabla de multiplicar

fun tablaDeMultiplicar() {
    val number = leerNumero()

    for(i in 1..10){
        println("$number x $i = ${number*i}")
    }
}

// Leer dos numeros y realizar el producto mediante sumas

fun productoMedianteSumas() {
    val number = leerNumero()
    val number2 = leerNumero()
    var producto = 0

    for(i in 1..number2){
        producto += number;
    }
    println("Producto: $producto")
}

/* Leer una secuencia de n numeros almacenarlos en un arreglo y
   mostrar la posicion donde se encuentre el mayor valor leido
*/

fun posicionMayorValor(){
    var numbers = arrayOf<Int>()
    var mayor = 0

    println("Ingrese numeros. (Vacio para detener)")
    while (true) {
        val siguiente = readlnOrNull().takeUnless {
            it.isNullOrEmpty()
        } ?: break
        if(numbers.contains(siguiente.toInt())){
            println("Ya ingresaste este numero")
            continue
        }
        if(siguiente.toInt() >= mayor) mayor = siguiente.toInt()
        numbers += siguiente.toInt()
    }
    println("Mayor : $mayor , Posicion : ${numbers.indexOf(mayor)}")
}

/* Dado dos vectores A y B de n elementos cada uno,
   obtener un arreglo C donde la posicion i
   se almacena la suma de A[i]+B[i]
*/

fun sumaVectores(){
    println("Ingrese el tamaño de los vectores:")
    val size = leerNumero();

    println("Vector A\n")
    val vectorA = leerArreglo(size)
    print("Vector B\n")
    val vectorB = leerArreglo(size)
    val vectorC = DoubleArray(size)

    for (i in 0 until size){
        vectorC[i] = vectorA[i]+vectorB[i]
    }
    println("Vector C: ${vectorC.contentToString()}");
}

// Calcular la media de una secuencia de numeros proporcionados por  el usuario

fun media(){
    val numeros = leerArreglo()
    val media = numeros.sum() / numeros.size
    println("Media : $media")
}

/* Dada una secuencia de numeros leidos y almacenados en un arreglo A
   mostrar dichos números en orden
 */

fun ordenarNumeros(){
    val numeros = leerNumeros()
    numeros.sort()
    println("Numeros Ordenados: ${numeros.contentToString()}")
}

/* Dado una secuencia de textos proporcionados por el usuario visualizar
   la longitud, en mayusculas, en minusculas, cada uno de ellas.
*/

fun LongMayusMinus(){
    val textos = leerCadenas();
    textos.forEach {
        println("Longitud: ${it.length}, Mayusculas: ${it.uppercase()}, Minusculas: ${it.lowercase()}")
    }
}

