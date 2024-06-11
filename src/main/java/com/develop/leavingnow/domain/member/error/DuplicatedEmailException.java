package com.develop.leavingnow.domain.member.error;

import com.develop.leavingnow.global.error.exception.BusinessException;
import com.develop.leavingnow.global.error.exception.ErrorCode;

public class DuplicatedEmailException extends BusinessException {

    public DuplicatedEmailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
