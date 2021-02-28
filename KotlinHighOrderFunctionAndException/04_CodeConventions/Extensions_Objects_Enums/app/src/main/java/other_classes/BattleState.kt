package other_classes

import team.Team

sealed class BattleState {
    class Progress(teamOne: Team, teamTwo: Team) : BattleState() {
        private val teamOneSumHealth = teamOne.listWarriors.sumBy { it.currentHealthLevel }
        private val teamTwoSumHealth = teamTwo.listWarriors.sumBy { it.currentHealthLevel }
        override fun toString(): String {
            return "Progress(teamOneSumHealth=$teamOneSumHealth, teamTwoSumHealth=$teamTwoSumHealth)"
        }
    }

    class TeamOneWin : BattleState() {
        override fun equals(other: Any?): Boolean {
            return this === other
        }

        override fun hashCode(): Int {
            return System.identityHashCode(this)
        }
    }

    class TeamTwoWin : BattleState() {
        override fun equals(other: Any?): Boolean {
            return this === other
        }

        override fun hashCode(): Int {
            return System.identityHashCode(this)
        }
    }

    class Draw : BattleState() {
        override fun equals(other: Any?): Boolean {
            return this === other
        }

        override fun hashCode(): Int {
            return System.identityHashCode(this)
        }
    }
}
