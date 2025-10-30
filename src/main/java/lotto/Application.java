package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.Collections;
import java.util.List;

public class Application {

    public static final int LOTTO_AMOUNT = 1000;

    public static void main(String[] args) {

        int purchaseAmount;

        try {
            purchaseAmount = Integer.parseInt(Console.readLine());
            if (purchaseAmount % LOTTO_AMOUNT != 0) {
                System.out.println("[ERROR] 로또 구입 금액은 1000원으로 나누어 떨어져야합니다.");
                throw new IllegalArgumentException("[ERROR] 로또 번호는 1000원으로 나누어 떨어져야합니다.");
            }

            if (purchaseAmount <= 0) {
                System.out.println("[ERROR] 로또 구입 금액은 양의 정수이어야 합니다.");
                throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 양의 정수이어야 합니다.");
            }
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 로또 구입 금액은 양의 정수이어야 합니다.");
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 양의 정수이어야 합니다.");
        }

        int ticketCount = purchaseAmount / LOTTO_AMOUNT;

        Lotto[] lottos = new Lotto[ticketCount];

        for (int i = 0; i < ticketCount; i++) {
            List<Integer> lottoNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(lottoNumbers);
            lottos[i] = new Lotto(lottoNumbers);
        }
        System.out.println(ticketCount + "개를 구매했습니다.");

        for (int i = 0; i < ticketCount; i++) {
            Lotto lotto = lottos[i];
            System.out.println(lotto.toString());
        }

    }
}
