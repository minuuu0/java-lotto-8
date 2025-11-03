package lotto;

class Money {
    private static final int LOTTO_PRICE = 1000;

    private final int amount;

    Money(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validateDivisible(amount);
        validatePositive(amount);
    }

    private void validateDivisible(int amount) {
        if (amount % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 1000원으로 나누어 떨어져야합니다.");
        }
    }

    private void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 로또 구입 금액은 양의 정수이어야 합니다.");
        }
    }

    int calculateTicketCount() {
        return amount / LOTTO_PRICE;
    }

    int getAmount() {
        return amount;
    }
}
