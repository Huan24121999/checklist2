package viettel.huannt14.checklist.service;

import viettel.huannt14.checklist.entity.ResultItem;

import java.util.List;

/**
 * service is provided to execute checklist items base on id's items
 *
 * @author huannt14
 */
public interface ExecuteService {
    List<ResultItem> execute(List<Integer> itemIds);
}
