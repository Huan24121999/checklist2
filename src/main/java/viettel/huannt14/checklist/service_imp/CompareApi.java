package viettel.huannt14.checklist.service_imp;

import viettel.huannt14.checklist.service.CompareData;

import javax.servlet.http.HttpServletResponse;

public class CompareApi implements CompareData<Object> {
    @Override
    public Boolean compare(Object result, Object valuePass) {
        return false;
    }
}
