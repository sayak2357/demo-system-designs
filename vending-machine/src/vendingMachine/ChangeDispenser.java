package vendingMachine;

import java.util.*;

public class ChangeDispenser {
    private Map<Double,Integer> denominationCountMap;

    public ChangeDispenser() {
        denominationCountMap = new TreeMap<>(Comparator.reverseOrder());
        denominationCountMap.put(10.0,10);
        denominationCountMap.put(5.0,10);
        denominationCountMap.put(2.0,10);
        denominationCountMap.put(1.0,15);
        denominationCountMap.put(0.5,15);
    }

    public List<Double> getChange(double amount) throws Exception{
        List<Double> res = new ArrayList<>();

        for(double key:denominationCountMap.keySet()) {
            while (amount>=key && denominationCountMap.get(key)>0) {
                amount -= key;
                res.add(key);
                denominationCountMap.put(key,denominationCountMap.get(key)-1);
            }
        }
        if(amount>0.001){
            throw new Exception("Not enough change available");
        }
        return res;
    }

}
