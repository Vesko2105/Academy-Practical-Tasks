package org.softserve.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class Rookie extends Warrior {
    @Override
    public int getAttack() {
        return 1;
    }
}

class BattleTest {
    @Test
    @DisplayName("Smoke test")
    void warriorKnightSmokeTest() {
        //Arrange section
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();

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

    @Test
    @DisplayName("Defence functionality")
    void defenceFunc(){
        //Arrange section
        var unit1 = new Defender();
        var unit2 = new Rookie();

        //A
    }

//"8. Fight": [
//        prepare_test(middle_code='''unit_1 = Defender()
//        unit_2 = Rookie()
//        fight(unit_1, unit_2)''',
//        test="unit_1.health",
//        answer=60)
//        ],
//        "9. Fight": [
//        prepare_test(middle_code='''unit_1 = Defender()
//        unit_2 = Rookie()
//        unit_3 = Warrior()
//        fight(unit_1, unit_2)''',
//        test="fight(unit_1, unit_3)",
//        answer=True)
//        ]

    @ParameterizedTest
    @MethodSource("warriorPairProvider")
    void tests(Warrior challenger, Warrior responder, boolean fightExpectedResult, boolean challengerAliveEcpectance, boolean responderAliveEcpectance) {

        //Action section
        boolean fightResult = Battle.fight(challenger, responder);
        boolean challengerIsAlive = challenger.isAlive();
        boolean responderIsAlive = responder.isAlive();

        //Assertion section
        assertEquals(fightExpectedResult, fightResult);
        assertEquals(challengerAliveEcpectance, challengerIsAlive);
        assertEquals(responderAliveEcpectance, responderIsAlive);
    }

    static Stream<Arguments> warriorPairProvider(){

        return Stream.of(
                //Battle between a Warrior(being first) and another Warrior
                arguments(new Warrior(), new Warrior(), true, true, false),

                //Battle between a Warrior(being first) and a Knight
                arguments(new Warrior(), new Knight(), false, false, true),

                //Battle between a Knight(being first) and a Warrior
                arguments(new Knight(), new Warrior(), true, true, false),

                //Battle between a Knight(being first) and another Knight
                arguments(new Knight(), new Knight(), true, true, false)
        );
    }
}