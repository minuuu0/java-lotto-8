package lotto;

import java.util.List;

class Lotto {
    private static final int PRICE = 1000;
    private final List<Integer> numbers;

    Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    static void validatePurchaseAmount(int amount) {
        if (amount % PRICE != 0) {
            System.out.println("[ERROR] 로또 구입 금액은 1000원으로 나누어 떨어져야합니다.");
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1000원으로 나누어 떨어져야합니다.");
        }

        if (amount <= 0) {
            System.out.println("[ERROR] 로또 구입 금액은 양의 정수이어야 합니다.");
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 양의 정수이어야 합니다.");
        }
    }

    static int calculateTicketCount(int amount) {
        return amount / PRICE;
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
