package lotto;

import java.util.Map;

class GameResult {
    private final Map<Rank, Integer> result;
    private final double profitRate;

    GameResult(Map<Rank, Integer> result, Money purchaseAmount) {
        this.result = result;
        this.profitRate = calculateProfitRate(purchaseAmount);
    }

    private double calculateProfitRate(Money purchaseAmount) {
        long totalPrize = calculateTotalPrize();
        return (double) totalPrize / purchaseAmount.getAmount() * 100;
    }

    private long calculateTotalPrize() {
        long totalPrize = 0;
        for (Rank rank : Rank.values()) {
            totalPrize += (long) rank.getPrize() * result.get(rank);
        }
        return totalPrize;
    }

    int getCountByRank(Rank rank) {
        return result.get(rank);
    }

    double getProfitRate() {
        return profitRate;
    }


}
