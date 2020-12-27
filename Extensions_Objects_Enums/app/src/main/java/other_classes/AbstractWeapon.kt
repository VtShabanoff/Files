package other_classes

import enums.Ammo

abstract class AbstractWeapon(
        private val maxNumbersOfRounds: Int,
        private val fireType: FireType,
) {

    private var listRounds: List<Ammo> = listOf()
    val isAvailabilityOfRounds: Boolean
        get() = listRounds.isNotEmpty()

    abstract fun createRound(): Ammo

    fun reloadingWeapons(){
        val newList = mutableListOf<Ammo>()
        for (i in 0..maxNumbersOfRounds){
            newList.add(createRound())
        }
        listRounds = newList
    }
    fun getRoundsForShot(): List<Ammo> {
        val result: List<Ammo>
        if (fireType is FireType.BurstShoot) {
           result = listRounds.take(fireType.burstSize)
            listRounds = listRounds.drop(fireType.burstSize)
        } else {
            result = listRounds.take(1)
            listRounds = listRounds.drop(1)
        }
        return result
    }
}