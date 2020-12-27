package `interface`

interface Warrior {
    val isKilled: Boolean
    val chanceToAvoidDeath: Int
    val currentHealthLevel: Int

    fun toAttack(enemy: Warrior)
    fun toTakeDamage(damage: Int)
}