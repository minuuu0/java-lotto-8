package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

class Application {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        int purchaseAmount = readValidPurchaseAmount();
        int ticketCount = Lotto.calculateTicketCount(purchaseAmount);

        List<Lotto> lottos = generateLottos(ticketCount);

        outputView.printPurchaseSuccess(ticketCount);
        outputView.printLottoNumbers(lottos);

        Lotto winningNumbers = readValidWinningNumbers();
        int bonusNumber = readValidBonusNumber(winningNumbers);

        Map<Rank, Integer> result = calculateWinningResult(lottos, winningNumbers, bonusNumber);
        double profitRate = calculateProfitRate(result, purchaseAmount);

        outputView.printWinningStatistics(result, profitRate);
    }

    private static int readValidPurchaseAmount() {
        while (true) {
            try {
                int purchaseAmount = inputView.readPurchaseAmount();
                Lotto.validatePurchaseAmount(purchaseAmount);
                return purchaseAmount;
            } catch (IllegalArgumentException e) {
                // 에러 메시지는 이미 출력되었으므로 재시도
            }
        }
    }

    private static Lotto readValidWinningNumbers() {
        while (true) {
            try {
                List<Integer> winningNumberList = inputView.readWinningNumbers();
                return new Lotto(winningNumberList);
            } catch (IllegalArgumentException e) {
                // 에러 메시지는 이미 출력되었으므로 재시도
            }
        }
    }

    private static int readValidBonusNumber(Lotto winningNumbers) {
        while (true) {
            try {
                int bonusNumber = inputView.readBonusNumber();
                Lotto.validateLottoNumberRange(bonusNumber);
                winningNumbers.validateBonusNumberNotDuplicate(bonusNumber);
                return bonusNumber;
            } catch (IllegalArgumentException e) {
                // 에러 메시지는 이미 출력되었으므로 재시도
            }
        }
    }

    private static List<Lotto> generateLottos(int ticketCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            List<Integer> lottoNumbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            Collections.sort(lottoNumbers);
            lottos.add(new Lotto(lottoNumbers));
        }
        return lottos;
    }

    private static Map<Rank, Integer> calculateWinningResult(List<Lotto> lottos, Lotto winningNumbers, int bonusNumber) {
        Map<Rank, Integer> result = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) {
            result.put(rank, 0);
        }

        for (Lotto lotto : lottos) {
            int matchCount = lotto.countMatches(winningNumbers);
            boolean matchBonus = lotto.containsNumber(bonusNumber);
            Rank rank = Rank.valueOf(matchCount, matchBonus);
            result.put(rank, result.get(rank) + 1);
        }

        return result;
    }

    private static double calculateProfitRate(Map<Rank, Integer> result, int purchaseAmount) {
        long totalPrize = 0;
        for (Rank rank : Rank.values()) {
            totalPrize += (long) rank.getPrize() * result.get(rank);
        }
        return (double) totalPrize / purchaseAmount * 100;
    }
}
