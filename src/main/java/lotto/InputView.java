package lotto;

import camp.nextstep.edu.missionutils.Console;

class InputView {

    private static final String PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요.";
    private static final String ERROR_POSITIVE_INTEGER = "[ERROR] 로또 구입 금액은 양의 정수이어야 합니다.";

    int readPurchaseAmount() {
        printPrompt();
        String input = readInput();
        return parseToInt(input);
    }

    private void printPrompt() {
        System.out.println(PURCHASE_AMOUNT_PROMPT);
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
}