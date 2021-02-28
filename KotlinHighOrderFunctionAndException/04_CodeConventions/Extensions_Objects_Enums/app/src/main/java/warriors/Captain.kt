package warriors

import other_classes.AbstractWarrior
import other_classes.Weapons

class Captain : AbstractWarrior(
    maxHealth = 70,
    weapon = Weapons.createRifle(),
    accuracy = 50,
    chanceToAvoidDeath = 40
)
