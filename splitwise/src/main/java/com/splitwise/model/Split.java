package com.splitwise.model;

import java.util.List;

public abstract class Split {
    protected int paidUserId;
    protected List<Integer> beneficiaryIds;
    protected double amount;
    protected SplitType splitType;
    public Split(int paidUserId,List<Integer> beneficiaryIds, double amount,SplitType splitType){
        this.paidUserId = paidUserId;
        this.beneficiaryIds = beneficiaryIds;
        this.amount = amount;
        this.splitType = splitType;
    }

    public int getPaidUserId() {
        return paidUserId;
    }

    public List<Integer> getBeneficiaryIds() {
        return beneficiaryIds;
    }

    public double getAmount() {
        return amount;
    }

    public SplitType getSplitType() {
        return splitType;
    }
}
