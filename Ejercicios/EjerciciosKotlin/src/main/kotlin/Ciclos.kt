fun main() {
    var option: Int
    do{
        println("- Ejercicios Kotlin -\n" +
                "1. Pares For\n" +
                "2. Sumatoria For\n" +
                "3. Pares While\n" +
                "4. Sumatoria While\n" +
                "5. Pares DoWhile\n" +
                "6. Sumatoria DoWhile\n" +
                "7. Salir\n")

        option = readln().toInt()

        when(option){
            1 -> paresFor()
            2 -> sumatoriaFor()
            3 -> paresWhile()
            4 -> sumatoriaWhile()
            5 -> paresDoWhile()
            6 -> sumatoriaDoWhile()
            7 -> continue
            else -> {
                print("No existe esa opcion\n")
            }
        }

        println("Presione una tecla para continuar...\n")
        readln();

    } while(option != 7)
}

fun sumatoriaDoWhile() {
    var suma = 0;

    var i=1
    do{
        suma+=i;
        i++;
    } while(i<=100);

    println("Suma : $suma")
}

fun paresDoWhile() {
    var pares = "";

    var i=0;
    do{
        if(i%2==0){
            pares+=i.toString()+" ";
        }
        i++;
    } while(i<=100);
    println("Pares : $pares")
}

fun sumatoriaWhile() {
    var suma = 0;

    var i=1;
    while(i<=100){
        suma+=i;
        i++;
    }
    println("Suma : $suma")
}

fun paresWhile() {
    var pares = "";
    var i=0;
    while(i<=100){
        if(i%2==0){
            pares+= "$i ";
        }
        i++;
    }
    println("Pares : $pares")
}

fun sumatoriaFor() {
    var suma = 0

    for (i in 0..100) {
        suma += i
    }
    println("Suma : $suma")
}

fun paresFor() {
    var pares = ""

    for (i in 0..100 step 2) {
            pares += "$i "
    }
    println("Pares : $pares")
}
