package other_classes

import `interface`.Warrior
import enums.isChance

abstract class AbstractWarrior(
        maxHealth: Int,
        override val chanceToAvoidDeath: Int,// шанс избежать попадания
        private val accuracy: Int,//точность война
        private val weapon: AbstractWeapon
        ): Warrior {

    final override var currentHealthLevel: Int = maxHealth
        private set(value) {
            field = if (value < 0) 0 else value
        }

    override val isKilled: Boolean
        get() = currentHealthLevel <= 0

    override fun toTakeDamage(damage: Int) {
        currentHealthLevel -= damage
    }

    override fun toAttack(enemy: Warrior) {
        if (!weapon.isAvailabilityOfRounds){
            weapon.reloadingWeapons()
            return
        }else{
            weapon.getRoundsForShot()
        }

        val sumDamage = weapon.getRoundsForShot().filter { accuracy.isChance() && !enemy.chanceToAvoidDeath.isChance() }
                .sumBy { it.getDamage() }

        enemy.toTakeDamage(sumDamage)
    }
}