import CoffeeMachineState.*


object CoffeeMachine {
    private var currentState: CoffeeMachineState = CoffeeMachineState.Idle()
    private var monedero: Int = 10
    private var agua: Int = 0
    private var cup: Boolean = false
    private var coffee: Int = 0


    enum class CoffeeType(val tipo: String, val precio: Int) {

        Cortado("Cortado", 2,5),
        Manchado("Manchado", 2.25),
        Solo("Solo", )

    }

    fun clean() {
        println("Limpiando la máquina...")
        currentState = CoffeeMachineState.Idle()
        println("Máquina limpia. Estado: $currentState")
    }


    fun makeCoffee() {
        println("Estado actual: $currentState")

        when (currentState) {
            is CoffeeMachineState.Idle if monedero > 0-> {
                println("Máquina encendida. Empezando a hacer café...")
                currentState = MakingCoffee
                Thread.sleep(2000)
            }

            is CoffeeMachineState.MakingCoffee if agua < 10 -> {
                println("¡Espera! La máquina no tiene agua.")
                currentState = RechargingWater

            }
            is CoffeeMachineState.ServingCoffee if !cup -> {
                println("No hay vaso añadiendo vaso .")
                currentState = AddingCups

            }
            is CoffeeMachineState.Error -> {
                println("La máquina tiene un error: ${(currentState as CoffeeMachineState.Error).message}")
            }

            is CoffeeMachineState.AddingCups ->{
                println("Añadiendo vasos")
                Thread.sleep(2000)
                cup = true
                currentState = ServingCoffee

            }
            is CoffeeMachineState.RechargingWater ->{

                println("Recargando agua... ")

                Thread.sleep(2000)

                agua = 100

                currentState = MakingCoffee

            }

            is CoffeeMachineState.Idle ->{

            println("Estado actual: $currentState a la espera de monedas")

            }

            is CoffeeMachineState.MakingCoffee-> {

                println("Preparando cafe...")
                currentState = ServingCoffee


            }


            is CoffeeMachineState.ServingCoffee -> {

                Thread.sleep(1000)

                println("Cafe servido coja su vaso")

                Thread.sleep(5000)

                println("Muchas gracias")

                coffee++
                monedero--
                agua -= 10
                cup = false

                clean()
            }

    }


}
}

