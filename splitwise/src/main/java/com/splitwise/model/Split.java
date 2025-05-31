package com.splitwise.model;

import java.util.List;

public class Split {
    private int paidUserId;
    private double amount;
    private List<Integer> beneficiaryIds;
    private SplitType splitType;

    public Split(int paidUserId, double amount, List<Integer> beneficiaryIds, SplitType splitType) {
        this.paidUserId = paidUserId;
        this.amount = amount;
        this.beneficiaryIds = beneficiaryIds;
        this.splitType = splitType;
    }

    public int getPaidUserId() {
        return paidUserId;
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public List<Integer> getBeneficiaryIds() {
        return beneficiaryIds;
    }

    public double getAmount() {
        return amount;
    }
}
