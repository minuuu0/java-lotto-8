package lotto;

import java.util.List;

class Application {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();
    private static final LottoGenerator lottoGenerator = new LottoGenerator();

    public static void main(String[] args) {
        Money money = readValidPurchaseAmount();
        int ticketCount = money.calculateTicketCount();

        Lottos lottos = lottoGenerator.generate(ticketCount);

        outputView.printPurchaseSuccess(ticketCount);
        outputView.printLottoNumbers(lottos.toList());

        Lotto winningNumbers = readValidWinningNumbers();
        int bonusNumber = readValidBonusNumber(winningNumbers);

        GameResult gameResult = lottos.calculateWinningResult(winningNumbers, bonusNumber, money);

        outputView.printWinningStatistics(gameResult);
    }

    private static Money readValidPurchaseAmount() {
        while (true) {
            try {
                int purchaseAmount = inputView.readPurchaseAmount();
                return new Money(purchaseAmount);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }

    private static Lotto readValidWinningNumbers() {
        while (true) {
            try {
                List<Integer> winningNumberList = inputView.readWinningNumbers();
                return new Lotto(winningNumberList);
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
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
                outputView.printError(e.getMessage());
            }
        }
    }
}
