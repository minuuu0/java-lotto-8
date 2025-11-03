package lotto;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

class Lottos {
    private final List<Lotto> lottos;

    Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    GameResult calculateWinningResult(Lotto winningNumbers, int bonusNumber, Money purchaseAmount) {
        Map<Rank, Integer> result = initializeResult();

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatches(winningNumbers);
            boolean matchBonus = lotto.containsNumber(bonusNumber);
            Rank rank = Rank.valueOf(matchCount, matchBonus);
            result.put(rank, result.get(rank) + 1);
        }

        return new GameResult(result, purchaseAmount);
    }

    private Map<Rank, Integer> initializeResult() {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }
        return result;
    }

    List<Lotto> toList() {
        return new ArrayList<>(lottos);
    }
}
