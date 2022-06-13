package by.atsitou.payment.service.api.v1.exceptions;

public class BankStatementsFileReadingError extends RuntimeException {
    public BankStatementsFileReadingError(Throwable cause) {
        super(cause);
    }
}
