package viettel.huannt14.checklist.service;

/**
 * compare Executed Result with the previous defined value
 * @param <T> Object to compare
 *
 * @author huannt14
 */
public interface CompareData<T> {
    Boolean compare(T result,T valuePass,String operator);
}
