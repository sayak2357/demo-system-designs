package vendingMachine;

import java.util.*;

import static Constants.Constants.VALID_DENOMINATIONS;

public class ChangeDispenser {
    private Map<Double,Integer> denominationCountMap;

    public ChangeDispenser() {
        denominationCountMap = new TreeMap<>(Comparator.reverseOrder());
        denominationCountMap.put(1000.0,15);
        denominationCountMap.put(500.0,5);
        denominationCountMap.put(100.0,12);
        denominationCountMap.put(10.0,10);
        denominationCountMap.put(5.0,10);
        denominationCountMap.put(2.0,10);
        denominationCountMap.put(1.0,15);
        denominationCountMap.put(0.5,15);
    }

    public synchronized List<Double> getChange(double amount) throws Exception{
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

    public synchronized void addCoin(double amount){
        if (!VALID_DENOMINATIONS.contains(amount)) {
            throw new IllegalArgumentException("Invalid denomination inserted: " + amount);
        }
        denominationCountMap.put(amount,denominationCountMap.getOrDefault(amount,0)+1);
    }

}
