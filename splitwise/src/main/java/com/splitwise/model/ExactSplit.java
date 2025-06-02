package com.splitwise.model;

import java.util.List;

public class ExactSplit extends Split {



    public ExactSplit(int paidUserId, double amount, List<Integer> beneficiaryIds) {
        super(paidUserId,beneficiaryIds,amount,SplitType.EXACT);
    }

}
