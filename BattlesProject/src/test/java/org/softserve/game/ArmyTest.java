package org.softserve.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
            Supplier<Warrior>[] attackerBarracks,
            Army defenders,
            int[] defendersCounts,
            Supplier<Warrior>[] DefendersBarracks,
            boolean expectedBattleResult
    ){
        //Arrange section
        attackers.addUnits(attackerBarracks, attackersCounts);
        defenders.addUnits(DefendersBarracks, defendersCounts);

        //Action section
        boolean battleResult = Battle.fight(attackers, defenders);

        //Assert section
        assertEquals(battleResult, expectedBattleResult);

    }

    static Stream<Arguments> armiesProvider(){
        return Stream.of(
                arguments("Attackers - 1W|2K; Defenders - 2W => Win",
                          new Army(), new int[]{1, 2}, new Supplier[]{Warrior::new, Knight::new},
                          new Army(), new int[]{3}, new Supplier[]{Warrior::new}, 
                          true),
                arguments("Attackers - 2W; Defenders - 3W => Loss",
                          new Army(), new int[]{2}, new Supplier[]{Warrior::new},
                          new Army(), new int[]{3}, new Supplier[]{Warrior::new}, 
                          false),
                arguments("Attackers - 5W; Defenders - 7W => Loss", new Army(),
                          new int[]{5}, new Supplier[]{Warrior::new},
                          new Army(), new int[]{7}, new Supplier[]{Warrior::new}, 
                          false),
                arguments("Attackers - 20W; Defenders - 21W => Win",
                          new Army(), new int[]{20}, new Supplier[]{Warrior::new},
                          new Army(), new int[]{21}, new Supplier[]{Warrior::new}, 
                          true),
                arguments("Attackers - 10W; Defenders - 11W => Win",
                          new Army(), new int[]{10}, new Supplier[]{Warrior::new},
                          new Army(), new int[]{11}, new Supplier[]{Warrior::new}, 
                          true),
                arguments("Attackers - 11W; Defenders - 7W => Win",
                          new Army(), new int[]{11}, new Supplier[]{Warrior::new},
                          new Army(), new int[]{7}, new Supplier[]{Warrior::new}, 
                          true),
                arguments("Attackers - 5W9D; Defenders - 4W => Win",
                        new Army(), new int[]{5, 9}, new Supplier[]{Warrior::new, Defender::new},
                        new Army(), new int[]{4}, new Supplier[]{Warrior::new},
                        true),
                arguments("Attackers - 5D20W; Defenders - 21W4D => Win",
                        new Army(), new int[]{5, 20}, new Supplier[]{Defender::new, Warrior::new},
                        new Army(), new int[]{21, 4}, new Supplier[]{Warrior::new, Defender::new},
                        true),
                arguments("Attackers - 10W5D; Defenders - 5W10D => Win",
                        new Army(), new int[]{10, 5}, new Supplier[]{Warrior::new, Defender::new},
                        new Army(), new int[]{5, 10}, new Supplier[]{Warrior::new, Defender::new},
                        true),
                arguments("Attackers - 10W5D; Defenders - 2D5W => Loss",
                        new Army(), new int[]{1, 1}, new Supplier[]{Warrior::new, Defender::new},
                        new Army(), new int[]{2, 5}, new Supplier[]{Defender::new, Warrior::new},
                        false)
        );
    }
}