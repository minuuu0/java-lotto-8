package lotto;

import java.util.List;
import java.util.Map;

class OutputView {

    void printPurchaseSuccess(int ticketCount) {
        System.out.println(ticketCount + "개를 구매했습니다.");
    }

    void printLottoNumbers(List<Lotto> lottos) {
        for (Lotto lotto : lottos) {
            System.out.println(lotto.toString());
        }
    }

    void printWinningStatistics(Map<Rank, Integer> result, double profitRate) {
        System.out.println("\n당첨 통계");
        System.out.println("---");
        System.out.printf("3개 일치 (5,000원) - %d개%n", result.get(Rank.FIFTH));
        System.out.printf("4개 일치 (50,000원) - %d개%n", result.get(Rank.FOURTH));
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", result.get(Rank.THIRD));
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", result.get(Rank.SECOND));
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", result.get(Rank.FIRST));
        System.out.printf("총 수익률은 %.1f%%입니다.%n", profitRate);
    }
}