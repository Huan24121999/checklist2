package viettel.huannt14.checklist.service;

import viettel.huannt14.checklist.entity.ResultItem;

import java.util.List;

public interface ExecuteService {
    List<ResultItem> execute(List<Integer> itemIds);
}
