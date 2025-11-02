package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Lotto {
    private static final int PRICE = 1000;
    private static final int LOTTO_NUMBER_COUNT = 6;
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private final List<Integer> numbers;

    Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateNoDuplicates(numbers);
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != LOTTO_NUMBER_COUNT) {
            System.out.println("[ERROR] 로또 번호는 6개여야 합니다.");
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateRange(List<Integer> numbers) {
        for (int number : numbers) {
            validateLottoNumberRange(number);
        }
    }

    static void validateLottoNumberRange(int number) {
        if (number < MIN_LOTTO_NUMBER || number > MAX_LOTTO_NUMBER) {
            System.out.println("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    private void validateNoDuplicates(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            System.out.println("[ERROR] 로또 번호는 중복될 수 없습니다.");
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
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

    void validateBonusNumberNotDuplicate(int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            System.out.println("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    int countMatches(Lotto winningNumbers) {
        return (int) numbers.stream()
                .filter(winningNumbers.numbers::contains)
                .count();
    }

    boolean containsNumber(int number) {
        return numbers.contains(number);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
