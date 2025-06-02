package com.splitwise.model;

import java.util.List;

public class EqualSplit extends Split {



    public EqualSplit(int paidUserId, double amount, List<Integer> beneficiaryIds) {
        super(paidUserId,beneficiaryIds,amount,SplitType.EQUAL);

    }




}
