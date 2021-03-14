package viettel.huannt14.checklist.service_imp;

import org.springframework.stereotype.Service;

@Service
public class Abc implements viettel.huannt14.checklist.service.Abc {

    private Integer point=0;

    @Override
    public int change() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        point++;
        System.out.println(point);
        return point;
    }
}
