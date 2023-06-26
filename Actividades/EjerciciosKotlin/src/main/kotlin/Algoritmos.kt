fun main() {
    var option: Int
    do{
        println("- Ejercicios Kotlin -\n" +
                "1. Bubble Sort\n" +
                "2. Insertion Sort\n" +
                "3. Selection Sort\n" +
                "4. Salir\n")

        option = readln().toInt()

        when(option){
            1 -> bubbleSort()
            2 -> insertionSort()
            3 -> selectionSort()
            4 -> continue
            else -> {
                print("No existe esa opcion\n")
            }
        }

        println("Presione una tecla para continuar...\n")
        readln();

    } while(option != 4)
}

fun bubbleSort(){
    val arreglo = leerNumeros();
    val len = arreglo.size-1
    for (i in 0 until len) {
        for (j in 0 until len) {
            if (arreglo[j] > arreglo[j + 1]) {
                val tmp = arreglo[j]
                arreglo[j] = arreglo[j + 1]
                arreglo[j + 1] = tmp
            }
        }
    }
    println(arreglo.contentToString())
}

fun insertionSort(){
    val arreglo = leerNumeros();
    val n = arreglo.size
    for (i in 1 until n) {
        val current = arreglo[i]
        var j = i
        while (j > 0 && current < arreglo[j-1]) {
            arreglo[j] = arreglo[j-1]
            j--
        }
        arreglo[j] = current
    }
    println(arreglo.contentToString())
}

fun selectionSort(){
    val arreglo = leerNumeros();
    val n = arreglo.size
    for (i in 0 until n) {
        var min = i
        for (j in i + 1 until n) {
            if (arreglo[j] < arreglo[min]) {
                min = j
            }
        }
        if (min != i) {
            val tmp = arreglo[i]
            arreglo[i] = arreglo[min]
            arreglo[min] = tmp
        }
    }
    println(arreglo.contentToString())
}
