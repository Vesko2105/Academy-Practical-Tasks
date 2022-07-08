package org.softserve.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import org.softserve.game.units.*;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ArmyTest {

    @ParameterizedTest(name = "{index}. {0}")
    @DisplayName("Basic army interactions")
    @MethodSource("armiesProvider")
    void tests(
            String testName,
            Army attackers,
            int[] attackersCounts,
            Supplier<Unit>[] attackerBarracks,
            Army defenders,
            int[] defendersCounts,
            Supplier<Unit>[] DefendersBarracks,
            boolean expectedBattleResult
    ){
        //Arrange section
        attackers.addUnits(attackerBarracks, attackersCounts);
        defenders.addUnits(DefendersBarracks, defendersCounts);

        //Action section
        boolean battleResult = Battle.fight(attackers, defenders);

        //Assert section
        assertEquals(expectedBattleResult, battleResult);

    }

    static Stream<Arguments> armiesProvider(){
        return Stream.of(
                arguments("Attackers: 1W|2K; Defenders: 2W => Win",
                          new Army(), new int[]{1, 2}, new Supplier[]{Warrior::new, Knight::new},
                          new Army(), new int[]{3}, new Supplier[]{Warrior::new}, 
                          true),
                arguments("Attackers: 2W; Defenders: 3W => Loss",
                          new Army(), new int[]{2}, new Supplier[]{Warrior::new},
                          new Army(), new int[]{3}, new Supplier[]{Warrior::new}, 
                          false),
                arguments("Attackers: 5W; Defenders: 7W => Loss", new Army(),
                          new int[]{5}, new Supplier[]{Warrior::new},
                          new Army(), new int[]{7}, new Supplier[]{Warrior::new}, 
                          false),
                arguments("Attackers: 20W; Defenders: 21W => Win",
                          new Army(), new int[]{20}, new Supplier[]{Warrior::new},
                          new Army(), new int[]{21}, new Supplier[]{Warrior::new}, 
                          true),
                arguments("Attackers: 10W; Defenders: 11W => Win",
                          new Army(), new int[]{10}, new Supplier[]{Warrior::new},
                          new Army(), new int[]{11}, new Supplier[]{Warrior::new}, 
                          true),
                arguments("Attackers: 11W; Defenders: 7W => Win",
                          new Army(), new int[]{11}, new Supplier[]{Warrior::new},
                          new Army(), new int[]{7}, new Supplier[]{Warrior::new}, 
                          true),
                arguments("Attackers: 5W|9D; Defenders: 4W => Win",
                        new Army(), new int[]{5, 9}, new Supplier[]{Warrior::new, Defender::new},
                        new Army(), new int[]{4}, new Supplier[]{Warrior::new},
                        true),
                arguments("Attackers: 5D|20W; Defenders: 21W|4D => Win",
                        new Army(), new int[]{5, 20}, new Supplier[]{Defender::new, Warrior::new},
                        new Army(), new int[]{21, 4}, new Supplier[]{Warrior::new, Defender::new},
                        true),
                arguments("Attackers: 10W|5D; Defenders: 5W|10D => Loss",
                        new Army(), new int[]{10, 5}, new Supplier[]{Warrior::new, Defender::new},
                        new Army(), new int[]{5, 10}, new Supplier[]{Warrior::new, Defender::new},
                        false),
                arguments("Attackers: 10W|5D; Defenders: 2D|5W => Loss",
                        new Army(), new int[]{1, 1}, new Supplier[]{Warrior::new, Defender::new},
                        new Army(), new int[]{2, 5}, new Supplier[]{Defender::new, Warrior::new},
                        false)
        );
    }

    @Test
    @DisplayName("Smoke test for the straightFight() method.")
    void straightFightSmokeTest() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var mike = new Knight();
        var rog = new Warrior();
        var lancelot = new Defender();
        var eric = new Vampire();
        var adam = new Vampire();
        var richard = new Defender();
        var ogre = new Warrior();
        var freelancer = new Lancer();
        var vampire = new Vampire();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertFalse(dave.isAlive());
        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());
        assertFalse(Battle.fight(bob, mike));
        assertTrue(Battle.fight(lancelot, rog));
        assertFalse(Battle.fight(eric, richard));
        assertTrue(Battle.fight(ogre, adam));
        assertTrue(Battle.fight(freelancer, vampire));
        assertTrue(freelancer.isAlive());
        assertEquals(14, freelancer.getHealth());

        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 2);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Vampire::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 1);
        myArmy.addUnits(Warrior::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);
        enemyArmy.addUnits(Lancer::new, 4);
        enemyArmy.addUnits(Healer::new, 1);
        enemyArmy.addUnits(Defender::new, 2);
        enemyArmy.addUnits(Vampire::new, 3);
        enemyArmy.addUnits(Healer::new, 1);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Lancer::new, 1);
        army3.addUnits(Healer::new, 1);
        army3.addUnits(Defender::new, 2);

        var army4 = new Army();
        army4.addUnits(Vampire::new, 3);
        army4.addUnits(Warrior::new, 1);
        army4.addUnits(Healer::new, 1);
        army4.addUnits(Lancer::new, 2);

        var army5 = new Army();
        army5.addUnits(Warrior::new, 10);

        var army6 = new Army();
        army6.addUnits(Warrior::new, 6);
        army6.addUnits(Lancer::new, 5);


        assertFalse(Battle.fight(myArmy, enemyArmy));
        assertTrue(Battle.fight(army3, army4));
        assertFalse(Battle.straightFight(army5, army6));
    }

    @Nested
    @DisplayName("Lining up behavior")
    class LineUpBehavior{
        @Test
        @DisplayName("Default lineup")
        void defaultLineup(){
            Army army = new Army();
            army.addUnits(new Supplier[]{Warrior::new, Knight::new, Defender::new}, new int[]{1, 1, 1});
            var iter1 = army.iterator();
            var iter2 = army.iterator();
            iter2.next();

            while(iter1.hasNext() && iter2.hasNext()){
                assertSame(iter1.next().getNext(), iter2.next());
            }
        }

        @Test
        @DisplayName("Lining up in a column")
        void lineUpInCol(){
            Army army = new Army();
            army.addUnits(new Supplier[]{Warrior::new, Knight::new, Defender::new}, new int[]{1, 1, 1});
            army.lineUpInColumn();
            var iter1 = army.iterator();
            var iter2 = army.iterator();
            iter2.next();

            while(iter1.hasNext() && iter2.hasNext()){
                assertSame(iter1.next().getNext(), iter2.next());
            }
        }

        @Test
        @DisplayName("Lining up in a row")
        void lineUpInRow(){
            Army army = new Army();
            army.addUnits(new Supplier[]{Warrior::new, Knight::new, Defender::new}, new int[]{1, 1, 1});
            army.lineUpInRow();
            var iter1 = army.iterator();
            var iter2 = army.iterator();
            iter2.next();

            while(iter1.hasNext() && iter2.hasNext()){
                assertNull(iter1.next().getNext());
            }
        }
    }


}