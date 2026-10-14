sealed class CoffeeMachineState {
        class Idle(val timestamp: Long = System.currentTimeMillis()) : CoffeeMachineState(){
            init{
                println("Estoy en la función inicial del estado IDLE")
            }
        }
        object MakingCoffee : CoffeeMachineState()
        object ServingCoffee : CoffeeMachineState()
        data class Error(val message: String) : CoffeeMachineState()
        object RechargingWater : CoffeeMachineState()
        object AddingCups : CoffeeMachineState()

    }
