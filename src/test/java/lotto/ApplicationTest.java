package lotto;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String ERROR_MESSAGE = "[ERROR]";

    @Test
    void 기능_테스트() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("8000", "1,2,3,4,5,6", "7");
                    assertThat(output()).contains(
                            "8개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]",
                            "[7, 11, 16, 35, 36, 44]",
                            "[1, 8, 11, 31, 41, 42]",
                            "[13, 14, 16, 38, 42, 45]",
                            "[7, 11, 30, 40, 42, 43]",
                            "[2, 13, 22, 32, 38, 45]",
                            "[1, 3, 5, 14, 22, 45]",
                            "3개 일치 (5,000원) - 1개",
                            "4개 일치 (50,000원) - 0개",
                            "5개 일치 (1,500,000원) - 0개",
                            "5개 일치, 보너스 볼 일치 (30,000,000원) - 0개",
                            "6개 일치 (2,000,000,000원) - 0개",
                            "총 수익률은 62.5%입니다."
                    );
                },
                List.of(8, 21, 23, 41, 42, 43),
                List.of(3, 5, 11, 16, 32, 38),
                List.of(7, 11, 16, 35, 36, 44),
                List.of(1, 8, 11, 31, 41, 42),
                List.of(13, 14, 16, 38, 42, 45),
                List.of(7, 11, 30, 40, 42, 43),
                List.of(2, 13, 22, 32, 38, 45),
                List.of(1, 3, 5, 14, 22, 45)
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            runException("1000j");
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 입력한_금액이_정수_외의_문자를_입력한_경우_IllegalArgumentException이_발생한다() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("ab"))
                    .isExactlyInstanceOf(IllegalArgumentException.class);
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 입력한_금액이_1000원으로_나누어_떨어지지_않은_경우_IllegalArgumentException을_발생한다() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("1300"))
                    .isExactlyInstanceOf(IllegalArgumentException.class);
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 입력한_금액이_0원_이하를_입력한_경우_IllegalArgumentException을_발생한다() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("0"))
                    .isExactlyInstanceOf(IllegalArgumentException.class);
            assertThat(output()).contains(ERROR_MESSAGE);
        });
    }

    @Test
    void 유효한_입력일_경우_구입한_로또_수량_및_번호를_오름차순_출력한다() {
        assertRandomUniqueNumbersInRangeTest(
                () -> {
                    run("2000");  // 2000원 입력
                    assertThat(output()).contains(
                            "2개를 구매했습니다.",
                            "[8, 21, 23, 41, 42, 43]",
                            "[3, 5, 11, 16, 32, 38]"
                    );
                },
                Arrays.asList(8, 21, 23, 41, 42, 43),
                Arrays.asList(3, 5, 11, 16, 32, 38)
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
