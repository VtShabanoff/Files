package warriors

import other_classes.AbstractWarrior
import other_classes.Weapons

class General: AbstractWarrior(
        maxHealth = 100,
        weapon = Weapons.createShotgun(),
        accuracy = 80,
        chanceToAvoidDeath = 60
) {
}