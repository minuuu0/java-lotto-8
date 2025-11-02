package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.ArrayList;
import java.util.List;

class InputView {

    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String WINNING_NUMBERS_PROMPT = "\n당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_PROMPT = "\n보너스 번호를 입력해 주세요.";
    private static final String ERROR_POSITIVE_INTEGER = "[ERROR] 로또 구입 금액은 양의 정수이어야 합니다.";
    private static final String ERROR_INVALID_WINNING_NUMBER = "[ERROR] 당첨 번호는 정수로만 입력해야 합니다.";
    private static final String ERROR_COMMA_SEPARATOR = "[ERROR] 당첨 번호는 쉼표(,)로 구분해야 합니다.";
    private static final String ERROR_INVALID_BONUS_NUMBER = "[ERROR] 보너스 번호는 정수여야 합니다.";

    int readPurchaseAmount() {
        printPurchasePrompt();
        String input = readInput();
        return parseToInt(input);
    }

    List<Integer> readWinningNumbers() {
        promptWinningNumbersInput();
        String input = readInput();
        return parseWinningNumbers(input);
    }

    int readBonusNumber() {
        promptBonusNumberInput();
        String input = readInput();
        return parseBonusNumber(input);
    }

    private void printPurchasePrompt() {
        System.out.println(PURCHASE_AMOUNT_PROMPT);
    }

    private void promptWinningNumbersInput() {
        System.out.println(WINNING_NUMBERS_PROMPT);
    }

    private void promptBonusNumberInput() {
        System.out.println(BONUS_NUMBER_PROMPT);
    }

    private String readInput() {
        return Console.readLine();
    }

    private int parseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_POSITIVE_INTEGER);
            throw new IllegalArgumentException(ERROR_POSITIVE_INTEGER);
        }
    }

    private List<Integer> parseWinningNumbers(String input) {
        validateCommaSeparator(input);
        String[] tokens = input.split("\\s*,\\s*");
        List<Integer> numbers = new ArrayList<>();

        for (String token : tokens) {
            numbers.add(parseWinningNumber(token));
        }

        return numbers;
    }

    private void validateCommaSeparator(String input) {
        if (!input.contains(",")) {
            System.out.println(ERROR_COMMA_SEPARATOR);
            throw new IllegalArgumentException(ERROR_COMMA_SEPARATOR);
        }
    }

    private int parseWinningNumber(String token) {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_WINNING_NUMBER);
            throw new IllegalArgumentException(ERROR_INVALID_WINNING_NUMBER);
        }
    }

    private int parseBonusNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_BONUS_NUMBER);
            throw new IllegalArgumentException(ERROR_INVALID_BONUS_NUMBER);
        }
    }
}