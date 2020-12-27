package main

import other_classes.Battle
import team.Team

fun main() {
    println("Введите количество войнов первой команды")
    val enterTeamOne = readLine()?.toIntOrNull() ?: return
    println("Введите количество войнов второй команды")
    val enterTeamTwo = readLine()?.toIntOrNull() ?: return
    val battle = Battle(Team(enterTeamOne), Team(enterTeamTwo))

    while (!battle.isBattleFinish){
        battle.stepOfBattle()
        println(battle.getBattleState())
    }


}