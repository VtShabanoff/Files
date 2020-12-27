package enums

import kotlin.random.Random

enum class Ammo(
    val strike: Int,
    val criticalStrikeChance: Int,
    val criticalStrikeRatio: Int
) {
    PISTOL(
        strike = 2,
        criticalStrikeChance = 10,
        criticalStrikeRatio = 4
    ),
    RIFLE(
        strike = 8,
        criticalStrikeChance = 25,
        criticalStrikeRatio = 18
    ),
    SHOTGUN(
        strike = 20,
        criticalStrikeChance = 35,
        criticalStrikeRatio = 45
    ),
    MACHINE_GUN(
        strike = 15,
        criticalStrikeChance = 28,
        criticalStrikeRatio = 35
    );

    fun getDamage(): Int {
        return if (criticalStrikeChance.isChance()){
            strike * criticalStrikeRatio
        } else {
            strike
        }
    }

}
fun Int.isChance(): Boolean = Random.nextInt(100) < this
