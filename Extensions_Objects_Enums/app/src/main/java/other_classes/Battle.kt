package other_classes

import team.Team
import kotlin.math.min

class Battle(private val teamOne: Team, private val teamTwo: Team) {
    private val teamOneIsKilled
        get() = teamOne.listWarriors.all { it.isKilled }
    private val teamTwoIsKilled
        get() = teamTwo.listWarriors.all { it.isKilled }

    val isBattleFinish: Boolean
        get() =  teamOneIsKilled || teamTwoIsKilled


    fun getBattleState(): BattleState{
        return when{
            teamOneIsKilled && teamTwoIsKilled -> BattleState.Draw()
            teamOneIsKilled -> BattleState.TeamTwoWin()
            teamTwoIsKilled -> BattleState.TeamOneWin()
            else -> BattleState.Progress(teamOne, teamTwo)
        }
    }

    fun stepOfBattle(){
        val sizeTeam = min(teamOne.listWarriors.size, teamTwo.listWarriors.size)
        if (!teamOneIsKilled && !teamTwoIsKilled){
            val shuffledTeamOne = teamOne.listWarriors.shuffled()
            val shuffledTeamTwo = teamTwo.listWarriors.shuffled()

            for (i in 0 until sizeTeam){
                if (!shuffledTeamOne[i].isKilled && !shuffledTeamTwo[i].isKilled)
                    shuffledTeamOne[i].toAttack(shuffledTeamTwo[i])
                if (!shuffledTeamTwo[i].isKilled && !shuffledTeamOne[i].isKilled)
                    shuffledTeamTwo[i].toAttack(shuffledTeamOne[i])
            }
        }
    }
}