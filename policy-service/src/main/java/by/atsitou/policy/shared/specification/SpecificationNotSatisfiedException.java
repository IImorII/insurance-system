package by.atsitou.policy.shared.specification;


import by.atsitou.policy.shared.exceptions.BusinessException;

public class SpecificationNotSatisfiedException extends BusinessException {
    public SpecificationNotSatisfiedException(String errorCode,Object[] params){
        super(errorCode, params);
    }
}
