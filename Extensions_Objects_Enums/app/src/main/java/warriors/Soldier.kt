package warriors

import other_classes.AbstractWarrior
import other_classes.Weapons

class Soldier: AbstractWarrior(
        maxHealth = 50,
        weapon = Weapons.createPistol(),
        accuracy = 30,
        chanceToAvoidDeath = 20

) {
}