package kr.co.ordermanagement.domain.order;

import kr.co.ordermanagement.domain.exception.CanNotCancellableStateException;

public enum State {
    CREATED {
        @Override
        void checkCancellable() {
            // override를 통해 CREATED Enum에서 해당 메서드를 실행하는 경우
            // 예외를 던지지 않도록 변경해준다.
        }
    },
    SHIPPING,
    COMPLETED,
    CANCELED;

    void checkCancellable() {
        throw new CanNotCancellableStateException("이미 취소되었거나 취소할 수 없는 주문상태입니다.");
    }
}
