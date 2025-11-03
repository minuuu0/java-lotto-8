package lotto;

import java.util.List;

class OutputView {

    void printPurchaseSuccess(int ticketCount) {
        System.out.println(ticketCount + "개를 구매했습니다.");
    }

    void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    void printWinningStatistics(GameResult gameResult) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        System.out.printf("3개 일치 (5,000원) - %d개%n", gameResult.getCountByRank(Rank.FIFTH));
        System.out.printf("4개 일치 (50,000원) - %d개%n", gameResult.getCountByRank(Rank.FOURTH));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", gameResult.getCountByRank(Rank.THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", gameResult.getCountByRank(Rank.SECOND));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", gameResult.getCountByRank(Rank.FIRST));
        System.out.printf("총 수익률은 %.1f%%입니다.%n", gameResult.getProfitRate());
    }

    void printError(String message) {
        System.out.println(message);
    }
}