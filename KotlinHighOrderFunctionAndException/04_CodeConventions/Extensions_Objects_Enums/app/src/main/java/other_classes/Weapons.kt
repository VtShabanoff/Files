package other_classes

import enums.Ammo

object Weapons {

    fun createPistol(): AbstractWeapon {
        return object : AbstractWeapon(12, FireType.SingleTypeShoot) {
            override fun createRound(): Ammo {
                return Ammo.PISTOL
            }
        }
    }

    fun createRifle(): AbstractWeapon {
        return object : AbstractWeapon(30, FireType.SingleTypeShoot) {
            override fun createRound(): Ammo {
                return Ammo.RIFLE
            }
        }
    }

    fun createShotgun(): AbstractWeapon {
        return object : AbstractWeapon(20, FireType.SingleTypeShoot) {
            override fun createRound(): Ammo {
                return Ammo.SHOTGUN
            }
        }
    }

    fun createMachineGun(): AbstractWeapon {
        return object : AbstractWeapon(30, FireType.BurstShoot(3)) {
            override fun createRound(): Ammo {
                return Ammo.MACHINE_GUN
            }
        }
    }
}
