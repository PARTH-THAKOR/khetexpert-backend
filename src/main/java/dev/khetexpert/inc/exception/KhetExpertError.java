// KhetExpertError Exception Class

package dev.khetexpert.inc.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KhetExpertError extends RuntimeException {

    private String exception;

    public KhetExpertError(String exception) {
        super(exception);
        this.exception = exception;
    }

}
