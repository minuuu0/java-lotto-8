package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Application {

    private static final InputView inputView = new InputView();
    private static final OutputView outputView = new OutputView();

    public static void main(String[] args) {
        int purchaseAmount = inputView.readPurchaseAmount();
        Lotto.validatePurchaseAmount(purchaseAmount);
        int ticketCount = Lotto.calculateTicketCount(purchaseAmount);

        List<Lotto> lottos = generateLottos(ticketCount);

        outputView.printPurchaseSuccess(ticketCount);
        outputView.printLottoNumbers(lottos);
    }

    private static List<Lotto> generateLottos(int ticketCount) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(lottoNumbers);
            lottos.add(new Lotto(lottoNumbers));
        }
        return lottos;
    }
}
