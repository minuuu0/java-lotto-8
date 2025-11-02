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
}