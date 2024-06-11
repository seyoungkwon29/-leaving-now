package com.develop.leavingnow.domain.member.error;

import com.develop.leavingnow.global.error.exception.BusinessException;
import com.develop.leavingnow.global.error.exception.ErrorCode;

public class DuplicatedNicknameException extends BusinessException {

    public DuplicatedNicknameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
