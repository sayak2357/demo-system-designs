package com.splitwise.model;

import java.util.List;

public class PercentageSplit extends Split {

    private double percentage;

    public PercentageSplit(int paidUserId, double amount, List<Integer> beneficiaryIds,double percentage) {
        super(paidUserId,beneficiaryIds,amount,SplitType.PERCENTGE);
        this.percentage=percentage;
    }

    public double getPercentage() {
        return percentage;
    }
}
