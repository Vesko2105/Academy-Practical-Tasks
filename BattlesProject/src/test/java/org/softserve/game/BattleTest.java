package org.softserve.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.softserve.game.units.*;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Rookie extends Warrior {
    public Rookie(int health){
        super(health);
    }

    @Override
    public int getAttack() {
        return 1;
    }
}

class BattleTest {
    @Test
    @DisplayName("1 on 1 battles")
    void battlesSimpleTests() {
        //Arrange section
        Unit chuck = new Warrior();
        Unit bruce = new Warrior();
        Unit carl = new Knight();
        Unit dave = new Warrior();

        //Action section
        boolean battleWarWar = Battle.fight(chuck, bruce);
        boolean battleWarKni = Battle.fight(dave, carl);
        boolean chuckAlive = chuck.isAlive();
        boolean bruceAlive = bruce.isAlive();
        boolean daveAlive = dave.isAlive();
        boolean carlAlive = carl.isAlive();

        //Assertion section
        assertTrue(battleWarWar);
        assertFalse(battleWarKni);
        assertTrue(chuckAlive);
        assertFalse(bruceAlive);
        assertFalse(daveAlive);
        assertTrue(carlAlive);
    }

    @Test
    @DisplayName("Defence smoke test")
    void defenderSmokeTest() {

        //Arrange section
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();

        var myArmy = new Army();
        myArmy.addUnits(new Supplier[]{Defender::new}, new int[]{1});
        var enemyArmy = new Army();
        enemyArmy.addUnits(new Supplier[]{Warrior::new}, new int[]{2});
        var myArmy2 = new Army();
        myArmy2.addUnits(new Supplier[]{Warrior::new, Defender::new}, new int[]{1, 1});
        var enemyArmy2 = new Army();
        enemyArmy2.addUnits(new Supplier[]{Warrior::new}, new int[]{2});

        //Action section
        boolean battleWarWarResult = Battle.fight(chuck, bruce);
        boolean battleWarKniResult = Battle.fight(dave, carl);
        boolean chuckIsAlive = chuck.isAlive();
        boolean bruceIsAlive = bruce.isAlive();
        boolean carlIsAlive1 = carl.isAlive();
        boolean daveIsAlive = dave.isAlive();
        boolean battleHurtKniWarResult = Battle.fight(carl, mark);
        boolean carlIsAlive2 = carl.isAlive();
        boolean battleDefKniResult = Battle.fight(bob, mike);
        boolean battleDefWarResult = Battle.fight(lancelot, rog);

        boolean armiesBattle1Result = Battle.fight(myArmy, enemyArmy);
        boolean armiesBattle2Result = Battle.fight(myArmy2, enemyArmy2);

        //Assertion section
        assertTrue(battleWarWarResult);
        assertFalse(battleWarKniResult);
        assertTrue(chuckIsAlive);
        assertFalse(bruceIsAlive);
        assertTrue(carlIsAlive1);
        assertFalse(daveIsAlive);
        assertFalse(battleHurtKniWarResult);
        assertFalse(carlIsAlive2);
        assertFalse(battleDefKniResult);
        assertTrue(battleDefWarResult);
        assertFalse(armiesBattle1Result);
        assertTrue(armiesBattle2Result);
    }

    @ParameterizedTest(name = "{index}. {0}")
    @DisplayName("All hand-to-hand 1 on 1 battle combinations")
    @MethodSource("unitPairProvider")
    void normal1to1Battles(String testName,
               Unit attacker, int ecpectedAttackerHealthLeft,
               Unit enemy, int expectedEnemyHealthLeft,
               boolean expectedFightResult) {

        //Action section
        boolean fightResult = Battle.fight(attacker, enemy);
        int attackerHealthLeft = attacker.getHealth();
        int enemyHealthLeft = enemy.getHealth();

        //Assertion section
        assertEquals(expectedFightResult, fightResult);
        assertEquals(ecpectedAttackerHealthLeft, attackerHealthLeft);
        assertEquals(expectedEnemyHealthLeft, enemyHealthLeft);
    }

    static Stream<Arguments> unitPairProvider(){

        return Stream.of(
                arguments("Warrior vs Warrior", new Warrior(), 5, new Warrior(), 0, true),

                arguments("Warrior vs Knight", new Warrior(), -6, new Knight(), 10, false),

                arguments("Warrior vs Defender", new Warrior(), -1, new Defender(), 9, false),

                arguments("Warrior vs Vampire", new Warrior(), 2, new Vampire(), -1, true),

                arguments("Warrior vs Lancer", new Warrior(), -4, new Lancer(), 5, false),

                arguments("Warrior vs Healer", new Warrior(), 50, new Healer(), 0, true),

                arguments("Knight vs Warrior", new Knight(), 15, new Warrior(), -6, true),

                arguments("Knight vs Knight", new Knight(), 1, new Knight(), -6, true),

                arguments("Knight vs Defender", new Knight(), 17, new Defender(), 0, true),

                arguments("Knight vs Vampire", new Knight(), 22, new Vampire(), -2, true),

                arguments("Knight vs Lancer", new Knight(), 8, new Lancer(), -6, true),

                arguments("Knight vs Healer", new Knight(), 50, new Healer(), -3, true),

                arguments("Defender vs Warrior", new Defender(), 12, new Warrior(), -1, true),

                arguments("Defender vs Knight", new Defender(), 0, new Knight(), 14, false),

                arguments("Defender vs Defender", new Defender(), 1, new Defender(), 0, true),

                arguments("Defender vs Vampire", new Defender(), 22, new Vampire(), -1, true),

                arguments("Defender vs Lancer", new Defender(), 0, new Lancer(), 5, false),

                arguments("Defender vs Healer", new Defender(), 60, new Healer(), 0, true),

                arguments("Vampire vs Warrior", new Vampire(), 4, new Warrior(), -2, true),

                arguments("Vampire vs Knight", new Vampire(), -2, new Knight(), 18, false),

                arguments("Vampire vs Defender", new Vampire(), -1, new Defender(), 20, false),

                arguments("Vampire vs Vampire", new Vampire(), 4, new Vampire(), 0, true),

                arguments("Vampire vs Lancer", new Vampire(), -2, new Lancer(), 10, false),

                arguments("Vampire vs Healer", new Vampire(), 40, new Healer(), 0, true)
        );
    }
}