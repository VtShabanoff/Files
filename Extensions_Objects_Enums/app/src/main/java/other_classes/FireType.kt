package other_classes

sealed class FireType{
    object SingleTypeShoot: FireType()
    data class BurstShoot(val burstSize: Int): FireType()
}
